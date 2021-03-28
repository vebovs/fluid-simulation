import javax.swing.event.*;
import java.awt.event.*;

class MouseHandler extends MouseInputAdapter {

    private int N;
    private int dirX = 0;
    private int dirY = 50;

    public MouseHandler(int N) {
        this.N = N;
    }

    public int getDirX() {
        return this.dirX;
    }

    public int getDirY() {
        return this.dirY;
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        
        if(e.getX() < this.N/2) {
            this.dirX = -e.getX();
        } else {
            this.dirX = e.getX();
        }
        
        if(e.getY() < this.N/2) {
            this.dirY = -e.getY();
        } else {
            this.dirY = e.getY();
        }

    }

}
