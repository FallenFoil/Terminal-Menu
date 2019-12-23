import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Menu{
    private String name;
    private int nOptions;
    private Map<String, String> allSettings; //All possible settings
    private Map<Integer, Trio> options;
    private List<String> data;
    private List<List<String>> table; // The List<String> is a line
    private List<Integer> biggestsData;
    private List<String> header;
    private int min, max, step;

    public Menu(){
        this.nOptions = 1;
        this.allSettings = new HashMap<>();
        this.options = new HashMap<>();
        this.data = new ArrayList<>();
        this.header = new ArrayList<>();
        this.table = new ArrayList<>();
        this.biggestsData = new ArrayList<>();
        this.min = 0;
        this.max = 4;
        this.step = 4;

        populateAllSettings();
    }

    public Menu(String newName){
        this.name = newName;
        this.nOptions = 1;
        this.options = new HashMap<>();
        this.allSettings = new HashMap<>();
        this.data = new ArrayList<>();
        this.header = new ArrayList<>();
        this.table = new ArrayList<>();
        this.biggestsData = new ArrayList<>();
        this.min = 0;
        this.max = 4;
        this.step = 4;

        populateAllSettings();
    }

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

    public void setStep(int newStep){
        this.step = newStep;
    }

    public void setMin(int newMin){
        this.min = newMin;
    }

    public void setMax(int newMax){
        this.max = newMax;
    }

    public int getStep(){
        return this.step;
    }

    public int getMin(){
        return this.min;
    }

    public int getMax(){
        return this.max;
    }

    public void increaseMinMax(){
        this.min += step;
        this.max += step;
    }

    public void decreaseMinMax(){
        if(this.min - step >= 0){
            this.min -= step;
        }
        if(this.max - step >= step){
            this.max -= step;
        }
    }

    private int biggestsDataSum(){
        int res = 0;

        for(Integer x : this.biggestsData){
            res += x;
        }

        return res;
    }

    private void updateTableDataSpaces(){
        if(!this.header.isEmpty()){
            int column=0;
            for(String str : this.header){
                if(str.length() < this.biggestsData.get(column)) {
                    this.header.set(column, str + " ".repeat(this.biggestsData.get(column) - str.length()));
                }
                column++;
            }
        }

        for(List<String> line : this.table){
            int column=0;
            for(String str : line){
                if(str.length() < this.biggestsData.get(column)){
                    line.set(column, str + " ".repeat(this.biggestsData.get(column)-str.length()));
                }
                column++;
            }
        }
    }

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
            int lineLength = 1 + (this.biggestsData.size() * 3) + biggestsDataSum();
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
            int lineLength = 1 + (this.biggestsData.size() * 3) + biggestsDataSum();
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

    public void addOption(String name, CallBack callBack){
        Trio trio = new Trio(name, null, callBack);
        this.options.put(this.nOptions, trio);
        this.nOptions++;
    }

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

    public void addData(String newData){
        this.data.add(newData);
    }

    public void addTableData(List<String> newData){
        if(this.biggestsData.isEmpty()){
            for(int i=0; i<newData.size(); i++){
                this.biggestsData.add(0);
            }
        }

        int i=0;
        List<String> list = new ArrayList<>();

        for(String str : newData){
            if(str.length() > this.biggestsData.get(i)){
                this.biggestsData.set(i, str.length());
            }

            list.add(str);
            i++;
        }

        this.table.add(list);
    }

    public void addTableHeader(List<String> newHeader){
        if(this.biggestsData.isEmpty()){
            for(int i=0; i<newHeader.size(); i++){
                this.biggestsData.add(0);
            }
        }

        int i = 0;
        for(String str : newHeader){
            if(str.length() > this.biggestsData.get(i)){
                this.biggestsData.set(i, str.length());
            }

            this.header.add(str);
            i++;
        }
    }

    public void clear(){
        this.nOptions = 1;
        this.options.clear();
        this.data.clear();
        this.header.clear();
        this.table.clear();
        this.biggestsData.clear();
        this.min = 0;
        this.max = 4;
        this.step = 4;
    }

    public interface CallBack {
        void run();
    }

    static class Trio{
        private final String name;
        private final List<String> settings;
        private final CallBack callback;

        Trio(String newName, List<String> newSettings, CallBack newCallbacks){
            this.name = newName;
            this.settings = newSettings;
            this.callback = newCallbacks;
        }

        String getName(){
            return this.name;
        }

        List<String> getSettings(){
            return this.settings;
        }

        CallBack getCallback(){
            return this.callback;
        }
    }
}
