	
	public class apartado2 {
	
		public static void burbuja(int[]matrix){
	        int temp;
	        for(int i=1;i < matrix.length;i++){
	            for (int j=0 ; j < matrix.length- 1; j++){
	                if (matrix[j] > matrix[j+1]){
	                    temp = matrix[j];
	                    matrix[j] = matrix[j+1];
	                    matrix[j+1] = temp;
	                }
	            }
	        }
	    }
		public void bubbleSort(int arr[], int n) 
		{ 
		    // Base case 
		    if (n == 1) 
		        return; 
		    // One pass of bubble sort. After 
		    // this pass, the largest element 
		    // is moved (or bubbled) to end. 
		    for (int i=0; i<n-1; i++) 
		        if (arr[i] > arr[i+1]) {
		            int x= arr[i];
		            int y = arr[i+1];
		            arr[i]=y;
		            arr[i+1]=x;
		        }
		    // Largest element is fixed, 
		    // recur for remaining array 
		    bubbleSort(arr, n-1); 
		} 
		
		public static void main(String[] args){
	
		}
	}
