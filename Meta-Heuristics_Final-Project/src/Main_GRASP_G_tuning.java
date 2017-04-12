import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main_GRASP_G_tuning {
	public static void main(String[] args) throws IOException {

		int numSets = 10000;
		int numElements = 1000;
		String[] probfiles = { "src/scpnrg1.txt" , "src/scpnrg2.txt",
													 "src/scpnrg3.txt"};
													// "src/scpnrg4.txt",
		// "src/scpnrg5.txt" };

		int[] Beta = {2, 8 };
		int k = 0;
		PrintWriter print_writer = new PrintWriter(new File("Tuning_Grasp_G1c.csv"));
	for (String dataset : probfiles) {
			StringBuilder string_builder = new StringBuilder();
			System.out.println("Parameter tuning on data: " + dataset);
			for (int beta : Beta) {
				System.out.println("  The beta value(RCL list): " + beta);
				for (int l = 0; l < 3; l++) {

				DataObject data = new DataObject(dataset, numSets);
				GRASP grasp = new GRASP(numElements, numSets);
				List<SetObject> bestSets = data.kBest(beta);
				List<List<SetObject>> RCL = new ArrayList<List<SetObject>>();
				System.out.println(k);
				k++;
				for (SetObject set : bestSets) {

					RCL.add(grasp.greedy(data.getSets(), set));

				}

				Random rand = new Random();
				int random = rand.nextInt(RCL.size());
				System.out.println("here is random !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + random);
				HillClimber hill = new HillClimber(RCL.get(random), data.getSets(), numElements);
				int solution = hill.calculateSolution(180);
				System.out.println(solution + " the solution");
				
				
				
				

				// Building the string builder
				// Printing the values from the for loop

				// System.out.println("Parameter tuning on data: " + dataset);
				// System.out.println(" The beta value(RCL list): " +beta);
//
				string_builder.append("Profiles: " + ";" + dataset);
				string_builder.append(';');
				string_builder.append("The beta value: " + ";" + beta);
				string_builder.append(';');
				string_builder.append("Solution Value: " + ";" + solution + "\n");
				System.out.println("done");
				System.out.println(k);
				
				RCL.clear();

			}
			print_writer.write(string_builder.toString());
			
		}
	print_writer.close();
		System.out.println(k);
		System.out.println("done!!!!!");
	}

}
}

// }

//
// // data.getSets()--> are all the sets
//
// PrintWriter print_writer = new PrintWriter(new File("Tuning_Grasp_G.csv"));
// StringBuilder string_builder = new StringBuilder();
//
// // LNS har= new LNS(50,500,data.getSets(),bestSets.get(0));
//
// for (int i = 0; i < probfiles.length; i++) {
// System.out.println("Parameter tuning on data: " + probfiles[i]);
// for (int j = 0; j < Beta.length; j++) {
// System.out.println(" The beta value(RCL list): " + Beta[j]);
// int numSets = 10000;
// int numElements = 1000;
// // DataObject data = new DataObject("src/scpnrg5.txt", numSets);
//
//
// // Lets make the RCL List
// List<SetObject> bestSets = data.kBest(Beta[i]);
//
// string_builder.append("Profiles: " + ";" + probfiles[i]);
// string_builder.append(';');
// string_builder.append("The beta value: " + ";" + Beta[j]);
// string_builder.append(';');
// // string_builder.append("Time: " + ";" + running_time[l]);
// // string_builder.append(';');
// // string_builder.append("Iterations: " + ";" +
// // sa.IterationCount);
// // string_builder.append(';');
// string_builder.append("Solution Value: " + ";" + solution + "\n");
// print_writer.write(string_builder.toString());
// System.out.println("done");
// }
//
// ;
//
// print_writer.close();
// System.out.println("done!!!!!");
//
// }
// }
// }