package pdf2;

// import WindowText.TextGenerator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;

public class DesktopFrame extends JFrame {

    private final JDesktopPane desktop;

    public DesktopFrame() {
        desktop = new JDesktopPane();
        add(desktop);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("New");
        JMenuItem createText = new JMenuItem("TextFile");
        JMenuItem createPDF = new JMenuItem("Open PDF");

        menu.add(createText);
        menu.add(createPDF);

        setJMenuBar(menuBar);

        createText.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //TextGenerator tg = new TextGenerator();
                        JTextPane tg = new JTextPane();
                        JInternalFrame newFrame = new JInternalFrame("New Sheet", true, true, true, true);
                        newFrame.add(tg);
                        newFrame.setVisible(true);
                        newFrame.setSize(600, 600);
                        desktop.add(newFrame);
                        
                    }
                });
        createPDF.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser fc = (JFileChooser)e.getSource();
                        String path = e.getActionCommand();
                        
                        if(path.equals(JFileChooser.APPROVE_OPTION)){
                            File f = fc.getSelectedFile();
                            
                        }
                        
                        
                        JInternalFrame newFrame = new JInternalFrame("New Sheet", true, true, true, true);
                        newFrame.add(fc);
                        newFrame.setVisible(true);
                        newFrame.setSize(700, 600);
                    }
                });
        menuBar.add(menu);

    }

}
