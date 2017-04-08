import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class HillClimber {

	private List<SetObject> solution;
	private List<SetObject> universe;
	private int IterationCount = 0;

	public HillClimber(List<SetObject> InitialSolution, List<SetObject> universe) {
		// TODO Auto-generated constructor stub
		this.solution = InitialSolution;
		this.universe = universe;
	}

	public List<SetObject> run(long runlength) {

		
		Date da = new Date();
		boolean done = false;
		long start_time = da.getTime();
		long max_sec = start_time + 1000 * runlength;
		da = new Date();

		boolean constraint_met = false;
		boolean decreased_cost = false;

		List<SetObject> Solution = null;
		System.out.println(max_sec);
		System.out.println("start hilclimbing..");
		while (da.getTime() < max_sec && done == false) {
			da = new Date();
			System.out.println(da.getTime());
			this.IterationCount++;
			for (SetObject solutionSet : solution) {
				for (SetObject universeSet : universe) {
					// make sure that no constraints are violated

					// ÞARF AÐ DOBLUE TÉKKA!!!!!
					// virkar þannig að ef universe settið inniheldur alla í
					// solution setinu þá tru annars false
					constraint_met = Arrays.asList(universeSet.getElements()).containsAll(solutionSet.getElements());

					if (constraint_met) {
						constraint_met = false;
						decreased_cost = universeSet.getCost() < solutionSet.getCost();
						if (decreased_cost) {
							solution.remove(solutionSet);
							solution.add(universeSet);
							decreased_cost = false;
						}
					}
				}

			}
		}
		return solution;
	}
	
	public void calculateSolution(long runlength){
		List<SetObject> solution = run(runlength);
		float totalCost = 0;
		
		for(SetObject set:solution){
			totalCost = totalCost + set.getCost();
			System.out.println("set: " + set.getName() + " is in the optimal solution");
		}
		
		System.out.println("The totalCost is: " + totalCost);
		
	}

}



