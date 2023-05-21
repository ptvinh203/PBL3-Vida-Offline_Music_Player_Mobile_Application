package view.edit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class AlbumEditView extends JFrame {
    private JPanel contentPane;
    private JLabel lbTitle;;
    private JTextField txtName;
    private JTextField txtArtist;

    private static AlbumEditView instance;

    private static final Color COLOR_1 = new Color(12, 19, 79);
    private static final Color COLOR_1_180 = new Color(12, 19, 79, 180);
    private static final Color COLOR_3 = new Color(92, 70, 156);
    private static final Color COLOR_LITTLE_WHILE = new Color(255, 255, 255, 200);

    public JButton btnChooseArtist;
    public JButton btnCreate;
    private JLabel lbId;
    private JTextField txtId;

    private void init() {
        contentPane = new JPanel(null);
        contentPane.setBorder(new LineBorder(Color.BLACK, 2));
        contentPane.setBackground(COLOR_3);

        lbTitle = new JLabel("-------- UPDATE ALBUM --------");
        lbTitle.setBounds(0, 0, 598, 84);
        contentPane.add(lbTitle);
        lbTitle.setFont(new Font("Verdana", Font.BOLD, 22));
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setForeground(COLOR_LITTLE_WHILE);

        JLabel lbName = new JLabel("Name:");
        lbName.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        lbName.setForeground(COLOR_LITTLE_WHILE);
        lbName.setBounds(10, 172, 150, 58);
        contentPane.add(lbName);

        txtName = new JTextField();
        txtName.setBounds(170, 172, 418, 58);
        txtName.setColumns(10);
        txtName.setBorder(new LineBorder(COLOR_1, 2));
        txtName.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtName.setBackground(Color.WHITE);
        contentPane.add(txtName);

        btnCreate = new JButton("CREATE");
        btnCreate.setFont(new Font("Verdana", Font.BOLD, 20));
        btnCreate.setBounds(10, 309, 578, 44);
        btnCreate.setBorder(new LineBorder(COLOR_1, 3));
        btnCreate.setBackground(COLOR_1_180);
        btnCreate.setForeground(COLOR_LITTLE_WHILE);
        contentPane.add(btnCreate);

        JLabel lbArtist = new JLabel("Artist:");
        lbArtist.setForeground(new Color(255, 255, 255, 200));
        lbArtist.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        lbArtist.setBounds(10, 240, 150, 58);
        contentPane.add(lbArtist);

        txtArtist = new JTextField();
        txtArtist.setEditable(false);
        txtArtist.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtArtist.setColumns(10);
        txtArtist.setBorder(new LineBorder(COLOR_1, 2));
        txtArtist.setBackground(Color.WHITE);
        txtArtist.setBounds(170, 240, 418, 58);
        contentPane.add(txtArtist);

        btnChooseArtist = new JButton("ARTIST");
        btnChooseArtist.setForeground(new Color(255, 255, 255, 200));
        btnChooseArtist.setFont(new Font("Verdana", Font.BOLD, 10));
        btnChooseArtist.setBorder(new LineBorder(COLOR_1, 3));
        btnChooseArtist.setBackground(new Color(12, 19, 79, 180));
        btnChooseArtist.setBounds(503, 73, 85, 21);
        contentPane.add(btnChooseArtist);

    }

    private AlbumEditView() {
        init();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(0, 0, 612, 400);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        
        lbId = new JLabel("Id:");
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
        setAlwaysOnTop(true);
        setTitle("Admin Page");
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

//    public AlbumModel getAlbumModel() throws Exception {
//        if (txtName.getText() == null || txtName.getText().isEmpty()) {
//            throw new Exception("Please fill full information!");
//        }
//        AlbumModel albumModel = new AlbumModel();
//        albumModel.setAlbumName(txtName.getText().trim());
//        return albumModel;
//    }

    public static AlbumEditView getInstance() {
        if (instance == null) {
            instance = new AlbumEditView();
        }
        return instance;
    }
}
