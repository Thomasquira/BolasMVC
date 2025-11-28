/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author thomas
 */
public class DataPanel extends JPanel {

    View view;
    JTable table;
    DefaultTableModel tableModel;

    public DataPanel(View view) {
        this.view = view;

        String[] columnNames = {"Título", "Valor"};
        Object[][] data = {
            {"FPS", "0"},
            {"Render Time", "0"},
            {"Contador de Bolas", "0"}
        };

        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void actualizar(String key, String value) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(key)) {
                tableModel.setValueAt(value, i, 1);
                return;
            }
        }
    }

}
