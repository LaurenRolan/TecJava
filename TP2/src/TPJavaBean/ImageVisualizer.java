package TPJavaBean;

import javafx.scene.image.Image;

import javax.swing.*;
import java.io.File;
import java.io.Serializable;

public class ImageVisualizer extends JComponent implements Serializable {
    File currentImage;

    public ImageVisualizer() {
        super();
        setSize(400, 500);
    }

    public void showImage() {
        Image Toolkit.getDefaultToolkit.createImage (currentImage.getName()) ;

    }

}


