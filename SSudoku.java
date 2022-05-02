package sudokusolver;

import java.util.*;
import java.io.*;

public class SSudoku {
	public static boolean helper(int [][]m, int r, int c) {
//		for(int i=0;i<9;i++) {
//			for(int j=0;j<9;j++) {
//				if(m[i][j]==0) {
//					for(int k=1;k<=9;k++) {
//						if(valid(m,i,j,k)) {
//							m[i][j]=k;
//							if(helper(m))
//							  return true;
//							else
//							  m[i][j]=0;
//						}
//					}
//					return false;
//				}
//			}
//		}
//		return true;
		if(r>=m.length)
			return true;
		int nrow=0;
		int ncol=0;
		if(c==m.length-1){
			nrow=r+1;
			ncol=0;
		}else {
			nrow=r;
			ncol=c+1;
		}
		if(m[r][c]!=0) {
			if(helper(m,nrow,ncol))
				return true;
		}else {
			for(int i=1;i<=9;i++) {
				if(valid(m,r,c,i)) {
					m[r][c]=i;
				   if(helper(m,nrow,ncol))
					   return true;
				   else
					  m[r][c]=0; 
				}
			}
		}
		return false;
	}
	
	public static boolean valid(int [][]m, int r, int c, int num) {
		for(int i=0;i<9;i++) {
			if(m[i][c]==num)
				return false;
		}
		for(int i=0;i<9;i++) {
			if(m[r][i]==num)
				return false;
		}
		int sr=3*(r/3);
		int sc=3*(c/3);
		for(int i=sr;i<sr+3;i++) {
			for(int j=sc;j<sc+3;j++) {
				if(m[i][j]==num)
					return false;
			}
		}
		return true;
	}
	
	public static boolean sudokuisvalid(int [][]m) {
	  Set <String> set= new HashSet <>();
	  for(int i=0;i<9;i++) {
		  for(int j=0;j<9;j++) {
			  if(m[i][j]!=0)
				  if(!set.add(m[i][j]+"in row"+i)||!set.add(m[i][j]+"in column"+j)||!set.add(m[i][j]+"in box"+i/3+" "+j/3))
					  return false;
		  }
	  }
		return true;
	}
	public static void main(String []args) {
		Scanner obj = new Scanner(System.in);
		int [][]m= new int [9][9];
		System.out.println("Enter 9*9 sudoku to be solved");
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				m[i][j]=obj.nextInt();
		obj.close();
		
	  if(sudokuisvalid(m)){	
	   if(helper(m,0,0)) {
		System.out.println("Solved 9*9 sudoku matrix");
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++)
				System.out.print(m[i][j]+" ");
			System.out.println();
		 }
	    }else {
		  System.out.println("Sudoku cannot be solved");
	    }
	  }else {
		  System.out.println("Sudoku is not valid");  
	  }
  }
}
