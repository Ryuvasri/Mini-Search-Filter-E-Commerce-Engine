import java.util.*;

public class Ecommerce {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Product[] products = {
            new Product(1, "Laptop", "Electronics"),
            new Product(2, "Phone", "Electronics"),
            new Product(3, "Watch", "Accessories"),
            new Product(4, "Sunglasses", "Fashion"),
            new Product(5, "Notebook", "Stationery"),
            new Product(6, "Camera", "Electronics"),
            new Product(7, "Headphones", "Electronics"),
            new Product(8, "Water Bottle", "Sports"),
            new Product(9, "Microwave", "Home Appliances"),
            new Product(10, "Pen", "Stationery"),
            new Product(11, "Pencil", "Stationery"),
            new Product(12, "Eraser", "Stationery"),
            new Product(13, "Highlighter", "Stationery"),
            new Product(14, "Sketchbook", "Stationery"),
            new Product(15, "Marker", "Stationery"),
            new Product(16, "Sticky Notes", "Stationery"),
            new Product(17, "Tablet", "Electronics"),
            new Product(18, "Charger", "Electronics"),
            new Product(19, "Smartwatch", "Accessories"),
            new Product(20, "T-Shirt", "Fashion"),
            new Product(21, "Shoes", "Fashion"),
            new Product(22, "Cricket Bat", "Sports"),
            new Product(23, "Basketball", "Sports"),
            new Product(24, "Air Fryer", "Home Appliances"),
            new Product(25, "Desk Organizer", "Stationery"),
            new Product(26, "Glue Stick", "Stationery"),
            new Product(27, "Tripod Stand", "Electronics"),
            new Product(28, "Bluetooth Speaker", "Electronics"),
            new Product(29, "Backpack", "Fashion"),
            new Product(30, "Fitness Band", "Accessories"),
            new Product(31, "Electric Kettle", "Home Appliances"),
            new Product(32, "Gym Bag", "Sports"),
            new Product(33, "Whiteboard Marker", "Stationery"),
            new Product(34, "Drawing Compass", "Stationery"),
            new Product(35, "Yoga Mat", "Sports"),
            new Product(36, "Formal Shoes", "Fashion")

        };

        Product[] sortedProducts = products.clone();
        Arrays.sort(sortedProducts, (a, b) -> a.productName.compareToIgnoreCase(b.productName));

        while (true) {
            System.out.println("\n--- E-Commerce Menu ---");
            System.out.println("1. Search Product (Linear Search)");
            System.out.println("2. Search Product (Binary Search)");
            System.out.println("3. Filter by Category");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter product name to search (Linear): ");
                    String name1 = sc.nextLine();
                    Product result1 = Search.linearSearch(products, name1);
                    if (result1 != null) 
                    {
                        System.out.println("\nProduct Found:");
                        System.out.println("ID       : " + result1.productId);
                        System.out.println("Name     : " + result1.productName);
                        System.out.println("Category : " + result1.category);
                    } else 
                    {
                        System.out.println("Product not found.");
                    }
                    break;

                case 2:
                    System.out.print("Enter product name to search (Binary): ");
                    String name2 = sc.nextLine();
                    Product result2 = Search.binarySearch(sortedProducts, name2);
                    if (result2 != null) 
                    {
                        System.out.println("\nProduct Found:");
                        System.out.println("ID       : " + result2.productId);
                        System.out.println("Name     : " + result2.productName);
                        System.out.println("Category : " + result2.category);
                    } else 
                    {
                        System.out.println("Product not found.");
                    }
                    break;

                case 3:
                    Filter.showAvailableCategories(products);
                    System.out.print("Enter category to filter: ");
                    String category = sc.nextLine();
                    Filter.filterByCategory(products, category);
                    break;

                case 4:
                    System.out.println("Thank you for using the app!");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

// Product Class
class Product {
    int productId;
    String productName;
    String category;

    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public String toString() {
        return "Product{" + "ID=" + productId + ", Name='" + productName + "', Category='" + category + "'}";
    }
}

// Search Class
class Search {
    public static Product linearSearch(Product[] products, String targetName) {
        for (Product product : products) {
            if (product.productName.equalsIgnoreCase(targetName)) {
                return product;
            }
        }
        return null;
    }

    public static Product binarySearch(Product[] products, String targetName) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int result = products[mid].productName.compareToIgnoreCase(targetName);

            if (result == 0)
                return products[mid];
            else if (result < 0)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return null;
    }
}

// Filter Class
class Filter {
    public static void filterByCategory(Product[] products, String targetCategory) {
        boolean found = false;
        System.out.println("\nProducts in category '" + targetCategory + "':");
        String format = "| %-4s | %-22s | %-16s |\n";
        System.out.println("----------------------------------------------------");
        System.out.printf("| %-4s | %-22s | %-16s |\n", "ID", "Product Name", "Category");
        System.out.println("----------------------------------------------------");
        for (Product product : products) {
            if (product.category.equalsIgnoreCase(targetCategory)) {
                System.out.printf(format, product.productId, product.productName, product.category);
                found = true;
            }
        }
         if (!found) {
        System.out.println("| No products found in this category.                      |");
    }
    System.out.println("----------------------------------------------------");
}

    public static void showAvailableCategories(Product[] products) {
        Set<String> categories = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        for (Product product : products) {
            categories.add(product.category);
        }

        System.out.println("\nAvailable Categories:");
        for (String category : categories) {
            System.out.println("- " + category);
        }
    }
}
