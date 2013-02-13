// btp500
// a1
// Nitin Prakash Panicker
// 058 850 108


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;



public class Search 
{
	// declaration and intialization of all the static variables
	public static int max = 0;
	public static int min = 0;
	public static int Q = 0;
	public static double variance = 0;
	public static double SD = 0;
	
	public static int accessBS = 0; 
	public static int accessIS = 0; 
	
	public static void main(String [] args)
	{
			PrintStream out = null; // creating a print stream to print the console output to a text file.
		try
		{
			out = new PrintStream(new FileOutputStream("output.txt")); /* connecting a new print stream to a new file output stream
																		and saving the console output to a newly ceated text file called output.txt*/
		} 
		catch (FileNotFoundException e)   // file not found exception catch
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.setOut(out);
    	
		for(int n = 100; n < 10000000 ; n = n * 10)  /* for loop to run for values of n from 100 to 10^6, the value of n is incremented 10 times 
														after every 100 runs */
		{
			System.out.println("************************** ARRAY SIZE n = "+ n +" **************************");
			System.out.println("    ");
			Search s = new Search(); // creating a new Search object
		
			double startTime, elapsedTime; // start time stores the initial value of nanoTime(), elapsedTime stores the elapsed time value 
		
		
			int[] intArray = new int[n];
		
			int key, index;
		
			double[] timeBS = new double[100]; // array to store all the times for 100 runs of binary search
		
			double[] timeIS = new double[100]; // array to store all the times for 100 runs of interpolation search
		
			int[] accesscountBS = new int[100]; // array to store the access counts for binary search for every 100 runs
		
			int[] accesscountIS = new int[100]; // array to store the access counts for interpolaton search for every 100 runs
		
		
			for(int i = 0;i < 100 ; i++)  // for loop to run the code 100 times for the current value of n
			{
				
				key = s.randomGenerator(intArray); //calling method randomgenerator to generate an array of random sorted values

				startTime = System.nanoTime();		// starting system time for calculation
				index = s.binarySearch(intArray,key);  // calling binarysearch method to perform binary search
				elapsedTime = System.nanoTime() - startTime; // calculating elapsed time
		
				timeBS[i] = elapsedTime;  //storing elapsed time for every run into an array. this is useful when average has to be calculated
		
				accesscountBS[i] = accessBS; // storing the number of array accesses for every run into an array.
												// this is useful when average, standard deviation , min and max for accesses have to be calculated
				System.out.println("Array access for binary " + accessBS);
				

				startTime = System.nanoTime();
				index = s.InterpolationSearch(intArray, key); // calling interpolationsearch method to perform interpolation
				elapsedTime = System.nanoTime() - startTime;
		
				timeIS[i] = elapsedTime; // storing elapsed time for every run
		
				accesscountIS[i] = accessIS; // storing array access for every run
				System.out.println("Array access for interpolation " + accessIS);
				
				accessBS = 0; // reset array counter for next run
				
				accessIS = 0; // reset array counter for next run
		
			}
			double averagetimeBS = s.Average_time(timeBS); // calculating average time for BS by calling average method
		
			System.out.println("Average time for Binary Search:" + averagetimeBS + " nanoseconds");
		
			double averageaccessBS = s.Average_access(accesscountBS); // calculating average accesses for BS by calling average
		
			System.out.println("Average array access for Binary Search:" + s.Average_access(accesscountBS));
		
		// for loop to find out what is the max number of accesses
			for(int i =0 ; i < accesscountBS.length ; i++)
		 	{
				if(accesscountBS[i] > max)
				{
					max = accesscountBS[i];
				}
		 	}
		
		
			System.out.println("Maximum array access for Binary Search:" + max);
		
			max = 0;
			// for loop to find out what is the min number of accesses
			for(int i = 1 ; i < accesscountBS.length ; i++)
			{
				if(accesscountBS[i] < min)
				{
					min = accesscountBS[i];
				}
			}
		
			System.out.println("Minimum array access for Binary Search:" + min);
		
			min = 0;
		
		// for loop to calculate standard deviation
				for (int i = 0; i < accesscountBS.length; i++)  // This code for standard deviation calculation was taken from an internet source.
				{ // referenced in the report.
					Q = Q + (accesscountBS[i] - (int) averageaccessBS) * (accesscountBS[i] - (int) averageaccessBS);
				}

				variance = Q / (accesscountBS.length-1); // variance calculation . internet source referenced in the report
				SD = Math.sqrt(variance); 
		
				System.out.println("Standard deviation for Binary Search:" + SD);
		
				Q=0;
		
				System.out.println("     ");
				double averagetimeIS = s.Average_time(timeIS); // calculating average time for IS
		
				System.out.println("Average time for Interpolation Search:" + averagetimeIS + " nanoseconds");
		
	
				double averageaccessIS = s.Average_access(accesscountIS); // calculating average accesses
		
				System.out.println("Average array access for Interpolation Search:" + averageaccessIS);
		
	
				System.out.println("Average array access for Interpolation Search:" + s.Average_access(accesscountIS));
		
		// for loop to calculate max number of accesses
				for(int i =0 ; i < accesscountIS.length ; i++)
				{
					if(accesscountIS[i] > max)
					{
						max = accesscountIS[i];
					}
				}
		
		
				System.out.println("Maximum array access for Interpolation Search:" + max);
				max = 0;
		
					// for loop to calculate min number of accesses
				for(int i = 1 ; i < accesscountIS.length ; i++)
				{
					if(accesscountIS[i] < min)
					{
						min = accesscountIS[i];
					}
				}
		
				System.out.println("Minimum array access for Interpolation Search:" + min);
		
				min = 0;
		// standard deviation...internet source. referenced in the report.
				for (int i = 0; i < accesscountIS.length; i++)
				{
					Q = Q + (accesscountIS[i] - (int) averageaccessIS) * (accesscountIS[i] - (int) averageaccessIS);
				}

				variance = Q / (accesscountIS.length-1);
				SD = Math.sqrt(variance);
		
				System.out.println("Standard deviation for Interpolation Search:" + SD);
		
				Q=0;
				
				System.out.println("**********************************************************************************");
				System.out.println("    ");
			}// n for loop end
		
	}
	/* binary search method. the while loop runs till the start index and the end index are not the same.
	 * if the mid point is not the key then the next else if is encountered where the key is compared with the mid point.
	 * if the key is less than mid point then it searches to the left of the array
	 * if the key is greater than mid point then it searches to the right of the array
	 */
	
	//http://www.roseindia.net/tutorial/java/core/binarySearch.html
	public int binarySearch(int[] arr, int find) 
	{
          int start, end, index;
          start = 0;
          end = arr.length - 1;
          
          while (start <= end)  
          {
                  index = (start + end) / 2;
                  if (arr[index] == find) 
                  {
                	  accessBS++;
                      return index;
                  } 
                  
                  else if (arr[index] < find) 
                  {
                	  accessBS++;
                      start = index + 1;
                  } 
                  
                  else 
                  {
                          end = index - 1;
                  }
          }
          return -1;
	}
	
	/* interpolation search method. interpolation search estimates the location of the key by comparing the 
	 * distance between the key and the low value to the distance between the low and high values of the array
	 * from the calculated percentage the index value of the key is estimated.
	 */
	// http://www.dreamincode.net/forums/topic/63702-interpolation-search/
	public int InterpolationSearch(int[] sortedArray, int toFind)
	{  
		  // Returns index of toFind in sortedArray, or -1 if not found  
		final int NOT_FOUND = -1;	
		int low = 0;  
		int high = sortedArray.length - 1;  
		int lastMid = -1;
		   
		while (true) 
		{ 
			int mid = (low + high) / 2;  
			if(sortedArray[mid] == toFind)
			{
				accessIS++;
				return mid;
			}
			if(toFind > sortedArray[mid]) 
			{
				accessIS++;
				low = mid + 1;
			}
			else 
			{
				high = mid -1;
			}
			if(lastMid == mid)
			{
				
				return NOT_FOUND;
			}
			lastMid = mid;
		} 
	}   
	 
	/* method to generate random values for the array and to generate a random key.
	 * The values in the array are evenly distributed by a factor 10.
	 */

	 public int randomGenerator(int[] integers)
	 {
		 int key,i;
		 int randomIndex =  (int)(Math.random() * (((integers.length-1)) + 1));
		
		 integers[0] =  (int)(Math.random() * (((50)) + 1));
		 for (i = 1; i < integers.length; i++) 
		 {
			 integers[i] = integers[i-1] + 10;
		 }

		 key = integers[randomIndex];
		 return key;
	 }
	 
	 
/* method to calculate average of the values in the array received */

	 public double Average_time(double[] Array)
	 {
		 double average = 0;
		 for(int i = 0 ; i < Array.length ; i++)
		 {
			 average += Array[i];
		 }
		 return (average/Array.length - 1);
	 }
	 
	 public double Average_access(int[] Array)
	 {
		 double average = 0;
		 for(int i = 0 ; i < Array.length ; i++)
		 {
			 average += Array[i];
		 }
		 return (average/Array.length - 1);
	 }

	
	}

	
