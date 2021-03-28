import javax.swing.event.*;
import java.awt.event.*;

class MouseHandler extends MouseInputAdapter {

    private int dirX = 0;
    private int dirY = 50;

    public int getDirX() {
        return this.dirX;
    }

    public int getDirY() {
        return this.dirY;
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Help");
    }

}
