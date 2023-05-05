package views.album;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import lombok.Getter;
import lombok.Setter;


@Setter@Getter
public class AlbumView extends JFrame {
    private JPanel headerPanel, pn1;
    private JTabbedPane tabbedPane;
    private JButton btnHomePage;
    
    private void init() {
        
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Edit Album", new JLabel("Updating") );
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.addTab("All Albums", new JLabel("Updating"));
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        btnHomePage = new JButton("HOME");

        pn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pn1.add(btnHomePage);
        headerPanel = new JPanel(new BorderLayout(0, 1));
        headerPanel.add(pn1, BorderLayout.NORTH);
        headerPanel.add(tabbedPane, BorderLayout.CENTER);
    }
    private AlbumView() {
        init();
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setBounds(100, 100, 700, 600);
        this.setTitle("Album Management");
        this.setLocationRelativeTo(null);
        this.setContentPane(headerPanel);
    }

    public void addActionListener(ActionListener actionListener) {
        btnHomePage.addActionListener(actionListener);
    }

    public static AlbumView getInstance() {
        return new AlbumView();
    }
}
