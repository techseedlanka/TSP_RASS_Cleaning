/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Sapumal Bandara @ TechSeed Solutions
 */
public class Table_Header {
    static public class HeaderColor extends DefaultTableCellRenderer {

        public HeaderColor() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            setFont(new Font("Georgia", Font.PLAIN, 12));
            setForeground(Color.BLUE);
            setBorder(BorderFactory.createBevelBorder(0, Color.lightGray, Color.LIGHT_GRAY));
            setBackground(Color.LIGHT_GRAY);
            return this;

        }

    }
}
