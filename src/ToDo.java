import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDo {
    private List<String> todoList;

    public ToDo(){
        this.todoList = new ArrayList<>();
    }

    private static int validIntegerInput(Scanner scan){
        String str = "Check line:\n$ ";
        System.out.print(str);
        int impossibleValue = -1;
        int value = impossibleValue;
        while(value==impossibleValue){
            try{
                System.out.print(str);
                String yearStr = scan.nextLine();
                value = Integer.parseInt(yearStr);
            }
            catch(NumberFormatException e){
                System.out.println("\u001B[31mInvalid Input\u001B[0m");
            }
        }

        return value;
    }

    public void mainMenu(){
        Scanner scan = new Scanner(System.in);

        Menu menu = new Menu("ToDo");

        List<String> header = new ArrayList<>();
        header.add("Id");
        header.add("ToDo");
        menu.addTableHeader(header);

        int i = 0;
        for(String str : this.todoList){
            List<String> list = new ArrayList<>();
            list.add(Integer.toString(i));
            list.add(str);
            i++;

            menu.addTableData(list);
        }

        menu.addOption("Check", ()->{
            int line = validIntegerInput(scan);
            this.todoList.remove(line);
            this.mainMenu();
        });

        menu.addOption("Add", ()->{
            System.out.print("ToDo:\n$ ");
            String line = scan.nextLine();

            this.todoList.add(line);

            this.mainMenu();
        });

        menu.start();
    }

    public static void main(String[] args) {
        ToDo system = new ToDo();

        system.mainMenu();
    }
}
