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
	
	public void greedy(ArrayList<SetObject> sets){
		
		float temp;
		float optimal = Float.POSITIVE_INFINITY;
		List<Integer> tempNotInComonElements = new ArrayList<Integer>();
		List<Integer> currentElements = new ArrayList<Integer>();
		List<Integer> universe = new ArrayList<Integer>();

		
		for(int i = 1; i <numElements; i++){
			universe.add(i);
		}
		
		System.out.println("er i grasp");
		System.out.println(sets.get(0));
		System.out.println(sets.get(0).getName());

		System.out.println(currentElements.containsAll(universe));
		int k = 0;
		while (!currentElements.containsAll(universe)) {
			for (int i = 0; i < sets.size(); i++) {
				List<Integer> c = new ArrayList<>(sets.get(i).getElements());
				c.removeAll(currentElements); // skoda hvort tetta se ofugt tetta er
										// rett

				try {
					temp = sets.get(i).getCost() / c.size();
				} catch (Exception e) {
					System.out.println("divide by zero");
					temp = 100000;
				}

				if (temp < optimal) {
					k = i;
					
					optimal = temp;
					tempNotInComonElements = c;					// heldur í while lykkjuni sem kemur að
										// ofan
					// contains all fyrir while lykkjuna
					System.out.println("TEMPNOTINCOMON");
					System.out.println(tempNotInComonElements);
					
				}

			}
			optimal = 100000;
			currentElements.addAll(tempNotInComonElements); // .eessu a ekki að vera í þessari lykju
			System.out.println(".etta er mengid " + (k+1));
			System.out.println("UNIVERSE");
			System.out.println(currentElements);
		}
	}
}
