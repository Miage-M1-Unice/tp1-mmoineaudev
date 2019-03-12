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

        ArrayList<File> al = getPaths("/home/asus/Desktop/work/prog_avancee_huet/TP2/target/classes");
        ArrayList<Class> classes = new ArrayList<>();
        MyClassLoader myCL = null;

        try {
            for(File fileInList : al){
                myCL = new MyClassLoader(fileInList.getPath());
                classes.add(myCL.findClass(fileInList.getPath()));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        MIAGE_M1.view.Console.debug(this, "Classes trouvées : ");
        for(Class c : classes){
            MIAGE_M1.view.Console.debug(this, c.getSimpleName());
        }
    }

    private ArrayList<File> getPaths(String aPath) {
        ArrayList<File> al = new ArrayList();
        File classDir = new File(aPath);
        for(File f : classDir.listFiles()) {
            if(f.isDirectory()) al.addAll(getPaths(f.getPath()));
            else al.add(f);
        }
        return al;
    }
}