package MIAGE_M1.IO;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

//Travail à faire : modifier la classe SeLit pour rediriger la sortie standard vers un fichier (ex. Output.txt).
public class Secrit extends SeLit{
    @Override
    String lecture(Scanner source) {
        String content = super.lecture(source);
        try{
            System.setOut(new PrintStream(new File("./result.txt")));
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(content);
        return content;
    }
}
