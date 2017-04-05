import java.io.IOException;

public class Main {
	//pæling hvort ég eigi að gera object fyrir setinn svo ég geti store-að kostnaðinn og
	// setið á sama staðnum, haft svo bara array af objectum...
	
	
	public static void main(String[] args) throws IOException{
		System.out.println("The final prject!");
//		DataObject data = new DataObject("src/scpnrg1.txt");
		DataObject data = new DataObject("src/scpe2.txt");
		GRASP grasp = new GRASP(50,500); // args are numElements and numSbbsets
		grasp.greedy(data.getSets());
		System.out.println(data.getSets().size());
		System.out.println("this is data");
		System.out.println(data);
		System.out.println("done");
		
	}

}
