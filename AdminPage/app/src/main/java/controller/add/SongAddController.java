package controller.add;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JOptionPane;

import controller.AlbumController;
import controller.ArtistController;
import controller.HomePageController;
import controller.SongController;
import httprequest.IAlbumResponse;
import httprequest.IArtistResponse;
import httprequest.IMyFileResponse;
import httprequest.ISongResponse;
import httprequest.implement.AlbumResponseImpl;
import httprequest.implement.ArtistResponseImpl;
import httprequest.implement.MyFileResponseImpl;
import httprequest.implement.SongResponseImpl;
import lombok.Getter;
import lombok.Setter;
import models.AlbumModel;
import models.ArtistModel;
import models.SongModel;
import view.add.SongAddView;

public class SongAddController implements ActionListener, WindowListener {
    private final SongAddView songAddView;

    @Setter
    @Getter
    private int albumId = -1, artistId = -1;

    @Setter
    private SongController songController;
    @Setter
    private AlbumController albumController;
    @Setter
    private ArtistController artistController;

    @Setter
    private ISongResponse iSongResponse;
    @Setter
    private IArtistResponse iArtistResponse;
    @Setter
    private IAlbumResponse iAlbumResponse;
    @Setter
    private IMyFileResponse iMyFileResponse;

    public SongAddController() {
        songAddView = SongAddView.getInstance();
        songAddView.addActionListener(this);
        songAddView.addWindowListener(this);
    }

    public void showGUI() {
        albumId = -1;
        artistId = -1;
        songAddView.reset();
        setIAlbumResponse(new AlbumResponseImpl());
        setIArtistResponse(new ArtistResponseImpl());
        try {
            if (albumId != -1) {
                AlbumModel albumModel = iAlbumResponse.findById(albumId);
                songAddView.getTxtAlbum().setText(albumModel.getAlbumName());
            }
            if (artistId != -1) {
                ArtistModel artistModel = iArtistResponse.findById(artistId);
                songAddView.getTxtArtist().setText(artistModel.getArtistName());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        songAddView.setVisible(true);
    }

    private void createSong() {
        try {
            File musicFile = songAddView.getMusicFile();
            File artworkFile = songAddView.getArtworkFile();
            SongModel songModel = songAddView.getSongModel();

            setISongResponse(new SongResponseImpl());
            setIMyFileResponse(new MyFileResponseImpl());

            SongModel fromServer = null;
            if (albumId != -1) {
                fromServer = iSongResponse.create(songModel, albumId, false);
            } else if (artistId != -1) {
                fromServer = iSongResponse.create(songModel, artistId, true);
            } else {
                throw new Exception("Please choose an album or an artist!");
            }

            if (fromServer != null) {
                iMyFileResponse.songFileUpload(fromServer.getSongId(), musicFile);
                iMyFileResponse.songFileUpload(fromServer.getSongId(), artworkFile);

                songAddView.dispose();
                setSongController(HomePageController.getSongController());
                songController.showGUI(iSongResponse.findAll());
            } else {
                throw new Exception("Create-new-song: unsuccessfully!");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(songAddView, ex.getMessage(), "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == songAddView.btnChooseFile) {
            songAddView.fileChooser.showOpenDialog(songAddView);
            if (songAddView.fileChooser.getSelectedFile() != null) {
                String file = songAddView.fileChooser.getSelectedFile().getAbsolutePath();
                String[] arr = file.split("/");
                if (arr[arr.length - 1].contains(".mp3")) {
                    songAddView.getTxtMusicFile().setText(file);
                } else if (arr[arr.length - 1].contains(".jpg") || arr[arr.length - 1].contains(".png")) {
                    songAddView.getTxtArtworkFile().setText(file);
                } else {
                    JOptionPane.showMessageDialog(songAddView,
                            "Please choose image file (.png or .jpg) or music file (.mp3)", "File chooser",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else if (e.getSource() == songAddView.btnChooseArtist) {
            setArtistController(HomePageController.getArtistController());
            setIArtistResponse(new ArtistResponseImpl());
            try {
                songAddView.dispose();
                artistController.chooseArtistMode();
                artistController.showGUI(iArtistResponse.findAll());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == songAddView.btnChooseAlbum) {
            setAlbumController(HomePageController.getAlbumController());
            setIAlbumResponse(new AlbumResponseImpl());
            try {
                songAddView.dispose();
                albumController.chooseAlbumMode();
                albumController.showGUI(iAlbumResponse.findAll());

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == songAddView.btnCreate) {
            createSong();
        } else if (e.getSource() == songAddView.btnReset) {
            artistId = -1;
            albumId = -1;
            songAddView.reset();
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        setSongController(HomePageController.getSongController());
        setISongResponse(new SongResponseImpl());
        try {
            songController.showGUI(iSongResponse.findAll());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
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
