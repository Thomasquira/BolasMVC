/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.*;

/**
 *
 * @author thomas
 */
public class ControlPanel extends JPanel {

    View view;
    JButton FIRE_BUTTON, borrar, play, pause, stop;
    JTextField minimoTamaño, maximoTamaño, minimoVelocidad, maximoVelocidad;
    JLabel minTam, maxTam, minVel, maxVel;
    JCheckBox automatico;

    public ControlPanel(View view) {
        this.view = view;
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        this.FIRE_BUTTON = new JButton("Bola");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(FIRE_BUTTON, gbc);

        gbc.insets = new Insets(2, 5, 5, 5);
        this.automatico = new JCheckBox("Lanzar bolas automáticamente");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(automatico, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        this.borrar = new JButton("Limpiar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(borrar, gbc);

        this.minTam = new JLabel("Tamaño mínimo:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        add(minTam, gbc);

        this.minimoTamaño = new JTextField("1");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        add(minimoTamaño, gbc);

        this.maxTam = new JLabel("Tamaño máximo:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        add(maxTam, gbc);

        this.maximoTamaño = new JTextField("20");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        add(maximoTamaño, gbc);

        gbc.insets = new Insets(30, 5, 5, 5);
        this.minVel = new JLabel("Mínimo Velocidad:");
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(minVel, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        this.minimoVelocidad = new JTextField("2");
        gbc.gridx = 0;
        gbc.gridy = 9;
        add(minimoVelocidad, gbc);

        this.maxVel = new JLabel("Máximo Velocidad:");
        gbc.gridx = 0;
        gbc.gridy = 10;
        add(maxVel, gbc);

        this.maximoVelocidad = new JTextField("5");
        gbc.gridx = 0;
        gbc.gridy = 11;
        add(maximoVelocidad, gbc);

        // --- Controles de reproducción (Play, Pause, Stop) ---
        JPanel controlButtonsPanel = new JPanel();
        controlButtonsPanel.setLayout(new GridLayout(1, 3, 3, 0)); // 1 fila, 3 columnas, separación horizontal de 3px

        this.play = new JButton("Play");
        this.pause = new JButton("Pause");
        this.stop = new JButton("Stop");


        Dimension size = new Dimension(80, 30);
        play.setPreferredSize(size);
        pause.setPreferredSize(size);
        stop.setPreferredSize(size);

        controlButtonsPanel.add(play);
        controlButtonsPanel.add(pause);
        controlButtonsPanel.add(stop);


        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.fill = GridBagConstraints.NONE;
        add(controlButtonsPanel, gbc);

    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public String getMinimoText() {
        return minimoTamaño.getText();
    }

    public String getMaximoText() {
        return maximoTamaño.getText();
    }

    public String getMinimoVelText() {
        return minimoVelocidad.getText();
    }

    public String getMaximoVelText() {
        return maximoVelocidad.getText();
    }

    public JLabel getMin() {
        return minTam;
    }

    public void setMin(JLabel min) {
        this.minTam = min;
    }

    public JLabel getMax() {
        return maxTam;
    }

    public void setMax(JLabel max) {
        this.maxTam = max;
    }

    public JButton getFIRE_BUTTON() {
        return this.FIRE_BUTTON;
    }

    public JCheckBox getautomatico() {

        return this.automatico;

    }

    public JButton getPlay() {
        return play;
    }

    public void setPlay(JButton play) {
        this.play = play;
    }

    public JButton getPause() {
        return pause;
    }

    public void setPause(JButton pause) {
        this.pause = pause;
    }

    public JButton getStop() {
        return stop;
    }

    public void setStop(JButton stop) {
        this.stop = stop;
    }

}
