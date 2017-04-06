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

	private int[] union; // utbi tad hugsanlega her
							// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	private ArrayList<SetObject> sets = new ArrayList<SetObject>();
	private int numSets;

	public DataObject(String Datafile, int numSets) throws IOException {
		// TODO Auto-generated constructor stub
		this.numSets = numSets;
		System.out.println("DataObject starting..");
		this.DataFile = Datafile;
		openFile(this.DataFile);
		readFile();
	}

	private void openFile(String Datafile) {
		try {
			streamReader = new StreamTokenizer(new FileReader(Datafile));
		} catch (FileNotFoundException e) {
			System.err.println("File \"" + Datafile + "\" not found");
			System.exit(0);
		}

	}

	private void readFile() throws IOException {
		// this.numCount = nextNumber();
		boolean done = false;
		int counter = 0;
		// int numSets = 500;//10000;
		int numElements = 50;// 1000;
		int setInElementSize = 0;
		// int setCounter= 0;

		List<Integer> setsInElement = new ArrayList<Integer>();

		// this one is dynamic.. that is the length of it tells the number of
		// what element we are examining
		// that is first we pick the first element(length=0), and assign element
		// 0 to all the sets that are in this row
		List<Integer> numElement = new ArrayList<Integer>();
		while (done == false) {

			this.numCount = nextNumber();
			if (numCount == -1) {

				// some printing to look at, at the end, then, ofc, delete
				System.out.println(sets.size());

				// assigning the last "subset"
				// SKOÐA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SEINASTA ELM
				// sets.get(setCounter).setSet(new
				// ArrayList<Integer>(singleSet));

				System.out.println("data is ready");
				System.out.println(counter);

				System.out.println("asdlfuahdglaudshgladsuighaldugajæsdigjasdg");
				System.out.println(sets.get(400).getElements()); // 8 to get set
																	// 9.. [0 is
																	// 1]
				System.out.println(sets.size());
				System.out.println(sets.get(400).getElements().size());

				System.out.println("asdlfuahdglaudshgladsuighaldugajæsdigjasdg");
				System.out.println(numElement.size());

				/*
				 * System.out.println("index for all costs");
				 * System.out.println(allCosts.get(0));
				 * System.out.println(allCosts.size());
				 * System.out.println(allCosts.get(1000));
				 */

				done = true;
			}

			if (counter < numSets + 1) {

				if (counter < numSets) {
					// make our sets
					String setName = "set" + counter;
					sets.add(new SetObject(setName, numCount));

				}

				if (numSets == counter) {
					// the final value of this if setnace
					// (if(counter<numColums)) is the first value
					// that tells us how many are in the next set
					System.out.println(counter);
					setInElementSize = numCount;
					numElement.add(setInElementSize);
					System.out.println("this is numcount...");
					System.out.println(setInElementSize);
				}
			} else {

				if (setInElementSize != setsInElement.size()) {
					// if the singleSet does not contain all the numbers it
					// should have in its set, lets add them to it
					setsInElement.add(numCount);

					// NYJA !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					sets.get(numCount - 1).addElements(numElement.size());
				} else {
					// þarf fyrir
					// nyja!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

					setsInElement.clear();
					setInElementSize = numCount; // find out how many elements
													// are in the next set
					numElement.add(setInElementSize);
				}

			}
			counter++; // counts every number in the text file
		}
		// posibly return the objects so we can use them in other objects..
	}

	public ArrayList<SetObject> getSets() {
		return sets;
	}

	private int nextNumber() throws IOException {
		try {
			streamReader.nextToken();
		} catch (IOException e) {
			System.err.println("Error: Failed to get next number. Is the file open?");
			System.exit(-1);
		}
		if (streamReader.ttype == StreamTokenizer.TT_EOF) {
			return -1; // this is end of file
		}

		if (streamReader.ttype != StreamTokenizer.TT_NUMBER) {
			System.err.println("Error: Found something other than a number on line " + streamReader.lineno());
			System.exit(-1);
		}

		return (int) streamReader.nval;
	}

	public List<SetObject> kBest(int k) {
		// this method selects the k best independent sets from our set.
		System.out.println("Welcome to the kBEst fucntion");
		
		// Initilize variables
		List<SetObject> bestList = new ArrayList<SetObject>();
		float worstCostSizeRatio = (float) sets.get(0).getCost() / sets.get(0).getElements().size();
		SetObject worstChosenSet = sets.get(0);
		SetObject tempWorstChosenSet = null;
		float temp_costSizeRatio = Float.POSITIVE_INFINITY;
		
		// ATH essi lykkja á að fara út!!!!! það er bara til að skoða setinn
		for (SetObject set : this.sets) {
			System.out.println("sets:" + set.getElements());
			System.out.println("length of sets:" + set.getElements().size());
		}
		
		for (int i = 0; i < numSets; i++) {
			// add to list until it is full
			temp_costSizeRatio = (float) sets.get(i).getCost() / sets.get(i).getElements().size();
			if (i < k) {
				bestList.add(sets.get(i));
				if (temp_costSizeRatio > worstCostSizeRatio) {
					// þetta er svona (>) herna vegna þess að við erum að öddum alltaf fyrstu k inn þvi þeir eru bestir..
					// og þá erum við að reyna finna versta af þeim, á meðan hérna fyrir neðan í else lykkjuni þá erum við að
					// passa að adda ekki inn eithverjum sem er ekki betri en núverandi versta set
					worstCostSizeRatio = temp_costSizeRatio;
					worstChosenSet = sets.get(i);
				}
			} else {
				System.out.println("temp_costRatio"+temp_costSizeRatio);
				System.out.println("worstCostSizeRatio"+worstCostSizeRatio);
				if (temp_costSizeRatio < worstCostSizeRatio) {
					bestList.remove(worstChosenSet);
					bestList.add(sets.get(i));
					//finding the worst value in the best list
					for (SetObject set : bestList) {
			             System.out.println("Count is: asdfasdfasdfasdf");
			             float setRatio = (float) set.getCost() / set.getElements().size();
			             float tempWorstChosenSetRatio = (float) worstChosenSet.getCost() / worstChosenSet.getElements().size();
			             if(setRatio > tempWorstChosenSetRatio  ){
			            	 tempWorstChosenSet = set;
			             }
			             worstCostSizeRatio = tempWorstChosenSetRatio;
			         }

				}
			}
		}
		System.out.println(bestList);
		for(SetObject set: bestList){
			System.out.println("one of the bests: " + set.getName());
		}
		return bestList;
	}

}
