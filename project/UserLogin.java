
package coe528.project;

import java.io.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;

/**
 *
 * @author
 * Oliver Mo
 * 500844905
 * Fall 2019 - COE 528 - 09
 */
public class UserLogin extends Application {
    private String user;
    private String pass;
    private double balance;
    private Manager manager = new Manager();
    
    @Override
    public void start(Stage primaryStage) {
    //Setting Text (Display) and Text Field (Input) 
    Text name = new Text("Bank of Mo"); 
    name.setTranslateX(0);
    name.setTranslateY(-100);
    Text userName = new Text(0,-25,"Username");  
    userName.setTranslateX(0);
    userName.setTranslateY(-25);
    Text password = new Text(0,25, "Password");
    password.setTranslateX(0);
    password.setTranslateY(25);
        
    TextField userText = new TextField();
    userText.setTranslateX(0);
    userText.setTranslateY(0);
    TextField passText = new TextField();
    passText.setTranslateX(0);
    passText.setTranslateY(50);
    
    /*Button used to login to an account
    *
    *The Manager's login credentals by default are. 
    *Username: admin
    *Password: admin
    */
    Button btn = new Button();  
    btn.setTranslateX(0);
    btn.setTranslateY(100);
        btn.setText("Login");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                user = userText.getText();
                pass = passText.getText();
                
                try{
                //Clears any lingeraing Customer ArrayList since multiple log ins will continously reload Customer ArrayList.
                manager.getAllCustomers().clear();
                
                //Extracts all Customers from the text files and places them into an array
                manager.loadCustomers();
                
                //Refer to Line 554 for details on this method.
                checkUser(user,pass,primaryStage);
                }
                catch(IOException e)
                {
                }
            }
        });
        //Creates pane elements
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        root.getChildren().add(userText);
        root.getChildren().add(passText);
        root.getChildren().add(name);
        root.getChildren().add(userName);
        root.getChildren().add(password);
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /*METHOD:
    *Opens Scene for Manager
    */
    public void manager(Stage primaryStage) {
    //Setting Text (Display) and Text Field (Input) 
    Text bankName = new Text("Welcome "+manager.getRole()+", " + manager.getUser()); 
    bankName.setTranslateX(-50);
    bankName.setTranslateY(-90);
    //Opens new Scene to addCustomers
    Button btn = new Button();  
    btn.setTranslateX(-50);
    btn.setTranslateY(0);
        btn.setText("Add Customer");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addCustomer(primaryStage);
            }
        });
    //Opens new Scene to deleteCustomers
    Button btn1 = new Button();  
    btn1.setTranslateX(50);
    btn1.setTranslateY(0);
        btn1.setText("Delete Cusotmer");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteCustomer(primaryStage);
            }
        });
    //Logout of Manager, returns to login screen.
    Button logout = new Button();  
    logout.setTranslateX(0);
    logout.setTranslateY(80);
        logout.setText("Logout");
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                start(primaryStage);
            }
        });
        //Creates pane elements
        StackPane root = new StackPane();
               Scene scene = new Scene(root, 250, 200);
               root.getChildren().add(bankName);
               root.getChildren().add(btn);
               root.getChildren().add(btn1);
               root.getChildren().add(logout);
        primaryStage.setTitle("Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /*MANAGER METHOD:
    * In a new Scene, creates a new Customer file given a username and password.
    */
    public void addCustomer(Stage primaryStage){
        //Setting Text (Display) and Text Field (Input) 
        Text name = new Text("Enter Customer's Username"); 
        name.setTranslateX(0);
        name.setTranslateY(-40);
        
        Text pText = new Text("Enter Customer's Password"); 
        pText.setTranslateX(0);
        pText.setTranslateY(10);
        
        TextField userText = new TextField();
        userText.setTranslateX(0);
        userText.setTranslateY(-20);
        
        TextField passText = new TextField();
        passText.setTranslateX(0);
        passText.setTranslateY(30);
        
        //Button use to perform addCustomer(user,pass) method from Manager Class, returns to Mananger Scene
        Button add = new Button();  
        add.setTranslateX(0);
        add.setTranslateY(75);
        add.setText("Add Customer");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                user = userText.getText();
                pass = passText.getText();
                try{
                manager.addCustomer(user, pass);
                //Refer to line 609 for details on Success method.
                success();
                }catch (IOException | NullPointerException e)
                { 
                    error(user, "Username already in use.");
                }
                manager(primaryStage);
            }
        });
        //Returns Scene to Manager Scene.
        Button back = new Button();  
        back.setTranslateX(70);
        back.setTranslateY(-80);
        back.setText("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {        
                manager(primaryStage);
            }
        });
        //Creates pane elements
        StackPane root = new StackPane();
               Scene scene = new Scene(root, 200, 200);
               root.getChildren().add(name);
               root.getChildren().add(pText);
               root.getChildren().add(userText);
               root.getChildren().add(passText);
               root.getChildren().add(add);
               root.getChildren().add(back);
        primaryStage.setTitle("Manger: Adding Customer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void deleteCustomer(Stage primaryStage){
        //Setting Text (Display) and Text Field (Input) 
        Text name = new Text("Enter Customer's Username"); 
        name.setTranslateX(0);
        name.setTranslateY(-25);
        
        TextField userText = new TextField();
        userText.setTranslateX(0);
        userText.setTranslateY(15);
        //Button use to perform deleteCustomer(user) method from Manager Class, returns to Mananger Scene
        Button delete = new Button();  
        delete.setTranslateX(0);
        delete.setTranslateY(50);
        delete.setText("Delete Customer");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                user = userText.getText();
                manager.deleteCustomer(user);
                success();
                }catch (IOException | NullPointerException e)
                {
                    error(user, "Does not Exist");
                }
                manager(primaryStage);
            }
        });
        //Returns Scene to Manager Scene.
        Button back = new Button();  
        back.setTranslateX(75);
        back.setTranslateY(-50);
        back.setText("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                manager(primaryStage);
            }
        });
        //Creates pane elements
        StackPane root = new StackPane();
               Scene scene = new Scene(root, 200, 130);
               root.getChildren().add(name);
               root.getChildren().add(userText);
               root.getChildren().add(delete);
               root.getChildren().add(back);
        primaryStage.setTitle("Manger: Deleting Customer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
   public void customer(Stage primaryStage){
    //Setting Text (Display) and Text Field (Input) 
    try{
    Text name = new Text("Welcome "+manager.getCustomer(user).getRole() +", "+ user); 
    name.setTranslateX(-50);
    name.setTranslateY(-90);
    
    Text level = new Text("Current Level: " + manager.getCustomer(user).getLevel());
    level.setTranslateX(-50);
    level.setTranslateY(-70);
    //Button opens new scene for getBalance.
    Button getB = new Button();  
    getB.setTranslateX(0);
    getB.setTranslateY(-50);
        getB.setText("Get Balance");
        getB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               getBalance(primaryStage);
            }
        });
    //Button opens new scene for deposit.
    Button deposit = new Button();  
    deposit.setTranslateX(0);
    deposit.setTranslateY(-20);
        deposit.setText("Deposit");
        deposit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            deposit(primaryStage);
            }
        });
    //Button opens new scene for withdraw.
    Button withdraw = new Button();  
    withdraw.setTranslateX(0);
    withdraw.setTranslateY(10);
        withdraw.setText("Withdraw");
        withdraw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                withdraw(primaryStage);
            }
        });
    //Button opens new scene for onlinePurchase
    Button onlinePurchase = new Button();  
    onlinePurchase.setTranslateX(0);
    onlinePurchase.setTranslateY(40);
        onlinePurchase.setText("Online Purchase");
        onlinePurchase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            onlinePurchase(primaryStage);
                
            }
        });
    //Button opens new scene, returning to login scene.    
    Button logout = new Button();  
    logout.setTranslateX(0);
    logout.setTranslateY(80);
        logout.setText("Logout");
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                start(primaryStage);
            }
        });
        //Creates pane elements
        StackPane root = new StackPane();
               Scene scene = new Scene(root, 250, 200);
               root.getChildren().add(name);
               root.getChildren().add(level);
               root.getChildren().add(getB);
               root.getChildren().add(deposit);
               root.getChildren().add(withdraw);
               root.getChildren().add(onlinePurchase);
               root.getChildren().add(logout);
        primaryStage.setTitle("Customer: "+user);
        primaryStage.setScene(scene);
        primaryStage.show();
       }
    catch(IOException e)
    {  
    }    
   }
   /*METHOD:
   *Button performs getBalance() method from Customer Class.
   */
   public void getBalance(Stage primaryStage){
       try{
            balance = this.manager.getCustomer(user).getBalance();//
       }catch(IOException e)
       {
       }
        Text amount = new Text(user +"'s Balance: $" + balance); 
    
    //Setting Text (Display) and Text Field (Input)     
    amount.setTranslateX(0);
    amount.setTranslateY(-30);
    //Button Back to go to previous page (Customer)
    Button back = new Button();  
        back.setTranslateX(0);
        back.setTranslateY(30);
        back.setText("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                customer(primaryStage);
            }
        });
        //Creates pane elements
        StackPane root = new StackPane();
               Scene scene = new Scene(root, 250, 100);
               root.getChildren().add(amount);
               root.getChildren().add(back);
        primaryStage.setTitle(user+" Balance:" + balance);
        primaryStage.setScene(scene);
        primaryStage.show();
   }
   /*METHOD:
   *Opene new Scene for deposit
   */
   public void deposit(Stage primaryStage){
    //Setting Text (Display) and Text Field (Input) 
    Text amount = new Text("Enter Amount"); 
    amount.setTranslateX(0);
    amount.setTranslateY(-35);
    TextField money = new TextField();
    money.setTranslateX(0);
    money.setTranslateY(0);
    //Button performs depost(balance) form Customer Class
    Button deposit = new Button();  
        deposit.setTranslateX(0);
        deposit.setTranslateY(25);
        deposit.setText("Deposit");
        deposit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                balance = Double.parseDouble(money.getText());
                if(balance<0){
                   throw new NumberFormatException("");
                }
                manager.getCustomer(user).deposit(balance);
                success();
                }catch( IOException | NumberFormatException e)
                {
                  error(money.getText(), "Only Positive Numbermical Values");
                }
                customer(primaryStage);
            }
        });
    //Button Back to go to previous page (Customer)    
    Button back = new Button();  
        back.setTranslateX(0);
        back.setTranslateY(50);
        back.setText("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                customer(primaryStage);
            }
        });
        //Creates pane elements
        StackPane root = new StackPane();
               Scene scene = new Scene(root, 250, 150);
               root.getChildren().add(amount);
                root.getChildren().add(money);
                root.getChildren().add(deposit);
               root.getChildren().add(back);  
        primaryStage.setTitle(user+ " Depositing");
        primaryStage.setScene(scene);
        primaryStage.show();
   }
   /*METHOD:
   *Opens new Scene for Withdraws
   */
   public void withdraw(Stage primaryStage){
    //Setting Text (Display) and Text Field (Input) 
    Text amount = new Text("Enter Amount"); 
    amount.setTranslateX(0);
    amount.setTranslateY(-35);
    TextField money = new TextField();
    money.setTranslateX(0);
    money.setTranslateY(0);
    //Button performs withdraw(balance) form Customer Class
    Button withdraw = new Button();  
        withdraw.setTranslateX(0);
        withdraw.setTranslateY(25);
        withdraw.setText("Withdraw");
        withdraw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                balance = Double.parseDouble(money.getText());
                if(balance<0|| manager.getCustomer(user).getBalance()<balance){
                   throw new NumberFormatException("");//Account must have atleast the total transaction amount: price
                }
                manager.getCustomer(user).withdraw(balance);
                success();
                }catch( IOException | NumberFormatException e)
                {
                  error(money.getText(), "Only positive amounts less or equal to balance");
                }
                customer(primaryStage);
            }
        });
     //Button Back to go to previous page (Customer)   
    Button back = new Button();  
        back.setTranslateX(0);
        back.setTranslateY(50);
        back.setText("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                customer(primaryStage);
            }
        });
        //Creates pane elements
        StackPane root = new StackPane();
               Scene scene = new Scene(root, 250, 150);
               root.getChildren().add(amount);
               root.getChildren().add(money);
               root.getChildren().add(withdraw);
               root.getChildren().add(back);
        primaryStage.setTitle(user+ " Withdrawing");
        primaryStage.setScene(scene);
        primaryStage.show();
   }
   
   /*METHOD:
   *Opens new Scene for Online Purchasing.
   */
   public void onlinePurchase(Stage primaryStage){
    //Setting Text (Display) and Text Field (Input) 
    Text amount = new Text("Enter Amount"); 
    amount.setTranslateX(0);
    amount.setTranslateY(-35);
    TextField money = new TextField();
    money.setTranslateX(0);
    money.setTranslateY(0);
    
    //Online Purchase button make purchases which are over $50.
    Button onlinePurchase = new Button();  
        onlinePurchase.setTranslateX(0);
        onlinePurchase.setTranslateY(25);
        onlinePurchase.setText("Online Purchase");
        onlinePurchase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
              try{
                balance = Double.parseDouble(money.getText());
                if(balance<50|| manager.getCustomer(user).getBalance()<(balance + manager.getCustomer(user).getFee())){
                   throw new NumberFormatException("");//Account must have atleast the total transaction amount: price + fee
                }
                manager.getCustomer(user).onlinePurchase(balance);
                success();
                }catch( IOException | NumberFormatException e)
                {
                  error(money.getText(), "Purchase Must be $50 or more, excluding fee");
                }
                customer(primaryStage);
            }
        });
    //Button Back to go to previous page (Customer)
    Button back = new Button();  
        back.setTranslateX(0);
        back.setTranslateY(50);
        back.setText("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                customer(primaryStage);
            }
        });
        //Creates pane elements
        StackPane root = new StackPane();
               Scene scene = new Scene(root, 250, 150);
               root.getChildren().add(amount);
               root.getChildren().add(money);
               root.getChildren().add(onlinePurchase);
               root.getChildren().add(back);
        primaryStage.setTitle(user+ " making Online Purchase");
        primaryStage.setScene(scene);
        primaryStage.show();
   }
   
   /*METHOD
   *Checks if the input for username and password is either a Manager or Customer.
   */
   public void checkUser(String u, String p, Stage primaryStage)throws IOException{
        if(u.equals(manager.getUser())){
            if(p.equals(manager.getPass())){
            System.out.println("3");
            manager(primaryStage);
            return;//end method since admin logs in.
            }
            else{
            error(p);
            return;//end method since admin tries to log in.
            }
        }
        for(int i = 0; i<manager.getAllCustomers().size();i++){
            Customer c = (Customer) manager.getAllCustomers().get(i);
            BufferedReader in = new BufferedReader(new FileReader(c.getFile()));
        if(in.readLine().equals(u)){
            if(in.readLine().equals(p)){
               customer(primaryStage); 
               return;//end method since customer logs in.
            }
            else{
                error(p);
                return;//end method since customer tries to log in.
            }
        }
        in.close();
        }
        error(u);
   }
   
   /*METHOD:
   *Genral error opening window.
   */
   public void error(String input){
       Stage stage = new Stage();
               
     stage.setTitle("Error"); // Set the stage title
    // Set a scene with a button in the stage
    stage.setScene(new Scene(new Button("Invalid Input: " + input), 200, 60));        
    stage.show(); // Display the stage
   }
   /*METHOD:
    *Genral error opening window for specific operations.
    */
   public void error(String input, String message){
       Stage stage = new Stage();
               
     stage.setTitle("Error"); // Set the stage title
    // Set a scene with a button in the stage
    stage.setScene(new Scene(new Button("Invalid Input: " + input+"\n"+ message), 300, 60));        
    stage.show(); // Display the stage
   }
   /*METHOD:
   *Creates new window. Says Operation successful if request is processed.
   */
   public void success(){
       Stage stage = new Stage();
               
     stage.setTitle("Success"); // Set the stage title
    // Set a scene with a button in the stage
    stage.setScene(new Scene(new Button("Operation Successful"), 200, 60));        
    stage.show(); // Display the stage
   }
   
   /*
   * Main method that starts the program.
   */
    public static void main(String[] args) {
        launch(args);
    }
    
}
