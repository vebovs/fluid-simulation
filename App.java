import javax.swing.*;

class App extends JFrame {
    
    private Draw draw;
    private int N = 500;

    public App() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Fluid simulation");
        this.setLocation(1000, 450);
        this.setSize(N + 16, N + 39);
        draw = new Draw(this.N);
        this.add(draw);
    }

    public void Run() {
        this.setVisible(true);
    }
}
