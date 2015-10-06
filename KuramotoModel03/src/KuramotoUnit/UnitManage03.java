package KuramotoUnit;

public class UnitManage03 extends UnitManage {

	public UnitManage03(double K, double dt) {
		super(K, dt);
	}
	
	@Override
	public Unit createUnit(double w) {
		CommonUnit u = new CommonUnit(w, dt, K);
		allunit.add(u);
		return u;
	}
	
	public Unit createOriginalUnit(double w) {
		Unit u = new Unit(w, dt, K);
		allunit.add(u);
		return u;
	}
	
	
	public void setUnit(Unit u, int index) {
		allunit.set(index, u);
	}

}
