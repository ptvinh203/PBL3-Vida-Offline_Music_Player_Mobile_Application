package controller.eventHandle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import httprequest.IAlbumResponse;
import httprequest.implement.AlbumResponseImpl;
import models.AlbumModel;
import views.artist.ArtistAlbumView;

public class ArtistAlbumController implements ActionListener{
    private ArtistAlbumView artistAlbumView = ArtistAlbumView.getInstance();
    private List<AlbumModel> listAlbumModels;

    private IAlbumResponse iAlbumResponse = new AlbumResponseImpl();

    public ArtistAlbumController() {
        artistAlbumView.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command) {
            case "SHOW SONG": {
                if (artistAlbumView.albumChoice.getItemCount() != 0){
                    setSongTableModel(artistAlbumView.albumChoice.getItem(artistAlbumView.albumChoice.getSelectedIndex()));
                }
                break;
            }
            case "CLOSE": {
                artistAlbumView.dispose();
            }
        }
    }

    public void resetTableSong() {
        artistAlbumView.resetTableSong();
    }
    public void setTitle(String artistName) {
        artistAlbumView.setTitle(artistName + " - Ablums");
    }
    public void setAlbumChoice(List<AlbumModel> listAlbumModels) {
        this.listAlbumModels = listAlbumModels;
        artistAlbumView.setAlbumChoice(this.listAlbumModels);
    }
    public void setSongTableModel(String albumName) {
        if (listAlbumModels != null && !listAlbumModels.isEmpty()) {
            for (AlbumModel albumModel : listAlbumModels) {
                if (albumModel.getAlbumName().compareToIgnoreCase(albumName) == 0) {
                    try {
                        artistAlbumView.setSongTableModel(iAlbumResponse.findAllSongByAlbumId(albumModel.getAlbumId())); 
                    }
                    catch(Exception ex) {
                        artistAlbumView.showMessageBox(ex.getMessage());
                    }
                }
            }
        }
    }
    public void showGUI() {
        artistAlbumView.setVisible(true); 
    }
}
