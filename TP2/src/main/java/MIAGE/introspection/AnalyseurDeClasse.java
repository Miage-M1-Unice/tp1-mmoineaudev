package MIAGE.introspection;
/**
 * @version 1.00 23 Mars 2001
 * @author Michel Buffa
 * Inspiré par la classe Reflectiontest.java de
 * Cay S. Horstmann & Gary Cornell, publiée dans le livre Core Java, Sun Press
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * Implémentation des questions du TP2
 */

public class AnalyseurDeClasse {

    public static void analyseClasse(String nomClasse) throws ClassNotFoundException, IOException {
        // Récupération d'un objet de type Class correspondant au nom passé en paramètres

        Class cl = getClasse(nomClasse);
        afficheEnTeteClasse(cl);

        System.out.println();
        afficheAttributs(cl);

        System.out.println();
        afficheConstructeurs(cl);

        System.out.println();
        afficheMethodes(cl);

        // L'accolade fermante de fin de classe !
        System.out.println("}");
    }


    /** Retourne la classe dont le nom est passé en paramètre */
    public static Class getClasse(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }

    /** Cette méthode affiche par ex "public class Toto extends Tata implements Titi, Tutu {" */
    public static void afficheEnTeteClasse(Class cl) {
        //  Affichage du modifier et du nom de la classe
        AnnotatedType[] interfaces = cl.getAnnotatedInterfaces();
        AnnotatedType superclass = cl.getAnnotatedSuperclass();
        String classname = cl.getSimpleName();
        String packagename = cl.getName().replace("."+classname,"");
        String visibility  =  Modifier.toString(cl.getModifiers());

        System.out.println("package "+packagename+";");
        System.out.println(visibility + " " + classname +
                ((interfaces.length>0)?(concatInterfaces(interfaces)):"")+
                ((superclass!=null)? " extends "+superclass.getType().getTypeName():""));

        // Enfin, l'accolade ouvrante !
        System.out.print(" {\n");
    }

    private static String concatInterfaces(AnnotatedType[] interfaces) {
        String res = " implements ";
        for(AnnotatedType i : interfaces) res+=i.getType().getTypeName()+", ";
        if(res.length()>0) res = res.substring(0, res.length()-1);//on enleve la derniere virgule
        return res;
    }

    public static void afficheAttributs(Class cl) {
        String res = "// Attributs  \n";

        Field[] fields = cl.getFields();
        for (Field f : fields){
            res+=f.getType()+ " " + f.getName()+";\n";
        }
        System.out.println(res);
    }

    public static void afficheConstructeurs(Class cl) {
        String res = "// Constructeurs  \n";


        Constructor[] constructors = cl.getConstructors();

        for(Constructor c : constructors) {
            res += Modifier.toString(c.getModifiers())+ " " +c.getName() +" ;\n";
        }

        System.out.println(res);
    }

    public static void afficheMethodes(Class cl) {
        String res = "// Methodes  \n";

        Method[] methods = cl.getMethods();

        for(Method m : methods) {

            res += Modifier.toString(m.getModifiers())+ " " + m.getName()+" ( ";
            for (Type t : m.getGenericParameterTypes()) res+= t.getTypeName()+ ",";
            res = res.substring(0, res.length()-1);
            res+= ") ;\n";
        }

        System.out.println(res);
    }


    public static String litChaineAuClavier() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    //Une méthode toString() générique
    public static String genericToString(Object o) throws ClassNotFoundException, IllegalAccessException {
        //toString qui prend en paramètre un objet de type Object et affiche la valeur de chacun de ses champs.
        // Attention, si les champs sont des références sur d'autres objets on descendra en profondeur pour
        // afficher "récursivement" leur valeur également.
        String res = o.getClass().getName()+" {";
        Class oClass = Class.forName(o.getClass().getName());
        Set<Field> fields = new HashSet<Field>(Arrays.asList(oClass.getFields()));
        //Set<Field> declared = new HashSet<Field>(Arrays.asList(oClass.getDeclaredFields()));
        fields.addAll(declared);
        for (Field f : fields){
            f.setAccessible(true);
            Object value = f.get(o);
            if(isWrapperType(value.getClass())){
                res+= f.getType().getName() + " "+ f.getName()+" = " +value+ ";\n";
            }
            else if(f.getType().isArray()){
                res+=  printArray(f.get(o), f);

            }else {
                res+= genericToString(f.get(o))+ " = " +f.getName()+ ";\n";
            }

        }
        return res+"}";
    }


    private static String printArray( Object f, Field field) throws IllegalAccessException, ClassNotFoundException {
        String res = "\n"+f.getClass().getTypeName()+" "+field.getName()+" = {";
        for(int i = 0; i<Array.getLength(f); i++){

            Object o = Array.get( f, i);
            res+= genericToString(o)+" "+( isWrapperType(o.getClass())? "= "+o :" ");
            if(i<Array.getLength(f)-1)res+=",\n";
        }
        return res+"\n"+"};\n";
    }

    //********************* source https://stackoverflow.com/questions/709961/determining-if-an-object-is-of-primitive-type
    private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();
    public static boolean isWrapperType(Class<?> clazz)
    {
        return WRAPPER_TYPES.contains(clazz);
    }

    private static Set<Class<?>> getWrapperTypes()
    {
        Set<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        return ret;
    }
    //*********************

}
