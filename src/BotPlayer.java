



public class BotPlayer {
    protected int[] BotPosition;
    public int[] Human;
    private int count = 0;
    private int min_number = 3;
    public int direction;
    private boolean change = false;


    public BotPlayer(Map map, HumanPlayer human, GameLogic logic) {
        this.BotPosition = human.generateCoordinates(map, false, logic);
        this.Human = new int[2];
        this.Human[0] = -1;

    }

    public void Action(Map map, GameLogic logic, int direct) {
        if (this.Human[0] == -1) {
            if (direct == -1) {
                logic.look(1);

                direction= 1;
            } else {
                if (direct == 1) {
                    logic.move('N', 1);
                    this.count++;
                    if (this.count == min_number) {
                        this.count = 0;
                        direction = 2;
                    }
                } else {
                    if (direct == 2) {
                        logic.move('E', 1);
                        this.count++;
                        if (this.count == min_number) {
                            this.count = 0;
                            direction = 3;
                        }
                    } else {
                        if (direct == 3) {
                            logic.move('S', 1);
                            this.count++;
                            if (this.count == min_number) {
                                this.count = 0;
                                direction = 4;
                            }
                        } else {
                            if (direct == 4) {
                                logic.move('W', 1);
                                this.count++;
                                if (this.count == min_number) {
                                    this.count = 0;
                                    min_number = min_number + 2;
                                    direction = -1;
                                }
                            }
                        }
                    }
                }
            }
        }else{
            if(this.Human[0] == this.BotPosition[0]){
                if(this.Human[1] < this.BotPosition[1] ){
                   if(!change){
                       logic.move('W', 1);
                       change = true;
                   }else{
                       logic.move('W', 1);
                       this.Human[0] = -1;
                       change = false;
                   }

                }else{
                    if(!change){
                        logic.move('E', 1);
                        change = true;
                    }else{
                        logic.move('E', 1);
                        this.Human[0] = -1;
                        change = false;
                    }

                }
            }else{
                if(this.Human[0] < this.BotPosition[0] ){
                    if(!change){
                        logic.move('S', 1);
                        change = true;
                    }else{
                        logic.move('S', 1);
                        this.Human[0] = -1;
                        change = false;
                    }


                }else{
                    if(!change){
                        logic.move('N', 1);
                        change = true;
                    }else{
                        logic.move('N', 1);
                        this.Human[0] = -1;
                        change = false;
                    }

                }
            }
        }
    }
}