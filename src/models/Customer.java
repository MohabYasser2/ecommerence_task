package models;


public class Customer {
    private String name;
    private double balance;
    
    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
    
    public String getName() { 

        return name; 
    }
    
    public double getBalance() { 

        return balance; 
    }
    
    public void setBalance(double balance) { 

        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        } //Assuming there is no credit system in place
         // and the balance should not go below zero.
        this.balance = balance; 
    }
    
    public boolean hasEnoughBalance(double amount) {

        return balance >=amount;
    }
    
    public void deductBalance(double amount) {

        if (hasEnoughBalance( amount )) {
            balance -=amount;
        }
    }
    
    @Override
    public String toString() {
        
        return String.format("Customer: %s, Balance: %.2f", name, balance);
    }
}
