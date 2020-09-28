
import java.util.Scanner;

 
public class Main {

	
	private static final int ThreadNumber = 10;
	@SuppressWarnings( "deprecation" )
	public static void main(String[] args) {
		
	       
		    int n = 0; 
	        try (Scanner s=new Scanner(System.in)) {
	        	while (true){
	        		System.out.println("Enter the number of Queens :");
	        		n = s.nextInt();
	        		if ( n == 2 || n ==3) {
	        			System.out.println("No Solution possible for "+ n +" Queens. Please enter another number");
	        		}
	        		else
	        			break;
	        	}
	        }
	       
	        
	        System.out.println("Solution to "+ n +" queens using hill climbing search:");
	        
	        HillClimbingSearch hcs = new HillClimbingSearch(n);
			ThreadGroup threadGroup=new ThreadGroup("solutions"); 
			Thread[] solutions = new Thread[ThreadNumber];
			for (int i = 0; i < ThreadNumber; i++) {
				solutions[i] = new Thread(threadGroup,hcs);}
			
			long timestamp1 = System.currentTimeMillis();
			for (Thread s : solutions) {
				s.start();
			}
			int activeThread = threadGroup.activeCount();
			System.out.format("Number of active threads of %s is %d %n",
					threadGroup.getName(), activeThread);
			
			// try {
			// 	for (Thread s : solutions) {
			// 		s.join();	
			// 	}
			// } catch (Exception e) {
			// 	System.out.println(e);
			// }
			
			
			while (!(threadGroup.isDestroyed())) {
				if (hcs.getFinalSolution() != null) {
					try {
						threadGroup.stop(); //  stop all the threads as soon as one of them finds a solution
					} catch (Exception e) {
						System.out.println(e);
					}
					hcs.printState(hcs.getFinalSolution());
					break;
				}
			}	
				 
			long timestamp2 = System.currentTimeMillis();
			long timeDiff = timestamp2- timestamp1;
			
			System.out.println("Execution Time: "+timeDiff+" ms");
	        
				int activeThread1 = threadGroup.activeCount();
				System.out.format("Number of active threads of %s is %d %n",
						threadGroup.getName(), activeThread1);	
	       
		}
	
}