package KuramotoUnit;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class TestModule {

	public static void main(String[] args) {
		
		// parameter
		int num = 10;
		int randprm = 30;
		int loop = 1000;
		
		double K = 0.4;
		double dt = 0.025;
		double sd = 0.8;
		
		UnitManage um = new UnitManage(K, dt, sd);
		Random r = new Random();
		for(int i = 0; i < num; i++) {
			um.createUnit(r.nextInt(randprm));
		}
		
		try {
			
			FileWriter fw0 = new FileWriter("output0.txt");
			FileWriter fw1 = new FileWriter("output1.txt");
			
			for(int i = 0; i < loop; i++) {
				um.nextStep();
				um.calcValue();
				
//				if(um.getUnit(0).getPhase() < 1) {
//					System.out.println(um.getUnit(0).getPhase() + " " + um.getUnit(0).t);
//				} else if(um.getUnit(0).getPhase() > (2 * Math.PI) - 1) {
//					System.out.println(um.getUnit(0).getPhase() + " " + um.getUnit(0).t);
//				}

				System.out.println(um.getOrderPrm());
				
				fw0.write(um.getUnitData_Str_Phase() + "\n");
				fw1.write(getWriteData(um.getUnitData_Array_Phase()) + "\n");
			}
			
			fw0.close();
			fw1.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		scriptFile(num, 0);
		scriptFile(num, 1);
		
		System.out.println("fin");
	}
	
	public static void scriptFile(int num, int opt) {
		try {
			FileWriter fw = new FileWriter("script" + opt + ".txt");
			
			fw.write("plot "
					+ "\"C:\\\\Users\\\\Iris\\\\Dropbox\\\\KuramotoModel\\\\KuramotoModel01\\"
					+ "\\output" + opt + ".txt\""
					+ " u " + 1 + " w l \n");
			
			for(int i = 2; i < (num + 1); i++) {
				fw.write("replot "
						+ "\"C:\\\\Users\\\\Iris\\\\Dropbox\\\\KuramotoModel\\\\KuramotoModel01\\"
						+ "\\output" + opt + ".txt\""
						+ " u " + i + " w l \n");
			}
			
			fw.close();			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	private static String getWriteData(ArrayList<Double> array) {
		String r = "";
		for(Double e: array) {
			r = r + Math.sin(e) + " ";
		}
		return r;
	}
	
}
