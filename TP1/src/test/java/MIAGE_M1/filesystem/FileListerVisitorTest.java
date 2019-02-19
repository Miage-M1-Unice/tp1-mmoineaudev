package MIAGE_M1.filesystem;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static MIAGE_M1.view.Console.print;
import static org.junit.Assert.*;

public class FileListerVisitorTest {
    FileListerVisitor instance = new FileListerVisitor();
    @Test
    public void visitorTest() {
            print(this, null);
            Path startingDir = Paths.get(".");

        try {
            Files.walkFileTree(startingDir, instance);
        } catch (IOException e) {
            fail();
        }
    }
}