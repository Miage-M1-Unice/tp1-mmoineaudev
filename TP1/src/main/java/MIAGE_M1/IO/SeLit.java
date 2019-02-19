package MIAGE_M1.IO;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Compléter la classe SeLit en ajoutant le code pour afficher le contenu d'un fichier texte (ex: SeLit.java) sur la console (System.out) en supprimant les lignes de commentaires commençant par //.
 * Bonus : on pourra, par exemple, utiliser une expression régulière pour lister plus d'un fichier à la fois (par ex : "*.java" listera l'ensemble des sources dans le répertoire courant).
 */
public class SeLit {
    String lecture(Scanner source) {
        String res = "LU: \n";
        while(source.hasNextLine()) {
            String s = source.nextLine();
            if(s.trim().startsWith("//")) continue;
            else if(s.trim().startsWith("*")) continue;
            else if(s.trim().startsWith("/*")) continue;
            else if(s.trim().startsWith("*/")) continue;
            else if(s.contains("//")){
               s= s.split("//")[0];
            }

            res+=s+"\n";
        }
        return res;
    }

}