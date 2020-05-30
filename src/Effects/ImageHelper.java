package Effects;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageHelper {
    public static Image loadImage(String fileName) {
        return new ImageIcon(ImageHelper.class.getResource("rsc/Pictures/" + fileName)).getImage();
    }
    public static BufferedImage loadImageAsStream(String fileName) throws IOException {
        return ImageIO.read(new FileInputStream("rsc/Pictures/" + fileName));
    }
}
