package TPJavaBean;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;

public class ImageVisualizer extends JComponent implements Serializable {
    private File[] images;
    int index = 0;

    public ImageVisualizer() {
        super();
        setSize(400, 500);
        getImages("/home/eleves/promo20/info/lrolan/Bureau/tecjava/img");
    }

    private Image showImage() {
        return Toolkit.getDefaultToolkit().createImage(getCurrentImage().getAbsolutePath());
    }

    @Override
    public Dimension getPreferredSize(){ return new Dimension(400, 500); }

    public void paint(Graphics g) {
        Dimension dim = getSize();
        int centerBoxX, centerBoxY;
        centerBoxX = dim.width / 2;
        centerBoxY = dim.height / 2;

        g.drawImage(showImage(), centerBoxX, centerBoxY, this);
    }

    public File getCurrentImage() {
        return images[index];
    }

    public void setCurrentImage(File currentImage) {
        images[index] = currentImage;
    }

    public void getImages(String path) {
        File directory = new File(path);
        images = directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".gif") || pathname.getName().endsWith(".jpg") || pathname.getName().endsWith(".jpeg");
            }
        });
    }

    void premier( ) {
        index = 0;
        showImage();
    }
    void dernier( ) {
        index = images.length - 1;
        showImage();
    }
    void suivant( ) {
        if(index < images.length - 1)
            index++;
        showImage();
    }
    void precedent( ) {
        if(index > 0)
            index--;
        showImage();
    }
}


