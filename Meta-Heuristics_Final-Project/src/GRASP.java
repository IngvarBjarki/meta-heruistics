import java.util.ArrayList;
import java.util.List;

public class GRASP {
	private int numElements;
	private int numSets;
	public GRASP(int numElements, int numSets) {
		// TODO Auto-generated constructor stub
		this.numElements = numElements;
		this.numSets = numSets;
	}
	
	public List<SetObject> greedy(ArrayList<SetObject> sets, SetObject startSet){
		
		float temp;
		float optimal = Float.POSITIVE_INFINITY;
		List<Integer> tempNotInComonElements = new ArrayList<Integer>();
		List<Integer> currentElements = new ArrayList<Integer>();
		List<SetObject> currentObjects = new ArrayList<SetObject>();
		List<Integer> universe = new ArrayList<Integer>();
		List<String> nameOfCurrentElements = new ArrayList<String>();
		String tempElement = null;
		SetObject tempObj = null;
		
		currentElements.addAll(startSet.getElements());
		nameOfCurrentElements.add(startSet.getName());
		currentObjects.add(startSet);
		for(int i = 1; i <numElements +1; i++){
			universe.add(i);
		}
		
		while (!currentElements.containsAll(universe)) {
			for (int i = 0; i < sets.size(); i++) {
				List<Integer> c = new ArrayList<>(sets.get(i).getElements());
				c.removeAll(currentElements); // skoda hvort tetta se ofugt tetta er
										// rett

				try {
					temp = (float)sets.get(i).getCost() / c.size();
				} catch (Exception e) {
					System.out.println("divide by zero");
					temp = Float.POSITIVE_INFINITY;
				}

				if (temp < optimal) {
					optimal = temp;
					tempNotInComonElements = c;
					tempElement = sets.get(i).getName();
					tempObj = sets.get(i);
					// heldur í while lykkjuni sem kemur að
										// ofan
					// contains all fyrir while lykkjuna
//					
				}

			}
			currentElements.addAll(tempNotInComonElements); 
			nameOfCurrentElements.add(tempElement);
			currentObjects.add(tempObj);
			optimal = Float.POSITIVE_INFINITY;
		}
		
		for(SetObject set : currentObjects){
			System.out.println("in the greedy: " +set.getName());
		}
		
		return currentObjects;
	}
}
