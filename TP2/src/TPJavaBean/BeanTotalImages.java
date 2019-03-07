package TPJavaBean;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

public class BeanTotalImages extends JLabel implements Serializable, PropertyChangeListener {
    String _totalImages;

    public BeanTotalImages() {
        _totalImages = "";
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        _totalImages = evt.getNewValue().toString();
    }
    @Override
    public Dimension getPreferredSize(){ return new Dimension(80, 50); }

    public void paint(Graphics g) {
        g.drawString(_totalImages, 0, 0);
    }
}
