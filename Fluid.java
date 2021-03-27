class Fluid {
    
    private int N;
    private int iter;

    private int size;
    private double dt;
    private double diff;
    private double visc;

    private double[] s;
    private double[] density;

    private double[] Vx;
    private double[] Vy;

    private double[] Vx0;
    private double[] Vy0;

    public Fluid(double dt, double diff, double visc) {
        
        this.N = 512;
        this.iter = 10;

        this.size = N;
        this.dt = dt;
        this.diff = diff;
        this.visc = visc;
        
        this.s = new double[N * N];
        this.density = new double[N * N];
        
        this.Vx = new double[N * N];
        this.Vy = new double[N * N];
        
        this.Vx0 = new double[N * N];
        this.Vy0 = new double[N * N];
    
    }
    
    private void addDensity(int x, int y, double amount) {
        this.density[Utils.IX(x, y, this.N)] += amount;
    }

    private void addVelocity(int x, int y, double amountX, double amountY)
    {
        int index = Utils.IX(x, y, this.N);
        
        this.Vx[index] += amountX;
        this.Vy[index] += amountY;
    }
}
