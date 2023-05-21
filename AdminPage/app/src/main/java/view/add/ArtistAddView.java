package view.add;

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
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class ArtistAddView extends JFrame {
    private JPanel contentPane;
    private JLabel lbTitle;;

    private static ArtistAddView instance;

    private static final Color COLOR_1 = new Color(12, 19, 79);
    private static final Color COLOR_1_180 = new Color(12, 19, 79, 180);
    private static final Color COLOR_3 = new Color(92, 70, 156);
    private static final Color COLOR_LITTLE_WHILE = new Color(255, 255, 255, 200);
    private JTextField txtName;
    @Getter
    private JTextField txtImage;

    public JButton btnCreate;
    public JButton btnReset;
    public JButton btnChooseFile;
    public JFileChooser fileChooser;

    private void init() {
        contentPane = new JPanel(null);
        contentPane.setBorder(new LineBorder(Color.BLACK, 2));
        contentPane.setBackground(COLOR_3);

        fileChooser = new JFileChooser(new File("."));

        lbTitle = new JLabel("-------- CREATE NEW ARTIST --------");
        lbTitle.setBounds(0, 0, 598, 84);
        contentPane.add(lbTitle);
        lbTitle.setFont(new Font("Verdana", Font.BOLD, 22));
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setForeground(COLOR_LITTLE_WHILE);

        JLabel lbName = new JLabel("Name:");
        lbName.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        lbName.setForeground(COLOR_LITTLE_WHILE);
        lbName.setBounds(10, 94, 150, 58);
        contentPane.add(lbName);

        JLabel lbImage = new JLabel("Artist's image:");
        lbImage.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        lbImage.setBounds(10, 162, 150, 58);
        lbImage.setForeground(COLOR_LITTLE_WHILE);
        contentPane.add(lbImage);

        txtName = new JTextField();
        txtName.setBounds(170, 94, 418, 58);
        txtName.setColumns(10);
        txtName.setBorder(new LineBorder(COLOR_1, 2));
        txtName.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtName.setBackground(Color.WHITE);
        contentPane.add(txtName);

        txtImage = new JTextField();
        txtImage.setColumns(10);
        txtImage.setBounds(170, 162, 418, 58);
        txtImage.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtImage.setBorder(new LineBorder(COLOR_1, 2));
        txtImage.setBackground(Color.WHITE);
        txtImage.setEditable(false);
        contentPane.add(txtImage);

        btnCreate = new JButton("CREATE");
        btnCreate.setFont(new Font("Verdana", Font.BOLD, 20));
        btnCreate.setBounds(10, 230, 578, 44);
        btnCreate.setBorder(new LineBorder(COLOR_1, 3));
        btnCreate.setBackground(COLOR_1_180);
        btnCreate.setForeground(COLOR_LITTLE_WHILE);
        contentPane.add(btnCreate);

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
    }

    private ArtistAddView() {
        init();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(0, 0, 612, 317);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setTitle("Admin Page");
    }

    public void addActionListener(ActionListener actionListener) {
        btnCreate.addActionListener(actionListener);
        btnReset.addActionListener(actionListener);
        btnChooseFile.addActionListener(actionListener);
    }

    public void reset() {
        txtName.setText("");
        txtImage.setText("");
    }

    public ArtistModel getArtistModel() throws Exception {
        if (txtName.getText() == null || txtName.getText().isEmpty()) {
            throw new Exception("Please fill full information!");
        } else {
            ArtistModel artistModel = new ArtistModel();
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

    public static ArtistAddView getInstance() {
        if (instance == null) {
            instance = new ArtistAddView();
        }
        return instance;
    }
}
