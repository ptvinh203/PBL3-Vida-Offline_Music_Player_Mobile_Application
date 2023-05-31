package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.concurrent.CompletableFuture;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class ToastMessage extends JFrame {
    public JWindow window;

    public ToastMessage(String message) {
        window = new JWindow();
        window.setBackground(new Color(0, 0, 0, 0));

        JPanel p = new JPanel() {
            public void paintComponent(Graphics g) {
                g.setFont(new Font("Verdana", Font.BOLD, 13));
                int width = g.getFontMetrics().stringWidth(message);
                int height = g.getFontMetrics().getHeight();

                g.setColor(new Color(12, 19, 79));
                g.fillRect(10, 10, width + 20, height + 15);
                g.setColor(new Color(12, 19, 79));
                g.drawRect(10, 10, width + 20, height + 15);

                g.setColor(new Color(255, 255, 255, 180));
                g.drawString(message, 19, 31);
                int t = 250;

                for (int i = 0; i < 4; i++) {
                    t -= 50;
                    g.setColor(new Color(212, 173, 252, t));
                    g.drawRect(10 - i, 10 - i, width + 20 + i * 2, height + 15 + i * 2);
                }
            }
        };
        window.add(p);
        window.setSize(message.length() * 15, 200);
    }

    public int getHeight() {
        return window.getHeight();
    }

    public int getWidth() {
        return window.getWidth();
    }

    public void showToast(int x, int y) {
        CompletableFuture.runAsync(() -> {
            try {
                window.setLocation(x, y);
                window.setOpacity(1);
                window.setVisible(true);

                Thread.sleep(3000);
                for (double d = 1.0; d > 0.2; d -= 0.1) {
                    Thread.sleep(50);
                    window.setOpacity((float) d);
                }

                window.setVisible(false);
            } catch (Exception ex) {
            }
        });
    }
}
