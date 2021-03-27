import javax.swing.*;

class App extends JFrame {
    
    private Graphics graphics = new Graphics();

    public App() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Fluid simulation");
        this.setLocation(1000, 450);
        this.setSize(450, 500);
        this.add(graphics);

    }

    public void Run() {
        this.setVisible(true);
    }
}
