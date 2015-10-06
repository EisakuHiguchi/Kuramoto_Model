package KuramotoUnit;

public class rungekutta {
	/*
	 * ルンゲクッタ法
	 * dθi/dt = ωi + K/NΣN,j=1(sin(θj - θi)
	 */
	
	protected double dt, phi, o, K;
	protected double[] phj, k0, k1, k2, k3;

	public rungekutta(double dt, double K) {
		this.K = K;
		this.dt = dt;
		int length = 4;
		k0 = new double[length];
		k1 = new double[length];
		k2 = new double[length];
		k3 = new double[length];
	}
	
	public void setParameter(double[] phj, double o,double phi){
		setParameter(phj);
		setNaturalPhase(o);
		setThisPhase(phi);
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
		double ss = 0;
		for(int i = 0; i < phj.length; i++)	ss = ss + Math.sin(phi - phj[i]);
		return  o - (K / phj.length) * ss;
	}
	
	private double[] sf1(double[] phj, double k) {
		double[] r = new double[phj.length];
		for(int i = 0; i < phj.length; i++)	r[i] = phj[i] + k;
		return r;
	}
	
	public void setParameter(double[] phj) { this.phj = phj; }	
	public double getNaturalPhase() { return o; }
	public double getThisPhase() { return phi; }
	public double getBand() { return K; }
	public double getDifTime() { return dt; }
	public double[] getOtherPhase() { return phj; }
	public void setNaturalPhase(double prm) { o = prm; }
	public void setThisPhase(double prm) { phi = prm; }
	public void setBand(double prm) { K = prm; }
	public void setDifTime(double prm) { dt = prm; }
	
}

