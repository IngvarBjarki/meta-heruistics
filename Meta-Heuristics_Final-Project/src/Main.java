import java.io.IOException;
import java.util.List;

public class Main {
	//p�ling hvort �g eigi a� gera object fyrir setinn svo �g geti store-a� kostna�inn og
	// seti� � sama sta�num, haft svo bara array af objectum...
	
	
	public static void main(String[] args) throws IOException{
		System.out.println("The final prject!");
//		DataObject data = new DataObject("src/scpnrg1.txt");
		DataObject data = new DataObject("src/scpe2.txt", 500);// data file and numSets
		GRASP grasp = new GRASP(50,500); // args are numElements and numSbbsets
		grasp.greedy(data.getSets());
		List<SetObject> bestSets = data.kBest(5);
		System.out.println(bestSets);
		
	}

}
