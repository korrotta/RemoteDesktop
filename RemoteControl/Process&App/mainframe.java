import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.*;

public class mainframe extends JFrame implements ActionListener {

    JMenuBar mb;
    JMenu m1;
    JMenu m2;
    JMenuItem m11;
    JMenuItem m12;
    JMenuItem m13;
    JMenuItem m21;
    JMenuItem m22;
    JMenuItem m23;


    mainframe() {
        this.setTitle("Client");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLayout(new FlowLayout());

        mb = new JMenuBar();

        m1 = new JMenu("Processes");
        m2 = new JMenu("Applications");

        mb.add(m1);
        mb.add(m2);

        m11 = new JMenuItem("List all processes");
        m12 = new JMenuItem("Start a process");
        m13 = new JMenuItem("Stop a process");
        m21 = new JMenuItem("List all applications");
        m22 = new JMenuItem("Start an application");
        m23 = new JMenuItem("Stop an application");

        m11.addActionListener(this);
        m12.addActionListener(this);
        m13.addActionListener(this);
        m21.addActionListener(this);
        m22.addActionListener(this);
        m23.addActionListener(this);

        m1.add(m11);
        m1.add(m12);
        m1.add(m13);
        m2.add(m21);
        m2.add(m22);
        m2.add(m23);

        this.setJMenuBar(mb);

        // //Creating the panel at bottom and adding components
        // JPanel panel = new JPanel(); // the panel is not visible in output
        // JLabel label = new JLabel("Enter Server's IP:");
        // JTextField tf = new JTextField(30); // accepts upto 30 characters
        // JButton connect = new JButton("Connect");
        // JButton clear = new JButton("Clear");
        // panel.add(label); // Components Added using Flow Layout
        // panel.add(tf);
        // panel.add(connect);
        // panel.add(clear);

        // // Text Area at the Center
        // JTextArea ta = new JTextArea();

        // //Adding Components to the frame.
        // this.getContentPane().add(BorderLayout.SOUTH, panel);
        // this.getContentPane().add(BorderLayout.NORTH, mb);
        // this.getContentPane().add(BorderLayout.CENTER, ta);
        
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == m11) {
            ListAllProcesses LP = new ListAllProcesses();
            LP.main(null);
        }

        if(e.getSource() == m12) {
            StartProcess SP = new StartProcess();
            SP.main(null);
        }

        if(e.getSource() == m13) {
            KillProcess KP = new KillProcess();
            KP.main(null);
        }
        if(e.getSource() == m21) {
            ListAllApplications LA = new ListAllApplications();
            LA.main(null);
        }

        if(e.getSource() == m22) {
            StartApplication SA = new StartApplication();
            SA.main(null);
        }

        if(e.getSource() == m23) {
            KillApplication KA = new KillApplication();
            KA.main(null);
        }
     }

}
