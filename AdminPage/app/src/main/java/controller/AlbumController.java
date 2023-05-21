package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JOptionPane;

import controller.add.SongAddController;
import httprequest.IAlbumResponse;
import httprequest.IArtistResponse;
import httprequest.implement.AlbumResponseImpl;
import httprequest.implement.ArtistResponseImpl;
import lombok.Setter;
import models.AlbumModel;
import view.AlbumView;

public class AlbumController implements ActionListener, WindowListener {
    private final AlbumView albumView;

    @Setter
    private ArtistController artistController;
    @Setter
    private SongController songController;
    @Setter
    private SongAddController songAddController;

    @Setter
    private IArtistResponse iArtistResponse;
    @Setter
    private IAlbumResponse iAlbumResponse;

    public AlbumController() {
        albumView = AlbumView.getInstance();
        albumView.addActionListener(this);
        albumView.addWindowListener(this);

        iAlbumResponse = new AlbumResponseImpl();
    }

    private void showSong() {
        int row = albumView.albumTable.getSelectedRow();
        if (row != -1) {
            try {
                AlbumModel albumModel = iAlbumResponse
                        .findById(Integer.parseInt(albumView.albumTable.getValueAt(row, 0).toString().trim()));

                setSongController(HomePageController.getSongController());
                albumView.setVisible(false);
                songController.changeSongView(albumModel.getAlbumName(), 1102, 519, false);
                songController.showGUI(albumModel.getSongsAlbum());
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(albumView, e1.getMessage(), "Invalid format album's ID",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(albumView, e1.getMessage(), "Not found object",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == albumView.btnShowSong) {
            showSong();
        }
    }

    public void showGUI() {
        albumView.setVisible(true);
    }

    public void showGUI(List<AlbumModel> listAlbumModel) {
        albumView.setAlbumTable(listAlbumModel);
        albumView.setVisible(true);
    }

    public void setArtistId(Integer artistId) {
        albumView.setArtistId(artistId);
    }

    public void reset() {
        albumView.reset();
    }

    public void artistAlbumView(String artistName, int width, int height) {
        albumView.artistAlbumView(artistName, width, height);
    }

    public void chooseAlbumMode() {
        albumView.chooseAlbumMode();
    }

    public int getAlbumId() {
        int row = albumView.albumTable.getSelectedRow();
        if (row != -1) {
            try {
                int result = Integer.parseInt(albumView.albumTable.getValueAt(row, 0).toString().trim());
                return result;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(albumView, ex.getMessage(), "Invalid format album's ID",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return -1;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (albumView.isArtistAlbumView()) {
            reset();
            albumView.setArtistId(null);

            setArtistController(HomePageController.getArtistController());
            setIArtistResponse(new ArtistResponseImpl());
            try {
                artistController.showGUI(iArtistResponse.findAll());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else if (albumView.isChooseAlbumMode()) {
            reset();
            setSongAddController(SongController.getSongAddController());
            songAddController.setAlbumId(getAlbumId());
            songAddController.showGUI();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        if (albumView.getArtistId() != null) {
            setIArtistResponse(new ArtistResponseImpl());
            try {
                showGUI(iArtistResponse.findById(albumView.getArtistId()).getAlbums());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
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
