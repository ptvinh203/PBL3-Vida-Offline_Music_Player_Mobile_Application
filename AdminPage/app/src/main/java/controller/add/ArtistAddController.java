package controller.add;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JOptionPane;

import controller.ArtistController;
import controller.HomePageController;
import httprequest.IArtistResponse;
import httprequest.IMyFileResponse;
import httprequest.implement.ArtistResponseImpl;
import httprequest.implement.MyFileResponseImpl;
import lombok.Setter;
import models.ArtistModel;
import view.add.ArtistAddView;

public class ArtistAddController implements ActionListener, WindowListener {
    private final ArtistAddView artistAddView;

    @Setter
    private IArtistResponse iArtistResponse;
    @Setter
    private IMyFileResponse iMyFileResponse;

    @Setter
    private ArtistController artistController;

    public ArtistAddController() {
        artistAddView = ArtistAddView.getInstance();
        artistAddView.addActionListener(this);
        artistAddView.addWindowListener(this);
    }

    public boolean checkValidArtist(ArtistModel artistModel) throws Exception {
        setIArtistResponse(new ArtistResponseImpl());
        for (ArtistModel artistModel2 : iArtistResponse.findAll()) {
            if (artistModel.getArtistName().compareTo(artistModel2.getArtistName()) == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == artistAddView.btnChooseFile) {
            artistAddView.fileChooser.showOpenDialog(artistAddView);
            if (artistAddView.fileChooser.getSelectedFile() != null) {
                artistAddView.getTxtImage().setText(artistAddView.fileChooser.getSelectedFile().getAbsolutePath());
            }
        } else if (e.getSource() == artistAddView.btnCreate) {
            try {
                File artistImageFile = artistAddView.getFile();
                ArtistModel artistModel = artistAddView.getArtistModel();

                if (!checkValidArtist(artistModel)) {
                    throw new Exception("This artist have been existed in Vida Application!");
                }

                setIArtistResponse(new ArtistResponseImpl());
                setIMyFileResponse(new MyFileResponseImpl());

                ArtistModel fromServer = iArtistResponse.create(artistModel);
                iMyFileResponse.artistFileUpload(fromServer.getArtistId(), artistImageFile);

                artistAddView.dispose();
                setArtistController(HomePageController.getArtistController());
                artistController.setEnabled(true);
                artistController.setArtistTable(iArtistResponse.findAll());
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(artistAddView, e1.getMessage(), "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == artistAddView.btnReset) {
            artistAddView.reset();
        }
    }

    public void showGUI() {
        artistAddView.reset();
        artistAddView.setVisible(true);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        setIArtistResponse(new ArtistResponseImpl());
        setArtistController(HomePageController.getArtistController());
        try {
            artistController.setEnabled(true);
            artistController.setArtistTable(iArtistResponse.findAll());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
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
