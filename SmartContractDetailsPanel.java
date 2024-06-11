/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodsupplychainsystem;

/**
 *
 * @author RIMSHA
 */
import javax.swing.*;
import java.awt.*;

public class SmartContractDetailsPanel extends JPanel {
    public SmartContractDetailsPanel(SmartContract contract) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(new JLabel("Smart Contract " + contract.getId()));
        add(new JLabel("Terms and Conditions:"));
        
        JTextArea termsArea = new JTextArea(contract.getTerms());
        termsArea.setLineWrap(true);
        termsArea.setWrapStyleWord(true);
        termsArea.setEditable(false);
        add(new JScrollPane(termsArea));
        
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
            Container parent = getParent();
            if (parent != null) {
                parent.remove(this);
                parent.revalidate();
                parent.repaint();
            }
        });
        
        add(closeButton);
    }
}
