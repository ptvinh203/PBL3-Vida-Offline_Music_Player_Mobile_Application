package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.HomePageView;

public class HomePageController implements ActionListener{
    private HomePageView homePageView = HomePageView.getInstance();

    private ArtistController artistController = new ArtistController();

    public HomePageController() {
        homePageView.addActionListener(this); 
        artistController.addActionListenerHomeButton(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command) {
            case "SONG": {

                break;
            }
            case "ARTIST": {
                homePageView.dispose();
                artistController.showGUI(true);
                break;
            }
            case "ALBUM": {
                break;
            }
            case "HOME": {
                artistController.showGUI(false);
                homePageView.setVisible(true);
            }
        }
    }
    
    public void showGUI() {
        homePageView.setVisible(true);
    }
}
