package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class HomePageView extends JFrame {
    private JPanel contentPane, pn1, pn2;
    public JButton btnSong, btnArtist, btnAlbum;

    private void init() {
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new LineBorder(Color.BLACK, 2));
        contentPane.setBackground(new Color(163, 163, 117));
        pn1 = new JPanel(new BorderLayout(30, 30));
        pn2 = new JPanel(new GridLayout(3, 1, 0, 30));
        pn1.setBackground(new Color(163, 163, 117));
        pn2.setBackground(new Color(163, 163, 117));

        btnSong = new JButton("SONG");
        btnSong.setBackground(new Color(224, 224, 235));
        btnSong.setBorder(new LineBorder(Color.BLACK, 2));

        btnArtist = new JButton("ARTIST");
        btnArtist.setBackground(new Color(224, 224, 235));
        btnArtist.setBorder(new LineBorder(Color.BLACK, 2));

        btnAlbum = new JButton("ALBUM");
        btnAlbum.setBackground(new Color(224, 224, 235));
        btnAlbum.setBorder(new LineBorder(Color.BLACK, 2));
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
        setBounds(100, 100, 600, 600);
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
