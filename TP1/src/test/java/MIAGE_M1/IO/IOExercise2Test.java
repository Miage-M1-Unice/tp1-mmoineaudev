package MIAGE_M1.IO;

import org.junit.Test;

import java.io.FileNotFoundException;

import static MIAGE_M1.view.Console.print;
import static org.junit.Assert.*;

public class IOExercise2Test {

    @Test
    public void readSeLit() {
        try {
            print(IOExercise2.readSeLit());
        } catch (Exception e) {
            fail();
        }
    }


    @Test
    public void readSecrit() {
        try {
            print(IOExercise2.readSecrit());
        } catch (Exception e) {
            fail();
        }
    }


    @Test
    public void readEveryThing() {
        try {
            print(IOExercise2.readEveryThing());
        } catch (Exception e) {
            fail();
        }
    }


}