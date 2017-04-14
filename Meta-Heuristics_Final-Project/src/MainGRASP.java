import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class MainGRASP {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int numSets = 10000;//10000;//500
		int numElements = 1000;//1000; //50
		
//		int[] betas = {2, 4, 8, 16, 32};
//		String[] probfiles = {"scpnrg1.txt", "scpnrg2.txt", "scpnrg5.txt"};
//		String[] probfiles = {"scpnrh1.txt", "scpnrh3.txt", "scpnrh5.txt"};
		int[] betas = {5};
		String[] probfiles = {"scpnrg1.txt", "scpnrg5.txt"};
		int numTimesRun = 1;
		int runningTime = 10;
		PrintWriter printWriter = new PrintWriter(new File("tuning_testIII.csv"));
		for(String dataset: probfiles){
			StringBuilder stringBuilder = new StringBuilder();
			for(int beta: betas){
				for(int i =0 ; i<numTimesRun; i++){
				DataObject data = new DataObject(("src/" + dataset), numSets);
				GRASP1 grasp = new GRASP1(numElements, numSets, data.getSets());
				int solution = grasp.run(runningTime, beta);
				
				
				stringBuilder.append("Profiles: " + ";" + dataset + ";");
				stringBuilder.append("The beta value: " + ";" + beta + ";");
				stringBuilder.append("ItrationCount" + ";" + grasp.itrationCount + ";");
				stringBuilder.append("Solution value: " + ";" + solution + "\n");
				}
			}
			printWriter.write(stringBuilder.toString());
		}
		printWriter.close();
		System.out.println("done!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

	}

}
