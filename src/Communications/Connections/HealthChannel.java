/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Communications.Connections;

/**
 *
 * @author thomas
 */
public class HealthChannel implements Runnable {

    private final Channel channel;
    private long lastUp = System.currentTimeMillis();
    private final long TIMEOUT = 5000;

    public HealthChannel(Channel channel) {
        this.channel = channel;
        
        new Thread(this).start();
    }
    
    public void Udate() {
        lastUp = System.currentTimeMillis();
    }
            
    @Override
    public void run() {
        while (channel.taVivo()) {            
            try {
                channel.send("PING");
                
                if (System.currentTimeMillis() - lastUp > TIMEOUT) {
                    System.out.println("Sin vida, cerradno el socket");
                    channel.close();
                    break;
                }
                
                Thread.sleep(2000);
            } catch (Exception e) {
                channel.close();
            }
        }
    }
    
}
