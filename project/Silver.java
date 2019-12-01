
package coe528.project;
import java.io.*;
/**
 *
 * @author 
 * Oliver Mo
 * 500844905
 * Fall 2019 - COE 528 - 09
 */
public class Silver extends Level{
    private final double fee = 20;
    public Silver(){
        super();
        
    }
    @Override
    public double getFee(){
        return fee;
    }
    @Override
    public void currentLevel(Customer c)throws IOException{
        if(c.getBank().getMoney() >=10000){
           Level l = new Gold();
            c.setLevel(l); 
        }
        else if(c.getBank().getMoney() >=20000){
            Level l = new Platinum();
           c.setLevel(l);
        }    
    }
    @Override
    public String toString(){
        return "Silver";
    }
}
