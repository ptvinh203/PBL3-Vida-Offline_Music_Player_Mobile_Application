package controller.eventHandle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import models.SongModel;
import views.artist.ArtistSingleAndEpSongView;

public class ArtistSingleAndEpSongController implements ActionListener{
    private ArtistSingleAndEpSongView artistSingleAndEpSongView = ArtistSingleAndEpSongView.getInstance();


    public ArtistSingleAndEpSongController() {
        artistSingleAndEpSongView.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command) {
            case "CLOSE": {
                artistSingleAndEpSongView.dispose();
                break;
            }
        }
    }

    public void setTitle(String artistName) {
        artistSingleAndEpSongView.setTitle(artistName + " - Single And Ep Songs");
    }
    public void setTableModel(List<SongModel> listSongModels) {
        artistSingleAndEpSongView.setTableModel(listSongModels);
    }
    public void showGUI() {
        artistSingleAndEpSongView.setVisible(true);
    }
}
