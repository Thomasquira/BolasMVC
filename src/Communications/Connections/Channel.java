/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Communications.Connections;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

/**
 *
 * @author thomas
 */
public class Channel implements Runnable {

    private Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    volatile boolean taVivo = true;
    private Thread thread;

    public Channel(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            while (taVivo) {                
                Object obj = in.readObject();
                procesarMensaje(obj);
            }
            
        } catch (Exception e) {
            close();
        }
    }
    
    public synchronized void send(Object obj) throws IOException {
        out.writeObject(obj);
        out.flush();
    }

    private void procesarMensaje(Object obj) {
        System.out.println("Recibido: " + obj.getClass().getSimpleName());
    }
    
    public void close() {
        taVivo = false;
        try {
            socket.close();
        } catch (IOException ignored) {
        }
    }

    public boolean taVivo() {
        return taVivo && !socket.isClosed();
    }
}
