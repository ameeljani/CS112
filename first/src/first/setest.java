package first;

public class setest {

	public static void main(String[] args) {
		
		int[]b= {'C','Q','S','A','X','B','T'};
		selectionSort(b);

	}
  
	public static void selectionSort(int[]a)
	{
		int n=a.length;
		
		for(int i=0; i<n;i++)
		{
			for(int x=0; x<a.length;x++)
			{
				System.out.print(a[x]+" ");
			}
			System.out.println();
			int min = i;
			for ( int j = i+1; j < n; j++ )
			{
				if (a[j] < a[min])
				{
					min = j;
				}
			}
			
			
			exchange(a, i, min);
			
		}	
		
	}
	private static void exchange (int[] a, int i, int j) 
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
}
