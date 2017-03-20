import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;

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
		int numSets = 10;
		ArrayList<SetObject> sets = new ArrayList<SetObject>(); 
		while(done == false){
			
			this.numCount = nextNumber();
			if(numCount == -1){
				done = true;
			}
			if(numCount == -2){
				//System.out.println("new line");
				numLine++;
			}

			if(counter < numSets){
				String setName = "set" + counter;
				sets.add(new SetObject(setName, numCount));
			}
			if(counter == 11){
				System.out.println(sets);
				System.out.println(sets.get(1).getName());
			}
			
			counter ++;
			
		}
		
	}
	
	
	 private int nextNumber() throws IOException
	    {
		try{ streamReader.nextToken(); 
		streamReader.eolIsSignificant(true);
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
		if(streamReader.ttype  == StreamTokenizer.TT_EOL)
	    {
		//System.out.println("starting line: " + streamReader.lineno());
		return -2; // this is end of line
	    }
		
		if(streamReader.ttype != StreamTokenizer.TT_NUMBER)
		    {
			System.err.println("Error: Found something other than a number on line " + streamReader.lineno());
			System.exit(-1);
		    }
	
		
		return (int) streamReader.nval;
	    }
	

}
