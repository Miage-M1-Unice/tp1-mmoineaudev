package MIAGE;

import MIAGE.introspection.AnalyseurDeClasse;
import MIAGE.introspection.ClasseBidon;

import java.io.IOException;
import java.util.Date;

import static MIAGE.introspection.AnalyseurDeClasse.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        boolean ok = false;

        while(!ok) {
            try {
                System.out.print("Entrez le nom d'une classe (ex : java.util.Date): ");
                String nomClasse = "java.util.Date";//litChaineAuClavier();

                analyseClasse(nomClasse);

                System.out.println("ToString : \n"+genericToString(0,new ClasseBidon()));

                ok = true;
            } catch(ClassNotFoundException e) {
                System.out.println("Classe non trouvée.");
            }catch(IOException e) {
                System.out.println("Erreur d'E/S!");
            } catch (IllegalAccessException e) {
                System.out.println("Erreur de permission");
            }
        }
    }
}

