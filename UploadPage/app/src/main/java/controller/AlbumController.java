package controller;

import java.awt.event.ActionListener;

import views.album.AlbumView;

public class AlbumController {
    private AlbumView albumView = AlbumView.getInstance();

    public void addActionListenerHomeButton(ActionListener actionListener) {
        albumView.addActionListener(actionListener);
    }

    public void showGUI(boolean show) {
        if (show) albumView.setVisible(true);
        else albumView.dispose();
    }
}
