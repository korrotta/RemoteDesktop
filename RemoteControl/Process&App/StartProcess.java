import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder;
import javax.swing.JOptionPane;

public class StartProcess {

    public void main(String[] args) {
        try {

            String ProcessName = JOptionPane.showInputDialog(null, "Enter the process's executable name to start: ",
                    "Start a process", JOptionPane.PLAIN_MESSAGE);

            if (ProcessName == null) {
                System.out.println("Cancel");
                return;
            }
            if (!ProcessName.contains(".exe")) {
                JOptionPane.showMessageDialog(null, ProcessName + " is not an executable",
                        "Error while starting process", JOptionPane.ERROR_MESSAGE);
                return;
            }

            File[] roots = File.listRoots();
            String line = "";
            String path = "";
            int i = 1;
            for (File root : roots) {
                try {
                    Process p = Runtime.getRuntime().exec("cmd /c " + root.toString().substring(0, 1) + ": && dir /s /b " + ProcessName);
                    BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    while (true) {
                        line = input.readLine();
                        if (line == null) {
                            break;
                        }
                        path = line;
                    }
                } catch (IOException | IllegalArgumentException ex) {
                    if (i == roots.length) {
                        JOptionPane.showMessageDialog(null, ProcessName + " could not be found anywhere on this computer",
                                "Error while starting process", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    i++;
                    continue;
                }
            }

            new ProcessBuilder(path).start();

        } catch (Exception err) {
            err.printStackTrace();
        }
    }

}
