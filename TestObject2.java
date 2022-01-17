import java.awt.*;

public class TestObject2 extends ProgramObject{
    private Handler handler;

    public TestObject2(float x, float y, ID id, Handler handler) {
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

        if (y <= 0 || y >= Main.HEIGHT) {
            velY *= -1;
        }
        if (x <= 0 || x >= Main.WIDTH) {
            velX *= -1;
        }

    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
