package KuramotoUnit;

import java.util.Random;

public class Unit {
	
//	double cv;
	double phase;
	
	double t;
	double dt;
	rungekutta rk;
	double w;
	
	public Unit(double w, double dt, double K, double sd) {
		init(w, dt, K);
		this.w = naturalWave(sd);
	}
	
	protected void init(double w, double dt, double K) {
		t = 0;
		this.dt = dt;
		setWavePrm(w);
		rk = new rungekutta(dt, K);
		
	}
	
	public double wave() {
		return Math.sin(w);
	}
	
	public void setWavePrm(double prm) {
		this.phase = prm;
	}
	
	private double naturalWave(double sd) {
		Random r = new Random();
		double res = r.nextDouble();
//		while(((res > (-2*Math.sqrt(3) * sd) )&& (res < 2*Math.sqrt(3) * sd))) {
//			res = r.nextDouble();
//		}
		return res;
	}
	
	public void setRKPrm(double[] phj) {
		rk.setParameter(phj);
		rk.setParameter(w, phase);
	}
	
	public void calcValue() {
		phase = rk.getImpulse(t);
//		if((phase % (2 * Math.PI)) < 0.01) {
//			w = phase / t;
//			phase = 0;
//			t = 0;
//		}
		
		
	}
	public void nexStep() {	t = t + dt; }
	
	public double getPhase() {return phase; }
//	public double getCv(){ return cv; }
}
