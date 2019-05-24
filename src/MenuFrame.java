
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public class MenuFrame extends JFrame {

    public MenuFrame() throws Exception {
        super("Dictionary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar mb = new JMenuBar();

        addStorageMenu(mb);
        addDictionaryMenu(mb);
        addToolbar();
        setJMenuBar(mb);
//        searchWord();

        Dictionary.loadFromDisk();
    }

    public void exit() {
        if (Dictionary.isModified()) {
            int option = JOptionPane.showConfirmDialog(MenuFrame.this, "You have some pending changes. Do you want to write them to disk and then exit?",
                    "Save", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (option == JOptionPane.YES_OPTION) {  // exit after save
                Dictionary.saveToDisk();
                System.exit(0);
            }
            else if (option == JOptionPane.NO_OPTION) // exit without saving
            {
               System.exit(0);
            }
        // if cancel then do not exit
        } else {
            System.exit(0);
        }
    }

    public ImageIcon getImage(String filename){
        return new ImageIcon(  this.getClass().getResource("/images/" + filename));
    }

    public void centerToParent(JFrame parent, JFrame child) {
        Dimension pd = parent.getSize();
        Dimension cd = child.getSize();
        int x = (int) (pd.getWidth() - cd.getWidth()) / 2;
        int y = (int) (pd.getHeight() - cd.getHeight()) / 2;
        child.setLocation(x, y);

    }

    public void addWord() {
        AddWordFrame w = new AddWordFrame();
        centerToParent(MenuFrame.this, w);
        w.setVisible(true);
    }

    public void deleteWord() {
        DeleteWordFrame w = new DeleteWordFrame();
        centerToParent(MenuFrame.this, w);
        w.setVisible(true);
    }

    public void searchWord() {
        SearchWordFrame w = new SearchWordFrame();
        centerToParent(MenuFrame.this, w);
//        JFrame parent = this;
//        Dimension pd = parent.getSize();
//        Dimension cd = w.getSize();
//        int x = (int) (pd.getWidth() - cd.getWidth()) / 3;
//        int y = (int) (pd.getHeight() - cd.getHeight()) / 3;
//        w.setLocation(x, y);

        w.setVisible(true);
    }

    private void listFavoriteWords() {
        ListFavoriteFrame w = new ListFavoriteFrame();
        w.setVisible(true);
        centerToParent(MenuFrame.this, w);

    }

    private void listWords() {
        ListWordsFrame w = new ListWordsFrame();
        w.setVisible(true);
        centerToParent(MenuFrame.this, w);
    }

    private void addToolbar() {
        JToolBar tb = new JToolBar();
        JButton b = new JButton( getImage("add.gif"));
        b.setPreferredSize( new Dimension(32,32));
        tb.add(b);
        b.setToolTipText("Add Word");
        b.addActionListener(e -> addWord());

        b = new JButton( getImage("delete.gif"));
        b.setPreferredSize( new Dimension(32,32));
        tb.add(b);
        b.setToolTipText("Delete Word");
        b.addActionListener(e -> deleteWord());

        b = new JButton( getImage("search.gif"));
        b.setPreferredSize( new Dimension(32,32));
        tb.add(b);
        b.setToolTipText("Search Word");
        b.addActionListener(e -> searchWord());


        b = new JButton( getImage("list.gif"));
        tb.add(b);
        b.setToolTipText("List Words");
        b.addActionListener(e -> listWords());

        tb.addSeparator();

        b = new JButton( getImage("save.gif"));
        tb.add(b);
        b.setToolTipText("Save Dictionary To Disk");
        b.addActionListener(e -> Dictionary.saveToDisk());

        b = new JButton( getImage("load.gif"));
        tb.add(b);
        b.setToolTipText("Load Dictionary From Disk");
        b.addActionListener(e -> Dictionary.loadFromDisk());

        getContentPane().add(tb, BorderLayout.NORTH);
    }

    private void addStorageMenu(JMenuBar mb) {

        JMenu mnuStorage = new JMenu("Storage");

        // options in Storage Menu
        JMenuItem option = new JMenuItem("Save Dictionary");
        option.setIcon( getImage("save.gif"));
        option.setAccelerator( KeyStroke.getKeyStroke("F2"));
        mnuStorage.add(option);
        option.addActionListener(e -> {
            boolean result = Dictionary.saveToDisk();
            if (result) {
                JOptionPane.showMessageDialog(MenuFrame.this, "Saved Dictionary Successfully!", "Feedback",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(MenuFrame.this, "Could Not Save Dictionary Successfully! Error --> " + Dictionary.getMessage(), "Feedback",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });


        option = new JMenuItem("Load Dictionary");
        option.setIcon( getImage("load.gif"));
        option.setAccelerator( KeyStroke.getKeyStroke("F3"));
        mnuStorage.add(option);
        option.addActionListener(e -> {
            boolean result = Dictionary.loadFromDisk();
            if (result) {
                JOptionPane.showMessageDialog(MenuFrame.this, "Loaded Dictionary Successfully!", "Feedback",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(MenuFrame.this, "Could Not Load Dictionary Successfully! Error --> " + Dictionary.getMessage(), "Feedback",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        mb.add(mnuStorage);

    }

    private void addDictionaryMenu(JMenuBar mb) {
        // create menu
        JMenu mnuDictionary = new JMenu("Dictionary");
        mb.add(mnuDictionary);

        // options in Dictionary Menu
        JMenuItem option = new JMenuItem("Add Word...");
        option.setIcon( getImage("add.gif"));
        option.setAccelerator( KeyStroke.getKeyStroke("F5"));
        mnuDictionary.add(option);
        option.addActionListener(e -> addWord());

        // options in Dictionary Menu
        option = new JMenuItem("Delete Word...");
        option.setIcon( getImage("delete.gif"));
        option.setAccelerator( KeyStroke.getKeyStroke("F6"));
        mnuDictionary.add(option);
        option.addActionListener(e -> deleteWord());

        mnuDictionary.addSeparator();

        option = new JMenuItem("Search Word...");
        option.setIcon( getImage("search.gif"));
        option.setAccelerator( KeyStroke.getKeyStroke("F7"));
        mnuDictionary.add(option);
        option.addActionListener(e -> searchWord());


        option = new JMenuItem("List favorite Words");
        option.setIcon( getImage("list.gif"));
        option.setAccelerator( KeyStroke.getKeyStroke("F8"));
        mnuDictionary.add(option);
        option.addActionListener(e -> listFavoriteWords());

        mnuDictionary.addSeparator();

        option = new JMenuItem("Exit");
        mnuDictionary.add(option);
        option.addActionListener(e -> exit());
    }

    public static void main(String[] args) throws Exception {
        MenuFrame f = new MenuFrame();
        f.setVisible(true);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
}
