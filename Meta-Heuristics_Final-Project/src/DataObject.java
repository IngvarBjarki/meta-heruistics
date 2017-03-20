import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

public class DataObject {

	// held eg geri arry af objects ef eg þarf baedi kostand og set.
	
	private String DataFile;
	private StreamTokenizer streamReader;
	private int numCount; // the number of numbers taken from the test file..
	private SetObject[] sets;
	private int[] union;
	
	public DataObject(String Datafile) throws IOException
	{
		// TODO Auto-generated constructor stub
		System.out.println("DataObject starting..");
		this.DataFile = Datafile;
		openFile(this.DataFile);
		readFile();
	}
	
	
	private void openFile(String Datafile){
		 try{ streamReader = new StreamTokenizer(new FileReader(Datafile)); }
	        catch(FileNotFoundException e)
		    {
			System.err.println("File \"" + Datafile + "\" not found");
			System.exit(0);
		    }
		
	}
	
	private void readFile() throws IOException{
		this.numCount = nextNumber();
		boolean done = false;
		int numLine = 0;
		int counter = 0;
		int numSets = 10000;
		int setSize = 0;
		int setCounter= -1; // so we start at 0 when adding to the list
		boolean newSet = false;
		ArrayList<SetObject> sets = new ArrayList<SetObject>(); 
		List<Integer> singleSet = new ArrayList<Integer>();
		while(done == false){
			
			this.numCount = nextNumber();
			if(numCount == -1){
				System.out.println(sets.size());
				System.out.println(sets.get(3).getSet());
				System.out.println(sets.get(3).getName());
				System.out.println(setCounter);
				done = true;
			}

			if(counter < numSets){
				String setName = "set" + counter;
				sets.add(new SetObject(setName, numCount));
				newSet = true;
			}
			else{
				if(newSet){
					
					setSize = numCount;
					newSet = false;
					setCounter++;
				}
				
				if(setSize != singleSet.size()){
					singleSet.add(numCount);
					
				}
				else{
					
					sets.get(setCounter).setSet(new ArrayList<Integer>(singleSet));
					singleSet.clear();
					newSet = true;
				}
						
			}
			counter ++;
		}
		
	}
	
	
	 private int nextNumber() throws IOException
	    {
		try{ streamReader.nextToken(); 
		}
		catch(IOException e)
		    {
			System.err.println("Error: Failed to get next number. Is the file open?");
			System.exit(-1);
		    }
		if(streamReader.ttype == StreamTokenizer.TT_EOF)
		    {
			return -1; // this is end of file
		    }
		
		if(streamReader.ttype != StreamTokenizer.TT_NUMBER)
		    {
			System.err.println("Error: Found something other than a number on line " + streamReader.lineno());
			System.exit(-1);
		    }
	
		
		return (int) streamReader.nval;
	    }
	

}
