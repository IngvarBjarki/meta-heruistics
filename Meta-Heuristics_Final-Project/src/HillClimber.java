
public class HillClimber {

	public HillClimber() {
		// TODO Auto-generated constructor stub
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
