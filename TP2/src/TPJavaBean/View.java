package TPJavaBean;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {
    private JPanel MainPanel;
    private JButton premierButton;
    private JButton dernierButton;
    private JButton precedentButton;
    private JButton suivantButton;
    private ImageVisualizer imageVisualizer;
    private JCheckBox modeDiaporamaCheckBox;
    private JCheckBox affichageEnBoucleCheckBox;
    private JSlider timer;

    public View() {
        premierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageVisualizer.premier();
            }
        });
        dernierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageVisualizer.dernier();
            }
        });
        precedentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageVisualizer.precedent();
            }
        });
        suivantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageVisualizer.suivant();
            }
        });

        affichageEnBoucleCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageVisualizer.setBoucle(affichageEnBoucleCheckBox.isSelected());
            }
        });

        modeDiaporamaCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageVisualizer.setDiaporama(modeDiaporamaCheckBox.isSelected());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Photo");
        frame.setContentPane(new View().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
