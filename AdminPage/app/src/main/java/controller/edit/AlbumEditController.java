package controller.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import controller.AlbumController;
import controller.ArtistController;
import controller.HomePageController;
import httprequest.IAlbumResponse;
import httprequest.IArtistResponse;
import httprequest.implement.AlbumResponseImpl;
import lombok.Setter;
import models.AlbumModel;
import view.edit.AlbumEditView;

public class AlbumEditController implements ActionListener, WindowListener {
    private final AlbumEditView albumEditView;

    @Setter
    private ArtistController artistController;
    @Setter
    private AlbumController albumController;

    @Setter
    private IAlbumResponse iAlbumResponse;
    @Setter
    private IArtistResponse iArtistResponse;

    public AlbumEditController() {
        albumEditView = AlbumEditView.getInstance();
        albumEditView.addActionListener(this);
        albumEditView.addWindowListener(this);
    }

    public void showGUI(AlbumModel albumModel) {
        albumEditView.setDefaultInfo(albumModel);
        albumEditView.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == albumEditView.btnUpdate) {
            try {
                AlbumModel albumModel = albumEditView.getAlbumModel();

                setAlbumController(HomePageController.getAlbumController());
                setIAlbumResponse(new AlbumResponseImpl());

                albumEditView.dispose();
                iAlbumResponse.update(albumModel.getAlbumId(), albumModel);
                albumController.setEnabled(true);
                albumController.setAlbumModel(iAlbumResponse.findAll());
                albumController.showUpdateToastMessage(albumModel.getAlbumId());
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(albumEditView, ex.getMessage(), "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        setIAlbumResponse(new AlbumResponseImpl());
        setAlbumController(HomePageController.getAlbumController());
        try {
            albumController.setEnabled(true);
            albumController.setAlbumModel(iAlbumResponse.findAll());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
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
