
package coe528.project;

import java.io.*;
import java.util.*;
/**
 *
 * @author
 * Oliver Mo
 * 500844905
 * Fall 2019 - COE 528 - 09
 */
public class Customer {
    private String username;
    private String password;
    private BankAccount account;
    private final String role;
    private Level level;
    private File file;
    
    /*
    * Constructor takes a File as parameters because a Customer has their own account file.
    * Thus, this ensures they have/access to an existing file.
    * 
    * Can not simpely assume the customer has a file, this ensures that the creation of customer and gives
    * BankAccount class access to such file.
    */
    public Customer(File file)throws IOException{
        this.file = file;
        BufferedReader in = new BufferedReader(new FileReader(file));
        this.username = in.readLine();
        this.password = in.readLine();
        this.role = in.readLine();
        this.account = new BankAccount(file);
         in.close();
         this.level = new Silver();
    }
    /*
    * Obtains the account balaence to allow customers access to their funds.
    */
    public double getBalance(){
        return this.account.getMoney();
    }
    
    /*
      * Performs deposit for user as long as the balance is greater than deposit amount.
      * Although the use can input 0, this has no effect on their balance.
     */
    public void deposit(double amount)throws IOException{
        if(amount>=0){
        this.account.setMoney(amount);
        currentLevel();
        }
    }
    /*
      * Performs withdraw for user as long as the balance is greater than withdraw amount.
      * Although the use can input 0, this has no effect on their balance.
     */
     public void withdraw(double amount) throws IOException{
         if(amount>=0 && getBalance()>=amount){
        this.account.setMoney(-amount);
        currentLevel();
         }
    }
     
     /*
      * Performs Online purchase for user when the price of the item is greater or equal to $50
     */
     public void onlinePurchase(double price)throws IOException{
         if(price>=50){
         //Performs online purchases with the fee for their account level.
         this.account.setMoney(-(price + getFee()));
         currentLevel();
         }
     }
     
     /*Method:
     * setLevel is used to constainly obtain the account's given level per operation of balance updates.
     * Ex, Depositing $10000 into a silver account, will change the user's level to Gold.
     *     Therefore, we will update or obtain the level that is written into the user's txt file.
     */
     public void setLevel(Level level)throws IOException{
         this.level = level;
        
        //Reads the user's file
        BufferedReader in = new BufferedReader(new FileReader(file));
        ArrayList<String> info = new ArrayList();
        
        for(int i =0; i<5;i++){
        info.add(in.readLine());
        }
        in.close();
        
        info.set(4, toString());// Set The 4th line (level), with the toString().
        //Rewrites the user's file.
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        for(int i =0; i<5;i++){
        out.write(info.get(i));
        out.newLine();
        }
        out.close();
     }
     
     //Getter Methods to obtain specific data.
     public BankAccount getBank(){
         return account;
     }
     public Level getLevel()throws IOException{
         currentLevel();
         return this.level;
     }
     public String getUser(){
         return this.username;
     }
     public String getPass(){
         return this.password;
     }
     public File getFile(){
         return this.file;
     }
     public String getRole(){
         return this.role;
     }
     
     /*
     * From State Design Pattern
     */
     public double getFee(){
         return this.level.getFee();
     }
     public void currentLevel()throws IOException{
         this.level.currentLevel(this);
     }
     public String toString(){
         return this.level.toString();
     }
     
}
