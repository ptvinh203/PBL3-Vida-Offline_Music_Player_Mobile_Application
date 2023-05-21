package controller.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JOptionPane;

import controller.HomePageController;
import controller.SongController;
import httprequest.IMyFileResponse;
import httprequest.ISongResponse;
import httprequest.implement.MyFileResponseImpl;
import httprequest.implement.SongResponseImpl;
import lombok.Setter;
import models.SongModel;
import view.edit.SongEditView;

public class SongEditController implements ActionListener, WindowListener {
    private final SongEditView songEditView;

    private String artworkFile_old;
    private String musicFile_old;

    @Setter
    private SongController songController;

    @Setter
    private IMyFileResponse iMyFileResponse;
    @Setter
    private ISongResponse iSongResponse;

    public SongEditController() {
        songEditView = SongEditView.getInstance();
        songEditView.addActionListener(this);
        songEditView.addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == songEditView.btnUpdate) {
            try {
                SongModel songModel = songEditView.getSongModel();

                setISongResponse(new SongResponseImpl());
                setIMyFileResponse(new MyFileResponseImpl());

                boolean udpate = iSongResponse.update(songModel.getSongId(), songModel);
                if (udpate) {
                    if (artworkFile_old.compareTo(songEditView.getTxtArtworkFile().getText()) != 0) {
                        File artworkFile_new = songEditView.getArtworkFile();
                        iMyFileResponse.songFileUpload(songModel.getSongId(), artworkFile_new);

                        if (artworkFile_old != null && !artworkFile_old.isEmpty()) {
                            String[] arrStr = artworkFile_old.split("/");
                            int fileId_old = Integer.parseInt(arrStr[arrStr.length - 1]);
                            iMyFileResponse.deleteById(fileId_old);
                        }
                    }
                    if (musicFile_old.compareTo(songEditView.getTxtMusicFile().getText()) != 0) {
                        File musicFile_new = songEditView.getMusicFile();
                        iMyFileResponse.songFileUpload(songModel.getSongId(), musicFile_new);

                        if (musicFile_old != null && !musicFile_old.isEmpty()) {
                            String[] arrStr = musicFile_old.split("/");
                            int fileId_old = Integer.parseInt(arrStr[arrStr.length - 1]);
                            iMyFileResponse.deleteById(fileId_old);
                        }
                    }
                }
                songEditView.dispose();
                setSongController(HomePageController.getSongController());
                songController.showGUI(iSongResponse.findAll());
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(songEditView, ex.getMessage(), "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == songEditView.btnChooseFile) {
            songEditView.fileChooser.showOpenDialog(songEditView);
            if (songEditView.fileChooser.getSelectedFile() != null) {
                String file = songEditView.fileChooser.getSelectedFile().getAbsolutePath();
                String[] arr = file.split("/");
                if (arr[arr.length - 1].contains(".mp3")) {
                    songEditView.getTxtMusicFile().setText(file);
                } else if (arr[arr.length - 1].contains(".jpg") || arr[arr.length - 1].contains(".png")) {
                    songEditView.getTxtArtworkFile().setText(file);
                } else {
                    JOptionPane.showMessageDialog(songEditView,
                            "Please choose image file (.png or .jpg) or music file (.mp3)", "File chooser",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    public void showGUI(SongModel songModel) {
        songEditView.setDefaultInfor(songModel);
        songEditView.setVisible(true);
        artworkFile_old = songEditView.getTxtArtworkFile().getText();
        musicFile_old = songEditView.getTxtMusicFile().getText();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        setISongResponse(new SongResponseImpl());
        setSongController(HomePageController.getSongController());
        try {
            songController.showGUI(iSongResponse.findAll());
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
