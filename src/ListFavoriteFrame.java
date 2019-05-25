
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListFavoriteFrame extends JFrame{
     public ListFavoriteFrame() {
        super("List of Favorite Words");
        setPreferredSize(new Dimension(800, 500));

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

        JTable table = new JTable(rows,headings);
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(350);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);

        JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        c.add(sp);
        pack(); // get requried size based on components
    }

}
