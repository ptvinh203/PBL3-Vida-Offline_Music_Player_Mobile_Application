package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;

public class AlbumView extends JFrame {
    private JPanel contentPane;
    private JPanel pnBody;
    private JLabel lbTitle;
    private JTable artistTable;

    private JTextField txtSearch;
    public JButton btnShowSong, btnAdd, btnEdit, btnDelete;;

    private static AlbumView instance;

    private static final Color COLOR_1 = new Color(12, 19, 79);
    // private static final Color COLOR_2 = new Color(29, 38, 125);
    private static final Color COLOR_3 = new Color(92, 70, 156);
    private static final Color COLOR_4 = new Color(212, 173, 252);
    private static final Color COLOR_LITTLE_WHILE = new Color(255, 255, 255, 180);
    private JLabel lbAllAlbum;

    private void init() {
        contentPane = new JPanel(null);
        contentPane.setBorder(new LineBorder(Color.BLACK, 2));
        contentPane.setBackground(COLOR_4);

        JPanel pnHeader = new JPanel();
        pnHeader.setBounds(0, 0, 1088, 180);
        pnHeader.setLayout(null);
        pnHeader.setBackground(COLOR_3);
        pnHeader.setBorder(new LineBorder(COLOR_1, 7));

        lbTitle = new JLabel("------------- ALBUM MANAGEMENT -------------");
        lbTitle.setFont(new Font("Verdana", Font.BOLD, 22));
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setForeground(COLOR_1);
        lbTitle.setBounds(0, 0, 1088, 180);
        pnHeader.add(lbTitle);

        contentPane.add(pnHeader);

        String[][] data = new String[1][3];
        data[0][0] = "1";
        data[0][1] = "2";
        data[0][2] = "3";
        String[] columnsName = { "ID", "Name", "Artist" };
        artistTable = new JTable(data, columnsName);
        artistTable.setBorder(new LineBorder(Color.BLACK, 1));
        artistTable.setBackground(COLOR_LITTLE_WHILE);
        artistTable.setShowGrid(true);
        artistTable.setSelectionBackground(Color.LIGHT_GRAY);
        artistTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        artistTable.setFont(new Font("Verdana", Font.PLAIN, 14));

        JScrollPane jScrollPane = new JScrollPane(artistTable);
        jScrollPane.getViewport().setBackground(COLOR_4);
        jScrollPane.setBorder(new LineBorder(Color.BLACK, 2));

        JTableHeader tableHeader = artistTable.getTableHeader();
        tableHeader.setFont(new Font("Verdana", Font.BOLD, 14));

        pnBody = new JPanel();
        pnBody.setBounds(0, 228, 1088, 454);
        pnBody.setBorder(new LineBorder(Color.BLACK, 2));
        pnBody.setBackground(COLOR_4);
        pnBody.setLayout(new BorderLayout());

        pnBody.add(tableHeader, BorderLayout.NORTH);
        pnBody.add(jScrollPane, BorderLayout.CENTER);
        contentPane.add(pnBody);

        btnShowSong = new JButton("Song");
        btnShowSong.setBounds(993, 190, 85, 28);
        btnShowSong.setBackground(Color.WHITE);
        btnShowSong.setBorder(new LineBorder(COLOR_1, 2));
        contentPane.add(btnShowSong);

        btnAdd = new JButton("Add");
        btnAdd.setBorder(new LineBorder(COLOR_1, 2));
        btnAdd.setBackground(Color.WHITE);
        btnAdd.setBounds(10, 190, 85, 28);
        contentPane.add(btnAdd);

        btnEdit = new JButton("Edit");
        btnEdit.setBorder(new LineBorder(COLOR_1, 2));
        btnEdit.setBackground(Color.WHITE);
        btnEdit.setBounds(105, 190, 85, 28);
        contentPane.add(btnEdit);

        btnDelete = new JButton("Delete");
        btnDelete.setBorder(new LineBorder(COLOR_1, 2));
        btnDelete.setBackground(Color.WHITE);
        btnDelete.setBounds(200, 190, 85, 28);
        contentPane.add(btnDelete);

        txtSearch = new JTextField();
        txtSearch.setHorizontalAlignment(SwingConstants.LEFT);
        txtSearch.setFont(new Font("Verdana", Font.PLAIN, 10));
        txtSearch.setBorder(new LineBorder(COLOR_1, 2));
        txtSearch.setBounds(441, 190, 542, 28);
        contentPane.add(txtSearch);
        txtSearch.setColumns(1);

    }

    private AlbumView() {
        init();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(0, 0, 1102, 719);
        setLocationRelativeTo(null);
        setContentPane(contentPane);

        lbAllAlbum = new JLabel("All album");
        lbAllAlbum.setFont(new Font("Verdana", Font.ITALIC, 14));
        lbAllAlbum.setHorizontalAlignment(SwingConstants.CENTER);
        lbAllAlbum.setBounds(295, 190, 139, 28);
        contentPane.add(lbAllAlbum);

        setTitle("Admin Page");
    }

    public void addActionListener(ActionListener actionListener) {
        btnAdd.addActionListener(actionListener);
        btnEdit.addActionListener(actionListener);
        btnDelete.addActionListener(actionListener);
        btnShowSong.addActionListener(actionListener);
    }

    public void addMouseListener(MouseListener mouseListener) {
    }

    public static AlbumView getInstance() {
        if (instance == null) {
            instance = new AlbumView();
        }
        return instance;
    }
}
