package service;

import model.*;
import java.util.*;

public class CustomerService {
    private static CustomerService cs = new CustomerService();
    private Map<String, Customer> mapOfCustomer = new HashMap<String, Customer>();

    public static CustomerService getInstance(){
        return cs;
    }
    public void addCustomer(String email, String firstName, String lastName) {
        try {
            if (mapOfCustomer.containsKey(email)) {
                System.out.println("This email is included in the database.");
            }
            Customer newCustomer = new Customer(firstName, lastName, email);
            mapOfCustomer.put(email, newCustomer);
        }
        catch (IllegalArgumentException ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public Customer getCustomer(String customerEmail) {
        return mapOfCustomer.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {
        return mapOfCustomer.values();
    }
}
        


