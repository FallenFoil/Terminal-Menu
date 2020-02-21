# TerminalMenu

## Description

A simple library for creating fancy terminal menus.

## Documentation

The documentation was generated using the `javadoc` command and is in the **doc** folder.

## Using custom appearance settings

The custom appearance settings consists in a string. Each setting is separated by **;**. All spaces are ignored, so feel free to use spaces as you like.

All possible settings:

- `exit`: Makes the option number be 0;
- `bold`: Make the text to be in bold;
- `italic`: Make the text to be in italic;
- `underline`: Make the text to be underlined;
- `reverse`: Makes a swap between the text color and background color;
- `crossed-out`: Make the text to be crossed;
- `double-underline`: Make the text to be doubled underline
- `framed`: Make the text to be framed
- `color=R,G,B`: Make the text color be (R,G,B);
- `color-white`: Make the text color to be white;
- `color-red`: Make the text color to be red;
- `color-lime`: Make the text color to be lime;
- `color-gold`: Make the text color to be gold;
- `color-blue`: Make the text color to be blue;
- `color-eggplant`: Make the text color to be eggplant color;
- `color-persiangreen`: Make the text color to be Persian green;
- `color-gray`: Make the text color to be gray;
- `color-default`: Make the text color to be the terminal default;
- `background-color=R,G,B`: Make the background color be (R,G,B);
- `background-color-white`: Make the background color to be white;
- `background-color-red`: Make the background color to be red;
- `background-color-lime`: Make the background color to be lime;
- `background-color-gold`: Make the background color to be gold;
- `background-color-blue`: Make the background color to be blue;
- `background-color-eggplant`: Make the background color to be eggplant color;
- `background-color-persiangreen`: Make the background color to be Persian green;
- `background-color-gray`: Make the background color to be gray;
- `background-color-default`: Make the background color to be the terminal default;

## Examples


### Example 1

```
Menu menu = new Menu("TerminalMenu");

menu.addOption("Option 1", ()->{
    System.out.println("Option 3 selected");
    menu.start();
});

menu.addOption("Option 2", ()->{
    System.out.println("Option 3 selected");
    menu.start();
});

menu.addOption("Exit", ()->{
    System.out.println("Exiting");
});

menu.start();
```

Output:

![Example1](https://github.com/FallenFoil/TerminalMenu/blob/master/Example1.png)

### Example 2

```
Menu menu = new Menu("TerminalMenu");

menu.addData("Some important information Here");
menu.addData("Also Here");

menu.addOption("Option 1", ()->{
    System.out.println("Option 3 selected");
    menu.start();
});

menu.addOption("Option 2", ()->{
    System.out.println("Option 3 selected");
    menu.start();
});

menu.addOption("Exit", ()->{
    System.out.println("Exiting");
});

menu.start();
```

Output:

![Example2](https://github.com/FallenFoil/TerminalMenu/blob/master/Example2.png)

### Example 3

```
Menu menu = new Menu("TerminalMenu");

List<String> header = new ArrayList<>();
header.add("Id");
header.add("Title");
header.add("Author");
header.add("Year");
header.add("NDownloads");

List<String> row_1 = new ArrayList<>();
row_1.add(Integer.toString(0));
row_1.add("Sandstorm");
row_1.add("Darude");
row_1.add("2000");
row_1.add(Integer.toString(9001));

List<String> row_2 = new ArrayList<>();
row_2.add(Integer.toString(1));
row_2.add("Sandstorm2");
row_2.add("Darude");
row_2.add("2000");
row_2.add(Integer.toString(1704));

List<String> row_3 = new ArrayList<>();
row_3.add(Integer.toString(2));
row_3.add("Sandstorm3");
row_3.add("Darude");
row_3.add("2000");
row_3.add(Integer.toString(1000000));


menu.addTableHeader(header);
menu.addTableData(row_1);
menu.addTableData(row_2);
menu.addTableData(row_3);


menu.addOption("Option 1", ()->{
    System.out.println("Option 3 selected");
    menu.start();
});

menu.addOption("Option 2", ()->{
    System.out.println("Option 3 selected");
    menu.start();
});

menu.addOption("Exit", ()->{
    System.out.println("Exiting");
});

menu.start();
```

Output:

![Example3](https://github.com/FallenFoil/TerminalMenu/blob/master/Example3.png)

### Exemple 4

```
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
```

Output:

![Example4](https://github.com/FallenFoil/TerminalMenu/blob/master/Example4.png)