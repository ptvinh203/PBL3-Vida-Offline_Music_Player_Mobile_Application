package views.artist;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.AlbumModel;
import models.SongModel;

public class ArtistAlbumView extends JFrame{
    private JPanel contentPane, tablePanel, choicePanel;
    public Choice albumChoice;
    private JTable tableSong;
    private JButton btnShow, btnExit;

    private void init() {
        contentPane = new JPanel(new BorderLayout(5, 5));
        tablePanel = new JPanel(new BorderLayout(1, 1));
        choicePanel = new JPanel(new GridLayout(1, 2, 10, 0));
        
        albumChoice = new Choice();
        btnShow = new JButton("SHOW SONG");
        choicePanel.add(albumChoice);
        choicePanel.add(btnShow);
        
        String[][] data = new String[0][4];
        String[] columnsName = {"Id", "Name", "Music File URL", "Artwork Image URL"};
        tableSong = new JTable(data, columnsName) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tablePanel.add(tableSong.getTableHeader(), BorderLayout.NORTH);
        tablePanel.add(new JScrollPane(tableSong), BorderLayout.CENTER);
        
        btnExit = new JButton("CLOSE");


        contentPane.add(btnExit, BorderLayout.SOUTH);
        contentPane.add(choicePanel, BorderLayout.NORTH);
        contentPane.add(tablePanel, BorderLayout.CENTER);
    }
    private ArtistAlbumView() {
        init();
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setBounds(100, 100, 800, 400);
        this.setLocationRelativeTo(null);
        this.setContentPane(contentPane);
    }

    public void setAlbumChoice(List<AlbumModel> listAlbumModels) {
        albumChoice.removeAll();
        for (AlbumModel albumModel : listAlbumModels) {
            albumChoice.add(albumModel.getAlbumName());
        }
    }
    public void setSongTableModel(List<SongModel> listSongModels) {
        String[][] data = new String[listSongModels.size()][];
        String[] columnsName = {"Id", "Name", "Music File URL", "Artwork Image URL"};
        TableModel tableModel = new DefaultTableModel(data, columnsName);
        int i = 0;
        for (SongModel songModel : listSongModels) {
            tableModel.setValueAt(String.valueOf(songModel.getSongId()), i, 0);
            tableModel.setValueAt(songModel.getSongName(), i, 1);
            tableModel.setValueAt(songModel.getMusicFileUrl(), i, 2);
            tableModel.setValueAt(songModel.getBackgroundImageFileUrl(), i, 3);
            i++;
        }
        tableSong.setModel(tableModel);
    }
    public void resetTableSong() {
        String[][] data = new String[0][4];
        String[] columnsName = {"Id", "Name", "Music File URL", "Artwork Image URL"};
        tableSong.setModel(new DefaultTableModel(data, columnsName));
    }
    public void addActionListener(ActionListener actionListener) {
        btnShow.addActionListener(actionListener);
        btnExit.addActionListener(actionListener);
    }
    public void showMessageBox(String message) {
        JOptionPane.showMessageDialog(this, message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    public static ArtistAlbumView getInstance() {
        return new ArtistAlbumView();
    }
}
