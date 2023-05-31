package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import models.UserModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class UserView extends JFrame {
    private JPanel contentPane;
    private JPanel pnBody;
    private JLabel lbTitle;
    public JTable userTable;
    public JButton btnReload, btnDelete;;

    private static UserView instance;

    private static final Color COLOR_1 = new Color(12, 19, 79);
    private static final Color COLOR_3 = new Color(92, 70, 156);
    private static final Color COLOR_4 = new Color(212, 173, 252);

    private void init() {
        contentPane = new JPanel(null);
        contentPane.setBorder(new LineBorder(Color.BLACK, 2));
        contentPane.setBackground(COLOR_4);

        JPanel pnHeader = new JPanel();
        pnHeader.setBounds(0, 0, 873, 180);
        pnHeader.setLayout(null);
        pnHeader.setBackground(COLOR_3);
        pnHeader.setBorder(new LineBorder(COLOR_1, 7));

        lbTitle = new JLabel("------------- USER_ACCOUNT MANAGEMENT -------------");
        lbTitle.setFont(new Font("Verdana", Font.BOLD, 22));
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setForeground(COLOR_1);
        lbTitle.setBounds(0, 0, 873, 180);
        pnHeader.add(lbTitle);

        contentPane.add(pnHeader);

        btnReload = new JButton("Reload");
        btnReload.setBounds(778, 142, 85, 28);
        pnHeader.add(btnReload);
        btnReload.setBackground(Color.WHITE);
        btnReload.setBorder(new LineBorder(COLOR_1, 2));

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(683, 142, 85, 28);
        pnHeader.add(btnDelete);
        btnDelete.setBorder(new LineBorder(COLOR_1, 2));
        btnDelete.setBackground(Color.WHITE);

        String[][] data = new String[0][];
        String[] columnsName = { "ID", "Username", "Full name", "Phone number" };
        userTable = new JTable(data, columnsName);
        userTable.setBorder(new LineBorder(Color.BLACK, 1));
        userTable.setBackground(COLOR_4);
        userTable.setShowGrid(true);
        userTable.setSelectionBackground(Color.LIGHT_GRAY);
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userTable.setFont(new Font("Verdana", Font.PLAIN, 14));
        userTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane jScrollPane = new JScrollPane(userTable);
        jScrollPane.getViewport().setBackground(COLOR_4);
        jScrollPane.setBorder(new LineBorder(Color.BLACK, 2));

        JTableHeader tableHeader = userTable.getTableHeader();
        tableHeader.setFont(new Font("Verdana", Font.BOLD, 14));

        pnBody = new JPanel();
        pnBody.setBounds(0, 177, 873, 328);
        pnBody.setBorder(new LineBorder(Color.BLACK, 2));
        pnBody.setBackground(COLOR_4);
        pnBody.setLayout(new BorderLayout());

        pnBody.add(tableHeader, BorderLayout.NORTH);
        pnBody.add(jScrollPane, BorderLayout.CENTER);
        contentPane.add(pnBody);

    }

    private UserView() {
        init();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(0, 0, 887, 542);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setIconImage(new ImageIcon("assets/app_logo.png").getImage());
        setTitle("User_Account Management");
        setResizable(false);
    }

    public void addActionListener(ActionListener actionListener) {
        btnDelete.addActionListener(actionListener);
        btnReload.addActionListener(actionListener);
    }

    public void setUserTable(List<UserModel> listUserModel) {
        String[][] data = new String[listUserModel.size()][];
        String[] columnsName = { "ID", "Username", "Full name", "Phone number" };
        TableModel tableModel = new DefaultTableModel(data, columnsName);
        int i = 0;
        for (UserModel userModel : listUserModel) {
            tableModel.setValueAt(String.valueOf(userModel.getUserId()), i, 0);
            tableModel.setValueAt(userModel.getUsername(), i, 1);
            tableModel.setValueAt(userModel.getFullName(), i, 2);
            tableModel.setValueAt(userModel.getPhoneNumber(), i, 3);
            i++;
        }
        userTable.setModel(tableModel);
    }

    public static UserView getInstance() {
        if (instance == null) {
            instance = new UserView();
        }
        return instance;
    }
}
