package common;

public class Metrics {
    public long dfs, edges, pushes, pops, relax;
    long t0, t1;

    public void start(){ t0=System.nanoTime();}
    public void stop(){ t1=System.nanoTime();}
    public double ms(){return (t1-t0)/1e6;}
}
