import java.util.ArrayList;
import java.util.List;

public class Main {
    private static void newMenu(Menu menu){
        menu.clear();

        menu.addOption("Sair", ()->{
            menu.start();
        });

        menu.start();
    }

    public static void main(String[] args) {
        Menu menu = new Menu("TerminalMenu");

        menu.addOption("Option 1", "color = 255, 200, 200", ()->{
            System.out.println("Option 1 selected");
            menu.start();
        });

        menu.addOption("Option 2", "background-color = 255, 200, 200", ()->{
            System.out.println("Option 2 selected");
            menu.start();
        });

        menu.addOption("Exit", "exit", ()->{
            System.out.println("Exiting");
        });

        menu.start();
    }
}
