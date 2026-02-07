package streams;

import javax.print.attribute.standard.OrientationRequested;
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
        System.out.println("--all electronic products and create a list of their names.");
        List<String> electronicProducts = new ArrayList<>();
        electronicProducts = products.stream().
                filter(p->p.category().equals("Electronics")).
                map(Product::name).toList();

        //Scenario: Find the first product in the “Sportswear” category that costs less than $20.
        System.out.println("\n--Find the first product in the “Sportswear” category that costs less than $20.");
        java.util.Optional<Product> afordableOptionalProduct = products.stream().
                filter(p->p.category().equals("Sportswear")).
                filter(p-> p.price().compareTo(new BigDecimal("20"))<0).
                findFirst();
        afordableOptionalProduct.
                ifPresent(p-> System.out.println("The most affordable product under 20$ is: "+p.name()+ " - $" + p.price()));

        //Scenario: Create a list of order summaries showing order ID and total item count.
        System.out.println("\n--Create a list of order summaries showing order ID and total item count.");
        List<OrderSummary> orderSummeries = orders.stream().map(order -> new OrderSummary(
                order.id(),
                order.items().stream().mapToInt(OrderItem::quantity).sum()
        )).collect(Collectors.toList());
        orderSummeries.forEach(System.out::println);

        //Scenario: Show all products sorted by price (lowest to highest).
        System.out.println("\n--Show all products sorted by category and then by price (lowest to highest)");
        List<Product> sortedProducts = products.stream().sorted(Comparator.comparing(Product::category).thenComparing(Product::price)).
                collect(Collectors.toList());
        sortedProducts.forEach(System.out::println);

        //Scenario: Implement a simple pagination for products, showing the second page with 3 products per page.
        System.out.println("\n--Implement a simple pagination for products, showing the second page with 3 products per page");
        int pageSize = 3;
        int pageNumber = 1;
        List<productShort> secondPage = products.stream().
                skip(pageNumber*pageSize).
                limit(pageSize).
                sorted(Comparator.comparing(Product::price)).
                map(product->new productShort(
                        product.name(),
                        product.price().intValue()
                )).
                collect(Collectors.toList());
        secondPage.forEach(System.out::println);

        //Flattening Nested Collections
        //Scenario: Create a list of all products that have been ordered.
        System.out.println("\n-- Scenario: Create a list of all products that have been ordered.");
        List<Product> orderedProducts = orders.stream().
                flatMap(order -> order.items().stream()). //after we go a stream of orderItems we need to map it to order
                                                                //flatMap() converts each order's list of items into a stream, then flattens all into a single stream
                map(OrderItem::product).
                distinct(). // to remove duplicates
                collect(Collectors.toList());
        orderedProducts.forEach(product -> System.out.println(product.name()));

        /**
         Reducing Elements
         Scenario: Calculate the total revenue from all orders.

         totalRevenue: Use BigDecimal.ZERO as identity and BigDecimal::add to safely sum values in a stream
         in case of Multiply: Use BigDecimal.ONE as identity and BigDecimal::multiply to compute the product
         // Both operations use reduce() to aggregate BigDecimal values immutably and precisely
         */
        System.out.println("\n--Calculate the total revenue from all order: ");
        BigDecimal totalRevenue = orders.stream()
                .flatMap(order -> order.items().stream())
                .map(orderItem -> orderItem.product().price().multiply(BigDecimal.valueOf(orderItem.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total Revenue: $" + totalRevenue);

        //Scenario: Check if any, all, or none of the products are in the “Electronics” category, and find any product over $100.
        /**
         * anyMatch, allMatch, and noneMatch are terminal operations that short-circuit as soon as the result is determined.
         * findAny returns any matching element.
         */
        System.out.println("\n--Check if any, all, or none of the products are in the “Electronics” category, and find any product over $1000.");
        boolean isThereAnyElectronics = products.stream().anyMatch(product -> product.category().equals("Electronics"));
        System.out.println("is there any Electronics products: "+ isThereAnyElectronics );
        boolean AllproductOver100$ = products.stream().
                allMatch(product -> product.price().compareTo(new BigDecimal("100"))>0);
        System.out.println("Is all products are over 100$: "+AllproductOver100$);
        Optional<Product> highPricedProduct = products.stream().
                filter(product->product.price().compareTo(new BigDecimal("1000"))>0).
                findAny();
        highPricedProduct.ifPresent(product -> System.out.println("a product over 1000$: "+ product.name()));

        /**
         *Using peek() for Debugging
         *Scenario: Trace the stream pipeline to debug filtering and mapping.
         *
         * peek() is an intermediate operation for debugging or tracing elements as they flow through the pipeline.
         * Avoid using peek() for side effects in production code.
         */
        System.out.println("\n--Trace the stream pipeline to debug filtering and mapping.");
        List<String> debuggedNames = products.stream()
                .peek(p -> {
                    if(!p.category().equals("Electronics"))
                        System.out.println("Before filter: " + p.name()+" (will not survive)");
                    System.out.println("Before filter: " + p.name());
                })
                .filter(product -> product.category().equals("Electronics"))
                .peek(p -> System.out.println("After filter: " + p.name()+" (survived)"))
                .map(Product::name)
                .peek(name -> System.out.println("Mapped name: " + name))
                .collect(Collectors.toList());

        /**
         * Joining Strings
         * Scenario: Create a comma-separated list of all product categories.
         */
        System.out.println("\n--Create a comma-separated list of all product categories.");
        String allCategories = products.stream().
                                        map(Product::category).distinct().sorted().
                                        collect(Collectors.joining(", "));
        System.out.println(allCategories);

        /**
         * Calculating Summary Statistics
         * Scenario: Calculate statistics for product prices.
         */
        System.out.println("\n--Calculate statistics for product prices.");
        DoubleSummaryStatistics doubleSummaryStatistics = products.stream().map(product -> product.price().doubleValue()).
                collect(Collectors.summarizingDouble(price->price));
        System.out.println("The max price of one product: "+ doubleSummaryStatistics.getMax());
        System.out.println("The avg price of one product: "+ doubleSummaryStatistics.getAverage());
        System.out.println("The count price of one product: "+ doubleSummaryStatistics.getCount());
        System.out.println("The total price of one product: "+ doubleSummaryStatistics.getSum());
        System.out.println("The min price of one product: "+ doubleSummaryStatistics.getMin());

        /**
         * Scenario: Collect product names into an unmodifiable list (Java 10+).
         */
        System.out.println("\n--Scenario: Collect product names into an unmodifiable list (Java 10+)");
        List<String> unmodifiableNames = products.stream().map(Product::name).collect(Collectors.toUnmodifiableList());
//        unmodifiableNames.add("will Throw an exception");

        /**
         * Collecting to Map
         * Scenario: Create a map of product names to their prices.
         */
        Map<String, BigDecimal> productPrices = products.stream()
                .collect(Collectors.toMap(Product::name, Product::price));
        productPrices.forEach((s, bigDecimal) -> System.out.println(s+" --> "+bigDecimal));

        /**
         * Filtering and Mapping in Collectors
         * Scenario: Use filtering and mapping as part of a collector.
         */
        System.out.println("\n--Use filtering and mapping as part of a collector.");
        Map<String, List<String>> electronicsNamesByCategory = products.stream()
                .collect(Collectors.
                        groupingBy( Product::category, Collectors.filtering(
                                                                    product -> product.category().equals("Electronics"),
                                                                            Collectors.mapping(Product::name,Collectors.toList())
                                                                            )
                                    ));
        System.out.println(electronicsNamesByCategory);

        /**
         * Handling Duplicate Keys in toMap()
         * Scenario: Safely collect products by price, merging names if prices are the same.
         * The third argument in toMap() is a merge function to handle duplicate keys.
         */
        System.out.println("\n--Safely collect products by price, merging names if prices are the same.");
        Map<BigDecimal, String> priceToNames = products.stream()
                .collect(Collectors.toMap(
                        Product::price,
                        Product::name,
                        (name1, name2) -> name1 + ", " + name2
                ));
        priceToNames.forEach((bigDecimal, s) -> System.out.println(bigDecimal+" --> "+s));

        /**
         * Downstream Collectors with groupingBy
         * Scenario: Count products in each category and collect their names.
         */
        System.out.println("\n--Scenario: Count products in each category and collect their names.");
        Map<String,Long> productsByCategories = products.stream().collect(Collectors.groupingBy(Product::category,Collectors.counting()));
        productsByCategories.forEach((s, aLong) -> System.out.println("Category "+s+" contains "+aLong+" product"));
        // in example (line 233) we added a filter to filter those products by a specific group "Electronics" next example we do not have any filtring
        Map<String, List<String>> namesByCategory = products.stream()
            .collect(Collectors.groupingBy(Product::category, Collectors.mapping(Product::name, Collectors.toList())));

        /**
         * Partitioning Data
         * Scenario: Divide products into “expensive” (>$100) and “affordable” categories.
         * partitioningBy() separates products into two groups based on the boolean predicate
         */
        Map<Boolean,List<Product>> productsByAffordabolity = products.stream().
                collect(Collectors.partitioningBy(product -> product.price().compareTo(new BigDecimal("100"))>0));
        System.out.println("\n--Divide products into “expensive” (>$100) and “affordable” categories. ");
        System.out.println("Expensive: ");
        List<Product> expensiveOnes = productsByAffordabolity.get(true);
        expensiveOnes.forEach(product -> System.out.println(product.name()+" --> "+product.price()));
        System.out.println("Affordable: ");
        List<Product> affordable = productsByAffordabolity.get(false);
        affordable.forEach(product -> System.out.println(product.name()+" --> "+product.price()));

        /**
         * Using collectingAndThen to transform the result after collection
         * Scenario: Analyze orders by customer tier and order status, showing order count and total items.
         * First level of groupingBy() separates orders by customer tier
         * Second level of groupingBy() further separates by order status
         * collectingAndThen() transforms the collected list into an OrderStats object
         * Within the transformation, a nested stream calculates the total quantity of items
         */
        System.out.println("\n--Analyze orders by customer tier and order status, showing order count and total items.");
        Map<String,Map<String,OrderStats>> orderAnalysisBytierAndStatus =
                orders.stream().collect(
                        Collectors.groupingBy(
                                o -> o.customer().tier(),
                                Collectors.groupingBy(Order::status,
                                        Collectors.collectingAndThen(
                                                Collectors.toList(), orders1 -> new OrderStats(
                                                            orders1.size(),
                                                            orders1.stream().flatMap(order -> order.items().stream()).mapToInt(OrderItem::quantity).sum()
                                                    )
                                        )
                                )
                )
                );
        orderAnalysisBytierAndStatus.forEach((tier, statusMap) -> {
            System.out.println("Customer Tier: " + tier);
            statusMap.forEach((status, stats) -> {
                System.out.println("  Status: " + status);
                System.out.println("    Order Count: " + stats.orderCount());
                System.out.println("    Total Items: " + stats.totalItems());
            });
        });

        /**
         *Parallel Streams
         * Parallel streams allow for concurrent processing of data, which can improve performance on large
         * datasets. We can use parallel streams by calling parallelStream() instead of stream().
         *
         * Internally, parallel streams use the ForkJoinPool to split the workload across
         * multiple threads. This can lead to significant performance improvements
         * for CPU-bound tasks.
         *
         * Scenario: Use parallel streams to find all electronics products ordered by premium customers.
         * What’s happening:
         *
         * parallelStream() processes the data in parallel, potentially using multiple CPU cores
         * The operations filter for premium customers and electronics products
         * distinct() ensures no duplicate products in the result
         */
        System.out.println("\n--parallel streams to find all electronics products ordered by premium customers.");
        List<Product> electronicsOrderedByPremium = orders.parallelStream().
                filter(order -> order.customer().tier().equals("premium")).
                flatMap(order -> order.items().stream()).
                map(OrderItem::product).
                filter(product -> product.category().equals("Electronics")).
                collect(Collectors.toList());
        electronicProducts.forEach(System.out::println);

        /**
         * Teeing Collector
         * The teeing() collector allows you to combine the results of two separate collectors into a
         * single result. This is useful when you want to perform two different aggregations on the same data.
         *
         * Scenario: Calculate both the total revenue and count of products sold in a single stream operation.
         */
        System.out.println("\n--Calculate both the total revenue and count of products sold in a single stream operation.");
        SalesStatistics salesStats = orders.stream()
                .flatMap(order -> order.items().stream())
                .collect(Collectors.teeing(
                        Collectors.mapping(
                                item -> item.product().price().multiply(BigDecimal.valueOf(item.quantity())),
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add) //not to list in this time but to Reducing the extracted big decimal numbers to sum
                        )
                        , Collectors.summingLong(OrderItem::quantity)
                        , SalesStatistics::new
                ));
        System.out.println("Total Revenue: $" + salesStats.totalRevenue());
        System.out.println("Total Products Sold: " + salesStats.totalProductsSold());


    }
}
record SalesStatistics(BigDecimal totalRevenue, long totalProductsSold) {}

record OrderStats(long orderCount, int totalItems) {}

record OrderSummary(String orderId, int itemCount) {}

record productShort(String name, int price) {}

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