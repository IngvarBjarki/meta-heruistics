import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class MainGRASP {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int numSets = 500;// 10000;
		int numElements = 50;//1000;
		
		int[] betas = {2, 4, 8, 16, 32};
		String[] probfiles = {"scpe2.txt"};
		PrintWriter printWriter = new PrintWriter(new File("tuning_test.csv"));
		for(String dataset: probfiles){
			StringBuilder stringBuilder = new StringBuilder();
			for(int beta: betas){
				
				DataObject data = new DataObject(("src/" + dataset), numSets);
				GRASP1 grasp = new GRASP1(numElements, numSets, data.getSets());
				int solution = grasp.run(100);
				
				
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
