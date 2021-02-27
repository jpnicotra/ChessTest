package com.jpn.chesstest.domain.chess;

import com.jpn.chesstest.domain.Board;
import com.jpn.chesstest.domain.CellBoard;
import com.jpn.chesstest.domain.Player;
import com.jpn.chesstest.domain.Position;
import com.jpn.chesstest.domain.Side;
import com.jpn.chesstest.domain.CellBoard.CellType;
import com.jpn.chesstest.domain.chess.pieces.Bishop;
import com.jpn.chesstest.domain.chess.pieces.King;
import com.jpn.chesstest.domain.chess.pieces.Knight;
import com.jpn.chesstest.domain.chess.pieces.Pawn;
import com.jpn.chesstest.domain.chess.pieces.Queen;
import com.jpn.chesstest.domain.chess.pieces.Rook;

/**
* Represents the chess board
* 
* @see CellBoard
* @author      Juan Pablo Nicotra
* @since       1.0
*/
public class Chessboard extends Board {
	private static final int rows = 8;
	private static final int cols = 8;
	private CellBoard cells[][] = new CellBoard[rows][cols];
	
	/**
	* Get cell from specific position
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
			ChessSide side = (ChessSide)players.get(i).getSide();

			int row = ((ChessPlayer)player).getStartRow();
			
			new Rook   (side, cells[row][0]);
			new Knight (side, cells[row][1]);
			new Bishop (side, cells[row][2]);
			new Queen  (side, cells[row][3]);
			new King   (side, cells[row][4]);
			new Bishop (side, cells[row][5]);
			new Knight (side, cells[row][6]);
			new Rook   (side, cells[row][7]);
			
			row = row + ((ChessPlayer)player).getDirection();
			for (int pawnI=0;pawnI<cols;pawnI++)
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
		for (int col=0;col<cols;col++) {
			print+=col+"\t";				
		}
		print+="\r\n";
		for (int row=0;row<rows;row++) {
			print+=row+"\t";				
			for (int col=0;col<cols;col++) {
				print+=cells[row][col].toString()+"\t";				
			}
			print+="\r\n";
		}
		return print;
	}

	public CellBoard[][] getCells() {
		return cells;
	}

	public void setCells(CellBoard[][] cells) {
		this.cells = cells;
	}

	public static int getRows() {
		return rows;
	}

	public static int getCols() {
		return cols;
	}
	

}
