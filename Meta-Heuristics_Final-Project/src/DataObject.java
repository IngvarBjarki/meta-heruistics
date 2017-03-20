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
	
	private int[] union;
	private ArrayList<SetObject> sets = new ArrayList<SetObject>();
	
	
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
		int setCounter= 0; 
		List<Integer> singleSet = new ArrayList<Integer>();
		int temp = 0;
		while(done == false){
			
			this.numCount = nextNumber();
			if(numCount == -1){
				// assigning the last "subset"
				sets.get(setCounter).setSet(new ArrayList<Integer>(singleSet)); 
				System.out.println(sets.get(0).getSet());
				System.out.println(sets.get(999).getCost());
				System.out.println(sets.get(999).getSet());
				System.out.println(sets.size());
				System.out.println(counter);
				done = true;
			}

			if(counter < numSets){
				// we were selecting one to many items, we add -1 since we start at 0
				String setName = "set" + counter;
				sets.add(new SetObject(setName, numCount));

				// so we get the right value (how long our first subset is) from the text file
				// this overwrites so we end upp with the length of the first subset
				setSize = numCount;
			}
			else{
				
				if(setSize != singleSet.size()){
					//if the singleSet does not contain all the numbers it should have in its set, lets add them to it
					singleSet.add(numCount);
				}
				else{
					// lets make the set objects have the "subsets" and clear the current set so we can add to the next one
					sets.get(setCounter).setSet(new ArrayList<Integer>(singleSet));
					singleSet.clear();
					
					// find out how many elements are in the next set
					setSize = numCount;
					setCounter++; // for selecting the next obj in the sets list
				}
						
			}
			counter ++; // counts every number in the text file
		}
		// posibly return the objects so we can use them in other objects..
	}
	
	
	 public ArrayList<SetObject> getSets() {
		return sets;
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
