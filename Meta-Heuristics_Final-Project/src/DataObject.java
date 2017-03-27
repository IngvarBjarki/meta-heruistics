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
	
	private int[] union; // utbi tad hugsanlega her !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
		//this.numCount = nextNumber();
		boolean done = false;
		int counter = 0;
		int numColums = 10000;
		int numSets = 1000;
		int setSize = 0;
		int setCounter= 0;

		List<Integer> singleSet = new ArrayList<Integer>();
		List<Integer> allCosts = new ArrayList<Integer>();
		List<Integer> costSet = new ArrayList<Integer>();
		while(done == false){
			
			this.numCount = nextNumber();
			if(numCount == -1){
				// assigning the last "subset"
				sets.get(setCounter).setSet(new ArrayList<Integer>(singleSet)); 
				System.out.println("data is ready");
				System.out.println(counter);
				System.out.println("AllCOSTS!!");
				System.out.println(allCosts);
				
				System.out.println("Her er totalcostid a fyrsta data setinu, og sidan gildin sem eru logd saman");
				System.out.println(sets.get(0).getTotalCost());
				System.out.println(sets.get(0).getCosts());
				
				/*System.out.println("index for all costs");
				System.out.println(allCosts.get(0));
				System.out.println(allCosts.size());
				System.out.println(allCosts.get(1000));*/
				done = true;
			}

			if(counter < numColums+1){
				
				if(counter <numSets){
					//make our sets
					String setName = "set" + counter;
					sets.add(new SetObject(setName, numCount));
					
				}

				if(numColums == counter){
					// the final value of this if setnace (if(counter<numColums)) is the first value
					// that tells us how many are in the next set
					System.out.println(counter);
					setSize = numCount;
				}
				else{
					// this one is here since then we wont add the last element to it, that is
					// the element from the dataset that denotes how long next subset is
					allCosts.add(numCount);
				}
			}
			else{
				
				if(setSize != singleSet.size()){
					//if the singleSet does not contain all the numbers it should have in its set, lets add them to it
					singleSet.add(numCount);
					costSet.add(allCosts.get(numCount-1)); // minus one, since numcount is [1 10000] and allCosts is [0 9999]
				}
				else{
					// lets make the set objects have the "subsets" and clear the current set so we can add to the next one
					sets.get(setCounter).setSet(new ArrayList<Integer>(singleSet));
					sets.get(setCounter).setCosts(new ArrayList<Integer>(costSet));

					singleSet.clear();
					costSet.clear();
					
					setSize = numCount; // find out how many elements are in the next set
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
