package TPJavaBean;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

public class BeanInformations extends JLabel implements Serializable, PropertyChangeListener {
    private ImageInfo _info;
    private ImageVisualizer _source;

    public BeanInformations() {
        _info = new ImageInfo("", "", "");
    }

    public void setSource(ImageVisualizer imageVisualizer) {
        _source = imageVisualizer;
        _source.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Info changed");
        _info = (ImageInfo) evt.getNewValue();
        repaint();
    }
    @Override
    public Dimension getPreferredSize(){ return new Dimension(1280, 50); }

    public void paint(Graphics g) {
        g.drawString(_info._index, 10, 20);
        g.drawString(_info._name, 500, 20);
        g.drawString(_info._size, 1000, 20);
    }
}
