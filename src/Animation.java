
import java.io.IOException;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author thomas
 */
public class Animation {

    public static void main(String[] args) throws IOException {
        String peerIP = args.length > 0 ? args[0] : "localhost";
        new MasterController(peerIP);
    }

}
