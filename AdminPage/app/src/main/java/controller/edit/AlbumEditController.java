package controller.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import controller.ArtistController;
import httprequest.IAlbumResponse;
import httprequest.IArtistResponse;
import lombok.Setter;
import models.AlbumModel;
import view.edit.AlbumEditView;

public class AlbumEditController implements ActionListener, WindowListener {
    private final AlbumEditView albumEditView;

    @Setter
    private ArtistController artistController;

    @Setter
    private IAlbumResponse iAlbumResponse;
    @Setter
    private IArtistResponse iArtistResponse;

    public AlbumEditController() {
        albumEditView = AlbumEditView.getInstance();
        albumEditView.addActionListener(this);
        albumEditView.addWindowListener(this);
    }

    public void showGUI(AlbumModel albumModel) {
        // albumEditView.setDefaultInfo(albumModel);
        albumEditView.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
