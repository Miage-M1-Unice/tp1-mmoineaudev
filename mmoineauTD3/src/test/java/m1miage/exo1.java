package m1miage;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * URLClassLoader
 * L'URLClassLoader est un classloader standard en Java qui permet de préciser un ou plusieurs chemins où trouver une classe.
 * Il supporte les répertoires mais aussi les fichiers jar.
 *
 * Créer 2 projets dans votre IDE et dans le premier créez une classe Test.
 * Dans le deuxième, créez une classe Main qui essaie de charger la classe du premier projet et échoue.
 * Quelle est l'exception levée ?
 *
 * Dans la classe Main, créez un URLClassLoader et spécifiez le chemin permettant de trouver la classe du premiere projet.
 * Vérifiez que cela fonctionne.
 */
public class exo1
{
    /**
     * Créer 2 projets dans votre IDE et dans le premier créez une classe Test.
     * Dans le deuxième, créez une classe Main qui essaie de charger la classe du premier projet et échoue.
     * Quelle est l'exception levée ?
     */
    @Test
    public void shouldFail()
    {
        ClassLoader ucl = this.getClass().getClassLoader();

        /*
        on essaie de récupérer
        file:///home/asus/Desktop/work/prog_avancee_huet/TP2/target/classes/MIAGE/introspection/AnalyseurDeClasse.class
         */
        try {
            ucl.loadClass("MIAGE.introspection.AnalyseurDeClasse");
        } catch (ClassNotFoundException e) {
            assertTrue( true );
        }

    }

    @Test
    public void shouldSucceed()
    {
        URL url = null;
        try {
            url = new URL("file:///home/asus/Desktop/work/prog_avancee_huet/TP2/target/TP2-1.0-SNAPSHOT.jar");
        } catch (MalformedURLException e) {
            fail();
        }
        URL[] urlArray = {url};
        URLClassLoader ucl = new URLClassLoader(urlArray, this.getClass().getClassLoader());

        /*
        on essaie de récupérer
        MIAGE/introspection/AnalyseurDeClasse.class
         */
        try {
            Class classLoaded = ucl.loadClass("MIAGE.introspection.AnalyseurDeClasse");
            Method[] m = classLoaded.getMethods();
            System.out.println("From TP2 : méthodes de MIAGE.introspection.AnalyseurDeClasse");
            for(Method aMethod : m){
                System.out.println(aMethod.getName());
            }
        } catch (ClassNotFoundException e) {
            fail();
        }

        assertTrue( true );
    }
}
