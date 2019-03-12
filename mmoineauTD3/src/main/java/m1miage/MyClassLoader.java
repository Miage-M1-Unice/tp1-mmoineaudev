package m1miage;

import java.io.*;
import java.nio.file.Files;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.List;

public class MyClassLoader extends SecureClassLoader {

    private ArrayList<String> paths;

    public MyClassLoader(List<String> s) {
        this.paths = new ArrayList<String>(s);
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        MIAGE_M1.view.Console.debug(this, "findClass :"+name);
        byte[] b = new byte[0];
        try {
            b = loadClassData(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class<?> res = super.defineClass(getName(name), b, 0, b.length);
        return res;

    }

    int i=0;
    private byte[] loadClassData(String name) throws ClassNotFoundException, IOException {
        MIAGE_M1.view.Console.debug(this, "loadClassData :"+name);

        File f = this.findFileInPath(name);

        if(f==null) throw new FileNotFoundException(name);
        byte[] res = Files.readAllBytes(f.toPath());
        MIAGE_M1.view.Console.debug(this,"res size : "+res.length);
        return res;
    }

    /**
     * @return null if no file found
     */
    private File findFileInPath(String name) {
        for(String aPath : paths){
            File f = new File(aPath);
            if(f.isDirectory()){
                File theReseachedFile = findInDir(f, name);
                if(theReseachedFile!=null) {
                    MIAGE_M1.view.Console.debug(this,"theReseachedFile = "+f.getName());
                    return theReseachedFile;
                }
            }else{
                if(f.getName()==name) return f;
            }
        }
        return null;
    }

    /**
     * @return null if no file found
     */
    private File findInDir(File f, String name) {
        MIAGE_M1.view.Console.debug(this, "findInDir :"+name + " in " + f.getName());
        for(File aFile : f.listFiles()){
            if(aFile.isDirectory()){
                File anotherFile = findInDir(aFile, name);
                if(anotherFile != null && anotherFile.getName()==name) return anotherFile;
            }else{
                MIAGE_M1.view.Console.print("found : "+aFile.getName());
                if(aFile.getName()==name)
                    return aFile;
            }
        }
        return null;
    }

    private String getName(String name) {

        String res = name.replace("classes/", "")
                .replace(".class", "")
                .replace("/", ".");

        MIAGE_M1.view.Console.debug(this, "getName :"+res);
        return res;
    }

}