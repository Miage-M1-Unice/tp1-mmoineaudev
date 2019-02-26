package MIAGE.introspection;

import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.fail;
//pas vraiment des tests mais ça permet de tout lancer avec un coup de mvn test et de découper en questions/exercice
public class AnalyseurDeClasseTest {
    private AnalyseurDeClasse instance = new AnalyseurDeClasse();
    @Test
    public void analyseClasse() {
        try {
            System.out.print("Entrez le nom d'une classe (ex : java.util.Date): ");
            String nomClasse = "java.util.Date";//litChaineAuClavier();
            System.out.println("analyseClasse with "+nomClasse);
            instance.analyseClasse(nomClasse);
        } catch (Exception e) {
            System.out.println("oupsie ! \n"+e.getMessage());
            fail();
        }
    }

    @Test
    public void genericToString() {
        String nomClasse = "java.util.Date";//litChaineAuClavier();
        System.out.println("genericToString with "+nomClasse);
        try {
            System.out.println("ToString : \n"+instance.genericToString(0,new ClasseBidon()));
        } catch (Exception e) {
            System.out.println("oupsie ! \n"+e.getMessage());
            fail();
        }
    }
    @Test
    public void givenTest() {
        try {
            System.out.println(instance.genericToString(0,new Point(12,24)));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Polygon pol = new Polygon(new int[]{10, 20, 30}, new int[]{20,30, 40}, 3);
        pol.getBounds();
        try {
            System.out.println(instance.genericToString(0, pol));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}