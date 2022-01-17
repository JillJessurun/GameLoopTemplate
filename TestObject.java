import java.awt.*;

public class TestObject extends ProgramObject{

    private Handler handler;

    public TestObject(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle ((int)x, (int)y, 32, 32);
    }

    private void collision(){
        for (int i = 0; i < handler.object.size(); i++) {
            if (this.getId() == ID.TestObject) {
                ProgramObject tempObject = handler.object.get(i);
                if (tempObject.getId() == ID.TestObject2) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        //collision code (under here happens when colliding)
                        HUD.HEALTH -= 2;
                    }
                }
            }
        }
    }

    public void tick() {
        x += velX;
        y += velY;

        x = Main.clamp(x, 0, Main.WIDTH - 64);
        y = Main.clamp(y, 0, Main.HEIGHT - 64);

        collision();
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int)x, (int)y, 32, 32);
    }
}
