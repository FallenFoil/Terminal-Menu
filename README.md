# TerminalMenu

- [Description](#description)
- [Documentation](#Documentation)
    - [Important Variables](#Important-Variables)
    - [Constructors](#Constructors)
    - [Methods](#Methods)

## Description


## Documentation

### Important Variables

- *String* **name**: Name of menu.
- *int* **nOptions**: Number of menu options.
- *Map<String, String>* **allSettings**: 
- *Map<Integer, Trio>* **options**: Options of menu.
- *List<String>* **data**: List of data lines to be inserted before options of the menu.
- *List<List<String>>* **table**: Table data to be inserted before options of the menu.
- *List<String>* **header**: Table header to be inserted before the table data of the menu.
- *int* **min**: Minimum of the pagination.
- *int* **max**: Maximum of the pagination.
- *int* **offset**: Offset of the pagination.

### Constructors

- [Menu]()()
- [Menu]()(String newName)

### Methods

- *void* [setStep]()(*int* **newStep**): Sets pagination step.
- *void* [setMin]()(*int* **newMin**): Sets pagination minimum.
- *void* [setMax]()(*int* **newMax**): Sets pagination maximum.
- *int* [getStep]()(): Get pagination step.
- *int* [getMin]()(): Get pagination minimum.
- *int* [getMax]()(): Get pagination maximum
- *void* [increaseMinMax]()(): Go to next pagination page.
- *void* [decreaseMinMax]()(): Go to previous pagination page.
- *void* [start]()(*String* **newName**): Prints menu with the name equals to **newName**.
- *void* [start]()(): Prints menu.
- *void* [addOption]()(*String* **name**, *CallBack* **callBack**): Add new menu option.
- *void* [addOption]()(*String* **name**, *String* **settings**, *CallBack* **callBack**): Add new menu option with settings.
- *void* [addData]()(*String* **newData**): Add more data to menu.
- *void* [addTableData]()(*List\<String\>* **newData**): Add more data to menus table
- *void* [addTableHeader]()(*List\<String\>* **newHeader**): Add header to menu table
- *void* [clear]()(): Clear all information about the menu.