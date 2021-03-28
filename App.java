import javax.swing.*;

class App extends JFrame {
    
    private Draw draw;
    private int N = 512;

    public App() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Fluid simulation");
        this.setLocation(1000, 450);
        this.setSize(N, N);
        draw = new Draw(this.N);
        this.add(draw);
    }

    public void Run() {
        this.setVisible(true);
    }
}
