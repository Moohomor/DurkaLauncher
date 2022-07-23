package durkalauncher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Objects;

public class Main {

    static final String COMMAND_TEMPLATE = "%gamedir\\runtime\\java-runtime-gamma\\windows\\java-runtime-gamma\\bin\\javaw.exe \"-Dos.name=Windows 10\" -Dos.version=10.0 -XX:HeapDumpPath=MojangTricksIntelDriversForPerformance_javaw.exe_minecraft.exe.heapdump -Xss1M -Djava.library.path=%gamedir\\versions\\quilt-loader-0.17.1-beta.6-1.19\\natives -Dminecraft.launcher.brand=minecraft-launcher -Dminecraft.launcher.version=2.3.173 -cp %gamedir\\libraries\\net\\fabricmc\\tiny-mappings-parser\\0.3.0+build.17\\tiny-mappings-parser-0.3.0+build.17.jar;%gamedir\\libraries\\net\\fabricmc\\sponge-mixin\\0.11.2+mixin.0.8.5\\sponge-mixin-0.11.2+mixin.0.8.5.jar;%gamedir\\libraries\\net\\fabricmc\\tiny-remapper\\0.8.1\\tiny-remapper-0.8.1.jar;%gamedir\\libraries\\net\\fabricmc\\access-widener\\2.1.0\\access-widener-2.1.0.jar;%gamedir\\libraries\\org\\quiltmc\\quilt-json5\\1.0.1\\quilt-json5-1.0.1.jar;%gamedir\\libraries\\org\\ow2\\asm\\asm\\9.3\\asm-9.3.jar;%gamedir\\libraries\\org\\ow2\\asm\\asm-analysis\\9.3\\asm-analysis-9.3.jar;%gamedir\\libraries\\org\\ow2\\asm\\asm-commons\\9.3\\asm-commons-9.3.jar;%gamedir\\libraries\\org\\ow2\\asm\\asm-tree\\9.3\\asm-tree-9.3.jar;%gamedir\\libraries\\org\\ow2\\asm\\asm-util\\9.3\\asm-util-9.3.jar;%gamedir\\libraries\\org\\quiltmc\\quilt-config\\1.0.0-beta.5\\quilt-config-1.0.0-beta.5.jar;%gamedir\\libraries\\net\\fabricmc\\intermediary\\1.19\\intermediary-1.19.jar;%gamedir\\libraries\\net\\fabricmc\\intermediary\\1.19\\intermediary-1.19.jar;%gamedir\\libraries\\org\\quiltmc\\quilt-loader\\0.17.1-beta.6\\quilt-loader-0.17.1-beta.6.jar;%gamedir\\libraries\\com\\mojang\\logging\\1.0.0\\logging-1.0.0.jar;%gamedir\\libraries\\com\\mojang\\blocklist\\1.0.10\\blocklist-1.0.10.jar;%gamedir\\libraries\\org\\tlauncher\\patchy\\2.2.10\\patchy-2.2.10.jar;%gamedir\\libraries\\com\\github\\oshi\\oshi-core\\5.8.5\\oshi-core-5.8.5.jar;%gamedir\\libraries\\net\\java\\dev\\jna\\jna\\5.10.0\\jna-5.10.0.jar;%gamedir\\libraries\\net\\java\\dev\\jna\\jna-platform\\5.10.0\\jna-platform-5.10.0.jar;%gamedir\\libraries\\org\\slf4j\\slf4j-api\\1.8.0-beta4\\slf4j-api-1.8.0-beta4.jar;%gamedir\\libraries\\org\\apache\\logging\\log4j\\log4j-slf4j18-impl\\2.17.0\\log4j-slf4j18-impl-2.17.0.jar;%gamedir\\libraries\\com\\ibm\\icu\\icu4j\\70.1\\icu4j-70.1.jar;%gamedir\\libraries\\com\\mojang\\javabridge\\1.2.24\\javabridge-1.2.24.jar;%gamedir\\libraries\\net\\sf\\jopt-simple\\jopt-simple\\5.0.4\\jopt-simple-5.0.4.jar;%gamedir\\libraries\\io\\netty\\netty-common\\4.1.77.Final\\netty-common-4.1.77.Final.jar;%gamedir\\libraries\\io\\netty\\netty-buffer\\4.1.77.Final\\netty-buffer-4.1.77.Final.jar;%gamedir\\libraries\\io\\netty\\netty-codec\\4.1.77.Final\\netty-codec-4.1.77.Final.jar;%gamedir\\libraries\\io\\netty\\netty-handler\\4.1.77.Final\\netty-handler-4.1.77.Final.jar;%gamedir\\libraries\\io\\netty\\netty-resolver\\4.1.77.Final\\netty-resolver-4.1.77.Final.jar;%gamedir\\libraries\\io\\netty\\netty-transport\\4.1.77.Final\\netty-transport-4.1.77.Final.jar;%gamedir\\libraries\\io\\netty\\netty-transport-native-unix-common\\4.1.77.Final\\netty-transport-native-unix-common-4.1.77.Final.jar;%gamedir\\libraries\\io\\netty\\netty-transport-classes-epoll\\4.1.77.Final\\netty-transport-classes-epoll-4.1.77.Final.jar;%gamedir\\libraries\\com\\google\\guava\\failureaccess\\1.0.1\\failureaccess-1.0.1.jar;%gamedir\\libraries\\com\\google\\guava\\guava\\31.0.1-jre\\guava-31.0.1-jre.jar;%gamedir\\libraries\\org\\apache\\commons\\commons-lang3\\3.12.0\\commons-lang3-3.12.0.jar;%gamedir\\libraries\\commons-io\\commons-io\\2.11.0\\commons-io-2.11.0.jar;%gamedir\\libraries\\commons-codec\\commons-codec\\1.15\\commons-codec-1.15.jar;%gamedir\\libraries\\com\\mojang\\brigadier\\1.0.18\\brigadier-1.0.18.jar;%gamedir\\libraries\\com\\mojang\\datafixerupper\\5.0.28\\datafixerupper-5.0.28.jar;%gamedir\\libraries\\com\\google\\code\\gson\\gson\\2.8.9\\gson-2.8.9.jar;%gamedir\\libraries\\org\\tlauncher\\authlib\\3.5.41\\authlib-3.5.41.jar;%gamedir\\libraries\\org\\apache\\commons\\commons-compress\\1.21\\commons-compress-1.21.jar;%gamedir\\libraries\\org\\apache\\httpcomponents\\httpclient\\4.5.13\\httpclient-4.5.13.jar;%gamedir\\libraries\\commons-logging\\commons-logging\\1.2\\commons-logging-1.2.jar;%gamedir\\libraries\\org\\apache\\httpcomponents\\httpcore\\4.4.14\\httpcore-4.4.14.jar;%gamedir\\libraries\\it\\unimi\\dsi\\fastutil\\8.5.6\\fastutil-8.5.6.jar;%gamedir\\libraries\\org\\apache\\logging\\log4j\\log4j-api\\2.17.0\\log4j-api-2.17.0.jar;%gamedir\\libraries\\org\\apache\\logging\\log4j\\log4j-core\\2.17.0\\log4j-core-2.17.0.jar;%gamedir\\libraries\\org\\lwjgl\\lwjgl\\3.3.1\\lwjgl-3.3.1.jar;%gamedir\\libraries\\org\\lwjgl\\lwjgl\\3.3.1\\lwjgl-3.3.1-natives-windows.jar;%gamedir\\libraries\\org\\lwjgl\\lwjgl\\3.3.1\\lwjgl-3.3.1-natives-windows-x86.jar;%gamedir\\libraries\\org\\lwjgl\\lwjgl-jemalloc\\3.3.1\\lwjgl-jemalloc-3.3.1.jar;%gamedir\\libraries\\org\\lwjgl\\lwjgl-jemalloc\\3.3.1\\lwjgl-jemalloc-3.3.1-natives-windows.jar;%gamedir\\libraries\\org\\lwjgl\\lwjgl-jemalloc\\3.3.1\\lwjgl-jemalloc-3.3.1-natives-windows-x86.jar;%gamedir\\libraries\\org\\lwjgl\\lwjgl-openal\\3.3.1\\lwjgl-openal-3.3.1.jar;%gamedir\\libraries\\org\\lwjgl\\lwjgl-openal\\3.3.1\\lwjgl-openal-3.3.1-natives-windows.jar;%gamedir\\libraries\\org\\lwjgl\\lwjgl-openal\\3.3.1\\lwjgl-openal-3.3.1-natives-windows-x86.jar;%gamedir\\libraries\\org\\lwjgl\\lwjgl-opengl\\3.3.1\\lwjgl-opengl-3.3.1.jar;%gamedir\\libraries\\org\\lwjgl\\lwjgl-opengl\\3.3.1\\lwjgl-opengl-3.3.1-natives-windows.jar;%gamedir\\libraries\\org\\lwjgl\\lwjgl-opengl\\3.3.1\\lwjgl-opengl-3.3.1-natives-windows-x86.jar;%gamedir\\libraries\\org\\lwjgl\\lwjgl-glfw\\3.3.1\\lwjgl-glfw-3.3.1.jar;%gamedir\\libraries\\org\\lwjgl\\lwjgl-glfw\\3.3.1\\lwjgl-glfw-3.3.1-natives-windows.jar;%gamedir\\libraries\\org\\lwjgl\\lwjgl-glfw\\3.3.1\\lwjgl-glfw-3.3.1-natives-windows-x86.jar;%gamedir\\libraries\\org\\lwjgl\\lwjgl-stb\\3.3.1\\lwjgl-stb-3.3.1.jar;%gamedir\\libraries\\org\\lwjgl\\lwjgl-stb\\3.3.1\\lwjgl-stb-3.3.1-natives-windows.jar;%gamedir\\libraries\\org\\lwjgl\\lwjgl-stb\\3.3.1\\lwjgl-stb-3.3.1-natives-windows-x86.jar;%gamedir\\libraries\\org\\lwjgl\\lwjgl-tinyfd\\3.3.1\\lwjgl-tinyfd-3.3.1.jar;%gamedir\\libraries\\org\\lwjgl\\lwjgl-tinyfd\\3.3.1\\lwjgl-tinyfd-3.3.1-natives-windows.jar;%gamedir\\libraries\\org\\lwjgl\\lwjgl-tinyfd\\3.3.1\\lwjgl-tinyfd-3.3.1-natives-windows-x86.jar;%gamedir\\libraries\\com\\mojang\\text2speech\\1.13.9\\text2speech-1.13.9.jar;%gamedir\\libraries\\com\\mojang\\text2speech\\1.13.9\\text2speech-1.13.9-natives-windows.jar;%gamedir\\versions\\quilt-loader-0.17.1-beta.6-1.19\\quilt-loader-0.17.1-beta.6-1.19.jar -Xmx%memoryM -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -XX:G1NewSizePercent=20 -XX:G1ReservePercent=20 -XX:MaxGCPauseMillis=50 -XX:G1HeapRegionSize=32M -Dfml.ignoreInvalidMinecraftCertificates=true -Dfml.ignorePatchDiscrepancies=true -Djava.net.preferIPv4Stack=true -Dminecraft.applet.TargetDirectory=%gamedir -Dlog4j.configurationFile=%gamedir\\assets\\log_configs\\client-1.12.xml org.quiltmc.loader.impl.launch.knot.KnotClient --username %username --version Durka_1.19 --gameDir %gamedir --assetsDir %gamedir\\assets --assetIndex 1.19 --uuid ecb2f9fb-ec79-11ea-ba71-b42e996a7d7a --accessToken null --clientId ${clientid} --xuid ${auth_xuid} --userType mojang --versionType release --width 925 --height 530 --server durkaP4.aternos.me";
    static Process process;
    static ConsoleGUI consoleGUI;
    static JButton button;
    static String lastOutput;
    static String fullCommand;
    static CheatChecker cheatChecker;


    public static void main(String[] args) throws IOException {
        //system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            System.err.println("Set Metal Look and Feel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*//on first launch
        if (!new Downloader().checkInstallation()) {
            new DownloaderGUI("first launch");
            while (state.equals("install1")) {
                System.out.print("");
            }
            new DownloaderGUI("install step2");
            while (state.equals("install2")) {
                System.out.print("");
            }
            new DownloaderGUI("finish install");
            while (state.equals("install3")){
                System.out.print("");
            }
        } else {//on not first launch
            try {
                new Downloader().update();
            } catch (DurkaNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,e.getMessage(),"durkalauncher.exceptions.DurkaNotFoundException",JOptionPane.ERROR_MESSAGE);
            }
        }*/

        //main code
        JFrame frame = new JFrame("Durka Launcher");
        frame.setResizable(false);
        frame.setIconImage(ImageIO.read(Objects.requireNonNull(Main.class.getClassLoader().getResource("icon.png"))));
        JPanel panel = new JPanel();
        JTextField nicknameField = new JTextField(16);
        JTextField memoryField = new JTextField( String.valueOf(((com.sun.management.OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean()).getTotalPhysicalMemorySize()/2097152),4);
        memoryField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // if it's not a number, ignore the event
                }
            }
        });
        JLabel showConsoleLabel = new JLabel("Copy last console output");
        showConsoleLabel.setForeground(Color.GRAY);
        showConsoleLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (lastOutput != null) {
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                            new StringSelection(lastOutput), null);
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {
                if (lastOutput != null) showConsoleLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                else showConsoleLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        button = new JButton("Go");
        button.addActionListener(e -> {
            try {
                if (process == null || !process.isAlive()) {
                    String dir = ".durka";
                    // set to .minecraft if 1.19 exists
                    String path = System.getProperty("user.home").replace("\\","\\\\")+"\\\\AppData\\\\Roaming\\\\"+dir;
                    if (!new File(path).exists()) throw new RuntimeException("I tried to run Durka but I didn't find it");
                    if (fullCommand==null)
                    fullCommand = COMMAND_TEMPLATE
                            .replaceAll("%gamedir", path)
                            .replaceAll("%memory", memoryField.getText())
                            .replaceAll("%username", nicknameField.getText());
                    System.out.println("Full command: "+fullCommand);
                    process = Runtime.getRuntime().exec(fullCommand);
                    consoleGUI = new ConsoleGUI();
                    cheatChecker = new CheatChecker(path);
                    cheatChecker.start();
                    consoleGUI.setVisible(true);
                    button.setText("Stop");
                    showConsoleLabel.setForeground(Color.BLACK);
                }
                else {
                    process.destroy();
                    button.setText("Go");
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        panel.add(new JLabel("Nickname:"));
        panel.add(nicknameField);
        panel.add(new JLabel("Memory:"));
        panel.add(memoryField);
        panel.add(new JLabel("MB"));
        panel.add(button);
        panel.add(showConsoleLabel);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {if (process != null) process.destroy();}
            @Override
            public void windowClosed(WindowEvent e) {}
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        frame.setVisible(true);
    }
}
