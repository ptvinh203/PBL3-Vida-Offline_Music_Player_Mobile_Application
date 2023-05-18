package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import models.AlbumModel;
import view.AlbumView;

public class AlbumController implements ActionListener {
    private final AlbumView albumView;

    public AlbumController() {
        albumView = AlbumView.getInstance();
        albumView.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public void showGUI(List<AlbumModel> listAlbumModel) {
        albumView.setAlbumTable(listAlbumModel);
        albumView.setVisible(true);
    }

}
