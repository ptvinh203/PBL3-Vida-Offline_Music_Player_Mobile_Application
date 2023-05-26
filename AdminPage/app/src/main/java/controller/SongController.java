package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import adminpage.App;
import controller.add.SongAddController;
import controller.edit.SongEditController;
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
import models.SongModel;
import view.SongView;
import view.ToastMessage;

public class SongController implements ActionListener, WindowListener, DocumentListener {
    private final SongView songView;

    @Setter
    private AlbumController albumController;
    @Setter
    private ArtistController artistController;

    @Getter
    private static final SongAddController songAddController = new SongAddController();
    @Getter
    private static final SongEditController songEditController = new SongEditController();

    @Setter
    private IArtistResponse iArtistResponse;
    @Setter
    private ISongResponse iSongResponse;
    @Setter
    private IMyFileResponse iMyFileResponse;
    @Setter
    private IAlbumResponse iAlbumResponse;

    public SongController() {
        songView = SongView.getInstance();
        songView.addActionListener(this);
        songView.addWindowListener(this);
        songView.addDocumentListener(this);

        iSongResponse = new SongResponseImpl();
    }

    private void deleteSong() {
        int row = songView.songTable.getSelectedRow();
        if (row != -1) {
            try {
                int option = JOptionPane.showConfirmDialog(songView, "Are you sure?", "DELETE OBJECT",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (option == 0) {
                    SongModel songModel = iSongResponse
                            .findById(Integer.parseInt(songView.songTable.getValueAt(row, 0).toString().trim()));

                    try {

                        setISongResponse(new SongResponseImpl());
                        setIMyFileResponse(new MyFileResponseImpl());

                        iSongResponse.deleteById(songModel.getSongId());
                        songView.setSongTable(iSongResponse.findAll());
                        ToastMessage toastMessage = new ToastMessage(
                                String.format("Delete song '%s' successfully!", songModel.getSongName()));
                        toastMessage.showToast(
                                songView.getX() + (songView.getWidth() / 2) - (toastMessage.getWidth() / 4),
                                songView.getY() + songView.getHeight() - 55);
                    } catch (NumberFormatException e1) {
                        throw new Exception("Invalid format song file's ID");
                    }
                }

            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(songView, e1.getMessage(), "Invalid format song's ID",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(songView, e1.getMessage(), "Not found object",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editSong() {
        int row = songView.songTable.getSelectedRow();
        if (row != -1) {
            try {
                SongModel songModel = iSongResponse
                        .findById((Integer.parseInt(songView.songTable.getValueAt(row, 0).toString().trim())));

                songView.setEnabled(false);
                songEditController.showGUI(songModel);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(songView, e.getMessage(), "Invalid format song's ID",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(songView, e.getMessage(), "Not found object",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void showUpdateToastMessage(int songId) {
        ToastMessage toastMessage = new ToastMessage(
                String.format("Update song with '%s' successfully!",
                        String.valueOf(songId)));
        toastMessage.showToast(
                songView.getX() + (songView.getWidth() / 2) - (toastMessage.getWidth() / 4),
                songView.getY() + songView.getHeight() - 55);
    }

    public void showCreateToastMessage() {
        ToastMessage toastMessage = new ToastMessage("Create new song successfully!");
        toastMessage.showToast(
                songView.getX() + (songView.getWidth() / 2) - (toastMessage.getWidth() / 4),
                songView.getY() + songView.getHeight() - 55);
    }

    public void setEnabled(boolean enabled) {
        songView.setEnabled(enabled);
    }

    public void setSongTable(List<SongModel> listSongModels) {
        songView.setSongTable(listSongModels);
    }

    public void reset() {
        songView.reset();
    }

    public void changeSongView(String name, int width, int height, boolean isArtistSongView) {
        songView.changeSongView(name, width, height, isArtistSongView);
    }

    public void showGUI(List<SongModel> listsSongModels) {
        songView.setSongTable(listsSongModels);
        songView.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == songView.btnAdd) {
            songView.setEnabled(false);
            songAddController.showGUI();
        } else if (e.getSource() == songView.btnEdit) {
            editSong();
        } else if (e.getSource() == songView.btnDelete) {
            deleteSong();

        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (songView.isArtistSongView()) {
            reset();

            setArtistController(HomePageController.getArtistController());
            setIArtistResponse(new ArtistResponseImpl());
            try {
                artistController.setEnabled(true);
                artistController.setArtistTable(iArtistResponse.findAll());
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } else if (songView.isAlbumSongView()) {
            reset();

            try {
                setAlbumController(HomePageController.getAlbumController());
                setIAlbumResponse(new AlbumResponseImpl());
                albumController.setEnabled(true);
                albumController.showGUI(iAlbumResponse.findAll());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            App.homePageController.showGUI();
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

    @Override
    public void insertUpdate(DocumentEvent e) {
        setISongResponse(new SongResponseImpl());
        try {
            songView.setSongTable(iSongResponse.search(songView.txtSearch.getText()));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        setISongResponse(new SongResponseImpl());
        try {
            songView.setSongTable(iSongResponse.search(songView.txtSearch.getText()));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }
}
