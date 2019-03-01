package TPJavaBean;


import javax.swing.*;
import java.awt.*;
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


    public ImageVisualizer() {
        super();
        getImages("/home/eleves/promo20/info/lrolan/Bureau/tecjava/img");
    }

    @Override
    public Dimension getPreferredSize(){ return new Dimension(1280, 860); }

    public void paint(Graphics g) {
       Dimension dim = getPreferredSize();
        g.drawImage(_img, 0, 0, dim.width, dim.height, this);
    }

    public void setTime(int time) { _time = time; }
    public void setDiaporama(boolean diaporama) { _diaporama = diaporama; }
    public void setBoucle(boolean boucle) { _boucle = boucle; }


    public void getCurrentImage() {
        _img = Toolkit.getDefaultToolkit().createImage(_images[_index].getAbsolutePath());
    }

    public void getImages(String path) {
        File directory = new File(path);
        _images = directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".gif") || pathname.getName().endsWith(".jpg") || pathname.getName().endsWith(".jpeg");
            }
        });
        getCurrentImage();
    }

    void premier( ) {
        _index = 0;
        getCurrentImage();
        repaint();
    }
    void dernier( ) {
        _index = _images.length - 1;
        getCurrentImage();
        repaint();
    }
    boolean suivant( ) {
        if(_index < _images.length - 1) {
            _index++;
            getCurrentImage();
            repaint();
            return true;
        }
        return false;
    }
    boolean precedent( ) {
        if(_index > 0) {
            _index--;
            getCurrentImage();
            repaint();
            return true;
        }
        return false;
    }
}


