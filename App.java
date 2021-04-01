import javax.swing.*;
import java.awt.*;

class App extends JFrame {
    
    private Draw draw;
    private int size = 500;
    private int scale = 4;

    public App() {
        this.setTitle("Fluid simulation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(1000, 450);
        draw = new Draw(this.size, this.scale);
        draw.setPreferredSize(new Dimension(size, size));
        this.add(draw);
        this.pack();
    }

    public void Run() {
        this.setVisible(true);
    }
}
