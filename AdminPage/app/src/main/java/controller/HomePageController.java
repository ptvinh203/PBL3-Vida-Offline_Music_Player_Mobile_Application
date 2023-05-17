package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.HomePageView;

public class HomePageController implements ActionListener {
    private final HomePageView homePageView;

    private ArtistController artistController;
    private SongController songController;
    private AlbumController albumController;

    public HomePageController() {
        homePageView = HomePageView.getInstance();
        homePageView.addActionListener(this);

        artistController = new ArtistController();
        songController = new SongController();
        albumController = new AlbumController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homePageView.btnSong) {
            songController.showGUI();
        } else if (e.getSource() == homePageView.btnAlbum) {
            albumController.showGUI();
        } else {
            artistController.showGUI();
        }
    }

    public void showGUI() {
        homePageView.setVisible(true);
    }
}
