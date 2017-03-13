import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

public class DataObject {

	private String DataFile;
	private StreamTokenizer streamReader;
	private int numCount; // the number of numbers taken from the test file..
	
	
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
	
	private int counter = 0;
	private void readFile() throws IOException{
		this.numCount = nextNumber();
		boolean done = false;
		while(done == false){
			System.out.println(numCount);
			this.numCount = nextNumber();
			if(numCount == -1){
				done = true;
			}
			
			if(counter > 30){
				System.out.println("counter says break!!");
				break;
			}
			
			counter++;

		}
		
	}
	
	
	 private int nextNumber() throws IOException
	    {
		try{ streamReader.nextToken(); }
		catch(IOException e)
		    {
			System.err.println("Error: Failed to get next number. Is the file open?");
			System.exit(-1);
		    }
		if(streamReader.ttype == StreamTokenizer.TT_EOF)
		    {
			return -1; // held tetta se end of file
		    }
		if(streamReader.ttype != StreamTokenizer.TT_NUMBER)
		    {
			System.err.println("Error: Found something other than a number on line " + streamReader.lineno());
			System.exit(-1);
		    }
		return (int) streamReader.nval;
	    }
	

}
