
package coe528.project;

import java.io.*;
import java.util.*;
/**
 *
 * @author AirDr
 */
public class BankAccount {
    private double money = 100;// Set base amount in a given new account to $100.
    private File file;
    
    /*
    * Similar to Customer Class, paramter file connects both Customer and Bank Account, giving both access
    * to their file.
    * The bank account class reads the customer's txt file to obtian their money.
    */
    public BankAccount(File file) throws IOException{
        this.file = file;
        BufferedReader in = new BufferedReader(new FileReader(this.file));
        in.readLine();
        in.readLine();
        in.readLine();
        this.money = Double.parseDouble(in.readLine());
        in.close();
    }
    /*METHOD:
    * This method is used by the customer to deposit, withdraw, or make online purchases.
    * Given the amount disired, money is updated.
    * Note: Withdraw and Online Purchase sets the negative price when usign setMoney() Method.
    */
    public void setMoney(double amount) throws IOException{
        this.money += amount;
        ArrayList<String> info = new ArrayList();
        BufferedReader in = new BufferedReader(new FileReader(file));
        for(int i =0; i<5;i++){

        info.add(in.readLine());
        }
        in.close();
        
        info.set(3, String.valueOf(this.money));
       
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        for(int i =0; i<5;i++){
           
        out.write(info.get(i)); 
        out.newLine();
        }
        out.close();
    }
    /*Getter Methods
    * Require getMonry Method for Level classes Silver, Gold, and Platinum
    *
    * Recall, since Custoemr has access to the file already, do not need a getFile method.
    */
    public double getMoney(){
        return this.money;
    }
    
     
}
