import java.io.BufferedReader;
import java.io.IOException;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Runs the game with a human player and contains code needed to read inputs.
 *
 */
public class HumanPlayer {
    protected int[] HumanPosition;
    protected int GoldAmount;
    public HumanPlayer(Map map, GameLogic logic){
        this.GoldAmount = 0;
        this.HumanPosition = generateCoordinates(map, true, logic);
    }
    protected int[] generateCoordinates(Map map, boolean human, GameLogic logic){
        int[] coordinates = new int[2];
        Random rand = new Random();

        boolean valid = false;
        int total = 0;
        int x_coordinate;
        int y_coordinate;
        do {
            x_coordinate = rand.nextInt((map.getMap()[0]).length);
            y_coordinate = rand.nextInt(map.getMap().length);
            coordinates[0] = y_coordinate;
            coordinates[1] = x_coordinate;
            if (human){
                if (map.getMap()[y_coordinate][x_coordinate] == '.' || map.getMap()[y_coordinate][x_coordinate] == 'E'){

                    valid = true;

                }
            }
            if (!human){
                if ((map.getMap()[y_coordinate][x_coordinate] != '#' ) && y_coordinate != logic.player.HumanPosition[0] && x_coordinate != logic.player.HumanPosition[1]){
                    valid = true;
                }
            }
        } while (!valid);
        coordinates[0] = y_coordinate;
        coordinates[1] = x_coordinate;
        return coordinates;
    }
    /**
     * Reads player's input from the console.
     * <p>
     * return : A string containing the input the player entered.
     * @param br reader from map.java
     */
    protected String getInputFromConsole(BufferedReader br) throws IOException {
        List my_List = new ArrayList();
        my_List.add("HELLO");
        my_List.add("GOLD");
        my_List.add("MOVE");
        my_List.add("PICKUP");
        my_List.add("LOOK");
        my_List.add("QUIT");
        System.out.println("Enter one of the following commands");
        String input;
        for (Object i: my_List){
            System.out.println(i);
        }
        input = br.readLine();
        input = input.toUpperCase();
        input = input.replaceAll(" ", "");
        return input;
    }
    /**
     * Processes the command. It should return a reply in form of a String, as the protocol dictates.
     * Otherwise it should return the string "Invalid".
     *
     * @return : Processed output or Invalid if the @param command is wrong.
     */
    protected void getNextAction(String input, GameLogic logic) {

        if (input.equals("HELLO")){
            System.out.println("Gold to win: " + logic.hello());

        }else if (input.equals("GOLD")){
            System.out.println("Gold owned: " + logic.gold());
        }else if (input.contains("MOVE") && input.length() > 4){
            if (!(input.charAt(4) == 'E' || input.charAt(4) == 'S' || input.charAt(4) == 'N' || input.charAt(4) == 'W')){
                System.out.println("Invalid");
            }else{
                System.out.println(logic.move(input.charAt(4), logic.turn));
            }
        }else if (input.equals("PICKUP")){
            System.out.println(logic.pickup() + " " + logic.gold());
        }else if (input.equals("LOOK")){
            logic.look(logic.turn);
        }else if (input.equals("QUIT")){
            System.out.println(logic.quitGame());
        }else{
            System.out.println("Invalid");
        }
    }
}