package m1miage;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

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

        //FROM TP1
        ucl.loadClass()

        assertTrue( true );
    }
}
