package MIAGE.introspection;

import org.junit.Test;

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


}