package views;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.SongModel;

public class ArtistSingleAndEpSongView extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JButton btnExit;

    private void init() {
        contentPane = new JPanel(new BorderLayout(1, 1));

        String[][] data = new String[0][4];
        String[] columnsName = {"Id", "Name", "Music File URL", "Artwork Image URL"};
        table = new JTable(data, columnsName) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        
        btnExit = new JButton("CLOSE");

        contentPane.add(btnExit, BorderLayout.SOUTH);
        contentPane.add(table.getTableHeader(), BorderLayout.NORTH);
        contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
    }
    private ArtistSingleAndEpSongView() {
        init();
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setBounds(100, 100, 800, 400);
        this.setLocationRelativeTo(null);
        this.setContentPane(contentPane);
    }

    public void setTableModel(List<SongModel> listSong) {
        String[][] data = new String[listSong.size()][];
        String[] columnsName = {"Id", "Name", "Music File URL", "Artwork Image URL"};
        TableModel tableModel = new DefaultTableModel(data, columnsName);
        int i = 0;
        for (SongModel songModel : listSong) {
            tableModel.setValueAt(String.valueOf(songModel.getSongId()), i, 0);
            tableModel.setValueAt(songModel.getSongName(), i, 1);
            tableModel.setValueAt(songModel.getMusicFileUrl(), i, 2);
            tableModel.setValueAt(songModel.getBackgroundImageFileUrl(), i, 3);
            i++;
        }
        table.setModel(tableModel);
    }
    public void addActionListener(ActionListener actionListener) {
        btnExit.addActionListener(actionListener);
    }
    
    public static ArtistSingleAndEpSongView getInstance() {
        return new ArtistSingleAndEpSongView();
    }
}
