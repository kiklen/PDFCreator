
package pdfmanager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class TextManager {
    private Scanner lector;
    private FileInputStream archivo;
    
    public TextManager(){}
    
    public void bundle(String fileName){
        try{
            archivo = new FileInputStream(fileName);
            lector = new Scanner(archivo);
        }catch (IOException e){
            System.err.println("File not Found");
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    
    public String readParagraph(){
        String paragraph;
        if (lector != null){
            paragraph = lector.nextLine();
        }else{
            paragraph = "";
        }
        return paragraph;
    }
    
    public boolean isThereAParagraph(){
        return lector.hasNext();
    }
}
