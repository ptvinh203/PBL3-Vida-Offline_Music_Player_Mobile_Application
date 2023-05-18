package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import models.ArtistModel;
import view.ArtistView;

public class ArtistController implements ActionListener {
    private final ArtistView artistView;

    public ArtistController() {
        artistView = ArtistView.getInstance();
        artistView.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public void showGUI(List<ArtistModel> listArtistModels) {
        artistView.setArtistTable(listArtistModels);
        artistView.setVisible(true);
    }

}
