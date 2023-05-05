package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.List;

import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import HttpRequest.IArtistResponse;
import HttpRequest.IMyFileResponse;
import HttpRequest.implement.ArtistResponseImpl;
import HttpRequest.implement.MyFileResponseImpl;
import controller.eventHandle.ArtistAlbumController;
import controller.eventHandle.ArtistSingleAndEpSongController;
import models.ArtistModel;
import views.ArtistView;

public class ArtistController implements ActionListener, ChangeListener , ItemListener{
    private ArtistView artistView = ArtistView.getInstance();
    private IArtistResponse iArtistResponse = new ArtistResponseImpl();
    private IMyFileResponse iMyFileResponse = new MyFileResponseImpl();


    private ArtistAlbumController artistAlbumController = new ArtistAlbumController();
    private ArtistSingleAndEpSongController artistSingleAndEpSongController = new ArtistSingleAndEpSongController();


    public ArtistController() {
        artistView.addChangeListenerForTabbedPane(this);
        artistView.getArtistViewAllPanel().addActionListener(this);
        artistView.getArtistEditPanel().addActionListener(this);
        artistView.getArtistEditPanel().addItemChangeListener(this);
    }
    public void addActionListenerHomeButton(ActionListener actionListener) {
        artistView.addActionListener(actionListener);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (artistView.getTabbedPane().getSelectedIndex() == 1) {
            showAllArtist();
        }
        else {
            artistView.getArtistEditPanel().conditionByRdbtn();
        }
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        this.setChoiceArtistName();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        rdbtnChange(e.getSource());
        String command = e.getActionCommand();
        switch(command) {
            case "REFRESH": {
                showAllArtist();
                break;
            }
            case "SHOW ALBUM": {
                int row = artistView.getArtistViewAllPanel().getTableArtist().getSelectedRow(),
                    col = artistView.getArtistViewAllPanel().getTableArtist().getSelectedColumn();
                
                ArtistModel artistModel = null;
                if (row != -1 && col != -1) {
                        artistModel = getAnArtist(
                            Integer.parseInt(artistView.getArtistViewAllPanel().getTableArtist().getValueAt(row, 0).toString()) 
                        );
                    }
                if (artistModel != null) {
                    artistAlbumController.resetTableSong();
                    artistAlbumController.setTitle(artistModel.getArtistName());
                    artistAlbumController.setAlbumChoice(artistModel.getAlbums());
                }
                artistAlbumController.showGUI();
                break;
            }
            case "SHOW SINGLE AND EPS SONGS": {
                int row = artistView.getArtistViewAllPanel().getTableArtist().getSelectedRow(),
                    col = artistView.getArtistViewAllPanel().getTableArtist().getSelectedColumn();
                
                ArtistModel artistModel = null;
                if (row != -1 && col != -1) {
                        artistModel = getAnArtist(
                            Integer.parseInt(artistView.getArtistViewAllPanel().getTableArtist().getValueAt(row, 0).toString()) 
                        );
                    }
                if (artistModel != null) {
                    artistSingleAndEpSongController.setTitle(artistModel.getArtistName());
                    artistSingleAndEpSongController.setTableModel(artistModel.getSingleAndEpSongs());
                }
                artistSingleAndEpSongController.showGUI();
                break;
            }
            case "OK": {  
                try {
                    if (artistView.getArtistEditPanel().getRdbtnCreate().isSelected() &&
                         !artistView.getArtistEditPanel().getTxtName().getText().isEmpty() &&
                         !artistView.getArtistEditPanel().getTxtArtistImage().getText().isEmpty()) {
                        ArtistModel artistModel = artistView.getArtistEditPanel().createArtistModel();
                        File artistFile = new File(artistView.getArtistEditPanel().getTxtArtistImage().getText());
                        ArtistModel fromServer = iArtistResponse.create(artistModel);   

                        iMyFileResponse.artistFileUpload(fromServer.getArtistId(), artistFile);
                    }
                    else if (artistView.getArtistEditPanel().getRdbtnUpdate().isSelected() &&
                             !artistView.getArtistEditPanel().getTxtId().getText().isEmpty() &&
                             !artistView.getArtistEditPanel().getTxtArtistImage().getText().isEmpty()) {
                        
                        // iArtistResponse.update(Integer.parseInt(artistView.getArtistEditPanel().getTxtId().getText()), 
                        //                                         ArtistModel.builder()
                        //                                                 .artistName(artistView.getArtistEditPanel().getTxtName().getText())
                        //                                                 .build());
                        ArtistModel artistModel = iArtistResponse.findById(Integer.parseInt(artistView.getArtistEditPanel().getTxtId().getText()));
                        String[] tmp = artistModel.getArtistImageUrl().split("/");

                        File artistFile = new File(artistView.getArtistEditPanel().getTxtArtistImage().getText());
                        iMyFileResponse.artistFileUpload(artistModel.getArtistId(), artistFile);
                        iMyFileResponse.deleteById(Integer.parseInt(tmp[tmp.length - 1]));
                    }
                    else if (!artistView.getArtistEditPanel().getTxtId().getText().isEmpty()){ 
                        iArtistResponse.deleteById(Integer.parseInt(artistView.getArtistEditPanel().getTxtId().getText()));
                    }
                }
                catch (Exception ex) {
                    artistView.showMessageBox(ex.getMessage());
                }
                break;
            }
            case "FILE": {
                artistView.getArtistEditPanel().getFileChooser().showOpenDialog(artistView);
                artistView.getArtistEditPanel().getTxtArtistImage().setText(artistView.getArtistEditPanel().getFileChooser().getSelectedFile().getAbsolutePath());
            }
        }
    }

    public void setChoiceArtistName() {
        try {
            List<ArtistModel> listArtistModels = iArtistResponse.findAll();
            if (listArtistModels.size() > artistView.getArtistEditPanel().getChoiceName().getItemCount() ||
                 listArtistModels.size() < artistView.getArtistEditPanel().getChoiceName().getItemCount()) {
                artistView.getArtistEditPanel().setChoiceArtistName(listArtistModels);
            }
            for (ArtistModel artistModel : listArtistModels) {
                if (artistModel.getArtistName().compareTo(artistView.getArtistEditPanel().getChoiceName().getSelectedItem()) == 0) {
                    artistView.getArtistEditPanel().setDefaultInformation(artistModel);
                    break;
                }
            } 
        }
        catch(Exception ex) {
            artistView.showMessageBox(ex.getMessage());
        }
    }
    public void rdbtnChange(Object source) {
        if (source.getClass() == JRadioButton.class) {
            artistView.getArtistEditPanel().conditionByRdbtn();
        }

        if (source == artistView.getArtistEditPanel().getRdbtnUpdate() || 
             source == artistView.getArtistEditPanel().getRdbtnDelete()) {
            this.setChoiceArtistName();
        }
    }
    public ArtistModel getAnArtist(int id) {
        try {
            return iArtistResponse.findById(id);
        }
        catch(Exception ex) {
            artistView.showMessageBox(ex.getMessage());
            return null;
        }
    }
    public void showAllArtist() {
        try {
            List<ArtistModel> lArtistModels = iArtistResponse.findAll();
            artistView.getArtistViewAllPanel().setTableModel(lArtistModels);
        }
        catch(Exception ex) {
            artistView.showMessageBox(ex.getMessage());
        }
    }
    public void showGUI(boolean show) {
        if (show) {
            artistView.setVisible(true);
        }
        else artistView.dispose();
    }

    
}
