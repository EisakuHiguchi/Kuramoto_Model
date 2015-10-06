package KuramotoUnit;

public class Unit {	
	double t;
	rungekutta rk;
	
	public Unit(double w, double dt, double K) {
		t = 0;
		rk = new rungekutta(dt, K);
		setWavePrm(w);
	}
	public void setThisPhase(double prm) { rk.setThisPhase(prm); }
	public void setWavePrm(double prm) { rk.setNaturalPhase(prm); }
	public void setRKPrm(double[] phj) { rk.setParameter(phj); }
	public void calcValue() { rk.setThisPhase(rk.getImpulse(t)); }
	public void nextStep() { t = t + rk.getDifTime(); }
	public void nextStep(double[] phj) { rk.setParameter(phj); nextStep(); }
	public double getPhase() {return rk.getThisPhase(); }
	public double getWavePrm() { return rk.getNaturalPhase(); }
}
