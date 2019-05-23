
import java.awt.Container;
import java.util.Map;
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
        headings.add("Category");
        
        Vector rows = new Vector();

        Map<String, Word> words = Dictionary.getWords();
        for(String word :  words.keySet()) {
             Vector row= new Vector();
             Word w = words.get(word);
             if (!w.isFavorite()) continue;
             row.add(w.getWord());
             row.add(w.getMeaning());
             row.add(w.getCategory());
             rows.add(row);
        }

        JTable wordstable = new JTable(rows,headings);

        JScrollPane sp = new JScrollPane(wordstable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        c.add(sp);
        pack(); // get requried size based on components
    }

}
