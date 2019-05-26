
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Dictionary {
    private static boolean modified = false;
    private static String dictionaryfile;
    private static String line;

    static {
        dictionaryfile = System.getProperty("user.dir") + "/Dictionary.txt";
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

    private static Map<String, Word> words = new TreeMap<>();

    public static void setWords(Map<String, Word> words) {
        Dictionary.words = words;
        modified = true;
    }

    public static Map<String, Word> getWords() {
        return words;
    }

    public static Word searchWord(String word) {
        return words.get(word);
    }

    public static void addWord(String word, String meaning, String category) {
        words.put(word, new Word(word, meaning, category));
        modified = true;
    }

//    public static boolean savefToDisk(String word, String meaning) {
//        // create file and save to disk
//        fwords.put(word, meaning);
//        modified = true;
//        try {
//            // Assume default encoding.
//            FileWriter fileWriter =
//                    new FileWriter(fdictionaryfile);
//
//            // Always wrap FileWriter in BufferedWriter.
//            BufferedWriter bufferedWriter =
//                    new BufferedWriter(fileWriter);
//
//            for (Map.Entry<String, String> w : fwords.entrySet()) {
//                String key = w.getKey();
//                String value = w.getValue();
//                bufferedWriter.write(key + "  " + value + "\n");
//            }
//            // Always close files.
//            bufferedWriter.close();
//            return true;
//        } catch (IOException ex) {
//            System.out.println(
//                    "Error writing to file '"
//                            + fdictionaryfile + "'");
//            return false;
//            // Or we could just do this:
//            // ex.printStackTrace();
//        }
//
//    }

    public static boolean deleteWord(String word) {
        Object done = words.remove(word);
        if (done == null)
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

            for (Map.Entry<String, Word> w : words.entrySet()) {
//                String key = w.getKey();
//                String value = w.getValue();
                bufferedWriter.write(w.getValue().toString());
            }
            // Always close files.
            bufferedWriter.close();
            return true;
        } catch (IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + dictionaryfile + "'");
            return false;
            // Or we could just do this:
            // ex.printStackTrace();
        }

    }

    public static boolean loadFromDisk() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(dictionaryfile);
            Scanner sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.isBlank() || line.isEmpty() || line.trim().equals("")) continue;
                Word w = Word.fromString(line.trim());
                words.put(w.getWord(), w);
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
