import java.util.ArrayList;
import java.util.List;

public class Test{
    private static void search(){
        Menu menu = new Menu("TerminalMenu");

        List<String> header = new ArrayList<>();
        header.add("Key");
        header.add("Property");

        menu.addTableHeader(header);

        List<String> list = new ArrayList<>();
        list.add("os.name");
        list.add(System.getProperty("os.name"));
        menu.addTableData(list);
        list.clear();

        list.add("os.arch");
        list.add(System.getProperty("os.arch"));
        menu.addTableData(list);
        list.clear();

        list.add("os.version");
        list.add(System.getProperty("os.version"));
        menu.addTableData(list);

        menu.addOption("Back", ()->{
            mainMenu();
        });

        menu.start();
    }

    public static void mainMenu(){
        Menu menu = new Menu("TerminalMenu");

        menu.addOption("Search", ()->{
            search();
        });

        menu.addOption("Exit", "exit", ()->{
            System.out.println("Exiting");
        });

        menu.start();
    }

    public static void main(String[] args) {
        mainMenu();
    }
}
