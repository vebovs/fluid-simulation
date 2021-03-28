class Utils {
    
    static int IX(int x, int y, int N) {
        x = constraint(x, 0, N-1);
        y = constraint(y, 0, N-1);
        return x + (y * N);
    }

    static int constraint(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
     }

    static void diffuse(int b, double[] x, double[] x0, double diff, double dt, int iter, int N) {
        double a = dt * diff * (N - 2) * (N - 2);
        lin_solve(b, x, x0, a, 1 + 6 * a, iter, N);
    }
    
    static void lin_solve(int b, double[] x, double[] x0, double a, double c, int iter, int N) {
        double cRecip = 1 / c;
        for (int k = 0; k < iter; k++) {
                for (int j = 1; j < N - 1; j++) {
                    for (int i = 1; i < N - 1; i++) {
                        x[IX(i, j, N)] =
                            (x0[IX(i, j, N)]
                                + a*(    x[IX(i+1, j, N)]
                                        +x[IX(i-1, j, N)]
                                        +x[IX(i, j+1, N)]
                                        +x[IX(i, j-1, N)]
                                        +x[IX(i, j, N)]
                                        +x[IX(i, j, N)]
                            )) * cRecip;
                    }
                }
            set_bnd(b, x, N);
        }
    }

    static void advect(int b, double[] d, double[] d0, double[] velocX, double[] velocY, double dt, int N) {
        double i0, i1, j0, j1;
      
        double dtx = dt * (N - 2);
        double dty = dt * (N - 2);
      
        double s0, s1, t0, t1;
        double tmp1, tmp2, x, y;
      
        double Ndouble = N;
        double idouble, jdouble;
        int i, j;
      
        for (j = 1, jdouble = 1; j < N - 1; j++, jdouble++) { 
          for (i = 1, idouble = 1; i < N - 1; i++, idouble++) {
            tmp1 = dtx * velocX[IX(i, j, N)];
            tmp2 = dty * velocY[IX(i, j, N)];
            x = idouble - tmp1; 
            y = jdouble - tmp2;
            
            if (x < 0.5) x = 0.5; 
            if (x > Ndouble + 0.5) x = Ndouble + 0.5; 
            i0 = Math.floor(x); 
            i1 = i0 + 1.0;
            if (y < 0.5) y = 0.5; 
            if (y > Ndouble + 0.5) y = Ndouble + 0.5; 
            j0 = Math.floor(y);
            j1 = j0 + 1.0; 
      
            s1 = x - i0; 
            s0 = 1.0 - s1; 
            t1 = y - j0; 
            t0 = 1.0 - t1;
      
            int i0i = (int) i0;
            int i1i = (int) i1;
            int j0i = (int) j0;
            int j1i = (int) j1;
      
            d[IX(i, j, N)] = 
              s0 * (t0 * d0[IX(i0i, j0i, N)] + t1 * d0[IX(i0i, j1i, N)]) +
              s1 * (t0 * d0[IX(i1i, j0i, N)] + t1 * d0[IX(i1i, j1i, N)]);
          }
        }
      
        set_bnd(b, d, N);
      }
      

    static void project(double[] velocX, double[] velocY, double[] p, double[] div, int iter, int N) {
        for (int j = 1; j < N - 1; j++) {
            for (int i = 1; i < N - 1; i++) {
                div[IX(i, j, N)] = -0.5 *(
                        velocX[IX(i+1, j, N)]
                        -velocX[IX(i-1, j, N)]
                        +velocY[IX(i, j+1, N)]
                        -velocY[IX(i, j-1, N)]
                    )/N;
                p[IX(i, j, N)] = 0;
            }
        }

        set_bnd(0, div, N); 
        set_bnd(0, p, N);
        lin_solve(0, p, div, 1, 6, iter, N);
        
        for (int j = 1; j < N - 1; j++) {
            for (int i = 1; i < N - 1; i++) {
              velocX[IX(i, j, N)] -= 0.5 * (  p[IX(i+1, j, N)] -p[IX(i-1, j, N)]) * N;
              velocY[IX(i, j, N)] -= 0.5 * (  p[IX(i, j+1, N)] -p[IX(i, j-1, N)]) * N;
            }
        }

        set_bnd(1, velocX, N);
        set_bnd(2, velocY, N);
    }

    static void set_bnd(int b, double[] x, int N) {
        for (int i = 1; i < N - 1; i++) {
          x[IX(i, 0, N)] = b == 2 ? -x[IX(i, 1, N)] : x[IX(i, 1, N)];
          x[IX(i, N-1, N)] = b == 2 ? -x[IX(i, N-2, N)] : x[IX(i, N-2, N)];
        }
        
        for (int j = 1; j < N - 1; j++) {
          x[IX(0, j, N)] = b == 1 ? -x[IX(1, j, N)] : x[IX(1, j, N)];
          x[IX(N-1, j, N)] = b == 1 ? -x[IX(N-2, j, N)] : x[IX(N-2, j, N)];
        }
      
        x[IX(0, 0, N)] = 0.5 * (x[IX(1, 0, N)] + x[IX(0, 1, N)]);
        x[IX(0, N-1, N)] = 0.5 * (x[IX(1, N-1, N)] + x[IX(0, N-2, N)]);
        x[IX(N-1, 0, N)] = 0.5 * (x[IX(N-2, 0, N)] + x[IX(N-1, 1, N)]);
        x[IX(N-1, N-1, N)] = 0.5 * (x[IX(N-2, N-1, N)] + x[IX(N-1, N-2, N)]);
      }
}
