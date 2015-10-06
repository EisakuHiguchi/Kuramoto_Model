package KuramotoUnit;

import java.util.ArrayList;

public class UnitManage {

	ArrayList<Unit> allunit;
	double K, dt;
	
	public UnitManage(double K, double dt) {
		allunit = new ArrayList<>();
		this.K = K;
		this.dt = dt;
	}
	
	public Unit createUnit(double w) {
		Unit u = new Unit(w, dt, K);
		allunit.add(u);
		return u;
	}
	
	public double getOrderPrm() {
		double x = 0;
		double y = 0;
		double[] phj = getPhj();
		
		for(int i = 0; i < phj.length; i++) {
			x = x + Math.sin(phj[i]);
			y = y + Math.cos(phj[i]);
		}
		return Math.abs(Math.sqrt(x*x + y*y)) / phj.length;
	}
	
	public double[] getPhj() {
		double[] r = new double[allunit.size()];
		for(int i = 0; i < r.length; i++) r[i] = allunit.get(i).getPhase();
		return r;
	}
	public String getUnitData_Str_Phase() {
		String res = "";
		for(Unit u : allunit) res = res + u.getPhase() + " ";
		return res;
	}
	
	public ArrayList<Double> getUnitData_Array_Phase() {
		ArrayList<Double> array = new ArrayList<>();
		for(Unit u: allunit) array.add(u.getPhase());
		return array;
	}
	
	public void calcValue() { for(Unit u: allunit) u.calcValue(); }
	public void nextStep() { for(Unit u: allunit) u.nextStep(getPhj()); }
	public Unit getUnit(int index) { return allunit.get(index); }
	public ArrayList<Unit> getAllunit() { return allunit; }
}
