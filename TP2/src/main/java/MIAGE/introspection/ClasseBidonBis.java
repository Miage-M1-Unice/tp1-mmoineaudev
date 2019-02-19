package MIAGE.introspection;

/**
 * POJO utilité pour tester l'introspection
 */
public class ClasseBidonBis {
    public int bidonInt;
    public ClasseBidonBisBis bisBisBidon = new ClasseBidonBisBis(999);

    public ClasseBidonBis(int i) {
        this.bidonInt = i;
    }
}
