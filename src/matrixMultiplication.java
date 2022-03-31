import java.util.Random;

public class matrixMultiplication {
	
	
	/**
	 * first it will generate two nxn random matrices
	 * since the matrices should be powers of 2, there will be a loop
	 * to generate 2^i matrices.
	 * the running time will be calculated from endTime-startTime
	 * and then the average time will be recorded based on some n number of times
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		

		// generate two random n x n matrices A and B
		int[][] A, B;
		int n; // size of the matrix 
		int numberOfTimes = 15;
		
		// times to record
		long start;
		long end;
		
		
		long ClassicalTime = 0;
		long DevideAndConquerTime = 0;
		long StrassenTime = 0;
		
//THIS PART IS FOR TESTING THE GIVEN MATRIX
		long startTEST;
		long endTEST;
		
		long ClassicalTimeTEST = 0;
		long DevideAndConquerTimeTEST = 0;
		long StrassenTimeTEST = 0;
		
		
		int[][] ClassicResultTEST = new int[4][4];
		int[][] DCResultTEST = new int[4][4];
		int[][] StrassenResultTEST = new int[4][4];
		
		//sanity check using provided matrix 
		int[][] X = {{2, 0 , -1, 6},
					{3, 7, 8, 0},
					{-5, 1, 6, -2},
					{8, 0, 2, 7}};
		
		int[][] Y = {{0, 1 , 6, 3},
					{-2, 8, 7, 1},
					{2, 0, -1, 0},
					{9, 1, 6, -2}};
		
//		ClassicResultTEST = classicalMulti(X, Y, 4);
//		DCResultTEST = DivideAndConquerMult(X, Y, 4);
//		StrassenResultTEST = StrassenMult(X, Y, 4);
		
		System.out.println("\n\nclassical: ");
		startTEST = System.nanoTime();
		ClassicResultTEST = classicalMulti(X, Y, 4);
		endTEST = System.nanoTime();
		ClassicalTimeTEST += endTEST - startTEST;
		displayMatrix(ClassicResultTEST,4);
		
		System.out.println("DC: ");
		startTEST = System.nanoTime();
		DCResultTEST = DivideAndConquerMult(X, Y, 4);
		endTEST = System.nanoTime();
		DevideAndConquerTimeTEST += endTEST - startTEST;
		displayMatrix(DCResultTEST,4);
		
		System.out.println("Strassen: ");
		startTEST = System.nanoTime();
		StrassenResultTEST = StrassenMult(X, Y, 4);
		endTEST = System.nanoTime();
		StrassenTimeTEST += endTEST - startTEST;
		displayMatrix(StrassenResultTEST,4);
		
		
		System.out.println("\n\n  Classic matrix multiplication time = " + ClassicalTimeTEST + " ns\n");
		System.out.println("  Divide and Conquer matrix multiplication time = " + DevideAndConquerTimeTEST + " ns\n");
		System.out.println("  Strassen matrix multiplication time = " + StrassenTimeTEST + " ns\n");
		
		System.out.println("\n\n ============== TEST ENDED ================== ");
		
		System.out.println(" \n\n\n\n\n\n ============== STARTING INCREMENTING SIZE WITH RANDOM ELEMENTS ================== \n");
		
		
//END OF TEST PART
		
		
		
		//increment i as a power of 2 to get the matrix size (n)
		for (int i = 1; i < numberOfTimes ; i++) {
			
			n = (int)Math.pow(2, i);
			
			A = generateMatrix(n);
			B = generateMatrix(n);
			
//			System.out.println("The original matrices are: ");
//			displayMatrix(A,n);
//			displayMatrix(B,n);
			

			int[][] ClassicResult = new int[n][n];
			int[][] DCResult = new int[n][n];
			int[][] StrassenResult = new int[n][n];
			
			//run the multiplications for 20 times, and then get the average running time
			for(int j = 0; j < numberOfTimes; j++)
			{
				//classical multiplication time and result
				start = System.nanoTime();
				ClassicResult = classicalMulti(A, B, n);
				end = System.nanoTime();
				ClassicalTime += end - start;
//				System.out.println("classical: ");
//				displayMatrix(ClassicResult,n);
				
				//divide and conquer time and result
				start = System.nanoTime();
				DCResult = DivideAndConquerMult(A, B, n);
				end = System.nanoTime();
				DevideAndConquerTime += end - start;
//				System.out.println("DC: ");
//				displayMatrix(DCResult,n);
				
				//Strassen time and result
				start = System.nanoTime();
				StrassenResult = StrassenMult(A, B, n);
				end = System.nanoTime();
				StrassenTime += end - start;
//				System.out.println("Strassen: ");
//				displayMatrix(StrassenResult,n);
				
			}
			
			//calculating the average time for each method
			ClassicalTime = ClassicalTime / numberOfTimes;
			DevideAndConquerTime = DevideAndConquerTime / numberOfTimes;
			StrassenTime = StrassenTime / numberOfTimes;
			
			System.out.println("\n\t\t n = " + n + "\n");
			System.out.println("Classic matrix multiplication time = " + ClassicalTime + " ns\n");
			System.out.println("Divide and Conquer matrix multiplication time = " + DevideAndConquerTime + " ns\n");
			System.out.println("Strassen matrix multiplication time = " + StrassenTime + " ns\n");
			
			System.out.println("----------------------------------------------------------------------");
			
		}
		
		
		
		
		
	}
	
	/**
	 * this will generate the random nxn matrix
	 * 
	 * @param n size of matix
	 * @return  the array of nxn
	 */
	
	public static int[][] generateMatrix(int n){
		
		int [][] matrix = new int[n][n];
		Random rand = new Random();
		
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++)
				matrix[i][j] = rand.nextInt(100);
		}
		return matrix;
		
	}
	
	/**
	 * This method will print the matrix
	 * 
	 */
 
	public static void displayMatrix(int[][] m, int n) {
		
		System.out.println(" ");
		for(int i = 0; i < n; i++)
		{
			
			for(int j = 0; j < n; j++)
			{
				
				System.out.printf("%8d", m[i][j]);
			}
			System.out.println("\n");
		}
		System.out.println("--------------------------------------------");
		
	}
	
	
	/**
	 * this method will use Classical matrix multiplication
	 * 
	 * @param A  first matrix
	 * @param B  second matrix
	 * @param n  size of the matrices
	 * @return a matrix as result of the multiplication of A x B
	 */
	
	public static int[][] classicalMulti (int [][] A, int[][] B, int n){
		
		int [][] result = new int [n][n];
		
		//first the result matrix should be assigned to all zeros 
		//in order to add the multiplication
		for (int i = 0; i < n; i++)
		{
			for (int j=0; j < 0; j++)
				result[i][j] = 0;
		}
		
		// perform classic multiplication
		for( int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < n; k++)
					result[i][j] += A[i][k] * B[k][j]; 
			}
		}
		
		return result;
	}
	
	
	/**
	 * 
	 * @param A first matrix
	 * @param B second matrix
	 * @param n size of the matrices
	 * @return a matrix of the result of the multiplication
	 */
	
	public static int[][] DivideAndConquerMult(int[][] A, int[][] B, int n){
		
		int[][] result = new int[n][n];
		
		//base case
		if(n == 1 )
		{
			result[0][0] = A[0][0] * B[0][0];
			return result;
		}
		//dividing each section by 2
		else {
			int[][] A11 = new int[n / 2][n / 2];
			int[][] A12 = new int[n / 2][n / 2];
			int[][] A21 = new int[n / 2][n / 2];
			int[][] A22 = new int[n / 2][n / 2];
			int[][] B11 = new int[n / 2][n / 2];
			int[][] B12 = new int[n / 2][n / 2];
			int[][] B21 = new int[n / 2][n / 2];
			int[][] B22 = new int[n / 2][n / 2];
			
            //splitting each original matrix into 4 halves
			split(A, A11, 0, 0);
			split(A, A12, 0, n / 2);
			split(A, A21, n / 2, 0);
			split(A, A22, n / 2, n / 2);
			split(B, B11, 0, 0);
			split(B, B12, 0, n / 2);
			split(B, B21, n / 2, 0);
			split(B, B22, n / 2, n / 2);
			
            //adding corresponding elements
			int[][] C11 = addMatrix(DivideAndConquerMult(A11, B11, n / 2),
									DivideAndConquerMult(A12, B21, n / 2), n / 2);
			int[][] C12 = addMatrix(DivideAndConquerMult(A11, B12, n / 2),
									DivideAndConquerMult(A12, B22, n / 2), n / 2);
			int[][] C21 = addMatrix(DivideAndConquerMult(A21, B11, n / 2),
									DivideAndConquerMult(A22, B21, n / 2), n / 2);
			int[][] C22 = addMatrix(DivideAndConquerMult(A21, B12, n / 2),
									DivideAndConquerMult(A22, B22, n / 2), n / 2);
			
			//joining back final elements to a whole matrix
			join(C11, result, 0, 0);
			join(C12, result, 0, n / 2);
			join(C21, result, n / 2, 0);
			join(C22, result, n / 2, n / 2);
		}

		return result;
		
	}
	
	/**
	 * just a bridge method creating a new empty matrix
	 * @param A original first matrix 
	 * @param B original second matrix
	 * @param n size of the matrices
	 * @return a result matrix containing the full multiplication
	 */
	
	public static int[][] StrassenMult(int[][] A, int[][] B, int n) {
		int[][] result = new int[n][n];
		
		strassenMainMult(A, B, result, n);
		
		return result;
	}
	
	/**
	 * this is the main part of the Strassen algorithm
	 * @param A first matrix
	 * @param B second matrix
	 * @param C new matrix for the result
	 * @param n size of the matrices
	 */
	public static void strassenMainMult(int[][] A, int[][] B, int[][] C, int n) {

		if (n == 2) 
		{
			C[0][0] = (A[0][0] * B[0][0]) + (A[0][1] * B[1][0]);
			C[0][1] = (A[0][0] * B[0][1]) + (A[0][1] * B[1][1]);
			C[1][0] = (A[1][0] * B[0][0]) + (A[1][1] * B[1][0]);
			C[1][1] = (A[1][0] * B[0][1]) + (A[1][1] * B[1][1]);
		} 
		
		else {
			int[][] A11 = new int[n / 2][n / 2];
			int[][] A12 = new int[n / 2][n / 2];
			int[][] A21 = new int[n / 2][n / 2];
			int[][] A22 = new int[n / 2][n / 2];
			int[][] B11 = new int[n / 2][n / 2];
			int[][] B12 = new int[n / 2][n / 2];
			int[][] B21 = new int[n / 2][n / 2];
			int[][] B22 = new int[n / 2][n / 2];

			int[][] P1 = new int[n / 2][n / 2];
			int[][] P2 = new int[n / 2][n / 2];
			int[][] P3 = new int[n / 2][n / 2];
			int[][] P4 = new int[n / 2][n / 2];
			int[][] P5 = new int[n / 2][n / 2];
			int[][] P6 = new int[n / 2][n / 2];
			int[][] P7 = new int[n / 2][n / 2];

			split(A, A11, 0, 0);
			split(A, A12, 0, n / 2);
			split(A, A21, n / 2, 0);
			split(A, A22, n / 2, n / 2);
			split(B, B11, 0, 0);
			split(B, B12, 0, n / 2);
			split(B, B21, n / 2, 0);
			split(B, B22, n / 2, n / 2);

			strassenMainMult(addMatrix(A11, A22, n / 2), addMatrix(B11, B22, n / 2), P1, n / 2);
			strassenMainMult(addMatrix(A21, A22, n / 2), B11, P2, n / 2);
			strassenMainMult(A11, subtractMatrix(B12, B22, n / 2), P3, n / 2);
			strassenMainMult(A22, subtractMatrix(B21, B11, n / 2), P4, n / 2);
			strassenMainMult(addMatrix(A11, A12, n / 2), B22, P5, n / 2);
			strassenMainMult(subtractMatrix(A21, A11, n / 2), addMatrix(B11, B12, n / 2), P6, n / 2);
			strassenMainMult(subtractMatrix(A12, A22, n / 2), addMatrix(B21, B22, n / 2), P7, n / 2);
 
			int[][] C11 = addMatrix(subtractMatrix(addMatrix(P1, P4, P1.length), P5, P5.length), P7, P7.length);
			int[][] C12 = addMatrix(P3, P5, P3.length);
			int[][] C21 = addMatrix(P2, P4, P2.length);
			int[][] C22 = addMatrix(subtractMatrix(addMatrix(P1, P3, P1.length), P2, P2.length), P6, P6.length);

			join(C11, C, 0, 0);
			join(C12, C, 0, n / 2);
			join(C21, C, n / 2, 0);
			join(C22, C, n / 2, n / 2);
		}
	}
	
	
	
	/**
	 * this method splits the matrix
	 * 
	 * @param M  initial matrix (A or B)
	 * @param result  result matrix
	 * @param a  position of rows
	 * @param b  position of columns
	 */
	
	private static void split(int[][] M, int[][] result, int a, int b) {

		int x = b;
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result.length; j++) {
				result[i][j] = M[a][x++];
			}
			x = b;
			a++;
		}
	}
	
	
	/**
	 * This method add the two matrices
	 * @param A  first matrix
	 * @param B  second matrix
	 * @param n  size of the matrices
	 * @return  a matrix with addition of two matrices A and B
	 */
	private static int[][] addMatrix(int[][] A, int[][] B, int n) {

		int[][] result = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				result[i][j] = A[i][j] + B[i][j];
			}
		}
		return result;
	}
	
	/**
	 * this methos takes care of the subtract part of the strassen algorithm
	 * @param A  first matrix
	 * @param B  second matrix
	 * @param n  size of matrices
	 * @return  a matrix resulting subtraction of the matrices 
	 */
	private static int[][] subtractMatrix(int[][] A, int[][] B, int n) {

		int[][] result = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				result[i][j] = A[i][j] - B[i][j];
			}
		}
		return result;
	}

	
	/**
	 * this method joins the elements into one matrix at the end
	 * @param M  initial matrix
	 * @param result  the whole joined matrix
	 * @param a  position of rows
	 * @param b  position of columns
	 */
	private static void join(int[][] M, int[][] result, int a, int b) {

		int x = b;

		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M.length; j++) {
				result[a][x++] = M[i][j];
			}
			x = b;
			a++;
		}
	}
	
	
	
	
}























