import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Menu stored in a HashMap
        HashMap<String, Integer> menu = new HashMap<>();
        menu.put("Chicken", 12000);
        menu.put("Beef", 15000);
        menu.put("Fish", 18000);

        // List of customers
        ArrayList<String> customers = new ArrayList<>();
        ArrayList<String> orders = new ArrayList<>();

        boolean open = true;

        while (open) {
            System.out.println("\n--- Restaurant lola---");
            System.out.println("1. Show menu");
            System.out.println("2. Register customer");
            System.out.println("3. Show pending bills");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (option) {
                case 1:
                    System.out.println("\nAvailable menu:");
                    for (String dish : menu.keySet()) {
                        System.out.println("- " + dish + " : $" + menu.get(dish));
                    }
                    break;

                case 2:
                    System.out.print("Customer name: ");
                    String name = scanner.nextLine();
                    customers.add(name);

                    System.out.print("Dish chosen: ");
                    String dish = scanner.nextLine();

                    if (menu.containsKey(dish)) {
                        orders.add(dish);
                        System.out.println(" Customer " + name + " registered with " + dish
                                + " ($" + menu.get(dish) + ").");
                        System.out.println("The customer must pay $" + menu.get(dish) + " before serving.");
                    } else {
                        System.out.println("That dish is not on the menu.");
                        customers.remove(customers.size() - 1); // remove wrong customer
                    }
                    break;

                case 3:
                    System.out.println("\n--- Pending bills ---");
                    int total = 0;
                    for (int i = 0; i < customers.size(); i++) {
                        String client = customers.get(i);
                        String order = orders.get(i);
                        int price = menu.get(order);
                        total += price;
                        System.out.println(client + " ordered " + order + " ($" + price + ")");
                    }
                    System.out.println("TOTAL to collect: $" + total);
                    break;

                case 4:
                    open = false;
                    System.out.println("Closing restaurant...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}
