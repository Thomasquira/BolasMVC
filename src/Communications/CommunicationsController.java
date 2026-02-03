/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Communications;

import Communications.Conectors.*;
import Communications.Connections.Channel;
import java.io.IOException;

/**
 *
 * @author thomas
 */
public class CommunicationsController {

    private ServerConnector server;
    private ClientConnector client;

    public CommunicationsController(int myPort1, int myPort2, String peerHost, int peerPort1, int peerPort2) throws IOException {
        server = new ServerConnector(myPort1, myPort2);
        int myListeningPort = server.getListeningPort();
//    System.out.println("debuj, haz de cuenta que serves está en " + myListeningPort + ". acá no fue mano.");
        client = new ClientConnector(peerHost, peerPort1, peerPort2);
        client.setForbiddenPort(myListeningPort);
    }

    public void start() {
        new Thread(server).start();
        new Thread(client).start();
    }

    public Channel getConnection() {
        Channel c = client.getConnection();
        if (c != null && c.taVivo()) {
            return c;
        }
        return server.getConnection();
    }
}
