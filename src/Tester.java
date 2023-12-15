import model.Customer;

import java.sql.SQLOutput;

public class Tester {
    public static void main(String[] args){
//        Customer customer = new Customer("first", "second", "j@domain.com");
//        System.out.println(customer);

        Customer customer = new Customer("first", "second", "email");
        System.out.println(customer);
    }
}
