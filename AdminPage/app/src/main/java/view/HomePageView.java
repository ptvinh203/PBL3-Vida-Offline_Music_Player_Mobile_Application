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
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class HomePageView extends JFrame implements MouseListener {
    public JPanel contentPane, pnHeader;
    public JLabel lbTitle;
    public JButton btnAlbum, btnSong, btnArtist;

    private static final Color COLOR_1 = new Color(12, 19, 79);
    private static final Color COLOR_2 = new Color(29, 38, 125);
    private static final Color COLOR_3 = new Color(92, 70, 156);
    private static final Color COLOR_4 = new Color(212, 173, 252);
    private static final Color COLOR_LITTLE_WHILE = new Color(255, 255, 255, 180);

    private static HomePageView instance;

    private void init() {
        contentPane = new JPanel(null);
        contentPane.setBorder(new LineBorder(Color.BLACK));
        contentPane.setBackground(COLOR_4);

        pnHeader = new JPanel();
        pnHeader.setBounds(0, 0, 642, 180);
        pnHeader.setLayout(null);
        pnHeader.setBackground(COLOR_1);
        pnHeader.setBorder(new LineBorder(COLOR_2, 7));

        lbTitle = new JLabel("VIDA MUSIC APPLICATION'S ADMIN PAGE");
        lbTitle.setFont(new Font("Verdana", Font.BOLD, 22));
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setBounds(0, 0, 642, 180);
        lbTitle.setForeground(Color.WHITE);
        pnHeader.add(lbTitle);

        btnArtist = new JButton("ARTIST");
        btnArtist.setFont(new Font("Verdana", Font.BOLD, 20));
        btnArtist.setBounds(10, 190, 200, 212);
        btnArtist.setBorder(new LineBorder(Color.BLACK, 3));
        btnArtist.setBackground(COLOR_3);
        btnArtist.setForeground(COLOR_LITTLE_WHILE);
        contentPane.add(btnArtist);

        btnAlbum = new JButton("ALBUM");
        btnAlbum.setBounds(432, 190, 200, 212);
        btnAlbum.setFont(new Font("Verdana", Font.BOLD, 20));
        btnAlbum.setBorder(new LineBorder(Color.BLACK, 3));
        btnAlbum.setBackground(COLOR_3);
        btnAlbum.setForeground(COLOR_LITTLE_WHILE);
        contentPane.add(btnAlbum);

        btnSong = new JButton("SONG");
        btnSong.setBounds(222, 190, 200, 212);
        btnSong.setFont(new Font("Verdana", Font.BOLD, 20));
        btnSong.setBorder(new LineBorder(Color.BLACK, 3));
        btnSong.setBackground(COLOR_3);
        btnSong.setForeground(COLOR_LITTLE_WHILE);
        contentPane.add(btnSong);

        contentPane.add(pnHeader);

        addMouseListener(this);
    }

    private HomePageView() {
        init();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 656, 449);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setIconImage(new ImageIcon("assets/app_logo.png").getImage());

        // addComponentListener(new ComponentListener() {

        // @Override
        // public void componentResized(ComponentEvent e) {
        // lbTitle.setBounds(0, 0, getWidth() - 14, (int) (getHeight() /
        // 2.494444444444444));
        // pnHeader.setBounds(0, 0, getWidth() - 14, (int) (getHeight() /
        // 2.494444444444444));

        // btnArtist.setBounds(10, pnHeader.getHeight() + 10, (int) (getWidth() / 3.28),
        // (int) Math.ceil(getHeight() / 2.117924528301887));
        // btnSong.setBounds(btnArtist.getX() + btnArtist.getWidth() + 10,
        // btnArtist.getY(), btnArtist.getWidth(),
        // btnArtist.getHeight());
        // btnAlbum.setBounds(btnSong.getX() + btnSong.getWidth() + 10,
        // btnArtist.getY(), btnArtist.getWidth(),
        // btnArtist.getHeight());
        // }

        // @Override
        // public void componentMoved(ComponentEvent e) {
        // }

        // @Override
        // public void componentShown(ComponentEvent e) {
        // }

        // @Override
        // public void componentHidden(ComponentEvent e) {
        // }

        // });

        setTitle("Admin Page");
    }

    public void addActionListener(ActionListener actionListener) {
        btnAlbum.addActionListener(actionListener);
        btnSong.addActionListener(actionListener);
        btnArtist.addActionListener(actionListener);
    }

    public void addMouseListener(MouseListener mouseListener) {
        btnAlbum.addMouseListener(mouseListener);
        btnArtist.addMouseListener(mouseListener);
        btnSong.addMouseListener(mouseListener);
    }

    public static HomePageView getInstance() {
        if (instance == null) {
            instance = new HomePageView();
        }
        return instance;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == btnAlbum) {
            btnAlbum.setBackground(COLOR_3);
            btnAlbum.setForeground(COLOR_LITTLE_WHILE);
        } else if (e.getSource() == btnArtist) {
            btnArtist.setBackground(COLOR_3);
            btnArtist.setForeground(COLOR_LITTLE_WHILE);
        } else if (e.getSource() == btnSong) {
            btnSong.setBackground(COLOR_3);
            btnSong.setForeground(COLOR_LITTLE_WHILE);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == btnAlbum) {
            btnAlbum.setBackground(COLOR_LITTLE_WHILE);
            btnAlbum.setForeground(Color.BLACK);
        } else if (e.getSource() == btnArtist) {
            btnArtist.setBackground(COLOR_LITTLE_WHILE);
            btnArtist.setForeground(Color.BLACK);
        } else if (e.getSource() == btnSong) {
            btnSong.setBackground(COLOR_LITTLE_WHILE);
            btnSong.setForeground(Color.BLACK);
        }
    }
}
