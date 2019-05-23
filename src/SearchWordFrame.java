
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SearchWordFrame extends JFrame {
    private JTextField tfWord;
    private JTextArea taMeaning;
    private JButton btnSearch;
    private JButton addfavorite;
    private String meaning;

    public SearchWordFrame() {
        super("Search Word");
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        tfWord = new JTextField(20);
        taMeaning = new JTextArea();
        btnSearch = new JButton("Search");
        addfavorite = new JButton("Add Favorite");
        btnSearch.addActionListener(e -> {
                    if (tfWord.getText().length() > 0) {

                        Word w = Dictionary.searchWord(tfWord.getText());
                        if (w != null) {
                            meaning = w.getMeaning();
                            taMeaning.setText(meaning);

                        } else {
                            JOptionPane.showMessageDialog(SearchWordFrame.this, "Word  Not Found. Please try again!", "Search Word", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else
                        JOptionPane.showMessageDialog(SearchWordFrame.this, "Please enter word from dictionary!", "Search Word", JOptionPane.ERROR_MESSAGE);
                }
        );

        addfavorite.addActionListener(ae -> {
            if (tfWord.getText().length() > 0) {

                Word w = Dictionary.searchWord(tfWord.getText());
                if (w != null) {
                    w.setFavorite(true);
                    JOptionPane.showMessageDialog(SearchWordFrame.this, "Add word to favorite successfully!", "Favorite Word", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(SearchWordFrame.this, "Word  Not Found. Please try again!", "Favorite Word", JOptionPane.INFORMATION_MESSAGE);
                }
            } else
                JOptionPane.showMessageDialog(SearchWordFrame.this, "Please enter word from dictionary!", "Favorite Word", JOptionPane.ERROR_MESSAGE);

        });


        Container c = getContentPane();
        c.setLayout(gbl);

        // add tfWord
        gbc.anchor = GridBagConstraints.EAST;
        c.add(new JLabel("Search Word :"), gbc);
        gbc.anchor = GridBagConstraints.WEST;
        c.add(tfWord);
        gbc.anchor = GridBagConstraints.EAST;
        c.add(btnSearch);
        gbc.anchor = GridBagConstraints.EAST;
        c.add(addfavorite);


        // add taMeaning
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        c.add(new JLabel("Meaning :"), gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        taMeaning.setRows(3);
        taMeaning.setColumns(30);
        JScrollPane sp = new JScrollPane(taMeaning, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        c.add(sp, gbc);

        pack(); // get requried size based on components
    }

}
