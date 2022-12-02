package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;

    public Customer(String firstName, String lastName, String email){
        super();
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()){
            throw new IllegalArgumentException("Invalid Email address. Please try again.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    @Override
    public String toString() {
        return "Customer: " + firstName + " " + lastName
                + "\nEmail: " + email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Customer customer = (Customer) obj;
        return Objects.equals(this.getEmail(), customer.getEmail());
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }
}
