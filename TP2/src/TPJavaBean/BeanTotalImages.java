package TPJavaBean;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

public class BeanTotalImages extends JLabel implements Serializable, PropertyChangeListener {
    String _totalImages;
    ImageVisualizer _source;

    public BeanTotalImages() {
        _totalImages = "";
    }

    public void setSource(ImageVisualizer imageVisualizer) {
        _source = imageVisualizer;
        _source.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Directory changed");
        _totalImages = evt.getNewValue().toString();
        repaint();
    }
    @Override
    public Dimension getPreferredSize(){ return new Dimension(200, 50); }

    public void paint(Graphics g) {
        g.drawString(_totalImages, 0, 30);
    }
}
