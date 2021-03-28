import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Draw extends JPanel implements ActionListener {

    private Fluid fluid;
    private Timer timer;
    private int N;

    public Draw(int N) {
        this.N = N;
        fluid = new Fluid(0.1, 0, 0.0000001, N);
        timer = new Timer(10, this);
        timer.start();
    }
 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);

        Graphics2D g2d = (Graphics2D) g;

        fluid.Step();

        for(int i = 0; i < this.N; i++) {
            for(int j = 0; j < this.N; j++) {
                int density = (int) fluid.density[Utils.IX(i, j, this.N)];
                density = Utils.constraint(density, 0, 255);
                Color color = new Color(100, 100, 100, density);
                g2d.setColor(color);
                g2d.drawLine(i, j, i, j);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double amount = Math.random() * (40000 - 4000 + 1) + 4000;
        fluid.addDensity(this.N/2, this.N/2, amount);
        fluid.addVelocity(this.N/2, this.N/2, 50, 50); 
        repaint();
    }
     
}
