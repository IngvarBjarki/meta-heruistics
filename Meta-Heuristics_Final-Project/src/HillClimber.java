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


import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Martin Lundberg-Jensen
 */
public class HillClimber {

    public DataObject Data;
    public int IterationCount = 0;
    public HillClimber(DataObject DataFile) throws IOException
    {
        Data = DataFile;
    }

    public void Run(long runlength)
{
    Date da=new Date();
    boolean done = false;
    long start_time = da.getTime();
    long max_sec = start_time+1000*runlength;
    da=new Date();
    System.out.println("Start");

    Data.GetRandomInitialSolution();

        while(da.getTime() < max_sec && done == false) //Runs while there is time
        {
            da=new Date(); //Helps keep track of time
            done = true; //Runs while there are still changes being made
            this.IterationCount++; //Adds to the iteration count
            Data.BestCurrentValue = 0; //The current best solution is 0, as there hasn't been checked anything yet
            Data.DeepClone(Data.Tables, Data.CurrentTables); //A copy is made so the changes can be reversed

            for(int cover = 1 ; cover <= Data.TableCount; cover++)//Runs through all the covers
            {
                for(int seat = 1 ; seat <= Data.TablesMax ; seat++) //All the seats
                {
                    for(int table2 = 1 ; table2 <= Data.TableCount; table2++) //All the tables that can be swapped to
                    {
                        if(cover2 != cover)//The covers can't be the same
                        {
                            for(int seat2 = 1 ; seat2 <= Data.TablesMax ; seat2++) //All the seats that can be swapped to
                            {
                                Data.SwapPersons(seat, table, seat2, table2, Data.CurrentTables); //Tries swapping two se
                                int SolVal = Data.CalcSolutionValue(Data.CurrentTables); //Finds the value of the given solution

                                if(SolVal > Data.BestCurrentValue) //If this is the best so far, this is saved
                                {
                                    Data.BestCurrentValue = SolVal;
                                    Data.DeepClone(Data.CurrentTables, Data.CurrentBestTables );

                                }
                                Data.DeepClone(Data.Tables, Data.CurrentTables); //The current tables is being reset for a new swap
                            }

                        }

                    }
                    
                }

            }
            if(Data.BestCurrentValue > Data.BestValue) //If there has been found a solution that improves the best, it is saved
            {
                done = false; //If improvements were made, there might be more, and it runs again
                Data.BestValue = Data.BestCurrentValue;
                Data.DeepClone(Data.CurrentBestTables, Data.Tables);
            }

          }

        }

}
