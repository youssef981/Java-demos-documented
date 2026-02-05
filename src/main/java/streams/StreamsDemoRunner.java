package streams;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class StreamsDemoRunner {


    public static void main(String[] args) {

        List<Product> products = List.of(
                new Product("P1", "iPhone 14", "Electronics", new BigDecimal("999.99")),
                new Product("P2", "MacBook Pro", "Electronics", new BigDecimal("1999.99")),
                new Product("P3", "Coffee Maker", "Appliances", new BigDecimal("89.99")),
                new Product("P4", "Running Shoes", "Sportswear", new BigDecimal("129.99")),
                new Product("P5", "Yoga Mat", "Sportswear", new BigDecimal("25.99")),
                new Product("P6", "Water Bottle", "Sportswear", new BigDecimal("12.99")),
                new Product("P7", "Wireless Earbuds", "Electronics", new BigDecimal("159.99")),
                new Product("P8", "Smart Watch", "Electronics", new BigDecimal("349.99")),
                new Product("P9", "Blender", "Appliances", new BigDecimal("79.99")),
                new Product("P10", "Desk Lamp", "Home", new BigDecimal("34.99"))
        );
        List<Customer> customers = List.of(
                new Customer("C1", "John Smith", "john@example.com", LocalDate.of(2020, 1, 15), "elite"),
                new Customer("C2", "Emma Johnson", "emma@example.com", LocalDate.of(2021, 3, 20), "standard"),
                new Customer("C3", "Michael Brown", "michael@example.com", LocalDate.of(2019, 7, 5), "premium"),
                new Customer("C4", "Olivia Wilson", "olivia@example.com", LocalDate.of(2022, 2, 10), "standard"),
                new Customer("C5", "William Davis", "william@example.com", LocalDate.of(2020, 11, 25), "elite")
        );
        List<Order> orders = List.of(
                new Order("O1", customers.get(0), LocalDate.of(2023, 3, 15),
                        List.of(
                                new OrderItem(products.get(0), 1),
                                new OrderItem(products.get(7), 1)
                        ),
                        "delivered"),
                new Order("O2", customers.get(2), LocalDate.of(2023, 4, 2),
                        List.of(
                                new OrderItem(products.get(1), 1)
                        ),
                        "delivered"),
                new Order("O3", customers.get(1), LocalDate.of(2023, 4, 15),
                        List.of(
                                new OrderItem(products.get(2), 1),
                                new OrderItem(products.get(9), 2)
                        ),
                        "shipped"),
                new Order("O4", customers.get(0), LocalDate.of(2023, 5, 1),
                        List.of(
                                new OrderItem(products.get(3), 1),
                                new OrderItem(products.get(4), 1),
                                new OrderItem(products.get(5), 2)
                        ),
                        "placed"),
                new Order("O5", customers.get(4), LocalDate.of(2023, 5, 5),
                        List.of(
                                new OrderItem(products.get(6), 1)
                        ),
                        "canceled"),
                new Order("O6", customers.get(3), LocalDate.of(2023, 5, 10),
                        List.of(
                                new OrderItem(products.get(8), 1),
                                new OrderItem(products.get(9), 1)
                        ),
                        "placed"),
                new Order("O7", customers.get(2), LocalDate.of(2023, 5, 15),
                        List.of(
                                new OrderItem(products.get(0), 1),
                                new OrderItem(products.get(1), 1)
                        ),
                        "placed"),
                new Order("O8", customers.get(0), LocalDate.of(2023, 5, 20),
                        List.of(
                                new OrderItem(products.get(7), 1)
                        ),
                        "placed")
        );

        //Scenario: Find all electronic products and create a list of their names.
        List<String> electronicProducts = new ArrayList<>();
        electronicProducts = products.stream().
                filter(p->p.category().equals("Electronics")).
                map(Product::name).toList();

        //Scenario: Find the first product in the “Sportswear” category that costs less than $20.
        java.util.Optional<Product> afordableOptionalProduct = products.stream().
                filter(p->p.category().equals("Sportswear")).
                filter(p-> p.price().compareTo(new BigDecimal("20"))<0).
                findFirst();
        afordableOptionalProduct.
                ifPresent(p-> System.out.println("The most affordable product under 20$ is: "+p.name()+ " - $" + p.price()));

        //Scenario: Create a list of order summaries showing order ID and total item count.
        List<OrderSummary> orderSummaries = orders.stream()
                .map(order -> new OrderSummary(
                        order.id(),
                        order.items().stream().mapToInt(OrderItem::quantity).sum()
                ))
                .collect(Collectors.toList());

        orderSummaries.forEach(System.out::println);
    }
}

record OrderSummary(String orderId, int itemCount) {}

record Customer(
        String id,
        String name,
        String email,
        LocalDate registrationDate,
        String tier  // "standard", "premium", or "elite"
) {}

record Product(
        String id,
        String name,
        String category,
        BigDecimal price
) {}
record OrderItem(
        Product product,
        int quantity
) {}
record Order(
        String id,
        Customer customer,
        LocalDate orderDate,
        List<OrderItem> items,
        String status  // "placed", "shipped", "delivered", or "canceled"
) {}