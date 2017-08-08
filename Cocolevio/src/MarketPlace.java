// Mahmood Alam. 8/6/17 - 8/8/17. Cocolevio Programming Challenge:

import java.util.*;

public class MarketPlace {

	public static void main(String[] args) {
		// Declare givens (assuming companies listed in alphabetical order).
		int[] Amount = {1,2,5,6,7};
		int[] Price = {1,6,18,22,28};
		//int[] Amount = {1,2,3,4,5,6,7,8,9,10};
		//int[] Price = {1,5,8,13,17,20,22,22,24,28};
		// Input material capacity.
		Scanner s = new Scanner(System.in);
		int capacity = inputCapacity(s);
		// Determine max profit and display which companies to sell to.
		maxProfit(capacity, Amount, Price);
		// Repeat 
		main(args);
	}
	
	// Waiting for positive integer input for material capacity.
	public static int inputCapacity(Scanner s){
		int capacity = 0;
		boolean check = false;
		while(!check){
			System.out.println("--------------------------------");
			System.out.print("How many are you selling? ");
			while(!s.hasNextInt()){
				System.out.println("Invalid input.");
				System.out.println("Please input a positive integer.");
				System.out.println("--------------------------------");
				System.out.print("How many are you selling? ");
				s.next();
			}
			capacity = s.nextInt();
			if(capacity>0){
				check = true;
			} else{
				System.out.println("Invalid input.");
				System.out.println("Please input a positive integer.");
			}
		}
		return capacity;
	}
	
	// Determine and display both max profit and which companies to sell to.
	public static void maxProfit(int capacity, int[] Amount, int[] Price){
		int requestNum = Amount.length;
		// Table method for knapsack problem. 
		// Keep track of max profits as we build up from 0 capacity to full capacity.
		int[][] Table = new int[requestNum+1][capacity+1];
		for(int i=0; i<requestNum+1; i++){
			for(int j=0; j<capacity+1; j++){
				// Buffer of zeros for 0 requests and 0 capacity.
				if(i==0 | j==0){
					Table[i][j] = 0; 
				} 
				// Disregard if amount > current capacity.
				else if(Amount[i-1]>j){
					Table[i][j] = Table[i-1][j]; 
				} 
				// Determine if previous profit or new profit is greater.
				else{
					Table[i][j] = Math.max(Table[i-1][j], Price[i-1] + Table[i-1][j- Amount[i-1]]);
				}
			}
		}
		
		// Determine from table which companies to sell to.
		int[] SellTo = new int[requestNum];
		int dif = 0;
		for(int i=requestNum; i>=0; i--){
				if(Table[i][capacity-dif]!=0){
					if(Table[i][capacity-dif]!=Table[i-1][capacity-dif]){
						SellTo[i-1] = 1;
						dif+=Amount[i-1];
					}
				}
		}
		
		// Display which companies to sell to.
		char[] companies = {'A','B','C','D','E','F','G','H','I','J','K','L','M',
				'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		for(int i=0; i<requestNum; i++){
			if(SellTo[i]==1){
				System.out.println();
				System.out.println("Company "+companies[i]+":");
				System.out.println(" Amount: "+Amount[i]);
				System.out.println(" Price: "+Price[i]);
			}
		}
		
		// Display max profit.
		int maxProfit = Table[requestNum][capacity];
		System.out.println();
		System.out.println("Max Profit: " +maxProfit);
		
		/* Display table (for debugging purposes).
		for(int i=0; i<requestNum+1; i++){
			for(int j=0; j<capacity+1; j++){
				System.out.print(Table[i][j] + " ");
			}
			System.out.println();
		} */
	}
	
}
