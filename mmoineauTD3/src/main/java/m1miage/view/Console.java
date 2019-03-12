package MIAGE_M1.view;

import java.util.List;

public class Console {
    public Console() {
    }

    public static void print(List<String> data){
        if(data!=null) for(String s : data) System.out.println(" *** "+s);
    }
    public static void print(String... data){
        if(data!=null) for(String s : data) System.out.println(" *** "+s);
    }

    public static void print(Object from, String... data){
        System.out.println(from.getClass().getSimpleName()+" : ");
        print(data);
    }

    public static void debug(Object from, String s){
        print("DEBUG from "+from.getClass().getSimpleName(), s );
    }
}
