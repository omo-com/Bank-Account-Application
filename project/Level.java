
package coe528.project;
import java.io.*;
/**
 *
 * @author
 * Oliver Mo
 * 500844905
 * Fall 2019 - COE 528 - 09
 */
public abstract class Level{

    public Level(){
    }
    //Obtains the Fee accosiated with the Level
    public abstract double getFee();
    //Sets the curreent Level of the Customer's account, given thei're account balance
    public abstract void currentLevel (Customer c)throws IOException;
    //Displays the Customer's Level in a String.
    public abstract String toString();
}
