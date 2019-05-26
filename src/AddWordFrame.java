
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddWordFrame extends JFrame{
    private JTextField tfWord;
    private JTextField tfCategory;
    private JTextArea  taMeaning;
    private JButton btnAdd;

    public AddWordFrame() {
        super("Add Word");

        GridBagLayout gbl  = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.BOTH;

        tfWord = new JTextField(30);
        taMeaning = new JTextArea();
        tfCategory = new JTextField(30);
        btnAdd = new JButton("Add Word");
        btnAdd.addActionListener(e -> {
             if (  tfWord.getText().length() > 0 && taMeaning.getText().length() > 0  ) {
                  Dictionary.addWord(tfWord.getText(), taMeaning.getText(), tfCategory.getText());
                  tfWord.setText("");
                  taMeaning.setText("");
                  tfCategory.setText("");
                  tfWord.requestFocus();
                  JOptionPane.showMessageDialog( AddWordFrame.this, "Added Word Successfully!","Add Word", JOptionPane.INFORMATION_MESSAGE);
             }
             else
                 JOptionPane.showMessageDialog( AddWordFrame.this, "Please enter word and meaning!","Add Word", JOptionPane.ERROR_MESSAGE);


        }
        );

        Container c = getContentPane();
        c.setLayout(gbl);

        // add tfWord
        gbc.anchor = GridBagConstraints.EAST;
        c.add( new JLabel("Enter Word :"),gbc);
        gbc.anchor = GridBagConstraints.WEST;
        c.add(tfWord);

//        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        c.add( new JLabel("Enter Category:"),gbc);
        gbc.anchor = GridBagConstraints.WEST;
        c.add(tfCategory);

        // add taMeaning
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        c.add( new JLabel("Enter Meaning :"), gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        taMeaning.setRows(4);
        taMeaning.setColumns(30);
        JScrollPane sp = new JScrollPane(taMeaning, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        c.add(sp, gbc);

        // add button
        gbc.gridx  = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        c.add(btnAdd,gbc);

        pack(); // get requried size based on components
    }

    public static void main(String args[]) {
        AddWordFrame w = new AddWordFrame();
        w.setVisible(true);
    }
}
