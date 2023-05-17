package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.HomePageView;

public class HomePageController implements ActionListener {
    private final HomePageView homePageView;

    public HomePageController() {
        homePageView = HomePageView.getInstance();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public void showGUI(boolean check) {
        if (check)
            homePageView.setVisible(true);
        else
            homePageView.dispose();
    }
}
