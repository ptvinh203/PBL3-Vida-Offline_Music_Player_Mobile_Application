package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JOptionPane;

import adminpage.App;
import httprequest.IUserResponse;
import httprequest.implement.UserResponseImpl;
import lombok.Setter;
import models.UserModel;
import view.ToastMessage;
import view.UserView;

public class UserController implements ActionListener, WindowListener {
    private UserView userView;

    @Setter
    private IUserResponse iUserResponse;

    public UserController() {
        userView = UserView.getInstance();
        userView.addActionListener(this);
        userView.addWindowListener(this);

        iUserResponse = new UserResponseImpl();
    }

    public void showGUI(List<UserModel> userModels) {
        userView.setUserTable(userModels);
        userView.setVisible(true);
    }

    private void deleteUser() {
        int row = userView.userTable.getSelectedRow();
        if (row != -1) {
            try {
                int option = JOptionPane.showConfirmDialog(userView, "Are you sure?", "DELETE OBJECT",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (option == 0) {
                    iUserResponse.deleteById(Integer.parseInt(userView.userTable.getValueAt(row, 0).toString().trim()));
                    ToastMessage toastMessage = new ToastMessage(
                            String.format("Delete user (username: '%s') successfully!",
                                    userView.userTable.getValueAt(row, 1).toString()));
                    toastMessage.showToast(
                            userView.getX() + (userView.getWidth() / 2) - (toastMessage.getWidth() / 4),
                            userView.getY() + userView.getHeight() - 55);
                    userView.setUserTable(iUserResponse.findAll());
                }
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(userView, e1.getMessage(), "Invalid format user's ID",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(userView, e1.getMessage(), "Not found object",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == userView.btnDelete) {
            deleteUser();
        } else if (e.getSource() == userView.btnReload) {
            try {
                userView.setUserTable(iUserResponse.findAll());
            } catch (Exception e1) {
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        App.homePageController.showGUI();
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
