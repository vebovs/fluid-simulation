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

    public void Step() {
        int N = this.N;

        double visc = this.visc;
        double diff = this.diff;
        double dt = this.dt;

        double[] Vx = this.Vx;
        double[] Vy = this.Vy;

        double[] Vx0 = this.Vx0;
        double[] Vy0 = this.Vy0;

        double[] s = this.s;
        double[] density = this.density;
        
        Utils.diffuse(1, Vx0, Vx, visc, dt, 4, N);
        Utils.diffuse(2, Vy0, Vy, visc, dt, 4, N);
        
        Utils.project(Vx0, Vy0, Vx, Vy, 4, N);
        
        Utils.advect(1, Vx, Vx0, Vx0, Vy0, dt, N);
        Utils.advect(2, Vy, Vy0, Vx0, Vy0, dt, N);
        
        Utils.project(Vx, Vy, Vx0, Vy0, 4, N);
        
        Utils.diffuse(0, s, density, diff, dt, 4, N);
        Utils.advect(0, density, s, Vx, Vy, dt, N);
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
