package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import httprequest.IAlbumResponse;
import httprequest.IArtistResponse;
import httprequest.ISongResponse;
import httprequest.IUserResponse;
import httprequest.implement.AlbumResponseImpl;
import httprequest.implement.ArtistResponseImpl;
import httprequest.implement.SongResponseImpl;
import httprequest.implement.UserResponseImpl;
import lombok.Getter;
import view.HomePageView;

public class HomePageController implements ActionListener {
    private final HomePageView homePageView;

    @Getter
    private static final ArtistController artistController = new ArtistController();
    @Getter
    private static final SongController songController = new SongController();
    @Getter
    private static final AlbumController albumController = new AlbumController();
    @Getter
    private static final UserController userController = new UserController();

    private IArtistResponse iArtistResponse;
    private ISongResponse iSongResponse;
    private IAlbumResponse iAlbumResponse;
    private IUserResponse iUserResponse;

    public HomePageController() {
        homePageView = HomePageView.getInstance();
        homePageView.addActionListener(this);

        iArtistResponse = new ArtistResponseImpl();
        iSongResponse = new SongResponseImpl();
        iAlbumResponse = new AlbumResponseImpl();
        iUserResponse = new UserResponseImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homePageView.btnSong) {
            try {
                songController.showGUI(iSongResponse.findAll());
                homePageView.setVisible(false);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "Song Response Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == homePageView.btnAlbum) {
            try {
                albumController.showGUI(iAlbumResponse.findAll());
                homePageView.setVisible(false);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "Album Response Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == homePageView.btnUser) {
            try {
                userController.showGUI(iUserResponse.findAll());
                homePageView.setVisible(false);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "User Response Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            try {
                artistController.showGUI(iArtistResponse.findAll());
                homePageView.setVisible(false);
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
