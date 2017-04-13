import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class MainLNS {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int numSets = 10000;//500;// 10000;
		int numElements = 1000;//50;// 1000;

		int[] betas = {2, 4, 8, 16, 32};
		String[] probfiles = {"scpnrg1.txt", "scpnrg2.txt", "scpnrg5.txt"};
//		String[] probfiles = {"scpnrh1.txt", "scpnrh3.txt", "scpnrh5.txt"};
//		int[] betas = {5};
//		String[] probfiles = {"scpe2.txt"};
		int numTimesRun = 3;
		int runningTime = 10;
		PrintWriter printWriter = new PrintWriter(new File("tuning_test_LNS.csv"));
		for (String dataset : probfiles) {
			StringBuilder stringBuilder = new StringBuilder();
			for (int beta : betas) {
				for(int i = 0; i<numTimesRun; i++){
				DataObject data = new DataObject(("src/" + dataset), numSets);
				LNS lns = new LNS(numElements, numSets, data.getSets());
				int solution = lns.run(runningTime);
				
				stringBuilder.append("Profiles: " + ";" + dataset + ";");
				stringBuilder.append("The beta value: " + ";" + beta + ";");
				stringBuilder.append("ItrationCount" + ";" + lns.itrationCount + ";");
				stringBuilder.append("Solution value: " + ";" + solution + "\n");
				}
			}
			printWriter.write(stringBuilder.toString());
		}
		printWriter.close();
	}
}
