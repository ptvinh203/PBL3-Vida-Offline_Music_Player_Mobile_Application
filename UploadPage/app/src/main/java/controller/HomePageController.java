package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.HomePageView;

public class HomePageController implements ActionListener{
    private HomePageView homePageView = HomePageView.getInstance();

    private ArtistController artistController = new ArtistController();
    private SongController songController = new SongController();
    private AlbumController albumController = new AlbumController();
    
    public HomePageController() {
        homePageView.addActionListener(this); 
        artistController.addActionListenerHomeButton(this);
        songController.addActionListenerHomeButton(this);
        albumController.addActionListenerHomeButton(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command) {
            case "SONG": {
                homePageView.dispose();
                songController.showGUI(true);
                break;
            }
            case "ARTIST": {
                homePageView.dispose();
                artistController.showGUI(true);
                break;
            }
            case "ALBUM": {
                homePageView.dispose();
                albumController.showGUI(true);
                break;
            }
            case "HOME": {
                artistController.showGUI(false);
                songController.showGUI(false);
                albumController.showGUI(false);
                homePageView.setVisible(true);
            }
        }
    }
    
    public void showGUI() {
        homePageView.setVisible(true);
    }
}
