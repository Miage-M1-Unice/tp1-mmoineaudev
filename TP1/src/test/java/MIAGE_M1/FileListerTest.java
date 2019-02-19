package MIAGE_M1;

import MIAGE_M1.filesystem.FileLister;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static MIAGE_M1.view.Console.print;
import static org.junit.Assert.*;
public class FileListerTest {

    @Test
    public void listFilesInDir() {
        print("QUESTION 1");
        try {
            List<String> arbo = FileLister.listFilesInDir(".");//on est a la racine du projet on s'attends a récupérer les fichiers a la racine du projet
            assertTrue(arbo.contains("./src"));
            assertTrue(arbo.contains("./pom.xml"));
            assertTrue(arbo.contains("./readme.md"));
            print(arbo);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void listFilesInDirRecursive() {
        print("QUESTION 2");
        try {
            List<File> arbo = FileLister.getFileListRecursion(".");//on est a la racine du projet on s'attends a récupérer les fichiers a la racine du projet
            for(File file : arbo){
                print(file.getPath());
            }
            //ca veut pas dire grand chose mais ca renvoie bien ce a quoi on s'attend
            //j'ai mis ces fichiers ci car ils ne changeront sans doute pas tant que ce ficher existe
            assertTrue(arbo.contains(new File("./src/test/java/MIAGE_M1/FileListerTest.java") ));
            assertTrue(arbo.contains(new File("./src/main/java/MIAGE_M1/filesystem/FileLister.java") ));

        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void ListWithFilter() {
        print("QUESTION 3", "ListWithFilter");

        File[] fileArray = (new FileLister()).ListWithFilter("./src/main/java/MIAGE_M1/filesystem");
        List<String> list = new ArrayList<>();
        Arrays.stream(fileArray).forEach(l->list.add(l.getPath()));

        fileArray = (new FileLister()).ListWithFilter("./target/classes/MIAGE_M1/view");
        Arrays.stream(fileArray).forEach(l->list.add(l.getPath()));
        print(list);

        for(String s : list) {
            if(!s.endsWith(".java")) fail();//on accepte que les .java
            if(s.contains("/target")) fail();//on accepte que les .java
        }
    }
    @Test
    public void InnerListWithFilter() {
        print("QUESTION 3", "InnerListWithFilter");

        File[] fileArray = (new FileLister()).new InnerFileLister().ListWithFilter("./src/main/java/MIAGE_M1/filesystem");
        List<String> list = new ArrayList<>();
        Arrays.stream(fileArray).forEach(l->list.add(l.getPath()));

        fileArray = (new FileLister()).new InnerFileLister().ListWithFilter("./target/classes/MIAGE_M1/view");
        Arrays.stream(fileArray).forEach(l->list.add(l.getPath()));
        print(list);

        for(String s : list) {
            if(!s.endsWith(".java")) fail();//on accepte que les .java
            if(s.contains("/target")) fail();//on accepte que les .java
        }
    }

    @Test
    public void ListWithAnonymousInnerFilter() {
        print("QUESTION 3", "ListWithAnonymousInnerFilter");
        //Todo faire une interface et un test générique
        File[] fileArray = (new FileLister()).ListWithAnonymousInnerFilter("./src/main/java/MIAGE_M1/filesystem");
        List<String> list = new ArrayList<>();
        Arrays.stream(fileArray).forEach(l->list.add(l.getPath()));

        fileArray = (new FileLister()).ListWithAnonymousInnerFilter("./target/classes/MIAGE_M1/view");
        Arrays.stream(fileArray).forEach(l->list.add(l.getPath()));
        print(list);

        for(String s : list) {
            if(!s.endsWith(".java")) fail();//on accepte que les .java
            if(s.contains("/target")) fail();//on accepte que les .java
        }
    }

}