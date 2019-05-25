
import java.awt.*;
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
    private JButton btnFavorite;
    private String meaning;

    public SearchWordFrame() {
        super("Search Word");

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        tfWord = new JTextField(20);
        taMeaning = new JTextArea();
        btnSearch = new JButton("Search");
        btnFavorite = new JButton("Add Favorite");
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

        btnFavorite.addActionListener(ae -> {
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
        c.setLayout(layout);

        // add tfWord
        gbc.anchor = GridBagConstraints.EAST;
        c.add(new JLabel("Search Word :"), gbc);
        gbc.anchor = GridBagConstraints.WEST;
        c.add(tfWord);
        gbc.anchor = GridBagConstraints.EAST;
        c.add(btnSearch);
        gbc.anchor = GridBagConstraints.WEST;
        c.add(btnFavorite);


        // add taMeaning
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        c.add(new JLabel("Meaning :"), gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        taMeaning.setRows(6);
        taMeaning.setColumns(30);
        taMeaning.setLineWrap(true);
        JScrollPane sp = new JScrollPane(taMeaning, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        c.add(sp, gbc);

        pack(); // get requried size based on components
    }

}
