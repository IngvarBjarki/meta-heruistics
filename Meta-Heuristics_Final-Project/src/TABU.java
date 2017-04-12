//import java.io.IOException;
//import java.util.Date;
//
//public class TABU {
//
//    public DataObject Data;
//    public int IterationCount = 0;
//    public int tabooLength;
//    private int bestPerson1;
//    private int bestPerson2;
//    public TABU(DataObject DataFile, int TabooLength) throws IOException
//    {
//        Data = DataFile;
//        this.tabooLength = TabooLength;
//        Data.TabooLength = TabooLength;
//    }
//
//    public void Run(long runlength)
//{
//    Date da=new Date();
//    long start_time = da.getTime();
//
//    long max_sec = start_time+1000*runlength;
//    da=new Date();
//    System.out.println("Start");
//
//    Data.GetRandomInitialSolution();
//
//        while(da.getTime() < max_sec) //Runs while there is time
//        {
//            da=new Date(); //Helps keep track of time
//            this.IterationCount++; //Adds to the iteration count
//            Data.BestCurrentValue = 0; //The current best solution is 0, as there hasn't been checked anything yet
//            Data.DeepClone(Data.Tables, Data.CurrentTables); //A copy is made so the changes caqn be reversed
//
//            for(int table = 1 ; table <= Data.TableCount; table++)//Runs through all tables
//            {
//                for(int seat = 1 ; seat <= Data.TablesMax ; seat++) //All the seats
//                {
//                    for(int table2 = 1 ; table2 <= Data.TableCount; table2++) //All the tables that can be swapped to
//                    {
//                        if(table2 != table)//The tables can't be the same
//                        {
//                            for(int seat2 = 1 ; seat2 <= Data.TablesMax ; seat2++) //All the seats that can be swapped to
//                            {
//                                Data.SwapPersons(seat, table, seat2, table2, Data.CurrentTables); //Tries swapping two persons
//                                int SolVal = Data.CalcSolutionValue(Data.CurrentTables); //Finds the value of the given solution
//
//                                if(SolVal > Data.BestCurrentValue && Data.IsTaboo(Data.CurrentTables[table][seat], Data.CurrentTables[table2][seat2]) == false) //If this is the best so far and not taboo, this is saved
//                                {
//                                    Data.BestCurrentValue = SolVal;
//                                    bestPerson1 = Data.CurrentTables[table][seat];   //Remembers the person for the taboolist
//                                    bestPerson2 = Data.CurrentTables[table2][seat2]; //Remembers the person for the taboolist
//                                    Data.DeepClone(Data.CurrentTables, Data.CurrentBestTables );
//
//                                }
//                                Data.DeepClone(Data.Tables, Data.CurrentTables); //The currenttables is being reset for a new swap
//                            }
//
//                        }
//
//                    }
//                    
//                }
//
//            }
//            Data.DeepClone(Data.CurrentBestTables, Data.Tables); //The best solution of this iteration is chosen, even if it is worse than the original
//            Data.AddTaboo(bestPerson1, bestPerson2); //Makes the swap back taboo.
//
//            if(Data.BestCurrentValue > Data.BestValue) //If there has been found a solution that improves the best, it is saved
//            {
//                Data.BestValue = Data.BestCurrentValue;
//                Data.DeepClone(Data.CurrentBestTables, Data.BestTables); //The best solution so far is saved
//            }
//
//          }
//        Data.DeepClone(Data.BestTables, Data.Tables); //In the end the best solution is chosen
//
//
//        }
//
//}
//
//}
