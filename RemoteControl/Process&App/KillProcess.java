import java.io.*;
import javax.swing.*;
import java.util.regex.*;

public class KillProcess {
  private static final String TASKLIST = "tasklist";
  private static final String KILL = "taskkill /F /IM ";

  public static boolean isProcessRunning(String serviceName) throws IOException {
    Process p = Runtime.getRuntime().exec(TASKLIST);
    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
    String line;
    while ((line = reader.readLine()) != null) {
      if (Pattern.compile(Pattern.quote(serviceName), Pattern.CASE_INSENSITIVE).matcher(line).find()) {
        return true;
      }
    }

    return false;

  }

  public static void killProcess(String serviceName) throws IOException {

    Runtime.getRuntime().exec(KILL + serviceName);

  }

  public void main(String args[]) {
    try {
      String ProcessName = JOptionPane.showInputDialog(null, "Enter the process's name you want to stop:",
          "Stop a process", JOptionPane.PLAIN_MESSAGE);

      if (ProcessName == null) {
        System.out.println("Cancel");
        return;
      }

      if (isProcessRunning(ProcessName)) {

        killProcess(ProcessName);
      } else {
        JOptionPane.showMessageDialog(null, ProcessName + " is not currently running", "Error while stopping process",
            JOptionPane.ERROR_MESSAGE);
      }
    } catch (Exception err) {
      err.printStackTrace();
    }
  }
}
