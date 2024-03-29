import java.awt.*;
import java.awt.image.BufferStrategy;

//hoi

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 1800;
    public static final int HEIGHT = 1000;
    private Thread thread;
    private boolean running = true;

    //instances
    private Handler handler;
    private HUD hud;

    //constructor
    public Game(){
        handler = new Handler();
        new Window(WIDTH, HEIGHT, "Super Mario Bros", this);

        //keylisteners
        this.addKeyListener(new KeyInput(handler));

        hud = new HUD();

        //adding objects at startup program
        handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
        handler.addObject(new Enemy(300, 300, ID.Enemy, handler));
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch (Exception e){
            System.out.println("The thread cannot join :(");
        }
    }

    public void tick(){
        handler.tick();
        hud.tick();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //backgroundcolour
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();
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
        new Game();
    }

}
