package MIAGE_M1.IO;

import MIAGE_M1.filesystem.FileLister;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class IOExercise2 {
    public static String readSeLit() throws FileNotFoundException {
        SeLit luiMeme = new SeLit();
        String pathToSeLit = "./src/main/java/"+SeLit.class.getName().replace(".","/")+".java";
        FileReader fileReader = new FileReader(new File(pathToSeLit));
        Scanner scanner = new Scanner(fileReader);
        return luiMeme.lecture(scanner);
    }

    public static String readEveryThing() throws IOException, FileNotFoundException {
        SeLit etLesAutresAussi = new SeLit();
        ArrayList<String> listing = new ArrayList<>();
        for(File file : FileLister.getFileListRecursion("..")){
            if(okNotOk(file.getPath())) listing.add(file.getPath());
        }
        FileReader fileReader = null;
        String res="";

        for (String s : listing) {
            fileReader = new FileReader(new File(s));
            Scanner scanner = new Scanner(fileReader);
            res += etLesAutresAussi.lecture(scanner) + "\n**********\n";
        }

        return res;

    }

    private static boolean okNotOk(String fileName){
        Pattern p = Pattern.compile("^.*\\.java$");
        return p.matcher(fileName).matches();
    }

    public static String readSecrit() throws FileNotFoundException {
        Secrit luiMeme = new Secrit();
        String pathToSecrit = "./src/main/java/"+Secrit.class.getName().replace(".","/")+".java";
        FileReader fileReader = new FileReader(new File(pathToSecrit));
        Scanner scanner = new Scanner(fileReader);
        return luiMeme.lecture(scanner);
    }
}
