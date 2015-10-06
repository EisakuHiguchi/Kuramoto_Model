package KuramotoUnit;

import java.util.ArrayList;

public class UnitManage {

	ArrayList<Unit> allunit;
	double K;
	double dt;
	double sd;
	
	public UnitManage(double K, double dt, double sd) {
		allunit = new ArrayList<>();
		this.K = K;
		this.dt = dt;
		this.sd = sd;
	}
	
	public Unit createUnit(double w) {
		Unit u = new Unit(w, dt, K, sd);
		allunit.add(u);
		return u;
	}
	
	public double getOrderPrm() {
		double r = 0;
		int n = allunit.size();
		double x = 0;
		double y = 0;
		double sum = 0;
		double[] phj = getPhj();
		
		for(int i = 0; i < n; i++) {
			x = x + Math.sin(phj[i]);
			y = y + Math.cos(phj[i]);
		}
		sum = Math.sqrt(x*x + y*y);
		r = Math.abs(sum) / n;
		
		return r;
	}
	
	public void calcValue() {
		for(Unit u: allunit) {
			u.calcValue();
		}
	}
	
	public void nextStep() {
		for(Unit u: allunit) {
			u.setRKPrm(getPhj());
			u.nexStep();
		}
	}
	
	public double[] getPhj() {
		double[] r = new double[allunit.size()];
		for(int i = 0; i < r.length; i++) {
			r[i] = allunit.get(i).phase;
		}
		return r;
	}
	
	public Unit getUnit(int index) { return allunit.get(index); }
	public ArrayList<Unit> getAllunit() { return allunit; }
	public String getUnitData_Str_Phase() {
		String res = "";
		
		for(Unit u : allunit) {
			res = res + u.getPhase() + " ";
		}
		
		return res;
	}
	
	public ArrayList<Double> getUnitData_Array_Phase() {
		ArrayList<Double> array = new ArrayList<>();
		for(Unit u: allunit) {
			array.add(u.getPhase());
		}
		return array;
	}
	
	
}
