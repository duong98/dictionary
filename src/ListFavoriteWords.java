
import java.awt.Container;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListFavoriteWords extends JFrame{
     public ListFavoriteWords() {
        super("List of Favorite Words");

        Container c = getContentPane();

        Vector headings = new Vector();
        headings.add("Word");
        headings.add("Meaning");
        
        Vector rows = new Vector();

        TreeMap<String,String> words = Dictionary.getfWords();
        for(String word :  words.keySet()) {
             Vector row= new Vector();
             row.add(word);
             row.add( words.get(word));
             rows.add(row);
        }

        JTable wordstable = new JTable(rows,headings);

        JScrollPane sp = new JScrollPane(wordstable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        c.add(sp);
        pack(); // get requried size based on components
    }

}