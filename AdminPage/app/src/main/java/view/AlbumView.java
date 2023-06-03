package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import lombok.Getter;
import lombok.Setter;
import models.AlbumModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;

public class AlbumView extends JFrame {
    private JPanel contentPane;
    private JPanel pnBody;
    private JLabel lbTitle;
    private JLabel lbAllAlbum;

    public JTextField txtSearch;
    public JTable albumTable;
    public JButton btnShowSong, btnAdd, btnEdit, btnDelete;;

    @Setter
    @Getter
    private Integer artistId;

    private static AlbumView instance;

    private static final Color COLOR_1 = new Color(12, 19, 79);
    private static final Color COLOR_3 = new Color(92, 70, 156);
    private static final Color COLOR_4 = new Color(212, 173, 252);

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

        String[][] data = new String[0][];
        String[] columnsName = { "ID", "Name", "Artist", "The number of songs" };
        albumTable = new JTable(data, columnsName);
        albumTable.setBorder(new LineBorder(Color.BLACK, 1));
        albumTable.setBackground(COLOR_4);
        albumTable.setShowGrid(true);
        albumTable.setSelectionBackground(Color.LIGHT_GRAY);
        albumTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        albumTable.setFont(new Font("Verdana", Font.PLAIN, 14));
        albumTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane jScrollPane = new JScrollPane(albumTable);
        jScrollPane.getViewport().setBackground(COLOR_4);
        jScrollPane.setBorder(new LineBorder(Color.BLACK, 2));

        JTableHeader tableHeader = albumTable.getTableHeader();
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

        lbAllAlbum = new JLabel("All album");
        lbAllAlbum.setFont(new Font("Verdana", Font.ITALIC, 14));
        lbAllAlbum.setHorizontalAlignment(SwingConstants.CENTER);
        lbAllAlbum.setBounds(295, 190, 139, 28);
        contentPane.add(lbAllAlbum);

    }

    private AlbumView() {
        init();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(0, 0, 1102, 719);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setIconImage(new ImageIcon("assets/app_logo.png").getImage());
        setTitle("Album Management");
        setResizable(false);
    }

    public void addActionListener(ActionListener actionListener) {
        btnAdd.addActionListener(actionListener);
        btnEdit.addActionListener(actionListener);
        btnDelete.addActionListener(actionListener);
        btnShowSong.addActionListener(actionListener);
    }

    public void addDocumentListener(DocumentListener documentListener) {
        txtSearch.getDocument().addDocumentListener(documentListener);
    }

    public void setAlbumTable(List<AlbumModel> listAlbumModel) {
        String[][] data = new String[listAlbumModel.size()][];
        String[] columnsName = { "ID", "Name", "Artist", "The number of songs" };
        TableModel tableModel = new DefaultTableModel(data, columnsName);
        int i = 0;
        for (AlbumModel albumModel : listAlbumModel) {
            tableModel.setValueAt(String.valueOf(albumModel.getAlbumId()), i, 0);
            tableModel.setValueAt(albumModel.getAlbumName(), i, 1);
            tableModel.setValueAt(albumModel.getArtistName(), i, 2);
            tableModel.setValueAt(albumModel.getSongsAlbum().size(), i, 3);
            i++;
        }
        albumTable.setModel(tableModel);
    }

    public void reset() {
        lbTitle.setText("------------- ALBUM MANAGEMENT -------------");
        lbAllAlbum.setText("All album");
        pnBody.setBounds(0, 228, 1088, 454);
        setBounds(0, 0, 1102, 719);
        setLocationRelativeTo(null);
        setAlwaysOnTop(false);

        btnAdd.setEnabled(true);
        btnDelete.setEnabled(true);
        btnEdit.setEnabled(true);
        btnShowSong.setEnabled(true);
    }

    public void artistAlbumView(String artistName, int width, int height) {
        lbTitle.setText("------------- ALBUM OF ARTIST MANAGEMENT -------------");
        lbAllAlbum.setText("All album of " + artistName);
        pnBody.setBounds(0, 228, 1088, 454 - (719 - height));
        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        setBounds(0, 0, width, height);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
    }

    public void chooseAlbumMode() {
        lbTitle.setText("------------- CHOOSE SONG'S ALBUM -------------");
        btnAdd.setEnabled(false);
        btnDelete.setEnabled(false);
        btnEdit.setEnabled(false);
        btnShowSong.setEnabled(false);
    }

    public boolean isChooseAlbumMode() {
        return !btnAdd.isEnabled();
    }

    public boolean isArtistAlbumView() {
        return lbTitle.getText().contains("ARTIST");
    }

    public static AlbumView getInstance() {
        if (instance == null) {
            instance = new AlbumView();
        }
        return instance;
    }
}
