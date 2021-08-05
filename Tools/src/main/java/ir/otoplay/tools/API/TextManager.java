package ir.otoplay.tools.API;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextManager {
    public TextManager() {
    }
    public String Read(File file){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
            br.close();
        }
        catch (IOException e) {
        }
        return stringBuilder.toString();
    }
    public void Write(String path,String Data) {
        File file = new File(path);
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
            stream.write(Data.getBytes());
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
