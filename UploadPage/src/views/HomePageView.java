package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class HomePageView extends JFrame{
    private JPanel contentPane, pn1, pn2;
    public JButton btnSong, btnArtist, btnAlbum;


    private void init() {
        contentPane = new JPanel(new BorderLayout());
        pn1 = new JPanel(new BorderLayout(30, 30));
        pn2 = new JPanel(new GridLayout(3, 1, 0, 30));

        btnSong = new JButton("SONG");
        btnArtist = new JButton("ARTIST");

        btnAlbum = new JButton("ALBUM");
        pn2.add(btnSong);
        pn2.add(btnArtist);
        pn2.add(btnAlbum);

        String str = " ";
        pn1.add(new JLabel(str), BorderLayout.NORTH);
        pn1.add(new JLabel(str), BorderLayout.WEST);
        pn1.add(pn2, BorderLayout.CENTER);
        pn1.add(new JLabel(str), BorderLayout.EAST);
        pn1.add(new JLabel(str), BorderLayout.SOUTH);

        contentPane.add(new JLabel(str), BorderLayout.NORTH);
        contentPane.add(new JLabel(str), BorderLayout.WEST);
        contentPane.add(pn1, BorderLayout.CENTER);
        contentPane.add(new JLabel(str), BorderLayout.EAST);
        contentPane.add(new JLabel(str), BorderLayout.NORTH);
    }
    private HomePageView() {
        init();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 400);
        setTitle("HOME PAGE");
        setLocationRelativeTo(null);
        setContentPane(contentPane);
    }

    public void addActionListener(ActionListener actionListener) {
        btnSong.addActionListener(actionListener);
        btnArtist.addActionListener(actionListener);
        btnAlbum.addActionListener(actionListener);
    }

    public static HomePageView getInstance() {
        return new HomePageView();
    }
}
