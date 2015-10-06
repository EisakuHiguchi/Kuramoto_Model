package KuramotoUnit;

public class CommonUnit extends Unit {

	public CommonUnit(double w, double dt, double K) {
//		super(w, dt, K);
		super(0, dt, K);
	}
	
	@Override
	public void calcValue() {
		double r = rk.getImpulse(t);
//		double w = rk.getNaturalPhase();
		rk.setThisPhase(r);
	}
	

}
