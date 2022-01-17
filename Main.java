import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable{

    //instances
    private Handler handler;
    private Thread thread;
    private HUD hud;

    //variables
    public static final int WIDTH = 1281;
    public static final int HEIGHT = 840;
    private boolean running = true;

    public Main(){
        //window
        new Window(WIDTH, HEIGHT, "Naam game", this);

        //handler stuff
        handler = new Handler();
        handler.addObject(new TestObject(100, 100, ID.TestObject, handler));
        handler.addObject(new TestObject2(100, 100, ID.TestObject2, handler));

        //listeners
        this.addKeyListener(new KeyInput(handler));

        //hud
        hud = new HUD();
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        }catch (Exception e){
            System.out.println("Something went terribly wrong i dunno what sorry");
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running){
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
        hud.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();
    }

    //clamp method: if the var is at the max, it stays at the max (same with the min)
    public static float clamp(float var, float min, float max){
        if(var >= max){
            return var = max;
        }else if(var <= min){
            return var = min;
        }else{
            return var;
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
