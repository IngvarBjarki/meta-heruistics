import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class LNS {
	private int numElements;
	private int numSets;
	private ArrayList<SetObject> sets;
	private SetObject startSet;

	public LNS(int numElements, int numSets, ArrayList<SetObject> sets, SetObject startSet) {
		// TODO Auto-generated constructor stub
		this.numElements = numElements;
		this.numSets = numSets;
		this.sets = sets;
		this.startSet = startSet;
		greedy(sets, this.startSet);
	}

	public void run(int runlength) {
		List<SetObject> globalSolution = greedy(this.sets, this.startSet);
		List<SetObject> tempSolution = new ArrayList<SetObject>();
		List<SetObject> remainsFromDestroy = new ArrayList<SetObject>();
		List<SetObject> currentSolution = deepCopy(globalSolution);

		Date da = new Date();
		long start_time = da.getTime();
		long max_sec = start_time + 1000 * runlength;
		da = new Date();

		int tempSolutionValue = 99999999; // due to minimization
		int currentSolutionValue = calculateSolution(currentSolution);
		int globalSolutionValue = calculateSolution(globalSolution);
		int k = 0;


		System.out.println("Starting LNS...");
		 while(da.getTime() < max_sec){
		 da = new Date();
		 System.out.println(k);
		 k++;
		 
		 // RANDOM DESTROY
//		 System.out.println("destroy..");
//		 remainsFromDestroy = destroy(currentSolution);
		 
		 // MOST SIMILAR DESTROY
		 System.out.println("destroy..");
		 remainsFromDestroy = destroySimilar(currentSolution);
		 
		 System.out.println("repair..");
		 tempSolution = repair(this.sets, remainsFromDestroy);
		 System.out.println("done repearing ..");
		 
		 
		 if(calculateSolution(tempSolution) < currentSolutionValue){
		 System.out.println("changed local solution..");
		 currentSolution.clear();
		 currentSolution = deepCopy(tempSolution);
		 currentSolutionValue = calculateSolution(currentSolution);
		 tempSolution.clear();
		 }
		
		 if(currentSolutionValue < globalSolutionValue){
		 System.out.println("changed global solution");
		 globalSolution.clear();
		 globalSolution = deepCopy(currentSolution);
		 globalSolutionValue = calculateSolution(globalSolution); /// skoða hvernig reference er á int í java..
		 }
		 }
		
		 for(SetObject set : globalSolution){
		 System.out.println("set " + set.getName() + " is used");
		 }
		 System.out.println("The solution from the LNS is: " +
		 globalSolutionValue);

	}

	public List<SetObject> destroySimilar(List<SetObject> solution) {

		// the index here stands for the elements, making us able to select k
		// max from this array for most similarities
		int[] similarities = new int[1000];
		for (SetObject set : solution) {
			for (int element : set.getElements()) {
				System.out.println("printing..");
				System.out.println(element);
				similarities[element - 1] = similarities[element - 1] + 1;
			}
		}
		System.out.println(Arrays.toString(similarities));
		int[] kMaxElements = kMaxSimilarities(similarities, 3);
		
		// sidan forum vid i gegnum obj og hentum ut teim sem hafa indexinn sem eru i similaritites
		boolean destroy = false;
		List<SetObject> blabla = deepCopy(this.sets);
		for(int restrictedElements : kMaxElements){
			for(SetObject set : this.sets){
				destroy = set.getElements().contains(restrictedElements);
			if(destroy){
				blabla.remove(set);
				System.out.println("BOOM!");
				}
			}
		}
		
		return blabla;
	}

	public int[] kMaxSimilarities(int[] similaritites, int k) {
		int maxIndex = 0;
		int[] kMaxIndexes = new int[k];
		int minMaxIndex = 0;
		for (int i = 1; i < similaritites.length; i++) {
			int newNumber = similaritites[i];
			if (i < k) {
				kMaxIndexes[i] = i;
				if (newNumber < similaritites[minMaxIndex]) {
					minMaxIndex = i;
					System.out.println("minMaxIndex " +minMaxIndex);
				}
				continue;
			} else if (newNumber > similaritites[kMaxIndexes[minMaxIndex]]) {
				//minMaxIndex = 0;
				kMaxIndexes[minMaxIndex] = i;
				for (int j = 0; j < k; j++) {
					if (similaritites[kMaxIndexes[minMaxIndex]] > similaritites[kMaxIndexes[j]]) {
						//minMaxIndex = kMaxIndexes[j];
						System.out.println(similaritites[kMaxIndexes[minMaxIndex]] + " is greater than " + similaritites[kMaxIndexes[j]]);
						minMaxIndex = j;
						System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
						System.out.println(Arrays.toString(kMaxIndexes));
						System.out.println(similaritites[kMaxIndexes[minMaxIndex]]);
					}
						
					
				}
			}

		}
		System.out.println(Arrays.toString(kMaxIndexes));
		System.out.println(similaritites[kMaxIndexes[0]]);
		System.out.println(similaritites[kMaxIndexes[1]]);
		System.out.println(similaritites[kMaxIndexes[2]]);
		return kMaxIndexes;
	}

	public int calculateSolution(List<SetObject> solution) {
		int totalCost = 0;

		for (SetObject set : solution) {
			totalCost = totalCost + set.getCost();
			// System.out.println("set: " + set.getName() + " is in the optimal
			// solution");
		}
		return totalCost;
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

	public List<SetObject> greedy(ArrayList<SetObject> sets, SetObject startSet) {
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

		// System.out.println("er i grasp");
		// System.out.println(sets.get(0));
		// System.out.println(sets.get(0).getName());

		// System.out.println(currentElements.containsAll(universe));
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
					// System.out.println("divide by zero");
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
					// System.out.println("TEMPNOTINCOMON");
					// System.out.println(tempNotInComonElements);

				}

			}
			optimal = 100000;
			currentElements.addAll(tempNotInComonElements); // .eessu a ekki að
															// vera í þessari
															// lykju
			nameOfCurrentElements.add(tempElement);
			currentObjects.add(tempObj);
			// System.out.println(".etta er mengid " + (k + 1));
			// System.out.println("UNIVERSE");
			// System.out.println(currentElements);
			// System.out.println("SETS");
			// System.out.println(nameOfCurrentElements);
		}
		return currentObjects;
		// Destroy(currentObjects);
	}

	public List<SetObject> destroy(List<SetObject> solution) {
		int CoversRemoved = 7; // This is how many covers at random we will
								// remove at random from the initial
								// solution(often denoted as p)
		Random rand = new Random();
		for (int i = 1; i < CoversRemoved; i++) {
			solution.remove(solution.get(rand.nextInt(solution.size())));

		}
		// repair(this.sets,solution);
		return solution;
	}

	private List<Integer> getIntegerSolution(List<SetObject> FromDestroy) {
		List<Integer> CurrentElements = new ArrayList<Integer>();
		for (SetObject Set : FromDestroy) {
			CurrentElements.addAll(Set.getElements());

		}
		return CurrentElements;

	}

	public List<SetObject> repair(List<SetObject> sets, List<SetObject> FromDestroy) {

		float temp;
		float optimal = Float.POSITIVE_INFINITY;
		List<Integer> tempNotInComonElements = new ArrayList<Integer>();
		List<Integer> currentElements = new ArrayList<Integer>();
		List<SetObject> currentObjects = new ArrayList<SetObject>();
		List<Integer> universe = new ArrayList<Integer>();
		List<String> nameOfCurrentElements = new ArrayList<String>();
		String tempElement = null;
		SetObject tempObj = null;

		currentElements = getIntegerSolution(FromDestroy);
		// .addAll(startSet.getElements());
		// nameOfCurrentElements.add(startSet.getName());
		currentObjects.addAll(FromDestroy);

		for (int i = 1; i < numElements; i++) {
			universe.add(i);
		}

		// System.out.println("er i
		// LNS");System.out.println(sets.get(0));System.out.println(sets.get(0).getName());
		//
		// System.out.println(currentElements.containsAll(universe));
		int k = 0;
		while (!currentElements.containsAll(universe)) {
			for (int i = 0; i < sets.size(); i++) {
				List<Integer> c = new ArrayList<>(sets.get(i).getElements());
				c.removeAll(currentElements);

				try {
					temp = (float) sets.get(i).getCost() / c.size();
				} catch (Exception e) {
					// System.out.println("divide by zero");
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
					// System.out.println("TEMPNOTINCOMON");
					// System.out.println(tempNotInComonElements);

				}
			}
			optimal = 100000;
			currentElements.addAll(tempNotInComonElements); // .eessu a ekki að
															// vera í þessari
															// lykju
			nameOfCurrentElements.add(tempElement);
			currentObjects.add(tempObj);
		}
		// for (SetObject set:currentObjects){
		// System.out.println("Thetta kemur ur repair "+ set.getName());
		// }
		return currentObjects;
	}
}