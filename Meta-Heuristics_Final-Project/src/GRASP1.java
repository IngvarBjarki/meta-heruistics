import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class GRASP1 {

	private int numElements;
	private int numSets;
	private List<SetObject> allSets;
	public int itrationCount = 0;
	
	public GRASP1(int numElements, int numSets, List<SetObject> allSets) {
		// TODO Auto-generated constructor stub
		this.numElements = numElements;
		this.numSets = numSets;
		this.allSets = allSets;
	}

	public int run(long runlength, int RCLsize) {
		// initialization 
		List<SetObject> allSets = deepCopy(this.allSets); 
		ArrayList<SetObject> RCL = new ArrayList<SetObject>();
		ArrayList<Integer> currentElements = new ArrayList<Integer>();
		ArrayList<SetObject> currentElementObj = new ArrayList<SetObject>();
		ArrayList<Integer> universe = makeUniverse();
		int solution = 999999; // due to minimization
		
		Date da = new Date();
		boolean done = false;
		long start_time = da.getTime();
		long max_sec = start_time + 1000 * runlength;
		da = new Date();

		System.out.println("Starting GRASP");
		// GRASP STARTS
		while(da.getTime() < max_sec){
			da = new Date();
		// greedy randomized
		List<SetObject> allSetsCopy = deepCopy(allSets);
		while (!currentElements.containsAll(universe)) {
			Random rand = new Random();
			RCL = makeRCL(RCLsize, allSetsCopy, currentElements);
			int random = rand.nextInt(RCL.size());
			SetObject choosenSet = RCL.get(random);
			currentElements.addAll(choosenSet.getElements());
			currentElementObj.add(choosenSet);
			RCL.clear();
			allSetsCopy.remove(choosenSet);
			
//			System.out.println("CurrentElements" + currentElements);
		}
		currentElements.clear();
		
		System.out.println("total cost for randomized greedy is " + calculateSolution(currentElementObj));
		for(SetObject set: currentElementObj){
			System.out.println(set.getName() + " is chosen");
		}
		
		// local search
		HillClimber hill = new HillClimber(currentElementObj, allSets, numElements);
		int tempSolution = hill.calculateSolution(45);
		
//		HillClimberWithDelta hill = new HillClimberWithDelta(currentElementObj, allSets, numElements);
//		int tempSolution = hill.calculateSolution(45);
		
		if(tempSolution<solution){
			solution = tempSolution;
		}
		this.itrationCount++;
		System.out.println("DONE!");
		}
		return solution;
	}

	private ArrayList<Integer> makeUniverse() {
		ArrayList<Integer> universe = new ArrayList<Integer>();
		for (int i = 1; i < numElements + 1; i++) {
			universe.add(i);
		}
		return universe;
	}

	private ArrayList<SetObject> makeRCL(int size, List<SetObject> allSets, ArrayList<Integer> currentElements) {
		
		ArrayList<SetObject> RCL = new ArrayList<SetObject>();
		List<SetObject> allSetsCopy = deepCopy(allSets);
		HashSet<Integer> currentElementsCopy = deepCopyIntegerH(currentElements);
		
		float tempBest = Float.POSITIVE_INFINITY; // due to minimization
		SetObject tempBestObj = null;
		for (int i = 0; i < size; i++) {
			for (SetObject set : allSetsCopy) {
				List<Integer> uniqueElements = new ArrayList<Integer>(set.getElements());
				uniqueElements.removeAll(currentElementsCopy);
				float currentSetRatio = (float) set.getCost() / uniqueElements.size(); // float results in infinity if divided by zero
				if (currentSetRatio < tempBest) {
					tempBest = currentSetRatio;
					tempBestObj = set;
				}
			}
			RCL.add(tempBestObj);
			allSetsCopy.remove(tempBestObj);
			tempBest = Float.POSITIVE_INFINITY; // due to minimization
		}
		
		return RCL;
	}

	public int calculateSolution(List<SetObject> solution) {
		int totalCost = 0;

		for (SetObject set : solution) {
			totalCost = totalCost + set.getCost();
		}
		return totalCost;
	}
	
	private List<Integer> deepCopyInteger(List<Integer> list){
		
		List<Integer> tempList = new ArrayList<Integer>();
		for(Integer value: list){
			tempList.add(value);
		}
		
		return tempList;
	}
	
private HashSet<Integer> deepCopyIntegerH(List<Integer> list){
		
		HashSet<Integer> tempList = new HashSet<Integer>();
		for(Integer value: list){
			tempList.add(value);
		}
		
		return tempList;
	}

	private List<SetObject> deepCopy(List<SetObject> currentSolution) {

		List<SetObject> tempSolution = new ArrayList<SetObject>();
		int index = 0;
		for (SetObject set : currentSolution) {
			tempSolution.add(new SetObject(set.getName(), set.getCost()));
			tempSolution.get(index).setElements(set.getElements());
			index++;
		}

		return tempSolution;
	}

}