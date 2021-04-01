import javax.swing.*;

class App extends JFrame {
    
    private Draw draw;
    private int size = 500;
    private int scale = 3;

    public App() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Fluid simulation");
        this.setLocation(1000, 450);
        this.setSize(size + 16, size + 39);
        draw = new Draw(this.size, this.scale);
        this.add(draw);
    }

    public void Run() {
        this.setVisible(true);
    }
}
