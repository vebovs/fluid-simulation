import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Draw extends JPanel implements ActionListener {

    private Fluid fluid;
    private Timer timer;
    private MouseHandler ml;
    private int N;
    private int scale;

    public Draw(int size, int scale) {
        this.N = size;
        this.scale = scale;
        this.fluid = new Fluid(0.1, 0, 0.0000001, this.N/this.scale);
        this.timer = new Timer(1, this);
        this.timer.start();
        this.ml = new MouseHandler(this.N);
        this.addMouseMotionListener(ml);
    }
 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);

        Graphics2D g2d = (Graphics2D) g;

        fluid.Step();

        for(int i = 0; i < this.N/this.scale; i++) {
            for(int j = 0; j < this.N/this.scale; j++) {
                int density = (int) fluid.density[Utils.IX(i, j, this.N/this.scale)];
                density = Utils.constraint(density, 0, 255);
                Color color = new Color(100, 100, 100, density);
                g2d.setColor(color);
                g2d.fillRect(i * this.scale, j * this.scale, this.scale, this.scale);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double amount = Math.random() * (40000 - 4000 + 1) + 4000;
        //fluid.addDensity((this.N/this.scale)/2, (this.N/this.scale)/2, amount);
        //fluid.addVelocity((this.N/this.scale)/2, (this.N/this.scale)/2, 0, 50); 
        fluid.addDensity(this.N/2, this.N/2, amount);
        fluid.addVelocity(this.N/2, this.N/2, ml.getDirX(), ml.getDirY()); 
        repaint();
    }
     
}
