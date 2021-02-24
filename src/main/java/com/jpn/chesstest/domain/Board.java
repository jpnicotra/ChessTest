package com.jpn.chesstest.domain;

import com.jpn.chesstest.domain.pieces.Bishop;
import com.jpn.chesstest.domain.pieces.King;
import com.jpn.chesstest.domain.pieces.Knight;
import com.jpn.chesstest.domain.pieces.Pawn;
import com.jpn.chesstest.domain.pieces.Queen;
import com.jpn.chesstest.domain.pieces.Rook;

/**
* Represents the chess board
* 
* @see CellBoard
* @author      Juan Pablo Nicotra
* @since       1.0
*/
public class Board {
	private static final int rows = 8;
	private static final int cols = 8;
	private CellBoard cells[][] = new CellBoard[rows][cols];
	
	/**
	* Represents the chess board
	*
	* @see CellBoard
	* @param position Position of the cell we need
	* @return CellBoard for the requested position or null if it's out of boundary
	*/
	public CellBoard getCell (Position position) {
		if (position!=null && position.getRow()<rows && position.getCol()<cols) {
			return cells[position.getRow()][position.getCol()];
		}
		
		return null;
	}
	
	/**
	* Initialize chess board with both players pieces
	*
	* @see Player
	* @param players List of players
	*/
	public void newGame (java.util.List<Player> players) {
		boolean cellBlack = true;
		for (int row=0;row<rows;row++) {
			for (int col=0;col<cols;col++) {
				Position position = new Position (row, col);
				CellBoard.CellType cellType;
				if (cellBlack) {
					cellType = CellBoard.CellType.BLACK;
				}
				else {
					cellType = CellBoard.CellType.WHITE;
				}
				cellBlack=!cellBlack;
				cells[row][col] = new CellBoard (cellType, position);
			}
			// At the end of each row, the following should start with the same color
			// that we use in prior cell. That's why I am setting cell to previous color
			cellBlack=!cellBlack;
		}
		
		// Initialize each player with the corresponding pieces
		for (int i=0;i<players.size();i++) {
			Player player = players.get(i);
			Side side = players.get(i).getSide();

			int row = player.getStartRow();
			
			new Rook   (side, cells[row][0]);
			new Knight (side, cells[row][1]);
			new Bishop (side, cells[row][2]);
			new Queen  (side, cells[row][3]);
			new King   (side, cells[row][4]);
			new Bishop (side, cells[row][5]);
			new Knight (side, cells[row][6]);
			new Rook   (side, cells[row][7]);
			
			row = row + player.getDirection();
			for (int pawnI=0;pawnI<8;pawnI++)
				new Pawn (side, cells[row][pawnI]);
		}
	}

	/**
	 * Returns a character string representation of this class
	 * @return	String representation
	 */
	@Override
	public String toString() {
		String print = "\t";
		for (int col=0;col<8;col++) {
			print+=col+"\t";				
		}
		print+="\r\n";
		for (int row=0;row<8;row++) {
			print+=row+"\t";				
			for (int col=0;col<8;col++) {
				print+=cells[row][col].toString()+"\t";				
			}
			print+="\r\n";
		}
		return print;
	}
	

}
