package m1miage;

import java.io.*;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.List;

public class MyClassLoader extends SecureClassLoader {
    private ArrayList<File> path = null;

    public static ArrayList<File> getPaths() {
        ArrayList res = new ArrayList<File>();
        res.add(new File("/home/asus/Desktop/work/prog_avancee_huet/TP2/target/classes/MIAGE/introspection/ClasseBidonBisBis.class"));
        res.add(new File("/home/asus/Desktop/work/prog_avancee_huet/TP2/target/classes/MIAGE/introspection/ClasseBidonBis.class"));
        res.add(new File("/home/asus/Desktop/work/prog_avancee_huet/TP2/target/classes/MIAGE/introspection/ClasseBidon.class"));
        res.add(new File("/home/asus/Desktop/work/prog_avancee_huet/TP2/target/classes/MIAGE/introspection/AnalyseurDeClasse.class"));
        return res;
    }

    public MyClassLoader(ArrayList<File> p) {
        this.path = p;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        MIAGE_M1.view.Console.debug(this, "findClass :"+name);
        byte[] b = new byte[0];
        try {
            b = loadClassData(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (Class<?>) super.defineClass(getName(name), b, 0, b.length);
    }

    private String getName(String name) {

        String res = name.substring(name.indexOf("classes/"), name.length())
                .replace("classes/", "")
                .replace(".class", "")
                .replace("/", ".");

        MIAGE_M1.view.Console.debug(this, "getName :"+res);
        return res;
    }


    int i=0;
    private byte[] loadClassData(String name) throws ClassNotFoundException, IOException {
        MIAGE_M1.view.Console.debug(this, "loadClassData :"+name);

        DataInputStream inputStream = new DataInputStream(new FileInputStream(name));
        List<Byte> resList = new ArrayList<Byte>();
        while(inputStream.available()>0){
            resList.add(inputStream.readByte());
        }
        byte[] res = new byte[resList.size()];
        i=0;
        resList.stream().map(b -> res[i++] = b.byteValue());
        MIAGE_M1.view.Console.debug(this,"res size : "+res.length);
        return res;
    }

}