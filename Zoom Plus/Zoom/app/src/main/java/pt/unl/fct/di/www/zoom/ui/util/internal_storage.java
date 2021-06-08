package pt.unl.fct.di.www.zoom.ui.util;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class internal_storage {

    public static String read_file(Activity context, String file_name) {
        try {
            FileInputStream fis = context.openFileInput(file_name);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }

            fis.close();
            inputStreamReader.close();
            reader.close();

            return stringBuilder.toString();

        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public static void write_file(Activity context, String file_name, String file_contents) {

        try {
            FileOutputStream fos = context.openFileOutput(file_name, Context.MODE_PRIVATE);
            byte[] bytes = file_contents.getBytes();
            fos.write(bytes, 0, bytes.length);
            fos.flush();
            fos.close();
        } catch(FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
