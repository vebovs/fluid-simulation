import javax.swing.*;

class App extends JFrame {
    
    public App() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Fluid simulation");
        this.setLocation(1000, 450);
        this.setSize(450, 500);
    }

    public void Run() {
        this.setVisible(true);
    }
}
