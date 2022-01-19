import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BufferedImageLoader {

    BufferedImage image;

    public BufferedImage loadImage(String path) throws IOException {
        try {
            image = ImageIO.read(new File(path));
        }catch(Exception e){
            e.printStackTrace();
        }
        return image;
    }

}
