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

import models.SongModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;

public class SongView extends JFrame {
    private JPanel contentPane;
    private JPanel pnBody;
    private JLabel lbTitle;
    private JLabel lbAllSong;

    public JTextField txtSearch;
    public JTable songTable;
    public JButton btnAdd, btnEdit, btnDelete;;

    private static SongView instance;

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

        lbTitle = new JLabel("------------------ SONG MANAGEMENT ------------------");
        lbTitle.setFont(new Font("Verdana", Font.BOLD, 22));
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setForeground(COLOR_1);
        lbTitle.setBounds(0, 0, 1088, 180);
        pnHeader.add(lbTitle);

        contentPane.add(pnHeader);

        String[][] data = new String[0][];
        String[] columnsName = { "ID", "Name", "Artist", "Album", "MusicURL", "ArtworkURL" };
        songTable = new JTable(data, columnsName);
        songTable.setBorder(new LineBorder(Color.BLACK, 1));
        songTable.setBackground(COLOR_4);
        songTable.setShowGrid(true);
        songTable.setSelectionBackground(Color.LIGHT_GRAY);
        songTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        songTable.setFont(new Font("Verdana", Font.PLAIN, 14));
        songTable.setForeground(Color.BLACK);
        songTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane jScrollPane = new JScrollPane(songTable);
        jScrollPane.getViewport().setBackground(COLOR_4);
        jScrollPane.setBorder(new LineBorder(Color.BLACK, 2));

        JTableHeader tableHeader = songTable.getTableHeader();
        tableHeader.setFont(new Font("Verdana", Font.BOLD, 14));

        pnBody = new JPanel();
        pnBody.setBounds(0, 228, 1088, 454);
        pnBody.setBorder(new LineBorder(Color.BLACK, 2));
        pnBody.setBackground(COLOR_4);
        pnBody.setLayout(new BorderLayout());

        pnBody.add(tableHeader, BorderLayout.NORTH);
        pnBody.add(jScrollPane, BorderLayout.CENTER);
        contentPane.add(pnBody);

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
        txtSearch.setBounds(530, 190, 548, 28);
        contentPane.add(txtSearch);
        txtSearch.setColumns(1);

        lbAllSong = new JLabel("All song");
        lbAllSong.setFont(new Font("Verdana", Font.ITALIC, 18));
        lbAllSong.setHorizontalAlignment(SwingConstants.CENTER);
        lbAllSong.setBounds(295, 190, 227, 28);
        contentPane.add(lbAllSong);

    }

    private SongView() {
        init();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(0, 0, 1102, 719);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setIconImage(new ImageIcon("assets/app_logo.png").getImage());
        setTitle("Song Management");
        setResizable(false);
    }

    public void addActionListener(ActionListener actionListener) {
        btnAdd.addActionListener(actionListener);
        btnEdit.addActionListener(actionListener);
        btnDelete.addActionListener(actionListener);
    }

    public void addDocumentListener(DocumentListener documentListener) {
        txtSearch.getDocument().addDocumentListener(documentListener);
    }

    public void setSongTable(List<SongModel> listSongModels) {
        String[][] data = new String[listSongModels.size()][];
        String[] columnsName = { "ID", "Name", "Artist", "Album", "MusicURL", "ArtworkURL" };
        TableModel tableModel = new DefaultTableModel(data, columnsName);
        int i = 0;
        for (SongModel songModel : listSongModels) {
            tableModel.setValueAt(String.valueOf(songModel.getSongId()), i, 0);
            tableModel.setValueAt(songModel.getSongName(), i, 1);
            tableModel.setValueAt(songModel.getArtistName(), i, 2);
            tableModel.setValueAt(songModel.getAlbumName(), i, 3);
            tableModel.setValueAt(songModel.getMusicFileUrl(), i, 4);
            tableModel.setValueAt(songModel.getBackgroundImageFileUrl(), i, 5);
            i++;
        }
        songTable.setModel(tableModel);
    }

    public void reset() {
        lbTitle.setText("------------------ SONG MANAGEMENT ------------------");
        lbAllSong.setText("All song");
        pnBody.setBounds(0, 228, 1088, 454);
        setBounds(0, 0, 1102, 719);
        setLocationRelativeTo(null);
        setAlwaysOnTop(false);
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(true);
        btnDelete.setEnabled(true);
    }

    public void changeSongView(String name, int width, int height, boolean isArtistSongView) {
        if (isArtistSongView) {
            lbTitle.setText("------------------ SINGLE AND EP SONG OF ARTIST MANAGEMENT ------------------");
        } else {
            lbTitle.setText("------------------ SONG OF ALBUM MANAGEMENT ------------------");
        }
        lbAllSong.setText("All song of " + name);
        pnBody.setBounds(0, 228, 1088, 454 - (719 - height));
        setBounds(0, 0, width, height);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
    }

    public boolean isArtistSongView() {
        return lbTitle.getText().contains("ARTIST");
    }

    public boolean isAlbumSongView() {
        return lbTitle.getText().contains("ALBUM");
    }

    public static SongView getInstance() {
        if (instance == null) {
            instance = new SongView();
        }
        return instance;
    }
}
