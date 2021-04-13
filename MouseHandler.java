import javax.swing.event.*;
import java.awt.event.*;

class MouseHandler extends MouseInputAdapter {

    private int N;
    private int tol = 50;
    private int dirX = 0;
    private int dirY = 0;

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
        
        if(e.getX() < this.N/2 + this.tol && e.getX() > this.N/2  - this.tol) {
            this.dirX = 0;
        } else if(e.getX() < this.N/2) {
            this.dirX = -50;
        } else {
            this.dirX = 50;
        }
        
        if(e.getY() < this.N/2 + this.tol && e.getY() > this.N/2  - this.tol) {
            this.dirY = 0;
        } else if(e.getY() < this.N/2) {
            this.dirY = -50;
        } else {
            this.dirY = 50;
        }

    }

}
