import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Menu{
    private String name; //Menu Name
    private int nOptions; //Number of option in the menu
    private Map<String, String> allSettings; //All possible settings
    private Map<Integer, Trio> options; //Map with all option. The key is the id (appearance order) of the option
    private List<String> data; //A list of lines to be presented before the options
    private List<List<String>> table; //A list of rows. Each row is a list of elements (String)
    private List<Integer> biggestData; //A list of the size of the biggest words in each column, for indentation
    private List<String> header; //A list of header columns
    private int min, max, offset; //The min row, the max row, and the difference of both for the table pagination

    /**
     * Constructor that creates an empty menu object. 
     * The table pagination starts at element 0 and ends in element 4 with offset equal to 4.
     */
    public Menu(){
        this.nOptions = 1;
        this.allSettings = new HashMap<>();
        this.options = new HashMap<>();
        this.data = new ArrayList<>();
        this.header = new ArrayList<>();
        this.table = new ArrayList<>();
        this.biggestData = new ArrayList<>();
        this.min = 0;
        this.max = 4;
        this.offset = 4;

        populateAllSettings();
    }

    /**
     * Constructor that creates an empty menu object with a specific name. 
     * The table pagination starts at element 0 and ends in element 4 with offset equal to 4.
     *
     * @param newName The name for the menu.
     */
    public Menu(String newName){
        this.name = newName;
        this.nOptions = 1;
        this.options = new HashMap<>();
        this.allSettings = new HashMap<>();
        this.data = new ArrayList<>();
        this.header = new ArrayList<>();
        this.table = new ArrayList<>();
        this.biggestData = new ArrayList<>();
        this.min = 0;
        this.max = 4;
        this.offset = 4;

        populateAllSettings();
    }

    /**
     * Private method that populates the map "allSettings" with some appearance options
     */
    private void populateAllSettings(){
        this.allSettings.put("reset", "\u001B[0m");

        this.allSettings.put("bold", "\u001B[1m");
        this.allSettings.put("italic", "\u001B[3m");
        this.allSettings.put("underline", "\u001B[4m");
        this.allSettings.put("reverse", "\u001B[7m");
        this.allSettings.put("crossed-out", "\u001B[9m");
        this.allSettings.put("double-underline", "\u001B[21m");

        this.allSettings.put("color-white", "\u001B[30m");
        this.allSettings.put("color-red", "\u001B[31m");
        this.allSettings.put("color-lime", "\u001B[32m");
        this.allSettings.put("color-gold", "\u001B[33m");
        this.allSettings.put("color-blue", "\u001B[34m");
        this.allSettings.put("color-eggplant", "\u001B[35m");
        this.allSettings.put("color-persiangreen", "\u001B[36m");
        this.allSettings.put("color-gray", "\u001B[37m");
        this.allSettings.put("color-default", "\u001B[39m");

        this.allSettings.put("background-color-white", "\u001B[40m");
        this.allSettings.put("background-color-red", "\u001B[41m");
        this.allSettings.put("background-color-lime", "\u001B[42m");
        this.allSettings.put("background-color-gold", "\u001B[43m");
        this.allSettings.put("background-color-blue", "\u001B[44m");
        this.allSettings.put("background-color-eggplant", "\u001B[45m");
        this.allSettings.put("background-color-persiangreen", "\u001B[46m");
        this.allSettings.put("background-color-gray", "\u001B[47m");
        this.allSettings.put("background-color-default", "\u001B[49m");

        this.allSettings.put("framed", "\u001B[51m");
    }

    /**
     * Sets the offset in the table pagination.
     *
     * @param newStep An int that represents the new offset.
     */
    public void setOffset(int newStep){
        this.offset = newStep;
    }

    /**
     * Sets the minimum in the table pagination.
     *
     * @param newMin An int that represents the new minimum.
     */
    public void setMin(int newMin){
        this.min = newMin;
    }

    /**
     * Sets the maximum in the table pagination
     *
     * @param newMax An int that represents the new maximum
     */
    public void setMax(int newMax){
        this.max = newMax;
    }

    /**
     * Gets the offset in the table pagination.
     *
     * @return An int that represents the offset.
     */
    public int getOffset(){
        return this.offset;
    }

    /**
     * Gets the minimum in the table pagination.
     *
     * @return An int that represents the minimum.
     */
    public int getMin(){
        return this.min;
    }

    /**
     * Gets the maximum in the table pagination.
     *
     * @return An int that represents the maximum.
     */
    public int getMax(){
        return this.max;
    }


    /**
     * Goes to the next page in the table pagination, by incrementing the minimum and maximum with offset
     */
    public void increaseMinMax(){
        this.min += offset;
        this.max += offset;
    }

    /**
     * Goes to the previous page in the table pagination, by decreasing the minimum and maximum with offset.
     */
    public void decreaseMinMax(){
        if(this.min - offset >= 0){
            this.min -= offset;
        }
        if(this.max - offset >= offset){
            this.max -= offset;
        }
    }

    /**
     * Calculates the sum of the biggest elements of each column
     *
     * @return the sum of the biggest elements
     */
    private int biggestsDataSum(){
        int res = 0;

        for(Integer x : this.biggestData){
            res += x;
        }

        return res;
    }

    /**
     * Private method that adds spaces to each element of the table if there are some element bigger in the same column
     */
    private void updateTableDataSpaces(){
        if(!this.header.isEmpty()){
            int column=0;
            for(String str : this.header){
                if(str.length() < this.biggestData.get(column)) {
                    this.header.set(column, str + " ".repeat(this.biggestData.get(column) - str.length()));
                }
                column++;
            }
        }

        for(List<String> line : this.table){
            int column=0;
            for(String str : line){
                if(str.length() < this.biggestData.get(column)){
                    line.set(column, str + " ".repeat(this.biggestData.get(column)-str.length()));
                }
                column++;
            }
        }
    }

    /**
     * Prints the Menu and starts a scanner to ask to the user for the menu option.
     * The header of the Menu consists in the name of the menu.
     * If there is some data or an header in the table, it will be printed.
     * Having the option, this method calls the option callback.
     *
     * @param newName The string that represents the menu name.
     */
    public void start(String newName){
        //Header
        String asterisks = "*".repeat(Math.max(0, newName.length() * 3));
        String spaces = " ".repeat(Math.max(0, newName.length()));
        String header = "*" + asterisks + "*\n" +
                "*" + spaces + newName + spaces + "*\n" +
                "*" + asterisks + "*\n";

        //Body
        StringBuilder body = new StringBuilder();

        updateTableDataSpaces();

        //Table
        if(!this.header.isEmpty() || !this.table.isEmpty()){
            int lineLength = 1 + (this.biggestData.size() * 3) + biggestsDataSum();
            String lineSeparator = "|" + "-".repeat(lineLength - 2) + "|\n";
            body.append(lineSeparator);

            if(!this.header.isEmpty()){
                body.append("|");
                for(String str : this.header){
                    body.append(" ").append(str).append(" |");
                }
                body.append("\n").append(lineSeparator);
            }

            if(!this.table.isEmpty()){
                for(int i=this.min; i<this.table.size() && i>=this.min && i<this.max; i++){
                    body.append("|");
                    for(String str : this.table.get(i)){
                        body.append(" ").append(str).append(" |");
                    }
                    body.append("\n").append(lineSeparator);
                }
            }
        }
        else{
            for(int i=this.min; i<this.data.size() && i<this.max; i++){
                body.append(this.data.get(i)).append("\n");
            }
        }

        //Options
        for(int j=1; j<=this.options.size(); j++){
            if(this.options.containsKey(j)){
                Trio trio = this.options.get(j);
                body.append("  ");
                if(trio.getSettings() != null){
                    for(String str : trio.getSettings()){
                        if(!str.equals("exit")){
                            body.append(str);
                        }
                    }
                }
                body.append(j).append(")     ").append(trio.getName()).append("\u001B[0m\n");
            }
        }

        //Option exit, if exists
        if(this.options.containsKey(0)){
            Trio trio = this.options.get(0);
            body.append("  ");
            for(String str : trio.getSettings()){
                if(!str.equals("exit")){
                    body.append(str);
                }
            }
            body.append("0)     ").append(trio.getName()).append("\u001B[0m\n");
        }

        System.out.print(header + body.toString());

        //Scanner
        Scanner in = new Scanner(System.in);
        int op=-1;

        while(op==-1){
            System.out.print("$ ");
            try{

                String value = in.nextLine();
                op = Integer.parseInt(value);

                if(!this.options.containsKey(op)){throw new NumberFormatException();}
                this.options.get(op).getCallback().run();
            }
            catch(NumberFormatException e){
                System.out.println("Invalid Input");
            }
        }
    }

    /**
     * Prints the Menu and starts a scanner to ask to the user for the menu option.
     * The header of the Menu  consists in the name of the menu.
     * If there is some data or an header in the table, it will be printed. If not, data lines will be printed if there are some.
     * Having the option, this method calls the option callback.
     */
    public void start(){
        //Header
        String asterisks = "*".repeat(Math.max(0, this.name.length() * 3));
        String spaces = " ".repeat(Math.max(0, this.name.length()));

        String header = "*" + asterisks + "*\n" +
                "*" + spaces + name + spaces + "*\n" +
                "*" + asterisks + "*\n";

        //Body
        StringBuilder body = new StringBuilder();

        updateTableDataSpaces();

        //Table
        if(!this.header.isEmpty() || !this.table.isEmpty()){
            int lineLength = 1 + (this.biggestData.size() * 3) + biggestsDataSum();
            String lineSeparator = "|" + "-".repeat(lineLength - 2) + "|\n";
            body.append(lineSeparator);

            if(!this.header.isEmpty()){
                body.append("|");
                for(String str : this.header){
                    body.append(" ").append(str).append(" |");
                }
                body.append("\n").append(lineSeparator);
            }

            if(!this.table.isEmpty()){
                for(int i=this.min; i<this.table.size() && i>=this.min && i<this.max; i++){
                    body.append("|");
                    for(String str : this.table.get(i)){
                        body.append(" ").append(str).append(" |");
                    }
                    body.append("\n").append(lineSeparator);
                }
            }
        }
        else{
            for(int i=this.min; i<this.data.size() && i<this.max; i++){
                body.append(this.data.get(i)).append("\n");
            }
        }

        //Options
        for(int j=1; j<=this.options.size(); j++){
            if(this.options.containsKey(j)){
                Trio trio = this.options.get(j);
                body.append("  ");
                if(trio.getSettings() != null){
                    for(String str : trio.getSettings()){
                        if(!str.equals("exit")){
                            body.append(str);
                        }
                    }
                }
                body.append(j).append(")     ").append(trio.getName()).append("\u001B[0m\n");
            }
        }

        //Option exit, if exists
        if(this.options.containsKey(0)){
            Trio trio = this.options.get(0);
            body.append("  ");
            for(String str : trio.getSettings()){
                if(!str.equals("exit")){
                    body.append(str);
                }
            }
            body.append("0)     ").append(trio.getName()).append("\u001B[0m\n");
        }

        System.out.print(header + body.toString());

        //Scanner
        Scanner in = new Scanner(System.in);
        int op=-1;

        while(op==-1){
            System.out.print("$ ");
            try{

                String value = in.nextLine();
                op = Integer.parseInt(value);

                if(!this.options.containsKey(op)){throw new NumberFormatException();}
                this.options.get(op).getCallback().run();
            }
            catch(NumberFormatException e){
                System.out.println("Invalid Input");
            }
        }
    }

    /**
     * Private method that parses the custom appearance settings
     *
     * @param settings A string with custom setting seperated by ";"
     *
     * @return A list with the appearance codes to be added before the option
     */
    private List<String> parseOptions(String settings){
        String[] args = settings.split("[;]");
        List<String> list = new ArrayList<>();

        for(String arg : args) {
            String[] moreArgs = arg.split("[=]");
            switch (moreArgs[0].toLowerCase().replaceAll("\\s+", "")) {
                case "exit":
                    list.add("exit");
                    break;
                case "color":
                    String[] rgb = moreArgs[1].replaceAll("\\s+", "").split("[,]");
                    list.add("\u001B[38;2;" + rgb[0] + ";" + rgb[1] + ";" + rgb[2] + "m");
                    break;
                case "background-color":
                    String[] back_rgb = moreArgs[1].replaceAll("\\s+", "").split("[,]");
                    list.add("\u001B[48;2;" + back_rgb[0] + ";" + back_rgb[1] + ";" + back_rgb[2] + "m");
                    break;
                default:
                    list.add(this.allSettings.get(moreArgs[0].toLowerCase().replaceAll("\\s+", "")));
                    break;
            }
        }

        return list;
    }

    /**
     * Adds an option to the menu.
     *
     * @param name The name of the option.
     * @param callBack A implementation of method run of the interface CallBack. This implementation can be a lambda function/expression.
     */
    public void addOption(String name, CallBack callBack){
        Trio trio = new Trio(name, null, callBack);
        this.options.put(this.nOptions, trio);
        this.nOptions++;
    }

    /**
     * Adds an option to the menu with settings.
     *
     * @param name The name of the option
     * @param settings A string of setting to be used in this option.
     * @param callBack A implementation of method run of the interface CallBack. This implementation can be a lambda function/expression.
     */
    public void addOption(String name, String settings, CallBack callBack){
        List<String> map = parseOptions(settings);
        Trio trio;

        if(map.contains("exit")){
            trio = new Trio(name, map, callBack);
            this.options.put(0, trio);
        }
        else{
            trio = new Trio(name, map, callBack);
            this.options.put(this.nOptions, trio);
            this.nOptions++;
        }
    }

    /**
     * Adds a new data line to printed before the menu options.
     *
     * @param newData A string that represents a data line to be inserted.
     */
    public void addData(String newData){
        this.data.add(newData);
    }

    /**
     * Adds a new row to the menu table.
     *
     * @param newData A list of String (row) to be added to the bottom of the table.
     */
    public void addTableData(List<String> newData){
        if(this.biggestData.isEmpty()){
            for(int i=0; i<newData.size(); i++){
                this.biggestData.add(0);
            }
        }

        int i=0;
        List<String> list = new ArrayList<>();

        for(String str : newData){
            if(str.length() > this.biggestData.get(i)){
                this.biggestData.set(i, str.length());
            }

            list.add(str);
            i++;
        }

        this.table.add(list);
    }

    /**
     * Adds the table header or replaces it if there is some already.
     *
     * @param newHeader A list of string that represents the header.
     */
    public void addTableHeader(List<String> newHeader){
        if(this.biggestData.isEmpty()){
            for(int i=0; i<newHeader.size(); i++){
                this.biggestData.add(0);
            }
        }

        int i = 0;
        for(String str : newHeader){
            if(str.length() > this.biggestData.get(i)){
                this.biggestData.set(i, str.length());
            }

            this.header.add(str);
            i++;
        }
    }

    /**
     * Clears all user inserted data, except the name of the menu.
     */
    public void clear(){
        this.nOptions = 1;
        this.options.clear();
        this.data.clear();
        this.header.clear();
        this.table.clear();
        this.biggestData.clear();
        this.min = 0;
        this.max = 4;
        this.offset = 4;
    }

    /**
     * Interface that permites to create an Callback to each option of the Menu
     */
    public interface CallBack {
        /**
         * Method that will run when an option is chosen
         */
        void run();
    }

    /**
     * Custom tuple to store an option name, list of settings and callback.
     */
    static class Trio{
        private final String name; //Option name
        private final List<String> settings; //Option appearance settings
        private final CallBack callback; //Option callback

        /**
         * Constructor that create Trio object, representing an option with a name, settings and callback
         * @param newName Option name
         * @param newSettings Option appearence settings
         * @param newCallbacks Option callback
         */
        Trio(String newName, List<String> newSettings, CallBack newCallbacks){
            this.name = newName;
            this.settings = newSettings;
            this.callback = newCallbacks;
        }

        /**
         * Gets the name of the Option
         *
         * @return name of the option
         */
        String getName(){
            return this.name;
        }

        /**
         * Gets the appearance settings of an option
         *
         * @return A list of appearance settings
         */
        List<String> getSettings(){
            return this.settings;
        }

        /**
         * Gets the option callback
         *
         * @return A Callback implemented object with the callback
         */
        CallBack getCallback(){
            return this.callback;
        }
    }
}
