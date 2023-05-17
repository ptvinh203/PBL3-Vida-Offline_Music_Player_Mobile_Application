package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;

public class ArtistView extends JFrame implements MouseListener {
    private JPanel contentPane;
    private JLabel lbTitle;
    public JButton btnAlbum, btnSong, btnArtist;

    private static ArtistView instance;

    private void init() {
        contentPane = new JPanel(null);
        contentPane.setBorder(new LineBorder(Color.BLACK));
        contentPane.setBackground(new Color(214, 232, 219));

        JPanel pnHeader = new JPanel();
        pnHeader.setBounds(0, 0, 642, 180);
        pnHeader.setLayout(null);
        pnHeader.setBackground(new Color(153, 169, 143));
        pnHeader.setBorder(new LineBorder(Color.BLACK, 2));

        lbTitle = new JLabel("VIDA MUSIC APPLICATION'S ADMIN PAGE");
        lbTitle.setFont(new Font("Verdana", Font.BOLD, 22));
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setBounds(0, 0, 642, 180);
        pnHeader.add(lbTitle);

        btnArtist = new JButton("ARTIST");
        btnArtist.setFont(new Font("Verdana", Font.BOLD, 20));
        btnArtist.setBounds(10, 190, 200, 212);
        btnArtist.setBorder(new LineBorder(Color.BLACK, 2));
        btnArtist.setBackground(new Color(169, 144, 126));
        contentPane.add(btnArtist);

        btnAlbum = new JButton("ALBUM");
        btnAlbum.setBounds(432, 190, 200, 212);
        btnAlbum.setFont(new Font("Verdana", Font.BOLD, 20));
        btnAlbum.setBorder(new LineBorder(Color.BLACK, 2));
        btnAlbum.setBackground(new Color(169, 144, 126));
        contentPane.add(btnAlbum);

        btnSong = new JButton("SONG");
        btnSong.setBounds(222, 190, 200, 212);
        btnSong.setFont(new Font("Verdana", Font.BOLD, 20));
        btnSong.setBorder(new LineBorder(Color.BLACK, 2));
        btnSong.setBackground(new Color(169, 144, 126));
        contentPane.add(btnSong);

        contentPane.add(pnHeader);

        addMouseListener(this);
    }

    private ArtistView() {
        init();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 656, 449);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setTitle("Admin Page");
    }

    public void addActionListener(ActionListener ActionListener) {

    }

    public void addMouseListener(MouseListener mouseListener) {
        btnAlbum.addMouseListener(mouseListener);
        btnArtist.addMouseListener(mouseListener);
        btnSong.addMouseListener(mouseListener);
    }

    public static ArtistView getInstance() {
        if (instance == null) {
            instance = new ArtistView();
        }
        return instance;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == btnAlbum) {
            btnAlbum.setBackground(new Color(153, 169, 143));
        } else if (e.getSource() == btnArtist) {
            btnArtist.setBackground(new Color(153, 169, 143));
        } else if (e.getSource() == btnSong) {
            btnSong.setBackground(new Color(153, 169, 143));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btnAlbum) {
            btnAlbum.setBackground(new Color(153, 169, 143));
        } else if (e.getSource() == btnArtist) {
            btnArtist.setBackground(new Color(153, 169, 143));
        } else if (e.getSource() == btnSong) {
            btnSong.setBackground(new Color(153, 169, 143));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == btnAlbum) {
            btnAlbum.setBackground(new Color(153, 169, 143));
        } else if (e.getSource() == btnArtist) {
            btnArtist.setBackground(new Color(153, 169, 143));
        } else if (e.getSource() == btnSong) {
            btnSong.setBackground(new Color(153, 169, 143));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == btnAlbum) {
            btnAlbum.setBackground(new Color(255, 248, 222));
        } else if (e.getSource() == btnArtist) {
            btnArtist.setBackground(new Color(255, 248, 222));
        } else if (e.getSource() == btnSong) {
            btnSong.setBackground(new Color(255, 248, 222));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == btnAlbum) {
            btnAlbum.setBackground(new Color(169, 144, 126));
        } else if (e.getSource() == btnArtist) {
            btnArtist.setBackground(new Color(169, 144, 126));
        } else if (e.getSource() == btnSong) {
            btnSong.setBackground(new Color(169, 144, 126));
        }
    }
}
