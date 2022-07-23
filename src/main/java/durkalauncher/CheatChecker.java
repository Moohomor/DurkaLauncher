package durkalauncher;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class CheatChecker extends Thread {

    String mcdir;

    public CheatChecker(String mcdir) {
        this.mcdir = mcdir+"\\mods";
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                File mods = new File(mcdir);
                File[] files =  mods.listFiles();
                for (File f:files)
                    System.out.println(f.getName());
                for (File mod : files) {
                    System.out.println(mod.getAbsolutePath());
                    ZipFile file = new ZipFile(mod);
                    ZipEntry entry = file.getEntry("fabric.mod.json");
                    if (entry == null)
                        entry = file.getEntry("quilt.mod.json");
                    InputStream is = file.getInputStream(entry);
                    String result = new BufferedReader(new InputStreamReader(is))
                            .lines().parallel().collect(Collectors.joining("\n"));
                    System.out.println(result + '\n');
                    result = result.toLowerCase();
                    if (result.contains("xray") || result.contains("wurst") || result.contains("cheat")) {
                        if (Main.process.isAlive())
                            Main.process.destroy();
                        JOptionPane.showMessageDialog(null,
                                "Cheats are forbidden on this server. I'll remove one",
                                "Cheats detected",
                                JOptionPane.WARNING_MESSAGE);
                        if (!mod.delete())
                            JOptionPane.showMessageDialog(null,
                                    "WTF?! Why I can't delete cheat?",
                                    "Can't delete file!",
                                    JOptionPane.ERROR_MESSAGE);
                    }
                    Main.consoleGUI.printToConsole(mod.getName() + " checked.");
                }
                Main.consoleGUI.printToConsole("All mods checked");
                sleep(300000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
