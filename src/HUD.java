import java.awt.*;

public class HUD {

    public static int HEALTH = 100;

    public void tick(){

    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 50);
    }
}
