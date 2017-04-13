import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class MainLNS {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int numSets = 500;// 10000;
		int numElements = 50;// 1000;

		int[] betas = { 2, 4, 8, 16, 32 };
		String[] probfiles = { "scpe2.txt" };
		PrintWriter printWriter = new PrintWriter(new File("tuning_test_LNS.csv"));
		for (String dataset : probfiles) {
			StringBuilder stringBuilder = new StringBuilder();
			for (int beta : betas) {

				DataObject data = new DataObject(("src/" + dataset), numSets);
				LNS lns = new LNS(numElements, numSets, data.getSets());
				int solution = lns.run(50);
				
				stringBuilder.append("Profiles: " + ";" + dataset + ";");
				stringBuilder.append("The beta value: " + ";" + beta + ";");
				stringBuilder.append("ItrationCount" + ";" + lns.itrationCount + ";");
				stringBuilder.append("Solution value: " + ";" + solution + "\n");

			}
			printWriter.write(stringBuilder.toString());
		}
		printWriter.close();
	}
}
