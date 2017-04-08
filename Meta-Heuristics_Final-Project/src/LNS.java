import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LNS {
	private int numElements;
	private int numSets;
	private  ArrayList <SetObject> sets; 
	private  SetObject startSet;

	public LNS(int numElements, int numSets,ArrayList<SetObject> sets,SetObject startSet) {
		// TODO Auto-generated constructor stub
		this.numElements = numElements;
		this.numSets = numSets;
		this.sets=sets;
		this.startSet=startSet;
		greedy(sets,startSet);
	}

	public void greedy(ArrayList<SetObject> sets, SetObject startSet) {
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
		for (int i = 1; i < numElements; i++) {
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
				c.removeAll(currentElements); // skoda hvort tetta se ofugt
												// tetta er
				// rett

				try {
					temp = (float) sets.get(i).getCost() / c.size();
				} catch (Exception e) {
					System.out.println("divide by zero");
					temp = Float.POSITIVE_INFINITY;
				}

				if (temp < optimal) {
					k = i;

					optimal = temp;
					tempNotInComonElements = c;
					tempElement = sets.get(i).getName();
					tempObj = sets.get(i);
					// heldur í while lykkjuni sem kemur að
					// ofan
					// contains all fyrir while lykkjuna
					System.out.println("TEMPNOTINCOMON");
					System.out.println(tempNotInComonElements);

				}

			}
			optimal = 100000;
			currentElements.addAll(tempNotInComonElements); // .eessu a ekki að
															// vera í þessari
															// lykju
			nameOfCurrentElements.add(tempElement);
			currentObjects.add(tempObj);
			System.out.println(".etta er mengid " + (k + 1));
			System.out.println("UNIVERSE");
			System.out.println(currentElements);
			System.out.println("SETS");
			System.out.println(nameOfCurrentElements);
		}
		//return currentObjects;
		Destroy(currentObjects);
	}

	public  void Destroy(List<SetObject>Solution){
			int CoversRemoved = 5; // This is how many covers at random we will remove at random from the initial solution(often denoted as p)
			Random rand = new Random();
			for(int i = 1; i <CoversRemoved; i++){
				Solution.remove(Solution.get(rand.nextInt(Solution.size())));
			
			}
			Repair(this.sets,Solution);
		}

	

	private List<Integer> GetIntegerSolution(List<SetObject> FromDestroy){
			List<Integer> CurrentElements=new ArrayList<Integer>();
			for (SetObject Set:FromDestroy){
				CurrentElements.addAll(Set.getElements());
				
			}
			return CurrentElements;
			

	}

	public void Repair(List<SetObject> sets, List<SetObject> FromDestroy) {

	

	float temp;
	float optimal = Float.POSITIVE_INFINITY;
	List<Integer> tempNotInComonElements = new ArrayList<Integer>();
	List<Integer> currentElements = new ArrayList<Integer>();
	List<SetObject> currentObjects = new ArrayList<SetObject>();
	List<Integer> universe = new ArrayList<Integer>();
	List<String> nameOfCurrentElements = new ArrayList<String>();
	String tempElement = null;
	SetObject tempObj = null;
	
	currentElements=GetIntegerSolution(FromDestroy);
	//.addAll(startSet.getElements());
	//nameOfCurrentElements.add(startSet.getName());
	currentObjects.addAll(FromDestroy);
	
	for(int i = 1;i<numElements;i++)
	{
		universe.add(i);
	}
	
	System.out.println("er i LNS");System.out.println(sets.get(0));System.out.println(sets.get(0).getName());

	System.out.println(currentElements.containsAll(universe));
	int k = 0;
	while(!currentElements.containsAll(universe))
	{
				for (int i = 0; i <sets.size(); i++) {
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
						k = i;
						
						optimal = temp;
						tempNotInComonElements = c;
						tempElement = sets.get(i).getName();
						tempObj = sets.get(i);
						currentObjects.add(sets.get(i));
						// heldur í while lykkjuni sem kemur að
											// ofan
						// contains all fyrir while lykkjuna
						System.out.println("TEMPNOTINCOMON");
						System.out.println(tempNotInComonElements);
					
						
					}
				}
		}
	for (SetObject set:currentObjects){
		System.out.println("Thetta kemur ur repair "+ set.getName());
	}
}
}