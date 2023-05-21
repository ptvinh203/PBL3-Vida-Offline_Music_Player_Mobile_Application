package controller.add;

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
import httprequest.implement.ArtistResponseImpl;
import lombok.Getter;
import lombok.Setter;
import models.AlbumModel;
import models.ArtistModel;
import view.add.AlbumAddView;

public class AlbumAddController implements ActionListener, WindowListener {
    private final AlbumAddView albumAddView;

    @Setter
    @Getter
    private int artistId = -1;

    @Setter
    private ArtistController artistController;
    @Setter
    private AlbumController albumController;

    @Setter
    private IAlbumResponse iAlbumResponse;
    @Setter
    private IArtistResponse iArtistResponse;

    public AlbumAddController() {
        albumAddView = AlbumAddView.getInstance();
        albumAddView.addActionListener(this);
        albumAddView.addWindowListener(this);

        iAlbumResponse = new AlbumResponseImpl();
    }

    public void showGUI() {
        // albumAddView.reset();
        setIAlbumResponse(new AlbumResponseImpl());
        setIArtistResponse(new ArtistResponseImpl());

        try {
            if (artistId != -1) {
                ArtistModel artistModel = iArtistResponse.findById(artistId);
                albumAddView.getTxtArtist().setText(artistModel.getArtistName());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        albumAddView.setVisible(true);
    }

    public boolean checkValidAlbum(AlbumModel albumModel) throws Exception {
        setIAlbumResponse(new AlbumResponseImpl());
        for (AlbumModel albumModel2 : iAlbumResponse.findAll()) {
            if (albumModel2.getAlbumName().compareTo(albumModel.getAlbumName()) == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == albumAddView.btnCreate) {
            try {
                AlbumModel albumModel = albumAddView.getAlbumModel();

                if (!checkValidAlbum(albumModel)) {
                    throw new Exception("This album have been existed in Vida Application!");
                }

                setIAlbumResponse(new AlbumResponseImpl());

                AlbumModel fromServer = null;
                if (artistId != -1) {
                    fromServer = iAlbumResponse.create(albumModel, artistId);
                } else {
                    throw new Exception("Please choose an artist!");
                }

                if (fromServer != null) {
                    albumAddView.dispose();
                    setAlbumController(HomePageController.getAlbumController());
                    albumController.setEnabled(true);
                    albumController.setAlbumModel(iAlbumResponse.findAll());
                } else {
                    throw new Exception("Create-new-album: unsuccessfully!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(albumAddView, ex.getMessage(), "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == albumAddView.btnReset) {
            albumAddView.reset();
        } else if (e.getSource() == albumAddView.btnChooseArtist) {
            setArtistController(HomePageController.getArtistController());
            setIArtistResponse(new ArtistResponseImpl());
            try {
                albumAddView.dispose();
                artistController.chooseAlbumArtistMode();
                artistController.showGUI(iArtistResponse.findAll());
            } catch (Exception e1) {
                e1.printStackTrace();
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
