package com.jpn.chesstest.domain;

import org.springframework.stereotype.Service;

import com.jpn.chesstest.domain.pieces.Bishop;
import com.jpn.chesstest.domain.pieces.King;
import com.jpn.chesstest.domain.pieces.Knight;
import com.jpn.chesstest.domain.pieces.Pawn;
import com.jpn.chesstest.domain.pieces.Queen;
import com.jpn.chesstest.domain.pieces.Rook;

@Service
public class Board {
	private CellBoard cells[][] = new CellBoard[8][8];
	
	public CellBoard getCell (Position position) {
		// TODO Controlar las posiciones y si hay espacios.
		
		if (position!=null) {
			return cells[position.getRow()][position.getCol()];
		}
		
		return null;
	}
	
	public void newGame (java.util.List<Player> players) {
		boolean cellBlack = true;
		for (int row=0;row<8;row++) {
			for (int col=0;col<8;col++) {
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
			// At the end of each row the following starts with the same color that we use in prior cell. That's
			// why I am setting cell to previous color
			cellBlack=!cellBlack;
		}
		
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
