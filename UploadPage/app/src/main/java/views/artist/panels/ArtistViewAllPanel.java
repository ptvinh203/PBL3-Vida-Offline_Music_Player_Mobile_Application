package views.artist.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import lombok.Getter;
import lombok.Setter;
import models.ArtistModel;

@Setter
@Getter
public class ArtistViewAllPanel extends JPanel {
    private JTextField txtTitle;
    private JTable tableArtist;
    private JPanel tablePanel, buttonPanel;
    private JButton btnAlbum, btnSingleAndEpSong, btnRefresh;

    private void init() {
        txtTitle = new JTextField("LIST ALL ARTISTS");
        txtTitle.setFont(new Font("Verdana", Font.BOLD, 24));
        txtTitle.setHorizontalAlignment(SwingConstants.CENTER);
        txtTitle.setBackground(Color.GRAY);
        txtTitle.setEnabled(false);

        String[][] data = new String[0][];
        String[] columnsName = { "ID", "Name", "ImageUrl" };
        tableArtist = new JTable(data, columnsName);

        btnRefresh = new JButton("REFRESH");
        btnAlbum = new JButton("SHOW ALBUM");
        btnSingleAndEpSong = new JButton("SHOW SINGLE AND EPS SONGS");
        buttonPanel = new JPanel(new GridLayout(1, 3, 30, 50));
        buttonPanel.add(btnAlbum);
        buttonPanel.add(btnSingleAndEpSong);
        buttonPanel.add(btnRefresh);

        tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(tableArtist.getTableHeader(), BorderLayout.NORTH);
        tablePanel.add(new JScrollPane(tableArtist), BorderLayout.CENTER);
    }

    private ArtistViewAllPanel() {
        super(new BorderLayout(5, 5));
        init();
        add(txtTitle, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setTableModel(List<ArtistModel> listArtistModels) {
        String[][] data = new String[listArtistModels.size()][3];
        String[] columnsName = { "ID", "Name", "ImageUrl" };
        TableModel tableModel = new DefaultTableModel(data, columnsName);
        int i = 0;
        for (ArtistModel artistModel : listArtistModels) {
            tableModel.setValueAt(String.valueOf(artistModel.getArtistId()), i, 0);
            tableModel.setValueAt(artistModel.getArtistName(), i, 1);
            tableModel.setValueAt(artistModel.getArtistImageUrl(), i, 2);
            i++;
        }
        tableArtist.setModel(tableModel);
    }

    public void addActionListener(ActionListener actionListener) {
        btnAlbum.addActionListener(actionListener);
        btnSingleAndEpSong.addActionListener(actionListener);
        btnRefresh.addActionListener(actionListener);
    }

    public static ArtistViewAllPanel getInstance() {
        return new ArtistViewAllPanel();
    }
}
