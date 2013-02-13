import java.util.Random;

public class BinarySearch
{
	public static int random, randomkey; 
	public static int countBS = 0, countIS = 0;
	public static int flag = 0, i=0;
	public static int[] accesscountBS = new int[100];
	public static int[] accesscountIS = new int[100];

		
        public static void main(String[] args) 
        {
                int[] intArray = new int[10];
                int index = 0;
                long startTime, elapsedTime;
               
                int max = 100;
                 
				 Random num = new Random();
                for(int i=0; i < intArray.length; i++)
                {
                	intArray[i] = num.nextInt(100);               	
                }
				
				for(int i=0; i<intArray.length; i++)   // sorting the array in ascending order
				{
					for(int j=i+1; j<intArray.length; j++ )
					{
						if(intArray[i] > intArray[j])
						{
							int temp = intArray[j];
							intArray[j]=intArray[i];
							intArray[i]= temp;
						}
					}
				}
				
                Random gen = new Random();
                         
                randomkey= intArray[gen.nextInt(intArray.length)];
              
          
                for(int i=0; i< intArray.length; i++)
                {
                	System.out.println("Array elements...");
                	System.out.println(intArray[i] + " ");
                }
                
                System.out.println("The random key is..." + randomkey);
                for(int j=0; j<10; j++)
                {
                	startTime = System.nanoTime();
                	index = binarySearch(intArray, 0, intArray.length - 1, randomkey, accesscountBS, flag, i=j );
                	flag = 1;
                
                	
                	if (index != -1) 
                	{
                			elapsedTime = System.nanoTime() - startTime;
                			System.out.println("Found at index: " + index);
                        
                			System.out.println("Time elapsed during Binary search = " + elapsedTime + " nanoseconds");
                	} 
                	else 
                	{
                			elapsedTime = System.nanoTime() - startTime;
                			System.out.println("Not Found");
                        
                			System.out.println("Time elapsed during Binary search = " + elapsedTime + " nanoseconds");
                	} 
                	
                }
              
				for(int j=0; j<10; j++)
                {
					startTime = System.nanoTime();
                	index = interpolationSearch(intArray, 0, intArray.length - 1, randomkey, accesscountBS, flag, i=j );
                	flag = 1;
                
                	if (index != -1) 
                	{
                			elapsedTime = System.nanoTime() - startTime;
                			System.out.println("Found at index: " + index);
                        
                			System.out.println("Time elapsed during Interpolation search = " + elapsedTime + " nanoseconds");
                	} 
                	else 
                	{
                			elapsedTime = System.nanoTime() - startTime;
                			System.out.println("Not Found");
                        
                			System.out.println("Time elapsed during Interpolation search = " + elapsedTime + " nanoseconds");
                	}
				
                }
        }		
		/**
		* Binary search
		* @param arr array with uniformly distributed values in ascending order
		* @param search searched value
		* @param fIndex first index that might be touched
		* @param lIndex last index that might be touched
		*/
        public static int binarySearch(int[] arr, int fIndex, int lIndex,int search, int[] count, int flag, int i)
		{
        	//int[] accesscountBS = {0};
        	int flag2 = 0;
        	
 			int middle = (fIndex + (lIndex - fIndex) / 2);
 
			if(fIndex<lIndex )
			{
 				if (search == arr[middle])
				{
					count[i]++;
					if(flag2!=1 || flag==1)
					{
			
					System.out.println("Number of array accesses in Binary search = "+ count[i]);
					}
					return middle;
				}
 
				else if(search < arr[middle])
				{
					if(search == arr[0])
					{
						count[i] = count[i] + 2;
						if(flag2!=1 || flag==1)
						{
				
						System.out.println("Number of array accesses in Binary search = "+ count[i]);
						}
						return 0;
					}	
					count[i] = count[i] +2;
				
					if(flag2!=0 && flag==1)
					{
			
					System.out.println("Number of array accesses in Binary search = "+ count[i]);
					}
					return binarySearch(arr, fIndex, middle, search, count, flag,i);
				}
 
				else if(search > arr[middle])
				{
					if(search == arr[middle+1])
					{
						count[i] = count[i] +2;
						if(flag2!=1 || flag==1)
						{
				
						System.out.println("Number of array accesses in Binary search = "+ count[i]);
						}
						return middle + 1;
					}	
					
					count[i] = count[i]+2;
					if(flag2!=0 && flag==1)
					{
			
					System.out.println("Number of array accesses in Binary search = "+ count[i]);
					}
					
					return binarySearch(arr, middle+1, lIndex, search, count, flag,i);
				}
 				
 				
 			}
			
			
				System.out.println("Number of array accesses in Binary search = "+ count[i]);
			
			return -1;
		}		
		/**
		* Interpolation search
		* @param array array with uniformly distributed values in ascending order
		* @param value searched value
		* @param from first index that might be touched
		* @param to last index that might be touched
		* @return index index of the searched value in the array, -1 if not found
		*/
		public static int interpolationSearch(int[] array, int from, int to, int value,int[] count, int flag, int i)
		{
			int flag2 = 0;
			
			if(array[from] == value) 
			{
				count[i]++;
				if(flag2!=1 || flag==1)
				{
		
				System.out.println("Number of array accesses in Interpolation search = "+ count[i]);
				}
				return from; 
			}
			else if(from == to || array[from] ==  array[to]) 
			{
				count[i] = count[i] +2;
				if(flag2!=1 || flag==1)
				{
		
				System.out.println("Number of array accesses in Interpolation search = "+ count[i]);
				}
				return -1; //not found
			}
				

			//probable position of the searched value
			int index = from + ((to - from)/(array[to] - array[from])) * (value - array[from]);
			count[i]= count[i] + 3;
    
			if(array[index] == value) 
			{
				count[i]++;
				if(flag2!=1 || flag==1)
				{
		
				System.out.println("Number of array accesses in Interpolation search = "+ count[i]);
				}
				return index;//found
			}	
			//continue in the right part of the array
			else if(array[index] < value) 
			{
				count[i]++;
				if(flag2!=0 && flag==1)
				{
		
				System.out.println("Number of array accesses in Interpolation search = "+ count[i]);
				}
				return interpolationSearch(array, index + 1, to, value,count,flag,  i);
			}
			//continue in the left part of the array
			else 
			{
				if(flag2!=0 && flag==1)
				{
		
				System.out.println("Number of array accesses in Interpolation search = "+ count[i]);
				}
				return interpolationSearch(array, from, index - 1, value,count, flag, i);
			}
		}
		
		
		
}