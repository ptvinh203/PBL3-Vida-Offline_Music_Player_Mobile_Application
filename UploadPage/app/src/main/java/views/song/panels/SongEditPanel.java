package views.song.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SongEditPanel extends Panel {
    private JPanel bodyPanel, chooseMusicFilePanel, chooseArtworkFilePanel, chooseArtistPanel,
            chooseAlbumPanel, subJPanel1, subJPanel2;
    private JLabel lbChooseOption, lbId, lbName, lbMusicFile, lbArtworkFile, lbArtist, lbAlbum;
    private JTextField txtHeaderTitle, txtId, txtName, txtMusicFile, txtArtworkFile, txtArtist, txtAlbum;

    private JTextField txtArtistName, txtAlbumName;
    private JButton btnOK, btnChooseMusicFile, btnChooseArtworkFile, btnChooseArtist, btnChooseAlbum;
    private JFileChooser fileChooser;

    private void init() {
        bodyPanel = new JPanel(new GridLayout(6, 2, 35, 10));
        chooseMusicFilePanel = new JPanel(new BorderLayout(1, 1));
        chooseArtworkFilePanel = new JPanel(new BorderLayout(1, 1));
        chooseArtistPanel = new JPanel(new BorderLayout(1, 1));
        chooseAlbumPanel = new JPanel(new BorderLayout(1, 1));
        subJPanel1 = new JPanel(new GridLayout(1, 2, 1, 1));
        subJPanel2 = new JPanel(new GridLayout(1, 2, 1, 1));

        txtHeaderTitle = new JTextField("CREATE NEW SONG");
        txtHeaderTitle.setFont(new Font("Verdana", Font.BOLD, 28));
        txtHeaderTitle.setHorizontalAlignment(SwingConstants.CENTER);
        txtHeaderTitle.setEnabled(false);
        txtHeaderTitle.setBackground(Color.GRAY);

        lbId = new JLabel("Id:", SwingConstants.RIGHT);
        lbId.setFont(new Font("Verdana", Font.BOLD, 20));
        lbName = new JLabel("Name:", SwingConstants.RIGHT);
        lbName.setFont(new Font("Verdana", Font.BOLD, 20));
        lbArtist = new JLabel("Artist:", SwingConstants.RIGHT);
        lbArtist.setFont(new Font("Verdana", Font.BOLD, 20));
        lbAlbum = new JLabel("Album:", SwingConstants.RIGHT);
        lbAlbum.setFont(new Font("Verdana", Font.BOLD, 20));
        lbMusicFile = new JLabel("Music file:", SwingConstants.RIGHT);
        lbMusicFile.setFont(new Font("Verdana", Font.BOLD, 20));
        lbArtworkFile = new JLabel("Artwork file:", SwingConstants.RIGHT);
        lbArtworkFile.setFont(new Font("Verdana", Font.BOLD, 20));

        txtId = new JTextField();
        txtId.setFont(new Font("Verdana", Font.PLAIN, 18));
        txtId.setEnabled(false);
        txtName = new JTextField();
        txtName.setFont(new Font("Verdana", Font.PLAIN, 18));
        txtArtist = new JTextField();
        txtArtist.setFont(new Font("Verdana", Font.PLAIN, 18));
        txtArtist.setEnabled(false);
        txtAlbum = new JTextField();
        txtAlbum.setFont(new Font("Verdana", Font.PLAIN, 18));
        txtAlbum.setEnabled(false);
        txtMusicFile = new JTextField();
        txtMusicFile.setFont(new Font("Verdana", Font.PLAIN, 18));
        txtMusicFile.setEnabled(false);
        txtArtworkFile = new JTextField();
        txtArtworkFile.setFont(new Font("Verdana", Font.PLAIN, 18));
        txtArtworkFile.setEnabled(false);

        btnChooseMusicFile = new JButton("CHOOSE");
        chooseMusicFilePanel.add(txtMusicFile, BorderLayout.CENTER);
        chooseMusicFilePanel.add(btnChooseMusicFile, BorderLayout.EAST);
        btnChooseArtworkFile = new JButton("CHOOSE");
        chooseArtworkFilePanel.add(txtArtworkFile, BorderLayout.CENTER);
        chooseArtworkFilePanel.add(btnChooseArtworkFile, BorderLayout.EAST);

        txtArtistName = new JTextField();
        txtArtistName.setFont(new Font("Verdana", Font.BOLD, 24));
        txtArtistName.setEnabled(false);
        txtAlbumName = new JTextField();
        txtAlbumName.setFont(new Font("Verdana", Font.BOLD, 24));
        txtAlbumName.setEnabled(false);

        subJPanel1.add(txtArtist);
        subJPanel1.add(txtArtistName);
        subJPanel2.add(txtAlbum);
        subJPanel2.add(txtAlbumName);

        btnChooseAlbum = new JButton("CHOOSE");
        btnChooseArtist = new JButton("CHOOSE");
        chooseArtistPanel.add(subJPanel1, BorderLayout.CENTER);
        chooseArtistPanel.add(btnChooseArtist, BorderLayout.EAST);
        chooseAlbumPanel.add(subJPanel2, BorderLayout.CENTER);
        chooseAlbumPanel.add(btnChooseAlbum, BorderLayout.EAST);

        bodyPanel.add(lbId);
        bodyPanel.add(txtId);
        bodyPanel.add(lbName);
        bodyPanel.add(txtName);
        bodyPanel.add(lbArtist);
        bodyPanel.add(chooseArtistPanel);
        bodyPanel.add(lbAlbum);
        bodyPanel.add(chooseAlbumPanel);
        bodyPanel.add(lbMusicFile);
        bodyPanel.add(chooseMusicFilePanel);
        bodyPanel.add(lbArtworkFile);
        bodyPanel.add(chooseArtworkFilePanel);

        btnOK = new JButton("OK");

        fileChooser = new JFileChooser(new File("."));
    }

    private SongEditPanel() {
        super(new BorderLayout(5, 5));
        init();
        add(txtHeaderTitle, BorderLayout.NORTH);
        add(bodyPanel, BorderLayout.CENTER);
        add(btnOK, BorderLayout.SOUTH);
    }

    public void resetAll() {
        txtId.setText("");
        txtName.setText("");
        txtArtist.setText("");
        txtArtistName.setText("");
        txtAlbum.setText("");
        txtAlbumName.setText("");
        txtMusicFile.setText("");
        txtArtworkFile.setText("");
    }

    public void addActionListener(ActionListener actionListener) {
        btnOK.addActionListener(actionListener);
        btnChooseArtist.addActionListener(actionListener);
        btnChooseAlbum.addActionListener(actionListener);
        btnChooseArtworkFile.addActionListener(actionListener);
        btnChooseMusicFile.addActionListener(actionListener);
    }

    public static SongEditPanel getInstance() {
        return new SongEditPanel();
    }
}
