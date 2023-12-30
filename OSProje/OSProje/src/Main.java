
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException, InterruptedException {
        String dosyaYolu = "giris.txt";
        int id=0;
        Color clr = new Color(); // her prosese renk atama işlemi için color sınıfından nesne olusturuyorum
        Kuyruk prosesler = new Kuyruk(); // txt dosyasından okumuş olduğum prosesleri prosesler kuyruğuna atacagım

        File data;
        Scanner readFile;
        try {//Dosyadan okuma ve hata yakalama
            data = new File(dosyaYolu);
            readFile = new Scanner(data);
        } 
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        
        

        while (readFile.hasNextLine()) {
            String line = readFile.nextLine();
            String[] columns = line.split(", ");


            int varisZamani = Integer.parseInt(columns[0]);
            int oncelik = Integer.parseInt(columns[1]);
            int prosesSuresi = Integer.parseInt(columns[2]);
            int bayt = Integer.parseInt(columns[3]);
            int yazici = Integer.parseInt(columns[4]);
            int tarayici = Integer.parseInt(columns[5]);
            int modem = Integer.parseInt(columns[6]);
            int cd = Integer.parseInt(columns[7]);

            // txt dosyasından okumuş olduğum satırları tek tek prosesler kuyruğuna atiyorum
            prosesler.ekle(new Proses(id,varisZamani,oncelik,prosesSuresi,bayt,yazici,tarayici,modem,cd,clr.getRandomColor()));
            id++;

        }
        //prosesler kuyrugunu görevlendirici sinifina gönderip islemleri orada icra edeceğim
        Gorevlendirici gorevlendirici = new Gorevlendirici(prosesler);
        gorevlendirici.baslat();

    }

}
