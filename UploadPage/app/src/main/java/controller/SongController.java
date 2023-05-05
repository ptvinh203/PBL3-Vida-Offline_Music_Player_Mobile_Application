package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.song.SongView;

public class SongController implements ActionListener{
    private SongView songView = SongView.getInstance();



    public void addActionListenerHomeButton(ActionListener actionListener) {
        songView.addActionListener(actionListener);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public void showGUI(boolean show) {
        if (show) {
            songView.setVisible(true);
        }
        else songView.dispose();
    }
    
}
