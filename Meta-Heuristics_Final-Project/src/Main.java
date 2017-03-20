import java.io.IOException;

public class Main {
	//pæling hvort ég eigi að gera object fyrir setinn svo ég geti store-að kostnaðinn og
	// setið á sama staðnum, haft svo bara array af objectum...
	
	
	public static void main(String[] args) throws IOException{
		System.out.println("The final prject!");
		DataObject data = new DataObject("src/scpnrg1.txt");
		System.out.println(data.getSets().size());
		System.out.println("done");
		
	}

}
