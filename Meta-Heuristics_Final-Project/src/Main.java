import java.io.IOException;

public class Main {
	//p�ling hvort �g eigi a� gera object fyrir setinn svo �g geti store-a� kostna�inn og
	// seti� � sama sta�num, haft svo bara array af objectum...
	
	
	public static void main(String[] args) throws IOException{
		System.out.println("The final prject!");
		DataObject data = new DataObject("src/scpnrg1.txt");
		System.out.println(data.getSets().size());
		System.out.println("done");
		
	}

}
