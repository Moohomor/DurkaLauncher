package durkalauncher;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleGUI extends JFrame {

    private final JTextArea textArea;

    public ConsoleGUI() {
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        ConsoleThread consoleThread = new ConsoleThread();
        JPanel panel = new JPanel(new BorderLayout());
        JPanel toolsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton killButton = new JButton("Kill minecraft");
        JButton copyToClipboard = new JButton("Copy output to clipboard");

        setSize(800,600);
        setTitle("Minecraft Control Panel");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        killButton.addActionListener(e -> Main.process.destroy());
        copyToClipboard.addActionListener(e -> Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                new StringSelection(textArea.getText()), null));

        toolsPanel.add(killButton);
        toolsPanel.add(copyToClipboard);

        textArea.setEditable(false);
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(Color.BLACK);
        DefaultCaret caret = (DefaultCaret)textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);


        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane,BorderLayout.CENTER);
        panel.add(toolsPanel,BorderLayout.SOUTH);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override public void windowClosing(WindowEvent e) {
                Main.process.destroy();
            }
            @Override public void windowClosed(WindowEvent e) {
                Main.process.destroy();
            }
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override  public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });
        add(panel);
        consoleThread.start();
    }

    private class ConsoleThread extends Thread {

        @Override
        public void run() {
            super.run();
                /*while (true) {
                    try {
                        textArea.setText(textArea.getText() + "\n" + inputStream.read());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
            textArea.setText("Full command: "+Main.fullCommand);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(Main.process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(Main.process.getErrorStream()));

            String s = null;
            while (true) {
                try {
                    if ((s = stdInput.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textArea.setText(textArea.getText()+"\n"+s);
            }
            
            while (true) {
                try {
                    if ((s = stdError.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textArea.setText(textArea.getText()+"\n"+s);
            }
            if (!Main.process.isAlive()) {
                Main.lastOutput = textArea.getText();
                Main.button.setText("Go");
                dispose();
                interrupt();
            }
        }
    }
}
