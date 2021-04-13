import javax.swing.*;
import java.awt.*;

class App extends JFrame {
    
    private Draw draw;
    private int size = 500;
    private int scale = 2;

    public App() {
        
        this.setTitle("Fluid simulation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screenSize.width/2 - this.size/2, screenSize.height/2 - this.size/2);
        
    }

    public void Run() {
        this.draw = new Draw(this.size, this.scale);
        this.draw.setPreferredSize(new Dimension(this.size, this.size));
        this.add(draw);
        this.pack();
        this.setVisible(true);
    }
}
