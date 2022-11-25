package dungoen;

import java.util.Scanner;
import java.util.TreeMap;

public class Pits {
	public static int stepCount=Integer.MAX_VALUE;
	public static StringBuilder minPath=new StringBuilder();
	public static TreeMap<Integer,String> pathMap=new TreeMap<>();
	
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter row and column:");
		int row=scanner.nextInt();
		int col=scanner.nextInt();
		int visited[][]=new int[row][col];
		
		
		System.out.println("Enter adventure position (row and column):");
		int advRow=scanner.nextInt();
		int advCol=scanner.nextInt();
		advRow--;
		advCol--;
		
		System.out.println("Enter Gold position (row and column):");
		int goldRow=scanner.nextInt();
		int goldCol=scanner.nextInt();
		goldRow--;
		goldCol--;
		
		System.out.println("Enter number of pits : ");
		int pitsCount=scanner.nextInt();

		for(int index=0;index < pitsCount;index++) {
			int r=scanner.nextInt();
			int c=scanner.nextInt();
			visited[r-1][c-1]=2;
		}
		
		
		findMinimumPossiblePaths(visited,advRow,advCol,goldRow,goldCol,0,"");
		int advSteps =pathMap.size() >0 ? pathMap.firstKey():0;
		pathMap.clear();
		if(advSteps>0) {
			System.out.println("Minimum adventurer steps : "+advSteps);
		}
		else {
			System.out.println("No possible paths");
		}
		
		
	}
	
	
	public static void findMinimumPossiblePaths(int[][] visited, int row, int col, int targetRow, int targetCol,
			int count, String path) {
		if (row == targetRow && col == targetCol) {
			pathMap.put(count, path);
			count =0;
			path="";
			return;
		}
		
		if(row < 0 || col < 0 || row > visited.length-1 || col > visited[0].length-1 || visited[row][col]==1 || visited[row][col]==2) {
			return;
		}
		
		visited[row][col]=1;
		
		if(col < visited[0].length-1) {
			findMinimumPossiblePaths(visited,row,col+1,targetRow,targetCol,count+1,path+"R");
		}
		
		if(row < visited.length-1) {
			findMinimumPossiblePaths(visited,row+1,col,targetRow,targetCol,count+1,path+"D");
		}
		
		if(col > 0) {
			findMinimumPossiblePaths(visited,row,col-1,targetRow,targetCol,count+1,path+"L");
		}
		
		if(row > 0) {
			findMinimumPossiblePaths(visited,row-1,col,targetRow,targetCol,count+1,path+"U");
		}
		
		visited[row][col]=0;
		return ;
	}

}
