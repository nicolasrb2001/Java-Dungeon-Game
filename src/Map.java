import java.io.*;
import java.util.*;




/**
 * Reads and contains in memory the map of the game.
 *
 */
public class Map {

    /* Representation of the map */
    public char[][] map;
    public BufferedReader br;



    /* Map name */
    private String mapName = "";

    /* Gold required for the human player to win */
    private int goldRequired;
    /* Stores location of the file with the map chosen to be loaded */
    private String MapLocation;

    /**
     * Constructor that accepts a map to read in from.
     *
     * @param : The filename of the map file.
     */
	/*the constructor takes a path by default and calls a method to allow the user to choose from different
	 maps or to specify a file/folder containing them*/
    public Map(String fileName) {

        boolean mapvalid = false;
        this.br = new BufferedReader(new InputStreamReader(System.in));
        do {
            String[] x;
            x = FileLocation(fileName, this.br);
            this.mapName = x[0];
            this.MapLocation = x[1];
            this.map = new char[(int) readMap(this.MapLocation, new char[][]{{'e', 'r'}}, true, false, false, false)[0][0]][(int) readMap(this.MapLocation, new char[][]{{'e', 'r'}}, false, true, false, false)[0][0]];
            if (getMap().length == 0){
                mapvalid = false;
                System.out.println("no map found in the file");
                continue;
            }
            this.goldRequired = readMap(this.MapLocation, this.map, false, false, true, false)[0][0];
            if (getGoldRequired()== 0 ){
                mapvalid =false;
                System.out.println("no win condition found in the file");
                continue;
            }
            if (getMapName().equals("")){
                mapvalid = false;
                System.out.println("no map name found in the file");
                continue;
            }
            this.map = readMap(this.MapLocation, this.map, false, false, false, false);
            mapvalid = true;


        } while(!mapvalid);
    }


    protected int getGoldRequired() {
        /* reads second line from the file*/
        return this.goldRequired;
    }

    public String[] FileLocation(String fileName, BufferedReader br){
        boolean chosen = false;
        String[] result = new String[2];
        StringBuilder names = new StringBuilder();
        do {
            List<String> filenames = new ArrayList<>();
            File[] files = new File(fileName).listFiles();
            Hashtable<String, String> my_dict = new Hashtable<>();
			/*if the array does not contain ane files, it means that the path specified by the user
			 is a path to a file and not a folder*/
            if (files == null) {
                File z = new File(fileName);
                result[0] = z.getName();
                result[1] = fileName;
                return result;
            }/*the path specified by the user is a folder so the user will be prompted to choose whichever map they want */
            for (File file : files) {
                if (file.isFile()) {
                    for (char i : readMap(file.getPath(), new char[][] {{'e','r'}}, false, false, false, true )[0]){
                        names.append(i);
                    }
                    my_dict.put(names.toString().trim(), file.getName());

                    filenames.add(names.toString().trim());
                    names = new StringBuilder();
                }
            }

            System.out.println("Would you like to choose one of these? If so, please type in the name. If not, please type in the location of the desired folder containing maps or the location of the file containing a the desired map");
            System.out.println("The path to the desired file/folder should be typed in the format path\\to\\file");

            for (String name : filenames){
                System.out.println(name);
            }
            String choice;
            choice = null;

            try {
                choice = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (String name: filenames) {

                if (choice.equals(name)) {
                    chosen = true;
                    fileName = fileName + "\\" +  my_dict.get(name);
                    result[0] = name;
                    result[1] = fileName;
                    break;
                }
            }
            if (!chosen) {
                fileName = choice;
            }

        } while (!chosen);


        return result;
    }


    /**
     * @return : The map as stored in memory.
     */
    protected char[][] getMap() {

        return this.map;
    }
    protected void printmap(Map map){
        for (int i = 0; i < map.getMap().length; i++){
            for (int x = 0; x < map.getMap()[i].length; x++){
                System.out.print(map.getMap()[i][x]);
            }
            System.out.print("\n");
        }
    }




    /**
     * @return : The name of the current map.
     */
    protected String getMapName() {
        return this.mapName;
    }


    /**
     * Reads the map from file.
     *
     * @param : Name of the map's file.
     */
    protected char[][] readMap(String fileName, char[][] map, boolean rows, boolean columns, boolean gold, boolean mapnames) {

        File path = new File(fileName);
        BufferedReader br = null;
        StringBuilder number = new StringBuilder();
        int longest = -1;


        int index =0;
        try {
            br = new BufferedReader(new FileReader(path.getPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String input;
        input = "";

        do {
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (input == null){
                continue;
            }



            if (((!columns && !rows) && !input.contains("win") && !input.contains("name")) || (input.contains("win") && gold || input.contains("name") && mapnames))  {
                if (mapnames){
                    input = input.substring(input.indexOf("name") + 4);

                    char[][] MapName = new char[1][input.length()+1];

                    for (int i = 0; i < input.length(); i++){

                        MapName[0][i] = input.charAt(i);
                    }
                    return MapName;

                }



                for (int i = 0; i < input.length(); i++) {
                    if (!gold){
                        map[index][i] = input.charAt(i);
                    }else{
                            if (Character.isDigit(input.charAt(i))){
                                if (input.charAt(i-1) == '-'){
                                    break;
                                }
                                number.append(input.charAt(i));
                            }
                        }


                }


            } else {
                    if(columns && !input.contains("win") && !input.contains("name")) {
                        do{
                            if (input.length() > longest){
                                longest = input.length();
                            }
                            try {
                                input = br.readLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (input == null){
                                break;
                            }
                        } while (!input.contains("win") || !input.contains("name"));
                        index = longest;
                        map[0][0] = (char) index;

                        return map;
                    }



            }
            if (input.contains("win") && gold) {
                map[0][0] = (char) Integer.parseInt(number.toString());
                return map;
            }
            if (!input.contains("win") && !input.contains("name")){
                index++;
            }



        } while (input != null);
        if (rows){

            map[0][0] = (char) (index);


        }


        return map;
    }



}
