import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ListAllProcesses {
    public void main(String args[]){
        try {
            String line;
            String result = "";
            Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
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
        JTextArea textArea = new JTextArea(24, 85);
        textArea.setText(msg);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("monospaced", Font.PLAIN, 14));
        textArea.setSelectedTextColor(Color.WHITE);
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setOpaque(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        javax.swing.JOptionPane.showConfirmDialog((java.awt.Component)
           null, scrollPane, "Running Processes", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

        
      }
}
