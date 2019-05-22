
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Dictionary {
    private static boolean modified = false;
    private static String dictionaryfile;
    private static String fdictionaryfile;
    private static String line;
    static  {
         dictionaryfile =  System.getProperty("user.dir") + "/Dictionary.txt";
    }
    static  {
         fdictionaryfile =  System.getProperty("user.dir") + "/favorite.txt";
    }
    public static boolean isModified() {
        return modified;
    }

    private static String message = "";

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        Dictionary.message = message;
    }
    private static TreeMap<String, String> words = new TreeMap<String,String>();
    private static TreeMap<String, String> fwords = new TreeMap<String,String>();

    public static TreeMap<String,String> getfWords() {
        return fwords;
    }
    public static TreeMap<String,String> getWords() {
        return words;
    }

    public static void setWords(TreeMap<String,String> words) {
        Dictionary.words = words;
        modified = true;
    }

    public static String searchWord(String word) {

        return words.get(word);
    }
    public static void addWord(String word, String meaning) {
        words.put(word,meaning);
        modified = true;
    }
    public static boolean savefToDisk(String word, String meaning) {
        // create file and save to disk
        fwords.put(word,meaning);
        modified = true;
        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fdictionaryfile);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            for(Map.Entry<String,String> w : fwords.entrySet()){
                String key= w.getKey();
                String value = w.getValue();
                bufferedWriter.write(key+"  "+value+"\n");
            }
            // Always close files.
            bufferedWriter.close();
            return true;
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fdictionaryfile + "'");
            return false;
            // Or we could just do this:
            // ex.printStackTrace();
        }

    }
    public static boolean deleteWord(String word) {
        Object done =  words.remove(word);
        if ( done == null)
            return false;
        else {
            modified = true;
            return true;
        }
    }

    public static boolean saveToDisk() {
        // create file and save to disk
        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(dictionaryfile);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            for(Map.Entry<String,String> w : words.entrySet()){
                String key= w.getKey();
                String value = w.getValue();
                bufferedWriter.write(key+"  "+value+"\n");
            }
            // Always close files.
            bufferedWriter.close();
            return true;
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + dictionaryfile + "'");
            return false;
            // Or we could just do this:
            // ex.printStackTrace();
        }

    }

    public static boolean loadFromDisk(){
    FileInputStream inputStream = null;
        try {
            // read words from serialized treemap
            inputStream = new FileInputStream(dictionaryfile);
            Scanner sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.isBlank() || line.isEmpty() || line.trim().equals("")) continue;
                String[] tokens=line.split("  ");
                words.put(tokens[0].trim(), tokens[1].trim());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                inputStream.close();
                return true;
            } catch (IOException ex) {
                Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
    }
}
