
package coe528.project;
import java.io.*;
/**
 *
 * @author
 * Oliver Mo
 * 500844905
 * Fall 2019 - COE 528 - 09
 */
public class Gold extends Level{
    private final double fee = 10;
    public Gold(){
        super();
        
    }

    @Override
    public double getFee(){
        return fee;
    }
    @Override
    public void currentLevel(Customer c)throws IOException{
        if(c.getBank().getMoney() <10000){
            Level l = new Silver();
           c.setLevel(l);
        }
        else if(c.getBank().getMoney() >=20000){
           Level l = new Platinum();
           c.setLevel(l);
        }
    }
    @Override
    public String toString(){
        return "Gold";
    }
}
