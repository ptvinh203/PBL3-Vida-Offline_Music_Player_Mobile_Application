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

import models.ArtistModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;

public class ArtistView extends JFrame {
    private JPanel contentPane;
    private JPanel pnBody;
    private JLabel lbTitle;

    public JTextField txtSearch;
    public JTable artistTable;
    public JButton btnShowSong, btnShowAlbum, btnAdd, btnEdit, btnDelete;;

    private static ArtistView instance;

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

        lbTitle = new JLabel("------------- ARTIST MANAGEMENT -------------");
        lbTitle.setFont(new Font("Verdana", Font.BOLD, 22));
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setForeground(COLOR_1);
        lbTitle.setBounds(0, 0, 1088, 180);
        pnHeader.add(lbTitle);

        contentPane.add(pnHeader);

        String[][] data = new String[0][];
        String[] columnsName = { "ID", "Name", "ImageUrl" };
        artistTable = new JTable(data, columnsName);
        artistTable.setBorder(new LineBorder(Color.BLACK, 1));
        artistTable.setBackground(COLOR_4);
        artistTable.setShowGrid(true);
        artistTable.setSelectionBackground(Color.LIGHT_GRAY);
        artistTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        artistTable.setFont(new Font("Verdana", Font.PLAIN, 14));
        artistTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

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

        btnShowAlbum = new JButton("Album");
        btnShowAlbum.setBounds(898, 190, 85, 28);
        btnShowAlbum.setBackground(Color.WHITE);
        btnShowAlbum.setBorder(new LineBorder(COLOR_1, 2));
        contentPane.add(btnShowAlbum);

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
        txtSearch.setBounds(364, 190, 524, 28);
        contentPane.add(txtSearch);
        txtSearch.setColumns(1);

    }

    private ArtistView() {
        init();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(0, 0, 1102, 719);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setIconImage(new ImageIcon("assets/app_logo.png").getImage());
        setTitle("Artist Management");
        setResizable(false);
    }

    public void addActionListener(ActionListener actionListener) {
        btnAdd.addActionListener(actionListener);
        btnEdit.addActionListener(actionListener);
        btnDelete.addActionListener(actionListener);
        btnShowAlbum.addActionListener(actionListener);
        btnShowSong.addActionListener(actionListener);
    }

    public void addDocumentListener(DocumentListener documentListener) {
        txtSearch.getDocument().addDocumentListener(documentListener);
    }

    public void setArtistTable(List<ArtistModel> listArtistModel) {
        String[][] data = new String[listArtistModel.size()][];
        String[] columnsName = { "ID", "Name", "ImageUrl" };
        TableModel tableModel = new DefaultTableModel(data, columnsName);
        int i = 0;
        for (ArtistModel artistModel : listArtistModel) {
            tableModel.setValueAt(String.valueOf(artistModel.getArtistId()), i, 0);
            tableModel.setValueAt(artistModel.getArtistName(), i, 1);
            tableModel.setValueAt(artistModel.getArtistImageUrl(), i, 2);
            i++;
        }
        artistTable.setModel(tableModel);
    }

    public void reset() {
        lbTitle.setText("------------- ARTIST MANAGEMENT -------------");
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(true);
        btnEdit.setEnabled(true);
        btnShowAlbum.setEnabled(true);
        btnShowSong.setEnabled(true);
    }

    public void chooseSongArtistMode() {
        lbTitle.setText("------------- CHOOSE SONG'S ARTIST -------------");
        btnAdd.setEnabled(false);
        btnDelete.setEnabled(false);
        btnEdit.setEnabled(false);
        btnShowAlbum.setEnabled(false);
        btnShowSong.setEnabled(false);
    }

    public void chooseAlbumArtistMode() {
        lbTitle.setText("------------- CHOOSE ALBUM'S ARTIST -------------");
        btnAdd.setEnabled(false);
        btnDelete.setEnabled(false);
        btnEdit.setEnabled(false);
        btnShowAlbum.setEnabled(false);
        btnShowSong.setEnabled(false);
    }

    public boolean isChooseSongArtistMode() {
        return lbTitle.getText().contains("SONG");
    }

    public boolean isChooseAlbumArtistMode() {
        return lbTitle.getText().contains("ALBUM");
    }

    public static ArtistView getInstance() {
        if (instance == null) {
            instance = new ArtistView();
        }
        return instance;
    }
}
