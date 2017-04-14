import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HillClimber {

    private List<SetObject> solution;
	private List<SetObject> all_sets;
	private int IterationCount = 0;
	private int numElements = 0;

	public HillClimber(List<SetObject> InitialSolution, List<SetObject> all_sets, int numElements) {
		// TODO Auto-generated constructor stub
		this.solution = InitialSolution;
		this.all_sets = all_sets;
		this.numElements = numElements;
	}

	public List<SetObject> run(long runlength) {

		// þurfum að clona object utaf annars eydist alltaf tempSolutions
		Date da = new Date();
		boolean done = false;
		long start_time = da.getTime();
		long max_sec = start_time + 1000 * runlength;
		da = new Date();

		boolean constraint_met_withoutSet = false;
		boolean constraint_met = false;
		boolean decreased_cost = false;
		boolean most_decreased_cost = false;
		boolean solution_changed = false;
		boolean solution_changed_In_Itreation = false;
//		for(int i =3000; i< 3020; i++){
//			solution.add(all_sets.get(i));
//		}

		List<Integer> currentElements = new ArrayList<Integer>();
		List<Integer> universe = new ArrayList<Integer>();
		List<SetObject> tempChooseBestOfTowSolutionObj = new ArrayList<SetObject>();
	
		List<SetObject> tempSolution = new ArrayList<SetObject>(solution);

		for (SetObject set : solution) {
			System.out.println("this set was in greedy solution: " + set.getName());
		}

		for (SetObject solutionSet : solution) {
			currentElements.addAll(solutionSet.getElements());
		}

		// generate the universe
		for (int i = 1; i < numElements+1; i++) {
			universe.add(i);
		}
		
	

		// List<SetObject> Solution = null;
		List<Integer> tempCurrentElements = new ArrayList<Integer>();
		System.out.println("the length of the universe: " + universe);
		System.out.println(solution.size());
		System.out.println("start hilclimbing..");
		while (da.getTime() < max_sec && done == false) {
			da = new Date();
			this.IterationCount++;
			int k = 0;
			System.out.println("tempSolution" + tempSolution.size());
			for (SetObject solutionSet : solution) {
				System.out.println(k);
				k++;
				tempSolution.remove(solutionSet);
				// temporary solution on integer form
				currentElements = getElementsInCurrentSolution(tempSolution);
				// check if we have a redundance in our solution, if so we
				// remove the redundant set
				constraint_met_withoutSet = currentElements.containsAll(universe);
				currentElements.clear();
				// and continue by start checking the next set in the line
				if (constraint_met_withoutSet) {
					System.out.println("redundant set.... " + solutionSet.getName());
					// WE HAVE ALREADY REMOVED THE SET FROM THE TEMP SOLUTION SO
					// WE GO TO THE NEXT ELEMENT IN THE SOLUTION FOR LOOP AND
					// INVESTIGATE THAT !!!!!!!!!!!
					solution_changed = true;
					break;
//					continue;

				}
				tempChooseBestOfTowSolutionObj.add(solutionSet); //bætti þessu við til að laga runnið
				for (SetObject setOfAllSets : all_sets) {
					tempSolution.add(setOfAllSets);
					currentElements = getElementsInCurrentSolution(tempSolution);
					constraint_met = currentElements.containsAll(universe);
					currentElements.clear();
//					System.out.println(tempSolution.size());
//					System.out.println(solution.size());
					if (constraint_met) {
//						System.out.println("constraint met, checking costs..");
						decreased_cost = setOfAllSets.getCost() < solutionSet.getCost();
						if (decreased_cost) {
							System.out.println("improved set....");
							System.out.println(setOfAllSets.getName() + " swaped for " +solutionSet.getName());
							solution_changed = true;
							solution_changed_In_Itreation = true;
							tempChooseBestOfTowSolutionObj.add(setOfAllSets); // hofum bara 2 object i þessum... sem á að skipta á til að athuga hað er best...bera saman bestu 2 skiptingarnar a gefnum tima
							most_decreased_cost = !(tempChooseBestOfTowSolutionObj.get(0).getCost() < tempChooseBestOfTowSolutionObj.get(1).getCost()); 
							if(most_decreased_cost){
								tempSolution.remove(tempChooseBestOfTowSolutionObj.get(0));
								tempChooseBestOfTowSolutionObj.remove(0);
							}
							else{
								tempSolution.remove(tempChooseBestOfTowSolutionObj.get(1));
								tempChooseBestOfTowSolutionObj.remove(1);
							}
						}
					}
					
					if (!(constraint_met && decreased_cost)) {
						// if the set does not improve our solution we remove it
//						System.out.println("nothing happend, add the set again and remove the setofallsets");
						tempSolution.remove(setOfAllSets);
						//tempSolution.add(solutionSet);
					}
					// initialize the constraints for the next setObject
					constraint_met = false;
					decreased_cost = false;
				if(!(constraint_met && decreased_cost && constraint_met_withoutSet)){
					
				}
				}
				
				if(!solution_changed_In_Itreation){
					tempSolution.add(solutionSet);
					solution_changed_In_Itreation = false;
					
				}

			}
			if (solution_changed) {
				System.out.println("We have sucessfully changed our solution..");
				solution.clear();
				solution = deepCopy(tempSolution);
				solution_changed = false;
			}
			else{
				System.out.println("tempSolution" + tempSolution.size());
				System.out.println("no more possible changes");
				break;
			}

		}
		return solution;
	}

	private List<Integer> getElementsInCurrentSolution(List<SetObject> tempSolution) {

		List<Integer> currentElements = new ArrayList<Integer>();

		for (SetObject set : tempSolution) {
			currentElements.addAll(set.getElements());

		}
		return currentElements;
	}

	public int calculateSolution(long runlength) {
		List<SetObject> solution = run(runlength);
		int totalCost = 0;

		for (SetObject set : solution) {
			totalCost = totalCost + set.getCost();
			System.out.println("set: " + set.getName() + " is in the optimal solution");
		}

		System.out.println("The totalCost is: " + totalCost);
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

}