package service;

import model.*;
import java.util.*;

public class CustomerService {

    private static CustomerService instance;
    private final Map<String, Customer> customerDatabase = new HashMap<>();

    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        try {
            if (customerDatabase.containsKey(email)) {
                System.out.println("This email is included in the database.");
            } else {
                Customer newCustomer = new Customer(firstName, lastName, email);
                customerDatabase.put(email, newCustomer);
                System.out.println("Customer account created successfully.");
            }
        }
        catch (IllegalArgumentException ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public Customer getCustomer(String email) {
        return customerDatabase.get(email);
    }

    public Collection<Customer> getAllCustomers() {
        return customerDatabase.values();
    }

    public void addSampleData() {
        Customer customer1 = new Customer("Joe", "Doe", "joe.d@gmail.com");
        Customer customer2 = new Customer("Mary", "Park", "mary.p@gmail.com");
        Customer customer3 = new Customer("Paul", "Ma", "paul.m@gmail.com");
        customerDatabase.put(customer1.getEmail(), customer1);
        customerDatabase.put(customer2.getEmail(), customer2);
        customerDatabase.put(customer3.getEmail(), customer3);
    }
}
        


