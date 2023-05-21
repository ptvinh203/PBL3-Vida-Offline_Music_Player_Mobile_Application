package controller.edit;

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
import view.edit.ArtistEditView;

public class ArtistEditController implements ActionListener, WindowListener {
    private String artistImageFile_old;
    private final ArtistEditView artistEditView;

    @Setter
    private ArtistController artistController;

    @Setter
    private IArtistResponse iArtistResponse;
    @Setter
    private IMyFileResponse iMyFileResponse;

    public ArtistEditController() {
        artistEditView = ArtistEditView.getInstance();
        artistEditView.addActionListener(this);
        artistEditView.addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == artistEditView.btnUpdate) {
            try {
                ArtistModel artistModel = artistEditView.getArtistModel();

                setIArtistResponse(new ArtistResponseImpl());
                setIMyFileResponse(new MyFileResponseImpl());

                boolean udpate = iArtistResponse.update(artistModel.getArtistId(), artistModel);
                if (udpate) {
                    if (artistImageFile_old.compareTo(artistEditView.getTxtImage().getText()) != 0) {
                        File artistImageFile_new = artistEditView.getFile();
                        iMyFileResponse.artistFileUpload(artistModel.getArtistId(), artistImageFile_new);

                        if (artistImageFile_old != null && !artistImageFile_old.isEmpty()) {
                            String[] arrStr = artistImageFile_old.split("/");
                            int fileId_old = Integer.parseInt(arrStr[arrStr.length - 1]);
                            iMyFileResponse.deleteById(fileId_old);
                        }

                    }
                }

                artistEditView.dispose();
                setArtistController(HomePageController.getArtistController());
                artistController.showGUI(iArtistResponse.findAll());
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(artistEditView, ex.getMessage(), "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == artistEditView.btnChooseFile) {
            artistEditView.fileChooser.showOpenDialog(artistEditView);
            if (artistEditView.fileChooser.getSelectedFile() != null) {
                artistEditView.getTxtImage().setText(artistEditView.fileChooser.getSelectedFile().getAbsolutePath());
            }
        }
    }

    public void showGUI(ArtistModel artistModel) {
        artistEditView.setDefaultInfor(artistModel);
        artistEditView.setVisible(true);
        artistImageFile_old = artistEditView.getTxtImage().getText();
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
            artistController.showGUI(iArtistResponse.findAll());
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
