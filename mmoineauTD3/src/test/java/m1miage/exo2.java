package m1miage;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class exo2 {


    @Test
    public void myClassLoaderTest() throws IOException {
        MyClassLoader myCL = new MyClassLoader(getPaths());
        MIAGE_M1.view.Console.debug(this, "Classes trouvées : ");
        try {
            Class<?> aClass = myCL.findClass("ClasseBidonBisBis.class");
        } catch (ClassNotFoundException e) {
            fail();
        }

    }

    private ArrayList<String> getPaths() {
        ArrayList<String> al = new ArrayList();
        al.add("/home/asus/Desktop/work/prog_avancee_huet/TP2/target/classes");
        al.add("/home/asus/Desktop/work/prog_avancee_huet/TP1/target/classes");
        return al;
    }
}