package TPJavaBean;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private JTextField filePath;
    private JButton fileChooser;
    private BeanTotalImages beanTotalImages1;
    private BeanInformations beanInformations1;

    private View() {
        imageVisualizer.setTime(timer.getValue());
        beanInformations1.setSource(imageVisualizer);
        beanTotalImages1.setSource(imageVisualizer);

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

        timer.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                imageVisualizer.setTime(timer.getValue());
            }
        });
        filePath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageVisualizer.getImages(filePath.getText());
            }
        });

        fileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser choice = new JFileChooser();
                choice.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = choice.showOpenDialog(MainPanel);
                if(result == JFileChooser.APPROVE_OPTION){
                    String path = choice.getSelectedFile().getAbsolutePath();
                    imageVisualizer.getImages(path);
                    filePath.setText(path);
                }
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
