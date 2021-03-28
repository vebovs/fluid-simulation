class Fluid {
    
    int N;
    int iter;

    int size;
    double dt;
    double diff;
    double visc;

    double[] s;
    double[] density;

    double[] Vx;
    double[] Vy;

    double[] Vx0;
    double[] Vy0;

    public Fluid(double dt, double diff, double visc, int N) {
        
        this.N = N;
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
        int iter = this.iter;

        double visc = this.visc;
        double diff = this.diff;
        double dt = this.dt;

        double[] Vx = this.Vx;
        double[] Vy = this.Vy;

        double[] Vx0 = this.Vx0;
        double[] Vy0 = this.Vy0;

        double[] s = this.s;
        double[] density = this.density;
        
        Utils.diffuse(1, Vx0, Vx, visc, dt, iter, N);
        Utils.diffuse(2, Vy0, Vy, visc, dt, iter, N);
        
        Utils.project(Vx0, Vy0, Vx, Vy, iter, N);
        
        Utils.advect(1, Vx, Vx0, Vx0, Vy0, dt, N);
        Utils.advect(2, Vy, Vy0, Vx0, Vy0, dt, N);
        
        Utils.project(Vx, Vy, Vx0, Vy0, iter, N);
        
        Utils.diffuse(0, s, density, diff, dt, iter, N);
        Utils.advect(0, density, s, Vx, Vy, dt, N);
    }
    
    public void addDensity(int x, int y, double amount) {
        this.density[Utils.IX(x, y, this.N)] += amount;
    }

    public void addVelocity(int x, int y, double amountX, double amountY) {
        int index = Utils.IX(x, y, this.N);
        
        this.Vx[index] += amountX;
        this.Vy[index] += amountY;
    }

}
