package views.artist.panels;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import lombok.Getter;
import lombok.Setter;
import models.ArtistModel;

@Setter
@Getter
public class ArtistEditPanel extends JPanel {
    private JPanel headerPanel, bodyPanel, chooseFilePanel;
    private ButtonGroup btnGroupOption;
    private JRadioButton rdbtnCreate, rdbtnUpdate, rdbtnDelete;
    private JLabel lbChooseOption, lbId, lbName, lbArtistImage;
    private JTextField txtId, txtName, txtArtistImage;
    private Choice choiceName;

    private JButton btnOK, btnChooseFile;
    private JFileChooser fileChooser;

    private void init() {
        headerPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        bodyPanel = new JPanel(new GridLayout(3, 2, 70, 100));
        chooseFilePanel = new JPanel(new BorderLayout(1, 1));

        lbChooseOption = new JLabel("Choose option: ", SwingConstants.CENTER);
        lbChooseOption.setFont(new Font("Verdana", Font.BOLD, 12));

        rdbtnCreate = new JRadioButton("Create", true);
        rdbtnUpdate = new JRadioButton("Update", false);
        rdbtnDelete = new JRadioButton("Delete", false);
        btnGroupOption = new ButtonGroup();
        btnGroupOption.add(rdbtnCreate);
        btnGroupOption.add(rdbtnUpdate);
        btnGroupOption.add(rdbtnDelete);

        headerPanel.add(lbChooseOption);
        headerPanel.add(rdbtnCreate);
        headerPanel.add(rdbtnUpdate);
        headerPanel.add(rdbtnDelete);

        lbId = new JLabel("Id:", SwingConstants.RIGHT);
        lbId.setFont(new Font("Verdana", Font.BOLD, 24));
        lbName = new JLabel("Name:", SwingConstants.RIGHT);
        lbName.setFont(new Font("Verdana", Font.BOLD, 24));
        lbArtistImage = new JLabel("Artist Image:", SwingConstants.RIGHT);
        lbArtistImage.setFont(new Font("Verdana", Font.BOLD, 24));

        txtId = new JTextField();
        txtId.setFont(new Font("Verdana", Font.PLAIN, 18));
        txtId.setEnabled(false);
        txtName = new JTextField();
        txtName.setFont(new Font("Verdana", Font.PLAIN, 18));
        txtArtistImage = new JTextField();
        txtArtistImage.setFont(new Font("Verdana", Font.PLAIN, 18));
        txtArtistImage.setEnabled(false);

        choiceName = new Choice();

        btnChooseFile = new JButton("FILE");
        chooseFilePanel.add(txtArtistImage, BorderLayout.CENTER);
        chooseFilePanel.add(btnChooseFile, BorderLayout.EAST);

        bodyPanel.add(lbId);
        bodyPanel.add(txtId);
        bodyPanel.add(lbName);
        bodyPanel.add(txtName);
        bodyPanel.add(lbArtistImage);
        bodyPanel.add(chooseFilePanel);

        btnOK = new JButton("OK");

        fileChooser = new JFileChooser(new File("."));
    }

    private ArtistEditPanel() {
        super(new BorderLayout(5, 35));
        init();
        add(headerPanel, BorderLayout.NORTH);
        add(bodyPanel, BorderLayout.CENTER);
        add(btnOK, BorderLayout.SOUTH);
        conditionByRdbtn();
    }

    public void conditionByRdbtn() {
        if (rdbtnCreate.isSelected()) {
            txtId.setText("");
            txtName.setText("");
            txtArtistImage.setText("");
            bodyPanel.remove(3);
            bodyPanel.add(txtName, 3);
            txtName.setEnabled(true);
        } else if (rdbtnUpdate.isSelected()) {
            txtId.setText("");
            txtArtistImage.setText("");
            bodyPanel.remove(3);
            bodyPanel.add(choiceName, 3);
            choiceName.setEnabled(true);
        } else {
            txtId.setText("");
            txtArtistImage.setText("");
            bodyPanel.remove(3);
            bodyPanel.add(choiceName, 3);
            choiceName.setEnabled(true);
        }
    }

    public void setChoiceArtistName(List<ArtistModel> listArtistModels) {
        choiceName.removeAll();
        for (ArtistModel artistModel : listArtistModels) {
            choiceName.add(artistModel.getArtistName());
        }
    }

    public void setDefaultInformation(ArtistModel artistModel) {
        txtId.setText(String.valueOf(artistModel.getArtistId()));
        txtArtistImage.setText(artistModel.getArtistImageUrl());
    }

    public void addActionListener(ActionListener actionListener) {
        rdbtnCreate.addActionListener(actionListener);
        rdbtnUpdate.addActionListener(actionListener);
        rdbtnDelete.addActionListener(actionListener);
        btnOK.addActionListener(actionListener);
        btnChooseFile.addActionListener(actionListener);
    }

    public void addItemChangeListener(ItemListener itemListener) {
        choiceName.addItemListener(itemListener);
    }

    public ArtistModel createArtistModel() {
        return ArtistModel.builder().artistName(txtName.getText()).build();
    }

    public ArtistModel updateArtistModel() {
        return ArtistModel.builder().artistId(Integer.parseInt(txtId.getText()))
                .artistName(choiceName.getItem(choiceName.getSelectedIndex()))
                .build();
    }

    public int deleteArtistModel() {
        return Integer.parseInt(txtId.getText());
    }

    public static ArtistEditPanel getInstance() {
        return new ArtistEditPanel();
    }
}
