package KuramotoUnit;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class _TestModule {

	public static void main(String[] args) {
		
		// parameter
		int num = 10;
		int loop = 1000;
		
		double K = 0.5;
		double dt = 0.025;
		double sd = 0.8;
		
		UnitManage um = new UnitManage(K, dt);
		for(int i = 0; i < num; i++) {
			um.createUnit(naturalWave(sd));
		}
		
		try {
			
			FileWriter fw0 = new FileWriter("output0.txt");
			FileWriter fw1 = new FileWriter("output1.txt");
			FileWriter fw2 = new FileWriter("order.txt");
			FileWriter fw3 = new FileWriter("output2.txt");
			FileWriter fw4 = new FileWriter("output3.txt");
			
			for(int i = 0; i < loop; i++) {
				um.nextStep();
				um.calcValue();
				fw2.write(um.getOrderPrm() + "\n");
				fw0.write(um.getUnitData_Str_Phase() + "\n");
				fw1.write(getWriteData(um.getUnitData_Array_Phase()) + "\n");
				fw3.write(getWriteData_Unit(um.getAllunit())+ "\n");
				fw4.write(getWriteData_naturalWave(um.getAllunit())+ "\n");
			}
			
			fw0.close();
			fw1.close();
			fw2.close();
			fw3.close();
			fw4.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		scriptFile(num, 0);
		scriptFile(num, 1);
		scriptFile(num, 2);
		scriptFile(num, 3);
		
		System.out.println("fin");
	}
	
	public static void scriptFile(int num, int opt) {
		try {
			FileWriter fw = new FileWriter("script" + opt + ".txt");
			
			fw.write("plot "
					+ "\"C:\\\\Users\\\\Iris\\\\Dropbox\\\\KuramotoModel\\\\KuramotoModel02\\"
					+ "\\output" + opt + ".txt\""
					+ " u " + 1 + " w l \n");
			
			for(int i = 2; i < (num + 1); i++) {
				fw.write("replot "
						+ "\"C:\\\\Users\\\\Iris\\\\Dropbox\\\\KuramotoModel\\\\KuramotoModel02\\"
						+ "\\output" + opt + ".txt\""
						+ " u " + i + " w l \n");
			}
			
			fw.close();			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	private static String getWriteData_naturalWave(ArrayList<Unit> array) {
		String r = "";
		for(Unit e: array) {
			r = r + Math.sin(e.getWavePrm()) + " ";
		}
		return r;
	}
	
	private static String getWriteData_Unit(ArrayList<Unit> array) {
		String r = "";
		for(Unit e: array) {
			r = r + Math.sin(e.getWavePrm() + e.getPhase()) + " ";
		}
		return r;
	}
	
	private static String getWriteData(ArrayList<Double> array) {
		String r = "";
		for(Double e: array) {
			r = r + Math.sin(e) + " ";
		}
		return r;
	}
	
	private static double naturalWave(double sd) {
		Random r = new Random();
		double res = r.nextDouble();
		while(!((res > (-2*Math.sqrt(3) * sd) )&& (res < 2*Math.sqrt(3) * sd))) {
			res = r.nextDouble();
		}
		return res;
	}
	
}
