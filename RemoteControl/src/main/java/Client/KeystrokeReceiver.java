/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author ad
 */
public class KeystrokeReceiver extends Thread {

    private String KEYSTROKE_START = "start";
    private String KEYSTROKE_STOP = "stop";
    private String message ="";

    public String getMessage() {
        return message;
    }
    
    Socket socket = null;

    public KeystrokeReceiver(Socket s) {
        this.socket = s;

        start();

    }
    public void emptyMessage(){
        message = " ";
        
    }
    public void run() {

        String res = "";
        try ( DataInputStream inp = new DataInputStream(socket.getInputStream());) {

            while (true) {
                  
//                System.out.println(inp.readUTF());
                message += inp.readUTF();
            }

        } catch (IOException e) {

        }

    }

}
