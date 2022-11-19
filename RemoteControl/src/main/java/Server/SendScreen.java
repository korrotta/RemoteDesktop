package Server;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;

class SendScreen extends Thread {

    Socket socket = null;

    Robot robot = null;
    Rectangle rectangle = null;
    boolean continueLoop = true;

    OutputStream oos = null;
    StringBuilder mess;

    public SendScreen(Socket socket, Robot robot, Rectangle rect) {
        this.socket = socket;
        this.robot = robot;
        this.rectangle = rect;

        start();
    }

    public void run() {

        try {

            oos = socket.getOutputStream();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        while (continueLoop) {
            BufferedImage image = robot.createScreenCapture(rectangle);

            try {
                ImageIO.write(image, "jpeg", oos);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
