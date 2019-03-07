package TPJavaBean;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

public class BeanInformations extends JLabel implements Serializable, PropertyChangeListener {
    private ImageInfo _info;

    public BeanInformations() {
        _info = new ImageInfo("", "", "");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        _info = (ImageInfo) evt.getNewValue();
    }
    @Override
    public Dimension getPreferredSize(){ return new Dimension(1280, 50); }

    public void paint(Graphics g) {
        g.drawString(_info._index, 0, 0);
        g.drawString(_info._name, 0, 0);
        g.drawString(_info._size, 0, 0);
    }
}
