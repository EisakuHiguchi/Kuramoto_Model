package KuramotoUnit;

public class rungekutta {
	/*
	 * ルンゲクッタ法
	 * dθi/dt = ωi + K/NΣN,j=1(sin(θj - θi)
	 */
	
	double dt;
	double phi, o, K;
	double[] phj;
	
	int length = 4;
	protected double[] k0;
	protected double[] k1;
	protected double[] k2;
	protected double[] k3;

	public rungekutta(double dt, double K) {
		init(dt, K);
	}
	
	private void init(double dt, double K) {
		this.K = K;
		
		this.dt = dt;
		
		k0 = new double[length];
		k1 = new double[length];
		k2 = new double[length];
		k3 = new double[length];
		
	}
	
	public void setParameter(double[] phj, double o,double phi){
		setParameter(phj);
		setParameter(o, phi);
	}
	
	public void setParameter(double[] phj) {
		this.phj = phj;
	}
	
	public void setParameter(double o, double phi) {
		this.o = o;
		this.phi = phi;
	}

	public double getImpulse(double t) {

		k0[0] = dt * f1(t, o, phi, phj, K);

		k1[0] = dt * f1(t + dt / 2.0
				, o + k0[0] / 2.0
				, phi + k0[1] / 2.0
				, sf1(phj, k0[2] / 2.0)
				, K + k0[3] / 2.0
				);


		k2[0] = dt * f1(t + dt / 2.0
				, o + k1[0] / 2.0
				, phi + k1[1] / 2.0
				, sf1(phj, k1[2] / 2.0)
				, K + k1[3] / 2.0
				);

		k3[0] = dt * f1(t + dt
				, o + k2[0]
				, phi + k2[1]
				, sf1(phj, k2[2])
				, K + k2[3]
				);

		phi = phi + (k0[0] + 2.0 * k1[0] + 2.0 * k2[0] + k3[0]) / 6.0;
		return phi;
	}

	protected double f1(double t,double o, double phi, double[] phj, double K) {
		double r;

		double n = phj.length;
		double ss = 0;
		for(int i = 0; i < n; i++) {
			ss = ss + Math.sin(phi - phj[i]);
		} 
		r = o - (K/n) * ss;
//		r =  - (K/n) * ss;
		return r;
	}
	
	private double[] sf1(double[] phj, double k) {
		double[] r = new double[phj.length];
		
		for(int i = 0; i < phj.length; i++) {
			r[i] = phj[i] + k;
		}
		
		return r;
	}
	
}
