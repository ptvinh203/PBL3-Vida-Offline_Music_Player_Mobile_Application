package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import httprequest.IAlbumResponse;
import httprequest.IArtistResponse;
import httprequest.ISongResponse;
import httprequest.implement.AlbumResponseImpl;
import httprequest.implement.ArtistResponseImpl;
import httprequest.implement.SongResponseImpl;
import view.HomePageView;

public class HomePageController implements ActionListener {
    private final HomePageView homePageView;

    private ArtistController artistController;
    private SongController songController;
    private AlbumController albumController;

    private IArtistResponse iArtistResponse;
    private ISongResponse iSongResponse;
    private IAlbumResponse iAlbumResponse;

    public HomePageController() {
        homePageView = HomePageView.getInstance();
        homePageView.addActionListener(this);

        artistController = new ArtistController();
        songController = new SongController();
        albumController = new AlbumController();

        iArtistResponse = new ArtistResponseImpl();
        iSongResponse = new SongResponseImpl();
        iAlbumResponse = new AlbumResponseImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homePageView.btnSong) {
            try {
                songController.showGUI(iSongResponse.findAll());
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "Song Response Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == homePageView.btnAlbum) {
            try {
                albumController.showGUI(iAlbumResponse.findAll());
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "Album Response Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            try {
                artistController.showGUI(iArtistResponse.findAll());
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "Artist Response Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void showGUI() {
        homePageView.setVisible(true);
    }
}
