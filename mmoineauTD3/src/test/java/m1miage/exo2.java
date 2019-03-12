package m1miage;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static m1miage.MyClassLoader.getPaths;
import static org.junit.Assert.*;

public class exo2 {


    @Test
    public void myClassLoaderTest(){

        ArrayList<File> al = getPaths();
        ArrayList<Class> classes = new ArrayList<>();
        MyClassLoader myCL = new MyClassLoader(al);

        try {
            for(File f : al){
                classes.add(myCL.findClass(f.getPath()));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        MIAGE_M1.view.Console.debug(this, "Classes trouvées : ");
        for(Class c : classes){
            MIAGE_M1.view.Console.debug(this, c.getSimpleName());
        }
    }
}