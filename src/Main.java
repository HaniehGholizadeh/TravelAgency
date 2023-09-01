import java.util.*;

import static java.lang.Integer.parseInt;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Map<Integer, City> cities = new HashMap<>();
    public static Map<Integer, Road> roads = new HashMap<>();

    


    public static void main(String[] args) {

        String input;
        boolean Exit = false;
        printMainMenu();
        while (!Exit) {
            input = scanner.next();
            switch (input) {
                case "1" -> help();
                case "2" -> add();
                case "3" -> delete();
                case "4" -> path();
                case "5" -> Exit = true;
                default -> printInvalidInput();
            }
        }
    }

    private static void printMainMenu() {
        String mainMenu = """
                Main Menu - Select an action:
                1. Help
                2. Add
                3. Delete
                4. Path
                5. Exit""";
        System.out.println(mainMenu);
    }

    private static void help() {
        System.out.println("Select a number from shown menu and enter. For example 1 is for help.");
        printMainMenu();
    }

    private static void add() {
        System.out.println("""
                Select model:
                1. City
                2. Road""");
        switch (scanner.nextInt()) {
            case 1 -> addCity();
            case 2 -> addRoad();
        }
    }

    private static void delete() {
        System.out.println("""
                Select model:
                1. City
                2. Road""");
        int model = scanner.nextInt();
        int id = scanner.nextInt();

        switch (model) {
            case 1 -> {
                if (cities.containsKey(id)) {
                    cities.remove(id);
                    System.out.format("City:%d deleted!\n", id);
                } else {
                    System.out.printf("City with id %d not found!\n", id);
                }
            }
            case 2 -> {
                if (roads.containsKey(id)) {
                    roads.remove(id);
                    System.out.format("Road:%d deleted!\n", id);
                } else {
                    System.out.printf("Road with id %d not found!\n", id);
                }
            }
        }
        printMainMenu();
    }

    private static void path() {
        String input = scanner.next();
        int srcId = parseInt(input.split(":")[0]);
        int desId = parseInt(input.split(":")[1]);
        List<Road> paths = new ArrayList<>();
        for (Road road : roads.values()) {
            if (road.getThrough().contains(srcId) && road.getThrough().contains(desId)) {
                if (road.isBiDirectional()) {
                    paths.add(road);
                } else if (road.getThrough().indexOf(srcId) < road.getThrough().indexOf(desId)) {
                    paths.add(road);
                }
            }
        }
        paths.sort(Comparator.comparingInt(Road::getTime));
        String srcName = cities.get(srcId).getName();
        String desName = cities.get(desId).getName();
        for (Road road : paths) {
            System.out.format("%s:%s via Road %s: Takes %s\n", srcName, desName, road.getName(), road.time());
        }
    }

    private static void addCity() {

        System.out.print("id=?");
        int id = scanner.nextInt();

        System.out.print("name=?");
        String name = scanner.next();

        if (!cities.containsKey(id)) {
            cities.put(id, new City(id, name));
            System.out.format("City with id=%d added!\n", id);
        } else {
            cities.get(id).setName(name);
            System.out.format("City with id=%d updated!\n", id);
        }

        System.out.println("""
                Select your next action
                1. Add another City
                2. Main Menu""");
        switch (scanner.nextInt()) {
            case 1 -> addCity();
            default -> printMainMenu();
        }
    }

    private static void addRoad() {

        System.out.print("id=?");
        int id = scanner.nextInt();

        System.out.print("name=?");
        String name = scanner.next();

        System.out.print("from=?");
        int from = scanner.nextInt();

        System.out.print("to=?");
        int to = scanner.nextInt();

        System.out.print("through=?");
        List<Integer> through = new ArrayList<>();
        String input = scanner.next();
        String[] inputs = input.split(",");
        for (String s : inputs) {
            through.add(parseInt(s));
        }

        System.out.print("speed_limit=?");
        int speedLimit = scanner.nextInt();

        System.out.print("length=?");
        int length = scanner.nextInt();

        System.out.print("bi_directional=?");
        int directional = scanner.nextInt();
        boolean biDirectional = directional == 1;
        if (!roads.containsKey(id)) {
            roads.put(id, new Road(id, name, from, to, through, speedLimit, length, biDirectional));
            System.out.format("Road with id=%d added!\n", id);
        } else {
            roads.get(id).update(name, from, to, through, speedLimit, length, biDirectional);
            System.out.format("Road with id=%d updated!\n", id);
        }

        System.out.println("""
                Select your next action
                1. Add another Road
                2. Main Menu""");

        switch (scanner.nextInt()) {
            case 1 -> addRoad();
            default -> printMainMenu();
        }

    }

    private static void printInvalidInput() {
        System.out.println("Invalid input. Please enter 1 for more info.");
        printMainMenu();
    }
}


