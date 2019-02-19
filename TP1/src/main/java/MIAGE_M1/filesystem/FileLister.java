package MIAGE_M1.filesystem;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Création d'un programme qui liste le contenu d'un répertoire, avec filtrage
 */
//TODO refactor to non static
public class FileLister implements FilenameFilter {
    public FileLister(){
        //rien
    }

    //listing des fichiers récursif, avec un filtre
    public File[] ListWithFilter(String path){
        File root = new File(path);
        return root.listFiles(this);//uses accept
    }
    @Override
    public boolean accept(File dir, String s) {
        Pattern p = Pattern.compile("^.*\\.java$");
        return p.matcher(s).matches();
    }

    //listing des fichiers récursif, avec un filtre
    public File[] ListWithAnonymousInnerFilter(String path){
        FilenameFilter inner = new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                return false;
            }
        };

        File root = new File(path);
        return root.listFiles(inner);//uses accept
    }

    //listing des fichiers récursif
    public static List<File> getFileListRecursion(String path) throws IOException {
        return getFileListRecursionF(new File(path));
    }
    private static List<File> getFileListRecursionF(File f) throws IOException {
        List<File> list = new ArrayList<File>();
        if(f.isDirectory()) {
            ls(f.getPath()).map(aPath -> aPath.toFile()).forEach(file -> {
                try {
                    list.addAll(getFileListRecursionF(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }else list.add(f);
        return list;
    }

    //listing des fichiers non récursif
    public static List<String> listFilesInDir(String path) throws IOException {
        ArrayList<String> list = new ArrayList();
        ls(path).map(aPath -> aPath.toFile()).forEach(file -> list.add(file.getPath()));
        return list;
    }
    private static Stream<Path> ls(String path) throws IOException {
        File dir = new File(path);
        return Files.list(dir.toPath());
    }


    //on aurait pu hériter ces méthodes et cacher les autres mais c'est un exercice
    public class InnerFileLister implements FilenameFilter{
        public InnerFileLister(){
            //rien
        }
        @Override
        public boolean accept(File dir, String s) {
            if(s.endsWith(".java"))
                return true;
            else return false;
        }
        //listing des fichiers récursif, avec un filtre
        public File[] ListWithFilter(String path){
            File root = new File(path);
            return root.listFiles(this);//uses accept
        }
    }
}