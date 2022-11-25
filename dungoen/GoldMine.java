package dungoen;

import java.util.Arrays;
import java.util.Scanner;

public class GoldMine {
	public static int stepCount=Integer.MAX_VALUE;
	public static String stepPath="";
	
	
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		System.out.print("Enter row");
		int row = scanner.nextInt();
		System.out.print("Enter column");
		int col = scanner.nextInt();

		String board[][]=new String[row][col];
		boolean visited[][]=new boolean[row][col];
		
		System.out.println("Enter adventurer position:");
		int advRow=scanner.nextInt();
		int advCol=scanner.nextInt();
		
		System.out.println("Enter gold position");
		int goldRow=scanner.nextInt();
		int goldCol=scanner.nextInt();
		
		board[advRow-1][advCol-1]="A";
		board[goldRow-1][goldCol-1]="G";
		
		visited[advRow-1][advCol-1]= true;
		visited[goldRow-1][goldCol-1]=true;
		
//		System.out.print(Arrays.deepToString(board));
//		System.out.println();
//		System.out.print(Arrays.deepToString(visited));
		
		findGold(board,visited,advRow-1,advCol-1,goldRow-1,goldCol-1,0,"");
		System.out.println("Minimum path count : "+stepCount);
		System.out.println("Minimum Path : "+stepPath);

	}
	
	
	public static void findGold(String[][] board,boolean[][] visited,int row,int col,int targetRow,int targetCol,int count,String path) {
		if(targetRow == row && targetCol == col) {
			if(stepCount > count) {
				stepCount = count;
				stepPath=path;
			}
			count =0;
			path="";
			//System.out.println(stepPath+" - "+stepCount);
			return;
		}
		
		if(row < 0 || col < 0 || row > board.length-1 || col > board[0].length-1 || (visited[row][col] && board[row][col]==null) ||(visited[row][col] && board[row][col]=="A" && count > 0) ) {
			return;
		}
		
		visited[row][col]=true;
		
		if(col==targetCol && row > targetRow) {
			findGold(board,visited,row-1,col,targetRow,targetCol,count+1,path+"U");
		}
		
		if(col==targetCol && row <targetRow) {
			findGold(board,visited,row+1,col,targetRow,targetCol,count+1,path+"D");
		}
		
		if(col < board[0].length-1) {
			findGold(board,visited,row,col+1,targetRow,targetCol,count+1,path+"R");
		}
		if(row < board.length-1) {
			findGold(board,visited,row+1,col,targetRow,targetCol,count+1,path+"D");
		}
		if(col > 0) {
			findGold(board,visited,row,col-1,targetRow,targetCol,count+1,path+"L");
		}
		if(row >0) {
			findGold(board,visited,row-1,col,targetRow,targetCol,count+1,path+"U");
		}
		
		visited[row][col]=false;
		
		
	}

}
