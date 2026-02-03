/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Controller;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.Ball;
import model.Controlable;
import model.Model;

/**
 *
 * @author thomas
 */
public class View extends JFrame {

    Controller controller;
    ControlPanel controlpanel;
    Viewer viewer;
    DataPanel dataPanel;
    Thread autoThread;
    boolean auto = false;
    Model model;
    private Controlable bolaControlable;

    public View(Controller controller) {
        this.controller = controller;
        this.controlpanel = new ControlPanel(this);
        this.viewer = new Viewer(this);
        this.dataPanel = new DataPanel(this);
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 600);

   
        JPanel content = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        viewer.getThread().start();
        controlpanel.FIRE_BUTTON.addActionListener(e -> addBall());
        controlpanel.borrar.addActionListener(e -> removeBalls());
        controlpanel.getautomatico().addActionListener(e -> autoMode());
        controlpanel.getPlay().addActionListener(e -> controller.play());
        controlpanel.getPause().addActionListener(e -> controller.pause());
        controlpanel.getStop().addActionListener(e -> controller.stop());
        controlpanel.getWorldButton().addActionListener(e -> {
            generateRandomWorld();
        });
     
        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints leftGbc = new GridBagConstraints();
        leftGbc.insets = new Insets(5, 5, 5, 5);
        leftGbc.fill = GridBagConstraints.BOTH;
        leftGbc.weightx = 1.0;

    
        leftGbc.gridx = 0;
        leftGbc.gridy = 0;
        leftGbc.weighty = 0.7;
        leftPanel.add(controlpanel, leftGbc);


        leftGbc.gridx = 0;
        leftGbc.gridy = 1;
        leftGbc.weighty = 0.5;
        leftPanel.add(dataPanel, leftGbc);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.1;
        gbc.weighty = 1.0;
        content.add(leftPanel, gbc);

     
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.9;
        gbc.weighty = 1.0;
        content.add(viewer, gbc);

 
        add(content);
        setLocationRelativeTo(null);
        setVisible(true);
        SwingUtilities.invokeLater(() -> {
            addBolaControlable();
        });
        SwingUtilities.invokeLater(() -> viewer.requestFocusInWindow());

    }

    public void addBall() {
        try {
            int min = Integer.parseInt(controlpanel.getMinimoText());
            int max = Integer.parseInt(controlpanel.getMaximoText());
            int velMax = Integer.parseInt(controlpanel.getMaximoVelText());
            int velMin = Integer.parseInt(controlpanel.getMinimoVelText());
            controller.setTamaño(min, max);
            controller.setVelocidad(velMax, velMin);
            controller.addBall();
            System.out.println("Bola añadida entre de tamaño entre " + min + " y " + max);
            System.out.println("Y de velocidad entre " + min + " y " + max);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Porfavor ingrese números válidos");
        }
        System.out.println("Funciono");
    }

    public void removeBalls() {
        controller.removeBalls();
    }

    public CopyOnWriteArrayList<Ball> getAllBalls() {
        return controller.getAllBalls();
    }

    public int getViewerWidth() {
        return viewer.getWidth();
    }

    public int getViewerHeight() {
        return viewer.getHeight();
    }

    public void startViewer() {
        viewer.startViewer();
    }

    public void addFireButtonListener(ActionListener listener) {
        controlpanel.getFIRE_BUTTON().addActionListener(listener);
    }

    private void autoMode() {
        if (controlpanel.getautomatico().isSelected()) {
            auto = true;
            autoThread = new Thread(() -> {
                while (auto) {
                    addBall();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            autoThread.start();

        } else {
            auto = false;
        }
    }
    public void generateRandomWorld() {
        controller.removeBalls();
        
        for (int i = 0; i < 15; i++) {
            addBall();
        }
        
        System.out.println("Nuevo mundo generado con 15 bolas");
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public DataPanel getDataPanel() {
        return dataPanel;
    }

    public Controlable getBolaControlable() {
        return bolaControlable;
    }

    public void setBolaControlable(Controlable bolaControlable) {
        this.bolaControlable = bolaControlable;
    }

    public void addBolaControlable() {
        this.bolaControlable = new Controlable(controller.getModel(), 100, 100);
        controller.getAllBalls().add(bolaControlable);
    }
    
    public void moverBolaIzq() {
       controller.moverBolaIzq();
    }

    public void moverBolaDer() {
       controller.moverBolaDer();
    }

    public void moverBolaArr() {
        controller.moverBolaArr();
    }

    public void moverBolaAba() {
        controller.moverBolaAba();
    }

    public ControlPanel getControlpanel() {
        return controlpanel;
    }

    public void setControlpanel(ControlPanel controlpanel) {
        this.controlpanel = controlpanel;
    }
    
    
}
