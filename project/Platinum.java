
package coe528.project;
import java.io.*;
/**
 *
 * @author
 * Oliver Mo
 * 500844905
 * Fall 2019 - COE 528 - 09
 */
public class Platinum extends Level{
    private final double fee = 0;
    public Platinum(){
        super();
    }
    
    @Override
    public double getFee(){
        return fee;
    }
    
    /*
    * Checks the Account Balance and then runs setLevel() from Customer class to change the Level.
    */
    @Override
    public void currentLevel(Customer c)throws IOException{
        if(c.getBank().getMoney() <10000){
           Level l = new Silver();
           c.setLevel(l);
        }
        else if(c.getBank().getMoney() <20000){
           Level l = new Gold();
           c.setLevel(l);
        }    
    }
    @Override
    public String toString(){
        return "Platinum";
    }
}
