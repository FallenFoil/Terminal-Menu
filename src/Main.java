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
        Menu menu = new Menu("Menu Name");

        List<String> header = new ArrayList<>();
        header.add("Id");
        header.add("Title");
        header.add("Author");
        header.add("Year");
        header.add("Tags");
        header.add("NDownloads");
        menu.addTableHeader(header);

        List<String> list = new ArrayList<>();
        list.add(Integer.toString(0));
        list.add("Meu Amor");
        list.add("Luis amcedo");
        list.add("2001");
        list.add("romance");
        list.add(Integer.toString(174));
        menu.addTableData(list);
        list.clear();

        list.add(Integer.toString(0));
        list.add("Minha Vida");
        list.add("Luis");
        list.add("20013");
        list.add("pop");
        list.add(Integer.toString(1704));
        menu.addTableData(list);
        list.clear();

        list.add(Integer.toString(0));
        list.add("Meu Amor do meu coração");
        list.add("Luis josé Macedo");
        list.add("2019");
        list.add("Pop,Jazz,Rock");
        list.add(Integer.toString(1000000));
        menu.addTableData(list);
        list.clear();


        menu.addOption("Next Page", "bold;color-blue", ()->{
            menu.increaseMinMax();
            menu.start();
        });

        menu.addOption("Previous Page", "italic;color-lime", ()->{
            menu.decreaseMinMax();
            menu.start();
        });

        menu.addOption("Option 3", "underline;", ()->{
            System.out.println("Opção 3 selecionada");
            menu.start();
        });

        menu.addOption("Option 4", "framed", ()->{
            System.out.println("Opção 4 selecionada");
            newMenu(menu);
        });

        menu.addOption("Option 5", ()->{
            System.out.println("Opção 5 selecionada");
            menu.start();
        });

        menu.start();
    }
}
