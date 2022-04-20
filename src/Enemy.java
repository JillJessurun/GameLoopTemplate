import java.awt.*;

public class Enemy extends GameObject{

    private Handler handler;

    public Enemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 4;
        velY = 2;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick() {

        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT) {
            velY *= -1;
        }
        if (x <= 0 || x >= Game.WIDTH) {
            velX *= -1;
        }

    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
