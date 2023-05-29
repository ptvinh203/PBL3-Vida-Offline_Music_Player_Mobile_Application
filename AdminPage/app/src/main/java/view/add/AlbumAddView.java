package view.add;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import lombok.Getter;
import models.AlbumModel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

public class AlbumAddView extends JFrame {
    private JPanel contentPane;
    private JLabel lbTitle;;
    private JTextField txtName;
    @Getter
    private JTextField txtArtist;

    private static AlbumAddView instance;

    private static final Color COLOR_1 = new Color(12, 19, 79);
    private static final Color COLOR_1_180 = new Color(12, 19, 79, 180);
    private static final Color COLOR_3 = new Color(92, 70, 156);
    private static final Color COLOR_LITTLE_WHILE = new Color(255, 255, 255, 200);

    public JButton btnChooseArtist;
    public JButton btnCreate;
    public JButton btnReset;

    private void init() {
        contentPane = new JPanel(null);
        contentPane.setBorder(new LineBorder(Color.BLACK, 2));
        contentPane.setBackground(COLOR_3);

        lbTitle = new JLabel("-------- CREATE NEW ALBUM --------");
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

        txtName = new JTextField();
        txtName.setBounds(170, 94, 418, 58);
        txtName.setColumns(10);
        txtName.setBorder(new LineBorder(COLOR_1, 2));
        txtName.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtName.setBackground(Color.WHITE);
        contentPane.add(txtName);

        btnCreate = new JButton("CREATE");
        btnCreate.setFont(new Font("Verdana", Font.BOLD, 20));
        btnCreate.setBounds(10, 231, 578, 44);
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

        JLabel lbArtist = new JLabel("Artist:");
        lbArtist.setForeground(new Color(255, 255, 255, 200));
        lbArtist.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        lbArtist.setBounds(10, 162, 150, 58);
        contentPane.add(lbArtist);

        txtArtist = new JTextField();
        txtArtist.setEditable(false);
        txtArtist.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtArtist.setColumns(10);
        txtArtist.setBorder(new LineBorder(COLOR_1, 2));
        txtArtist.setBackground(Color.WHITE);
        txtArtist.setBounds(170, 162, 418, 58);
        contentPane.add(txtArtist);

        btnChooseArtist = new JButton("ARTIST");
        btnChooseArtist.setForeground(new Color(255, 255, 255, 200));
        btnChooseArtist.setFont(new Font("Verdana", Font.BOLD, 10));
        btnChooseArtist.setBorder(new LineBorder(COLOR_1, 3));
        btnChooseArtist.setBackground(new Color(12, 19, 79, 180));
        btnChooseArtist.setBounds(408, 63, 85, 21);
        contentPane.add(btnChooseArtist);

    }

    private AlbumAddView() {
        init();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(0, 0, 612, 322);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setAlwaysOnTop(true);
        setIconImage(new ImageIcon("assets/app_logo.png").getImage());
        setTitle("Create New Album");
        setResizable(false);
    }

    public void addActionListener(ActionListener actionListener) {
        btnCreate.addActionListener(actionListener);
        btnReset.addActionListener(actionListener);
        btnChooseArtist.addActionListener(actionListener);
    }

    public void reset() {
        txtName.setText("");
        txtArtist.setText("");
    }

    public AlbumModel getAlbumModel() throws Exception {
        if (txtName.getText() == null || txtName.getText().isEmpty()) {
            throw new Exception("Please fill full information!");
        }
        AlbumModel albumModel = new AlbumModel();
        albumModel.setAlbumName(txtName.getText().trim());
        return albumModel;
    }

    public static AlbumAddView getInstance() {
        if (instance == null) {
            instance = new AlbumAddView();
        }
        return instance;
    }
}
