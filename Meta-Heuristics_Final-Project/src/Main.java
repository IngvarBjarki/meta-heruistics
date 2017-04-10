import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
	//pæling hvort ég eigi að gera object fyrir setinn svo ég geti store-að kostnaðinn og
	// setið á sama staðnum, haft svo bara array af objectum...
	
	
	public static void main(String[] args) throws IOException{
		int numSets = 10000;
		int numElements = 1000;
		DataObject data = new DataObject("src/scpnrg5.txt", numSets);
		//DataObject data = new DataObject("src/scpe2.txt", numSets);// data file and numSets
		GRASP grasp = new GRASP(numElements,numSets); // args are numElements and numSbbsets
		//int Beta = 0;
		// Lets make the RCL List
		List<SetObject> bestSets = data.kBest(5);
		
		
		List<List<SetObject>> RCL = new ArrayList<List<SetObject>>();
		
		
		for(SetObject set: bestSets ){
			
			RCL.add(grasp.greedy(data.getSets(),set));
		}
		
		Random rand = new Random();
		
		System.out.println(rand.nextInt(RCL.size()));
		System.out.println(rand.nextInt(RCL.size()));
		System.out.println(rand.nextInt(RCL.size()));
		System.out.println(rand.nextInt(RCL.size()));
		System.out.println(rand.nextInt(RCL.size()));
		System.out.println(rand.nextInt(RCL.size()));
		

		//data.getSets()--> are all the sets
		
//		HillClimber hill = new HillClimber(RCL.get(3),data.getSets(), numElements);
//		hill.calculateSolution(300);


		LNS har= new LNS(1000,10000,data.getSets(),bestSets.get(0));
		har.run(300);
		
//		System.out.println("RCL");
//		System.out.println(RCL);
//		System.out.println(RCL.size());
//		System.out.println(RCL.get(0));
//		System.out.println(RCL.get(1));
//		System.out.println(RCL.get(4));
//		System.out.println(data.getSets().size());
		
		// construct local optimum
		
	}

}
