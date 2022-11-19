package Client;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.util.zip.*;

import java.io.IOException;

class CreateFrame extends Thread {
	String width="", height="";
        private String ip ="";
	 private NewJFrame frame;
         Socket keySocket;
         int port = 80;
	private Socket cSocket = null;

	public CreateFrame(Socket cSocket, String width, String height,String ip) {

		this.width = width;
		this.height = height;
		this.cSocket = cSocket;
                this.ip = ip;
		start();
	}
	
	//Draw GUI per each connected client

	public void drawGUI() {

		
	}

	public void run() { 
                System.out.println("Running");
		//Used to read screenshots
		InputStream in = null;
		//start drawing GUI
		drawGUI();

		try{
                        keySocket = new Socket(ip,port);
			in = cSocket.getInputStream();
                        DataInputStream inp = new DataInputStream(keySocket.getInputStream());
			}catch (IOException ex){
			ex.printStackTrace();
		}
                
               frame = new NewJFrame(ip,keySocket); 
               
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		//Show thr frame in maximized state
//	
//		frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);		
               JPanel jPanel2 = frame. getjPanel2();
              
               JDesktopPane jDesktopPane = frame.getjDesktopPane1();
               Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
               jPanel2.setPreferredSize(dim);
               jPanel2.setFocusable(true);
               jPanel2.requestFocus();

               frame.setVisible(true);
              
                
               
               
               new ReceiveScreen(in, jPanel2,frame);
	       new SendEvents(cSocket, jPanel2,width,height);
               
	}
}
