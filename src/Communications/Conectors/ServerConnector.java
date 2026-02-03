/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Communications.Conectors;

import Communications.Connections.Channel;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author thomas
 */
public class ServerConnector implements Runnable {

    private ServerSocket serverSocket;
    private Channel connection;

    public ServerConnector(int port1, int port2) throws IOException {
        try {
            serverSocket = new ServerSocket(port1);
            System.out.println("Conexión en puerto: " + port1);
        } catch (Exception e) {
            serverSocket = new ServerSocket(port2);
            System.out.println("Conexión en puerto: " + port2);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket viene = serverSocket.accept();
                synchronized (this) {
                    if (connection == null || !connection.taVivo()) {
                        connection = new Channel(viene);
                        System.out.println("Conexión hecha");
                    } else {
                        viene.close();
                        System.out.println("Conexión rechazada");
                    }
                }
            } catch (Exception e) {
            }
        }
    }
    
    public int getListeningPort() {
    if (serverSocket != null && !serverSocket.isClosed()) {
        return serverSocket.getLocalPort();
    }
    return -1;
}

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Channel getConnection() {
        return connection;
    }

    public void setConnection(Channel connection) {
        this.connection = connection;
    }

}
