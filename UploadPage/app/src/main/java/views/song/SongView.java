package views.song;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;

import lombok.Getter;
import lombok.Setter;
import views.song.panels.SongEditPanel;
import views.song.panels.SongViewAllPanel;

@Setter
@Getter
public class SongView extends JFrame {
    private JPanel headerPanel, pn1;
    private JTabbedPane tabbedPane;
    private JButton btnHomePage;

    private final SongEditPanel songEditPanel = SongEditPanel.getInstance();
    private final SongViewAllPanel songViewAllPanel = SongViewAllPanel.getInstance();

    private void init() {

        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(33, 42, 62));
        tabbedPane.addTab("Edit Song", songEditPanel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.addTab("All Songs", songViewAllPanel);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        btnHomePage = new JButton("HOME");
        btnHomePage.setBackground(new Color(163, 163, 117));
        btnHomePage.setForeground(Color.BLACK);
        btnHomePage.setBorder(new LineBorder(Color.BLACK, 2));
        pn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pn1.setBackground(new Color(33, 42, 62));
        pn1.add(btnHomePage);
        headerPanel = new JPanel(new BorderLayout(0, 1));
        headerPanel.setBorder(new LineBorder(Color.BLACK, 2));
        headerPanel.setBackground(new Color(33, 42, 62));
        headerPanel.add(pn1, BorderLayout.NORTH);
        headerPanel.add(tabbedPane, BorderLayout.CENTER);
    }

    private SongView() {
        init();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 1000, 600);
        this.setTitle("Song Management");
        this.setLocationRelativeTo(null);
        this.setContentPane(headerPanel);
    }

    public void addActionListener(ActionListener actionListener) {
        btnHomePage.addActionListener(actionListener);
    }

    public void addChangeListenerForTabbedPane(ChangeListener changeListener) {
        this.tabbedPane.addChangeListener(changeListener);
    }

    public void showMessageBox(String message, String title, int typeMessage) {
        JOptionPane.showMessageDialog(this, message, title, typeMessage);
    }

    public static SongView getInstance() {
        return new SongView();
    }
}
