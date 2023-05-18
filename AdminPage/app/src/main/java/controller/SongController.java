package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import models.SongModel;
import view.SongView;

public class SongController implements ActionListener {
    private final SongView songView;

    public SongController() {
        songView = SongView.getInstance();
        songView.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public void showGUI(List<SongModel> listsSongModels) {
        songView.setSongTable(listsSongModels);
        songView.setVisible(true);
    }

}
