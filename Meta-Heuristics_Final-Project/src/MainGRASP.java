import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class MainGRASP {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int numSets = 10000;//500
		int numElements = 1000; //50
		
		//int[] betas = {2, 4, 8, 16, 32};
		int[] betas = {2};
		String[] probfiles = {"scpnrg2.txt"};
		PrintWriter printWriter = new PrintWriter(new File("tuning_testII.csv"));
		for(String dataset: probfiles){
			StringBuilder stringBuilder = new StringBuilder();
			for(int beta: betas){
				int RCLsize = 3;
				DataObject data = new DataObject(("src/" + dataset), numSets);
				GRASP1 grasp = new GRASP1(numElements, numSets, data.getSets());
				int solution = grasp.run(10, RCLsize);
				
				
				stringBuilder.append("Profiles: " + ";" + dataset + ";");
				stringBuilder.append("The beta value: " + ";" + beta + ";");
				stringBuilder.append("ItrationCount" + ";" + grasp.itrationCount + ";");
				stringBuilder.append("Solution value: " + ";" + solution + "\n");
				
			}
			printWriter.write(stringBuilder.toString());
		}
		printWriter.close();
		System.out.println("done");

	}

}
