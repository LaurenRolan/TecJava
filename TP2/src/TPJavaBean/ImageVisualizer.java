package TPJavaBean;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;

public class ImageVisualizer extends JPanel implements Serializable {
    private File[] _images;
    private int _index = 0;
    private Image _img;
    private boolean _diaporama;
    private boolean _boucle;
    private int _time;
    private Thread repeatThread;
    private int _quantity;
    private String _name;
    private String _size;
    private final PropertyChangeSupport _infoBean = new PropertyChangeSupport(this);
    private final PropertyChangeSupport _numberBean = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(BeanInformations listener) {
        _infoBean.addPropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(BeanTotalImages listener) {
        _numberBean.addPropertyChangeListener(listener);
    }

    public ImageVisualizer() {
        super();
    }

    @Override
    public Dimension getPreferredSize(){ return new Dimension(1280, 860); }

    public void paint(Graphics g) {
       Dimension dim = getPreferredSize();
       g.drawImage(_img, 0, 0, dim.width, dim.height, this);
    }

    void setTime(int time) { _time = time; }

    void setDiaporama(boolean diaporama) {
        _diaporama = diaporama;
        if(_diaporama) {
            repeatThread = new Diaporama();
            repeatThread.start();
        } else {
            repeatThread.stop();
        }
    }
    void setBoucle(boolean boucle) { _boucle = boucle; }

    String getIndex() { return String.format("Photo No. %d", _index); }
    String getQuantity() { return String.format("Nombre d'images %d", _quantity); }

    private void getCurrentImage() {
        _img = Toolkit.getDefaultToolkit().createImage(_images[_index].getAbsolutePath());
        _name = _images[_index].getName();
        _size = String.format("%d x %d", _img.getHeight(null), _img.getWidth(null));
        repaint();
        _infoBean.firePropertyChange(null, null, new ImageInfo(_name, _size, getIndex()));
    }

    public void getImages(String path) {
        File directory = new File(path);
        _images = directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".gif") || pathname.getName().endsWith(".jpg") || pathname.getName().endsWith(".jpeg");
            }
        });
        _quantity = _images.length;

        _numberBean.firePropertyChange(null, null, getQuantity());
        getCurrentImage();
    }

    void premier( ) {
        _index = 0;
        getCurrentImage();
    }
    void dernier( ) {
        _index = _images.length - 1;
        getCurrentImage();
    }
    boolean suivant( ) {
        if(_index < _images.length - 1) {
            _index++;
            getCurrentImage();
            return true;
        }
        return false;
    }
    boolean precedent( ) {
        if(_index > 0) {
            _index--;
            getCurrentImage();
            return true;
        }
        return false;
    }

    public class Diaporama extends Thread {
        public Diaporama() { }
        @Override
        public void run() {
            while(true)
            {
                try {
                   Thread.sleep(_time * 1000);
                    boolean next = suivant();
                    if(_boucle && !next) {
                        premier();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


