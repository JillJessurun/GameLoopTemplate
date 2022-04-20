import java.awt.*;

public class Player extends GameObject{

    private Handler handler;

    public Player(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    private void collision(){
        for (int i = 0; i < handler.object.size(); i++) {
            if (this.getId() == ID.Player) {
                GameObject tempObject = handler.object.get(i);
                if (tempObject.getId() == ID.Enemy) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        //collision code (under here happens when colliding)
                        System.out.println("collision!!");
                    }
                }
            }
        }
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 64);
        y = Game.clamp(y, 0, Game.HEIGHT - 64);

        collision();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int)x, (int)y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle ((int)x, (int)y, 32, 32);
    }
}
