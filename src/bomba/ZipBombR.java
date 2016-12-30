package bomba;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class ZipBombR implements Runnable {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        Random r = new Random();
        FileOutputStream out = null;

        try {
            File f = new File("file" + r.nextInt());
            while (f.exists()) {
                f = new File("file" + r.nextInt());
            }
            out = new FileOutputStream(f);
            for (int j = 0; j < 1000000; j++) {
                out.write(r.nextInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (Exception e) {

            }
        }
    }

}
