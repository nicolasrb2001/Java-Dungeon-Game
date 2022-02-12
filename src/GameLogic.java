import java.io.File;
import java.io.IOException;

/**
 * Contains the main logic part of the game, as it processes.
 *
 */
public class GameLogic {

    private Map map;
    protected HumanPlayer player;
    private boolean GameRunning;
    protected BotPlayer bot;
    protected int turn;
    protected int directions;

    public GameLogic() {
        this.map = new Map(System.getProperty("user.dir") + File.separator + "Examplemaps");
        this.player = new HumanPlayer(this.map, this);
        this.bot = new BotPlayer(this.map, this.player, this);

        this.GameRunning = true;
        this.turn = 0;
        this.directions = -1;
        try {
            do{
                if (this.turn == 0){
                    this.player.getNextAction(this.player.getInputFromConsole(this.map.br), this);
                    this.turn = 1;
                }else {
                    this.bot.Action(this.map, this, this.directions);
                    this.directions = this.bot.direction;
                    this.turn = 0;
                }

                if (this.player.HumanPosition[0] == this.bot.BotPosition[0] && this.player.HumanPosition[1] == this.bot.BotPosition[1] ){
                    quitGame();

                }

            } while (this.GameRunning);
            this.map.br.close();


        } catch (IOException e) {
            e.printStackTrace();
        }




    }


    /**
     * Returns the gold required to win.
     *
     * @return : Gold required to win.
     */
    protected int hello() {

        return this.map.getGoldRequired();
    }

    /**
     * Returns the gold currently owned by the player.
     *
     * @return : Gold currently owned.
     */
    protected int gold() {

        return this.player.GoldAmount;
    }

    /**
     * Checks if movement is legal and updates player's location on the map.
     *
     * @param direction : The direction of the movement.
     * @return : Protocol if success or not.
     */
    protected String move(char direction, int turn) {

        int[] identity = Identity(turn);

        if (direction == 'N'){
            if (this.map.getMap()[identity[0]-1][identity[1]] == '#'){
                return "Fail";
            }else{
                identity[0] = identity[0] - 1;
                if (turn == 0){
                    this.player.HumanPosition[0] = identity[0];
                }else{
                    this.bot.BotPosition[0] = identity[0] ;
                }


                return "Success";
            }
        }
        if (direction == 'S'){
            if (this.map.getMap()[identity[0]+1][identity[1]] == '#'){
                return "Fail";
            }else{
                identity[0] = identity[0] + 1;
                if (turn == 0){
                    this.player.HumanPosition[0] = identity[0];
                }else{
                    this.bot.BotPosition[0] = identity[0];

                }
                return "Success";
            }
        }
        if (direction == 'E'){
            if (this.map.getMap()[identity[0]][identity[1]+1] == '#'){
                return "Fail";
            }else{

                identity[1] = identity[1] + 1;
                if (turn == 0){
                    this.player.HumanPosition[1] = identity[1];
                }else{
                    this.bot.BotPosition[1] = identity[1];

                }


                return "Success";
            }
        }
        if (direction == 'W'){
            if (this.map.getMap()[identity[0]][identity[1]-1] == '#'){
                return "Fail";
            }else{

                identity[1] = identity[1] - 1;
                if (turn == 0){
                    this.player.HumanPosition[1] = identity[1];
                }else{
                    this.bot.BotPosition[1] = identity[1];
                }
                return "Success";
            }
        }
        return null;
    }

    private int[] Identity(int turn) {
        int[] identity = new int[2];
        if (turn == 0){
            identity[0] = this.player.HumanPosition[0];
            identity[1] = this.player.HumanPosition[1];
        }else{
            identity[0] = this.bot.BotPosition[0];
            identity[1] = this.bot.BotPosition[1];
        }
        return identity;
    }

    /**
     * Prints a 5x5 grid showing the tiles around the player with the player in the centre.
     *
     *
     */
    protected void look(int turn) {
        int[] identity = Identity(turn);

        char[][] position = new char[5][5];
        for (int y = 0; y < 5; y++){
            for (int x = 0; x < 5; x++){
                if (y <= 2 && x <= 2){
                    if(identity[0]-(2-y)<0 || identity[1]-(2-x)<0 ){
                        position[y][x] = '#';
                    }else{
                        if (y == 2 && x == 2){
                            if (turn == 0){
                                position[y][x] = 'P';
                            }else{
                                position[y][x] = 'B';
                            }

                        }else{
                            if (turn == 0 && identity[0]-(2-y) == this.bot.BotPosition[0] && identity[1]-(2-x) == this.bot.BotPosition[1] ){
                                position[y][x] = 'B';
                            }else {
                                if (turn == 1 && identity[0]-(2-y) == this.player.HumanPosition[0] && identity[1]-(2-x) == this.player.HumanPosition[1]){
                                    position[y][x] = 'P';
                                    this.bot.Human[0] = y;
                                    this.bot.Human[1] = x;
                                    System.out.println("fnrinf");
                                }else {
                                    position[y][x] =this.map.getMap()[identity[0]-(2-y)][identity[1]-(2-x)];
                                }
                            }

                        }

                    }
                }else {
                    if (y <= 2) {
                        if(identity[0]-(2-y)<0 || identity[1]+(x-2)>=this.map.getMap()[0].length ){
                            position[y][x] = '#';
                        }else{
                            if (turn == 0 && identity[0]-(2-y) == this.bot.BotPosition[0] && identity[1]+(x-2) == this.bot.BotPosition[1] ){
                                position[y][x] = 'B';
                            }else {
                                if (turn == 1 && identity[0]-(2-y) == this.player.HumanPosition[0] && identity[1]+(x-2) == this.player.HumanPosition[1]){
                                    position[y][x] = 'P';
                                    this.bot.Human[0] = y;
                                    this.bot.Human[1] = x;
                                }else {
                                    position[y][x] =this.map.getMap()[identity[0]-(2-y)][identity[1]+(x-2)];
                                }
                            }
                        }
                    }else {
                        if (x <= 2){
                            if(identity[0] + (y-2)>=this.map.getMap().length || identity[1]-(2-x)<0  ){
                                position[y][x] = '#';
                            }else{
                                if (turn == 0 && identity[0] + (y-2) == this.bot.BotPosition[0] && identity[1]-(2-x)== this.bot.BotPosition[1] ){
                                    position[y][x] = 'B';
                                }else {
                                    if (turn == 1 && identity[0] + (y-2) == this.player.HumanPosition[0] && identity[1]-(2-x) == this.player.HumanPosition[1]){
                                        position[y][x] = 'P';
                                        this.bot.Human[0] = y;
                                        this.bot.Human[1] = x;
                                    }else {
                                        position[y][x] =this.map.getMap()[identity[0] + (y-2)][identity[1]-(2-x)];
                                    }
                                }


                            }
                        }else{
                            if(identity[0] + (y-2)>=this.map.getMap().length || identity[1]+(x-2)>=this.map.getMap()[0].length ){
                                position[y][x] = '#';
                            }else{
                                if (turn == 0 && identity[0] + (y-2) == this.bot.BotPosition[0] && identity[1]+(x-2)== this.bot.BotPosition[1] ){
                                    position[y][x] = 'B';
                                }else {
                                    if (turn == 1 && identity[0] + (y-2) == this.player.HumanPosition[0] && identity[1]+(x-2) == this.player.HumanPosition[1]){
                                        position[y][x] = 'P';
                                        this.bot.Human[0] = y;
                                        this.bot.Human[1] = x;
                                    }else {
                                        position[y][x] =this.map.getMap()[identity[0] + (y-2)][identity[1]+(x-2)];
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (turn == 0){
            for (char[] chars : position) {
                for (char aChar : chars) {
                    System.out.print(aChar);
                }
                System.out.print("\n");
            }
        }
        System.out.print("\n");
        if (turn == 1){
            for (char[] chars : position) {
                for (char aChar : chars) {
                    System.out.print(aChar);
                }
                System.out.print("\n");
            }
        }




    }

    /**
     * Processes the player's pickup command, updating the map and the player's gold amount.
     *
     * @return If the player successfully picked-up gold or not.
     */
    protected String pickup() {
        if (this.map.getMap()[this.player.HumanPosition[0]][this.player.HumanPosition[1]] == 'G'){
            this.player.GoldAmount = gold() + 1;
            this.map.getMap()[this.player.HumanPosition[0]][this.player.HumanPosition[1]] = '.';
            return "Success";
        }
        return "Fail";
    }

    /**
     * Quits the game, shutting down the application.
     */
    protected String quitGame() {
        this.GameRunning= false;
        if (this.map.getMap()[this.player.HumanPosition[0]][this.player.HumanPosition[1]] == 'E' && gold() == hello()){
            return "WIN";
        }else{
            return "LOSE";
        }


    }

    public static void main(String[] args) {
        GameLogic logic = null;
        logic = new GameLogic();
    }
}