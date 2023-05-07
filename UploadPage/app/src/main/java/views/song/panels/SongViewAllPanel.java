package views.song.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import lombok.Getter;
import lombok.Setter;
import models.SongModel;

@Setter
@Getter
public class SongViewAllPanel extends Panel {
    private JTextField txtTitle;
    private JTable tableSong;
    private JPanel tablePanel;

    private void init() {
        txtTitle = new JTextField("LIST ALL SONGS");
        txtTitle.setFont(new Font("Verdana", Font.BOLD, 24));
        txtTitle.setHorizontalAlignment(SwingConstants.CENTER);
        txtTitle.setBackground(Color.GRAY);
        txtTitle.setEnabled(false);

        String[][] data = new String[0][];
        String[] columnsName = { "ID", "Name", "Artist", "Album", "MusicURL", "ArtworkURL" };
        tableSong = new JTable(data, columnsName);

        tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(tableSong.getTableHeader(), BorderLayout.NORTH);
        tablePanel.add(new JScrollPane(tableSong), BorderLayout.CENTER);
    }

    private SongViewAllPanel() {
        super(new BorderLayout(5, 5));
        init();
        add(txtTitle, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
    }

    public void setTableModel(List<SongModel> listSongModels) {
        String[][] data = new String[listSongModels.size()][];
        String[] columnsName = { "ID", "Name", "Artist", "Album", "MusicURL", "ArtworkURL" };
        TableModel tableModel = new DefaultTableModel(data, columnsName);
        int i = 0;
        for (SongModel songModel : listSongModels) {
            tableModel.setValueAt(String.valueOf(songModel.getSongId()), i, 0);
            tableModel.setValueAt(songModel.getSongName(), i, 1);
            tableModel.setValueAt(songModel.getArtist(), i, 2);
            tableModel.setValueAt(songModel.getAlbum(), i, 3);
            tableModel.setValueAt(songModel.getMusicFileUrl(), i, 4);
            tableModel.setValueAt(songModel.getBackgroundImageFileUrl(), i, 5);
            i++;
        }
        tableSong.setModel(tableModel);
    }

    public static SongViewAllPanel getInstance() {
        return new SongViewAllPanel();
    }
}
