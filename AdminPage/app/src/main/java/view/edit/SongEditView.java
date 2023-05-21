package view.edit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import lombok.Getter;
import models.SongModel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class SongEditView extends JFrame {
    private JPanel contentPane;
    private JLabel lbTitle;;
    private JLabel lbAlbum;
    private JLabel lbMusicFile;
    private JLabel lbArtworkFile;
    private JLabel lbId;

    private static SongEditView instance;

    private static final Color COLOR_1 = new Color(12, 19, 79);
    private static final Color COLOR_1_180 = new Color(12, 19, 79, 180);
    private static final Color COLOR_3 = new Color(92, 70, 156);
    private static final Color COLOR_LITTLE_WHILE = new Color(255, 255, 255, 200);

    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtArtist;
    private JTextField txtAlbum;
    @Getter
    private JTextField txtMusicFile;
    @Getter
    private JTextField txtArtworkFile;

    public JButton btnUpdate;
    public JButton btnReset;
    public JButton btnChooseFile;
    public JFileChooser fileChooser;

    private void init() {
        contentPane = new JPanel(null);
        contentPane.setBorder(new LineBorder(Color.BLACK, 2));
        contentPane.setBackground(COLOR_3);

        fileChooser = new JFileChooser(new File("."));

        lbTitle = new JLabel("-------- UPDATE SONG --------");
        lbTitle.setBounds(0, 0, 598, 84);
        contentPane.add(lbTitle);
        lbTitle.setFont(new Font("Verdana", Font.BOLD, 22));
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setForeground(COLOR_LITTLE_WHILE);

        JLabel lbName = new JLabel("Name:");
        lbName.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        lbName.setForeground(COLOR_LITTLE_WHILE);
        lbName.setBounds(10, 171, 150, 58);
        contentPane.add(lbName);

        JLabel lbArtist = new JLabel("Artist:");
        lbArtist.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        lbArtist.setBounds(10, 239, 150, 58);
        lbArtist.setForeground(COLOR_LITTLE_WHILE);
        contentPane.add(lbArtist);

        txtName = new JTextField();
        txtName.setBounds(170, 171, 418, 58);
        txtName.setColumns(10);
        txtName.setBorder(new LineBorder(COLOR_1, 2));
        txtName.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtName.setBackground(Color.WHITE);
        contentPane.add(txtName);

        txtArtist = new JTextField();
        txtArtist.setColumns(10);
        txtArtist.setBounds(170, 239, 418, 58);
        txtArtist.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtArtist.setBorder(new LineBorder(COLOR_1, 2));
        txtArtist.setBackground(Color.WHITE);
        txtArtist.setEditable(false);
        contentPane.add(txtArtist);

        btnUpdate = new JButton("UPDATE");
        btnUpdate.setFont(new Font("Verdana", Font.BOLD, 20));
        btnUpdate.setBounds(10, 511, 578, 44);
        btnUpdate.setBorder(new LineBorder(COLOR_1, 3));
        btnUpdate.setBackground(COLOR_1_180);
        btnUpdate.setForeground(COLOR_LITTLE_WHILE);
        contentPane.add(btnUpdate);

        btnReset = new JButton("RESET");
        btnReset.setFont(new Font("Verdana", Font.BOLD, 10));
        btnReset.setBounds(503, 63, 85, 21);
        btnReset.setBorder(new LineBorder(COLOR_1, 3));
        btnReset.setBackground(COLOR_1_180);
        btnReset.setForeground(COLOR_LITTLE_WHILE);
        contentPane.add(btnReset);

        btnChooseFile = new JButton("FILE");
        btnChooseFile.setForeground(new Color(255, 255, 255, 200));
        btnChooseFile.setFont(new Font("Verdana", Font.BOLD, 10));
        btnChooseFile.setBorder(new LineBorder(COLOR_1, 3));
        btnChooseFile.setBackground(COLOR_1_180);
        btnChooseFile.setForeground(COLOR_LITTLE_WHILE);
        btnChooseFile.setBounds(408, 63, 85, 21);
        contentPane.add(btnChooseFile);

        txtAlbum = new JTextField();
        txtAlbum.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtAlbum.setEditable(false);
        txtAlbum.setColumns(10);
        txtAlbum.setBorder(new LineBorder(COLOR_1, 2));
        txtAlbum.setBackground(Color.WHITE);
        txtAlbum.setBounds(170, 307, 418, 58);
        contentPane.add(txtAlbum);

        lbAlbum = new JLabel("Album:");
        lbAlbum.setForeground(new Color(255, 255, 255, 200));
        lbAlbum.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        lbAlbum.setBounds(10, 307, 150, 58);
        contentPane.add(lbAlbum);

        lbMusicFile = new JLabel("Music's file:");
        lbMusicFile.setForeground(new Color(255, 255, 255, 200));
        lbMusicFile.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        lbMusicFile.setBounds(10, 375, 150, 58);
        contentPane.add(lbMusicFile);

        txtMusicFile = new JTextField();
        txtMusicFile.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtMusicFile.setEditable(false);
        txtMusicFile.setColumns(10);
        txtMusicFile.setBorder(new LineBorder(COLOR_1, 2));
        txtMusicFile.setBackground(Color.WHITE);
        txtMusicFile.setBounds(170, 375, 418, 58);
        contentPane.add(txtMusicFile);

        txtArtworkFile = new JTextField();
        txtArtworkFile.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtArtworkFile.setEditable(false);
        txtArtworkFile.setColumns(10);
        txtArtworkFile.setBorder(new LineBorder(COLOR_1, 2));
        txtArtworkFile.setBackground(Color.WHITE);
        txtArtworkFile.setBounds(170, 443, 418, 58);
        contentPane.add(txtArtworkFile);

        lbArtworkFile = new JLabel("Artwork's file:");
        lbArtworkFile.setForeground(new Color(255, 255, 255, 200));
        lbArtworkFile.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        lbArtworkFile.setBounds(10, 443, 150, 58);
        contentPane.add(lbArtworkFile);

        lbId = new JLabel("Id");
        lbId.setForeground(new Color(255, 255, 255, 200));
        lbId.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        lbId.setBounds(10, 103, 150, 58);
        contentPane.add(lbId);

        txtId = new JTextField();
        txtId.setEditable(false);
        txtId.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtId.setColumns(10);
        txtId.setBorder(new LineBorder(COLOR_1, 2));
        txtId.setBackground(Color.WHITE);
        txtId.setBounds(170, 103, 418, 58);
        contentPane.add(txtId);

    }

    private SongEditView() {
        init();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(0, 0, 612, 602);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setAlwaysOnTop(true);
        setTitle("Admin Page");
    }

    public void addActionListener(ActionListener actionListener) {
        btnUpdate.addActionListener(actionListener);
        btnReset.addActionListener(actionListener);
        btnChooseFile.addActionListener(actionListener);
    }

    public void reset() {
        txtId.setText("");
        txtName.setText("");
        txtArtist.setText("");
        txtAlbum.setText("");
        txtMusicFile.setText("");
        txtArtworkFile.setText("");
    }

    public void setDefaultInfor(SongModel songModel) {
        txtId.setText(String.valueOf(songModel.getSongId()));
        txtName.setText(songModel.getSongName());
        txtArtist.setText(songModel.getArtistName());
        txtMusicFile.setText(songModel.getMusicFileUrl());
        txtArtworkFile.setText(songModel.getBackgroundImageFileUrl());
    }

    public SongModel getSongModel() throws Exception {
        if (txtName.getText() == null || txtName.getText().isEmpty() || txtId.getText() == null
                || txtId.getText().isEmpty()) {
            throw new Exception("Please fill full information!");
        }
        SongModel songModel = new SongModel();
        songModel.setSongId(Integer.parseInt(txtId.getText().trim()));
        songModel.setSongName(txtName.getText().trim());
        return songModel;
    }

    public File getMusicFile() throws Exception {
        if (txtMusicFile.getText() == null || txtMusicFile.getText().isEmpty()) {
            throw new Exception("Please fill full information!");
        }
        return new File(txtMusicFile.getText().trim());
    }

    public File getArtworkFile() throws Exception {
        if (txtArtworkFile.getText() == null || txtArtworkFile.getText().isEmpty()) {
            throw new Exception("Please fill full information!");
        }
        return new File(txtArtworkFile.getText().trim());
    }

    public static SongEditView getInstance() {
        if (instance == null) {
            instance = new SongEditView();
        }
        return instance;
    }
}
