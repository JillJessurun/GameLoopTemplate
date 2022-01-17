import java.awt.*;

public class HUD {

    public static int HEALTH = 100;

    public void tick(){
        HEALTH = (int)Main.clamp(HEALTH, 0, 100);
    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 50);
        g.setColor(Color.green);
        g.fillRect(15, 15, HEALTH * 2, 50);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 50);

        g.drawString(HEALTH+"%", 225, 40);
    }
}
