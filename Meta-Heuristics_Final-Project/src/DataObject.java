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
		boolean firstSet = false;
		ArrayList<SetObject> sets = new ArrayList<SetObject>(); 
		List<Integer> singleSet = new ArrayList<Integer>();
		while(done == false){
			
			this.numCount = nextNumber();
			if(numCount == -1){
				// some printing to look at, at the end,  then, ofc, delate
				System.out.println(sets.size());
				System.out.println(sets.get(0).getSet());
				System.out.println(sets.get(1).getSet());
				System.out.println(sets.get(2).getSet());
				System.out.println(sets.get(numSets-2).getName());
				System.out.println(sets.get(numSets-2).getCost());
				System.out.println(setCounter);
				done = true;
			}

			if(counter < numSets-1){
				// we were selecting one to many items, we add -1 since we start at 0
				String setName = "set" + counter;
				sets.add(new SetObject(setName, numCount));
				firstSet = true;
			}
			else{
				if(firstSet){
					// lets find all the elements for the first set and 
					// and go to the begining of the loop to start adding to it
					setSize = numCount;
					firstSet = false;
					setCounter++;
					continue;
					
				}
				
				if(setSize != singleSet.size()){
					//while the does not have all the numbers it should have, lets add to it
					singleSet.add(numCount);
				}
				else{
					// lets make the set objects have the "subsets" and clear the current set so we can add to the next one
					sets.get(setCounter).setSet(new ArrayList<Integer>(singleSet));
					singleSet.clear();
					// find out how many elements are in the next set
					setSize = numCount;
					setCounter++;
				}
						
			}
			counter ++;
		}
		// posibly return the objects so we can use them in other objects..
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
