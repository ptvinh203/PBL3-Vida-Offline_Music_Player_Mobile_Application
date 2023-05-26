package view.edit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import lombok.Getter;
import models.ArtistModel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class ArtistEditView extends JFrame {
    private JPanel contentPane;
    private JLabel lbTitle;;

    private static ArtistEditView instance;

    private static final Color COLOR_1 = new Color(12, 19, 79);
    private static final Color COLOR_1_180 = new Color(12, 19, 79, 180);
    private static final Color COLOR_3 = new Color(92, 70, 156);
    private static final Color COLOR_LITTLE_WHILE = new Color(255, 255, 255, 200);
    private JTextField txtName;
    @Getter
    private JTextField txtImage;
    private JTextField txtId;

    public JButton btnUpdate;
    public JButton btnChooseFile;
    public JFileChooser fileChooser;

    private void init() {
        contentPane = new JPanel(null);
        contentPane.setBorder(new LineBorder(Color.BLACK, 2));
        contentPane.setBackground(COLOR_3);

        fileChooser = new JFileChooser(new File("."));

        lbTitle = new JLabel("----------- UPDATE ARTIST -----------");
        lbTitle.setBounds(0, 0, 598, 94);
        contentPane.add(lbTitle);
        lbTitle.setFont(new Font("Verdana", Font.BOLD, 22));
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setForeground(COLOR_LITTLE_WHILE);

        JLabel lbName = new JLabel("Name:");
        lbName.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        lbName.setForeground(COLOR_LITTLE_WHILE);
        lbName.setBounds(10, 172, 150, 58);
        contentPane.add(lbName);

        JLabel lbImage = new JLabel("Artist's image:");
        lbImage.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        lbImage.setBounds(10, 240, 150, 58);
        lbImage.setForeground(COLOR_LITTLE_WHILE);
        contentPane.add(lbImage);

        txtName = new JTextField();
        txtName.setBounds(170, 172, 418, 58);
        txtName.setColumns(10);
        txtName.setBorder(new LineBorder(COLOR_1, 2));
        txtName.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtName.setBackground(Color.WHITE);
        contentPane.add(txtName);

        txtImage = new JTextField();
        txtImage.setColumns(10);
        txtImage.setBounds(170, 240, 418, 58);
        txtImage.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtImage.setBorder(new LineBorder(COLOR_1, 2));
        txtImage.setBackground(Color.WHITE);
        txtImage.setEditable(false);
        contentPane.add(txtImage);

        btnUpdate = new JButton("UPDATE");
        btnUpdate.setFont(new Font("Verdana", Font.BOLD, 20));
        btnUpdate.setBounds(10, 308, 578, 44);
        btnUpdate.setBorder(new LineBorder(COLOR_1, 3));
        btnUpdate.setBackground(COLOR_1_180);
        btnUpdate.setForeground(COLOR_LITTLE_WHILE);
        contentPane.add(btnUpdate);

        btnChooseFile = new JButton("FILE");
        btnChooseFile.setForeground(new Color(255, 255, 255, 200));
        btnChooseFile.setFont(new Font("Verdana", Font.BOLD, 10));
        btnChooseFile.setBorder(new LineBorder(COLOR_1, 3));
        btnChooseFile.setBackground(COLOR_1_180);
        btnChooseFile.setForeground(COLOR_LITTLE_WHILE);
        btnChooseFile.setBounds(503, 73, 85, 21);
        contentPane.add(btnChooseFile);

        JLabel lbId = new JLabel("ID:");
        lbId.setForeground(new Color(255, 255, 255, 200));
        lbId.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        lbId.setBounds(10, 104, 150, 58);
        contentPane.add(lbId);

        txtId = new JTextField();
        txtId.setEditable(false);
        txtId.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtId.setColumns(10);
        txtId.setBorder(new LineBorder(COLOR_1, 2));
        txtId.setBackground(Color.WHITE);
        txtId.setBounds(170, 104, 418, 58);
        contentPane.add(txtId);
    }

    private ArtistEditView() {
        init();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(0, 0, 612, 399);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setAlwaysOnTop(true);
        setIconImage(new ImageIcon("assets/app_logo.png").getImage());
        setTitle("Artist Update");
        setResizable(false);
    }

    public void addActionListener(ActionListener actionListener) {
        btnUpdate.addActionListener(actionListener);
        btnChooseFile.addActionListener(actionListener);
    }

    public void setDefaultInfor(ArtistModel artistModel) {
        txtId.setText(String.valueOf(artistModel.getArtistId()));
        txtName.setText(artistModel.getArtistName());
        txtImage.setText(artistModel.getArtistImageUrl());
    }

    public ArtistModel getArtistModel() throws Exception {
        if (txtName.getText() == null || txtName.getText().isEmpty() || txtId.getText() == null
                || txtId.getText().isEmpty()) {
            throw new Exception("Please fill full information!");
        } else {
            ArtistModel artistModel = new ArtistModel();
            artistModel.setArtistId(Integer.parseInt(txtId.getText().trim()));
            artistModel.setArtistName(txtName.getText().trim());
            return artistModel;
        }
    }

    public File getFile() throws Exception {
        if (txtImage.getText() == null || txtImage.getText().isEmpty()) {
            throw new Exception("Please fill full information!");
        } else {
            return new File(txtImage.getText().trim());
        }

    }

    public static ArtistEditView getInstance() {
        if (instance == null) {
            instance = new ArtistEditView();
        }
        return instance;
    }

}
