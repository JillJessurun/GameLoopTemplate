import javax.swing.*;
import java.awt.*;

public class Window {
    public Window(int width, int height, String title, Game game){
        //create frame
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));//set the size of the dimension of the frame
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        /*
        FULLSCREEN? ADD:
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);//make the screen fullscreen
            frame.setUndecorated(true);//makes sure you dont see the window
        */

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//makes the exit button int the top right corner work
        frame.setResizable(true);//makes sure you cannot resize the window which could generate a lot of problems
        frame.setLocationRelativeTo(null);//the window is locked to the middle of the screen now (null does that)
        frame.add(game);//add the Game class (the main class u just created) to the frame
        frame.setVisible(true);//makes the frame visible
        game.start();//runs the start method you created in your Game class
    }
}
