package views.panels;

import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class SongUploadPanel extends Panel{
    private JLabel lbSongName, lbSong;
    private JTextField txtSongName;

    private JButton btnUpload;
    private void init() {
        lbSongName = new JLabel("Name: ");
        txtSongName = new JTextField();
        btnUpload = new JButton("UPLOAD");
    }
    public SongUploadPanel() {
        super(new GridLayout(1, 1));

        init();
        add(lbSongName);
        add(txtSongName);
    }


}
