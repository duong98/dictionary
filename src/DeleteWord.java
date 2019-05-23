
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DeleteWord extends JFrame{
    private JTextField tfWord;
    private JButton btnDelete;

    public DeleteWord() {
        super("Delete Word");

        tfWord = new JTextField(20);
        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> {
             if (  tfWord.getText().length() > 0 ) {
                   boolean done = Dictionary.deleteWord(tfWord.getText());

             if (!done)
                   JOptionPane.showMessageDialog( DeleteWord.this, "Word  Not Found. Please try again!","Delete Word", JOptionPane.INFORMATION_MESSAGE);
             else
                   JOptionPane.showMessageDialog( DeleteWord.this, "Word  Deleted Successfully!","Delete Word", JOptionPane.INFORMATION_MESSAGE);
             }
             else
                  JOptionPane.showMessageDialog( DeleteWord.this, "Please enter word from dictionary!","Add Word", JOptionPane.ERROR_MESSAGE);
        }
        );

        Container c = getContentPane();
        c.setLayout( new FlowLayout());
        c.add( new JLabel("Word To Delete :"));
        c.add(tfWord);
        c.add( btnDelete);

        pack(); // get requried size based on components
    }

    public static void main(String args[]) {
        DeleteWord w = new DeleteWord();
        w.setVisible(true);
    }
}
