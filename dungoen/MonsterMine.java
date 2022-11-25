package dungoen;

import java.util.Scanner;
import java.util.TreeMap;

public class MonsterMine {
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
		
		System.out.println("Enter monster position (row and column):");
		int monsterRow=scanner.nextInt();
		int monsterCol=scanner.nextInt();
		monsterRow--;
		monsterCol--;
		
		findMinimumPossiblePaths(visited,advRow,advCol,goldRow,goldCol,0,"");
		int advSteps =pathMap.firstKey();
		pathMap.clear();
		
		findMinimumPossiblePaths(visited,monsterRow,monsterCol,goldRow,goldCol,0,"");
		int monsterSteps =pathMap.firstKey();
		pathMap.clear();
		
		System.out.println("Adventure min path : "+advSteps);
		System.out.println("Monster min path : "+monsterSteps);
		
		if(advSteps <= monsterSteps) {
			System.out.print("("+(advRow+1)+","+(advCol+1)+")");
			for(int index=0;index<advSteps;index++) {
				if(advCol < goldCol) {
					advCol++;
				}
				else if(advRow < goldRow) {
					advRow++;
				}
				else if(advCol > goldCol) {
					advCol--;
				}
				else if(advRow > goldRow) {
					advRow--;
				}
			System.out.print("->");
			System.out.print("("+(advRow+1)+","+(advCol+1)+")");
			}
			
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
		
		if(row < 0 || col < 0 || row > visited.length-1 || col > visited[0].length-1 || visited[row][col]==1) {
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
