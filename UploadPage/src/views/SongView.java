package views;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import lombok.Getter;
import lombok.Setter;
import views.panels.SongUploadPanel;
import views.panels.SongViewAllPanel;


@Setter@Getter
public class SongView extends JFrame{
    private JTabbedPane tabbedPane;
    private SongUploadPanel songUploadPanel;
    private SongViewAllPanel songViewAllPanel;

    private void init() {
        tabbedPane = new JTabbedPane();
        
        songUploadPanel = new SongUploadPanel();
        songViewAllPanel = new SongViewAllPanel();


        tabbedPane.addTab("Upload Song", songUploadPanel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.addTab("All Songs", songViewAllPanel);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
    public SongView() {
        init();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 700, 600);
        this.setTitle("Song Management");
        this.setLocationRelativeTo(null);
        this.setContentPane(tabbedPane);
    }
    public static void showGUI() {
        new SongView().setVisible(true);
    }
}
