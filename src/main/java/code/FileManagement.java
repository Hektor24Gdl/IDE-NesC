package code;

import exceptions.InvalidFileExeption;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class for read and save files.
 */
public class FileManagement {

    /**
     * Function for read a file.
     *
     * @param file to read.
     * @return String with the content of file.
     */
    public String readFile(File file) throws InvalidFileExeption {
        FileReader fr = null;
        BufferedReader br = null;
        String returned = "";
        
        ///TODO: Se agrego codigo para pruebas
        String ext = FileManagement.getExtension(file);
        if(ext.isEmpty())
        {
            throw new InvalidFileExeption();
        }
        else if(!("nc".equals(ext) || "ob".equals(ext)))
        {
            throw new InvalidFileExeption();
        }
        
        try {
         // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
                        
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                returned += linea + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
         // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return returned;
    }

    /**
     * Function for save a file.
     *
     * @param path
     * @param content
     * @return
     * @throws IOException
     */
    public boolean saveFile(String path, String content) throws IOException {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(path);
            pw = new PrintWriter(fichero);
            pw.print(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                    return true;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }
    public static String getExtension(File file) {
        String sPath = file.getAbsolutePath();
        String[] str = sPath.split("\\.");
        if(str.length > 1) {
            return str[str.length - 1];
        }
        return "";
    }
}
