import java.awt.*;
import java.util.LinkedList;

public class Handler {
    LinkedList<ProgramObject> object = new LinkedList<ProgramObject>();

    public void tick(){
        for (int i = 0; i < object.size(); i++) {
            ProgramObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g){
        for (int i = 0; i < object.size(); i++) {
            ProgramObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    public void addObject(ProgramObject object){
        this.object.add(object);
    }

    public void removeObject(ProgramObject object){
        this.object.remove(object);
    }

}
