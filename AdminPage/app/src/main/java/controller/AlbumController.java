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
import controller.add.AlbumAddController;
import controller.add.SongAddController;
import controller.edit.AlbumEditController;
import httprequest.IAlbumResponse;
import httprequest.IArtistResponse;
import httprequest.implement.AlbumResponseImpl;
import httprequest.implement.ArtistResponseImpl;
import lombok.Getter;
import lombok.Setter;
import models.AlbumModel;
import view.AlbumView;
import view.ToastMessage;

public class AlbumController implements ActionListener, WindowListener, DocumentListener {
    private final AlbumView albumView;

    @Setter
    private ArtistController artistController;
    @Setter
    private SongController songController;
    @Setter
    private SongAddController songAddController;

    @Getter
    private static final AlbumAddController albumAddController = new AlbumAddController();
    @Getter
    private static final AlbumEditController albumEditController = new AlbumEditController();

    @Setter
    private IArtistResponse iArtistResponse;
    @Setter
    private IAlbumResponse iAlbumResponse;

    public AlbumController() {
        albumView = AlbumView.getInstance();
        albumView.addActionListener(this);
        albumView.addWindowListener(this);
        albumView.addDocumentListener(this);

        iAlbumResponse = new AlbumResponseImpl();
    }

    private void showSong() {
        int row = albumView.albumTable.getSelectedRow();
        if (row != -1) {
            try {
                AlbumModel albumModel = iAlbumResponse
                        .findById(Integer.parseInt(albumView.albumTable.getValueAt(row, 0).toString().trim()));

                setSongController(HomePageController.getSongController());

                albumView.setEnabled(false);
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

    private void editAlbum() {
        int row = albumView.albumTable.getSelectedRow();
        if (row != -1) {
            try {
                AlbumModel albumModel = iAlbumResponse
                        .findById((Integer.parseInt(albumView.albumTable.getValueAt(row, 0).toString().trim())));

                albumView.setEnabled(false);
                albumEditController.showGUI(albumModel);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(albumView, e.getMessage(), "Invalid format artist's ID",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(albumView, e.getMessage(), "Not found object",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void delete() {
        int row = albumView.albumTable.getSelectedRow();
        if (row != -1) {
            try {
                int option = JOptionPane.showConfirmDialog(albumView, "Are you sure?", "DELETE OBJECT",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (option == 0) {
                    AlbumModel albumModel = iAlbumResponse
                            .findById(Integer.parseInt(albumView.albumTable.getValueAt(row, 0).toString().trim()));

                    try {
                        setIAlbumResponse(new AlbumResponseImpl());

                        iAlbumResponse.deleteById(albumModel.getAlbumId());
                        albumView.setAlbumTable(iAlbumResponse.findAll());
                        ToastMessage toastMessage = new ToastMessage(
                                String.format("Delete album '%s' successfully!", albumModel.getAlbumName()));
                        toastMessage.showToast(
                                albumView.getX() + (albumView.getWidth() / 2) - (toastMessage.getWidth() / 4),
                                albumView.getY() + albumView.getHeight() - 55);
                    } catch (NumberFormatException e1) {
                        throw new Exception("Invalid format album file's ID");
                    }
                }
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
        } else if (e.getSource() == albumView.btnAdd) {
            albumView.setEnabled(false);
            albumAddController.showGUI();
        } else if (e.getSource() == albumView.btnEdit) {
            editAlbum();
        } else if (e.getSource() == albumView.btnDelete) {
            delete();
        }
    }

    public void showUpdateToastMessage(int albumId) {
        ToastMessage toastMessage = new ToastMessage(
                String.format("Update album with '%s' successfully!",
                        String.valueOf(albumId)));
        toastMessage.showToast(
                albumView.getX() + (albumView.getWidth() / 2) - (toastMessage.getWidth() / 4),
                albumView.getY() + albumView.getHeight() - 55);
    }

    public void showCreateToastMessage() {
        ToastMessage toastMessage = new ToastMessage("Create new album successfully!");
        toastMessage.showToast(
                albumView.getX() + (albumView.getWidth() / 2) - (toastMessage.getWidth() / 4),
                albumView.getY() + albumView.getHeight() - 55);
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

    public void setAlbumModel(List<AlbumModel> albumModels) {
        albumView.setAlbumTable(albumModels);
    }

    public void artistAlbumView(String artistName, int width, int height) {
        albumView.artistAlbumView(artistName, width, height);
    }

    public void chooseAlbumMode() {
        albumView.chooseAlbumMode();
    }

    public void setEnabled(boolean enabled) {
        albumView.setEnabled(enabled);
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
                artistController.setEnabled(true);
                artistController.setArtistTable(iArtistResponse.findAll());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else if (albumView.isChooseAlbumMode()) {
            reset();
            setSongAddController(SongController.getSongAddController());
            songAddController.setAlbumId(getAlbumId());
            songAddController.showGUI();
        } else {
            App.homePageController.showGUI();
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

    @Override
    public void insertUpdate(DocumentEvent e) {
        setIAlbumResponse(new AlbumResponseImpl());
        try {
            albumView.setAlbumTable(iAlbumResponse.search(albumView.txtSearch.getText()));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        setIAlbumResponse(new AlbumResponseImpl());
        try {
            albumView.setAlbumTable(iAlbumResponse.search(albumView.txtSearch.getText()));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }
}
