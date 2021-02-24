package com.jpn.chesstest.input;

import java.io.IOException;

import com.whitehatgaming.UserInput;

public class StringUserInput implements UserInput {
	private String[] lines;
	private int index;
	
	public StringUserInput (String moves) {
		if (moves!=null) {
			lines = moves.split(System.getProperty("line.separator"));
			
			if (lines!=null && lines.length>0)
				index = 0;
			else
				index = -1;
		}
	}
	
	@Override
	public int[] nextMove() throws IOException {
		if (index==-1 || lines.length<=index)
			throw new IOException ("No more moves to read");
		
		String line = lines[index];
		
		if (line.length()==4) {
			int moves[] = new int [4];
			int init= (int) 'a';
			moves[1] = 8-Integer.parseInt(""+line.charAt(1));
			moves[0] = (int) line.charAt(0)-init;
			moves[3] = 8-Integer.parseInt(""+line.charAt(3));
			moves[2] = (int) line.charAt(2)-init;
			index+=1;
			return moves; 
		}
		else {
			throw new IOException ("Move does not have four characters (Example: e2e4)");
		}
	}

}
