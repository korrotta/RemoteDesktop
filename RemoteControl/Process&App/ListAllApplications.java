import java.awt.Font;
import java.awt.Color;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ListAllApplications {
    public void main(String args[]) {
        try {
            String line = "";
            String result = "";
            Process p = Runtime.getRuntime().exec(
                    "powershell -command \"Get-ItemProperty HKLM:\\Software\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\* | Select-Object DisplayName, Publisher\"");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                result += line + "\n";
            }
            input.close();
            msgBox(result);

        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public static void msgBox(String msg) {
        JTextArea textArea = new JTextArea(24, 120);
        textArea.setText(msg);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("monospaced", Font.PLAIN, 14));
        textArea.setSelectedTextColor(Color.WHITE);
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setOpaque(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null, scrollPane, "Installed Applications",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

    }
}
