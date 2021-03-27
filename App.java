import javax.swing.*;

class App extends JFrame {
    
    private Draw draw = new Draw();
    private int N = 512;

    public App() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Fluid simulation");
        this.setLocation(1000, 450);
        this.setSize(N, N);
        this.add(draw);
    }

    public void Run() {
        this.setVisible(true);
    }
}
