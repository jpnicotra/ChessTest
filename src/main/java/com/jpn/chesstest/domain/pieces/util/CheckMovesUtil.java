package com.jpn.chesstest.domain.pieces.util;

import com.jpn.chesstest.domain.CellBoard;
import com.jpn.chesstest.domain.Game;
import com.jpn.chesstest.domain.Position;
import com.jpn.chesstest.domain.Side;

/**
 * This class help checking movements validations
 * 
 * @author jnicotra
 * @since 1.0
 */
public class CheckMovesUtil {

	/**
	 * Check diagonal movement of this piece and also verifies if the way it's clear!
	 * @param game Game
	 * @param currentSide Current side
	 * @param actual Starting position
	 * @param to Ending position
	 * @return
	 */
	public static boolean checkDiagonalMovement(Game game, Side currentSide, Position actual, Position to) {
		int diffRows = actual.getRow()-to.getRow();
		int diffCols = actual.getCol()-to.getCol();
		
		// If Piece movement wasn't in diagonal return false
		if (Math.abs(diffRows) != Math.abs(diffCols))
			return false;
		
		Position pos = new Position(actual.getRow(), actual.getCol());
		// If movement was to a higher position we should move UP otherwise down
		int addValueRow = (diffRows>0 ? -1 : 1);
		int addValueCol = (diffCols>0 ? -1 : 1);
		for (int i=0;i<Math.abs(diffRows);i++) {
			pos.setRow(pos.getRow()+addValueRow);
			pos.setCol(pos.getCol()+addValueCol);
			CellBoard cell = game.getBoard().getCell(pos);

			// Another piece is in the way of this Piece and is not the last movement!!!
			if (cell.getPiece()!=null) {
				// It's the last cell
				if ((i+1)==Math.abs(diffRows)) {
					// The piece is from the same side
					if (cell.getPiece().getSide().equals(currentSide))
						return false;
				}
				else {
					return false;
				}
			}
		}
		return true;
	}


	/**
	 * Check straight movement of this piece and also verifies if the way it's clear!
	 * @param game Game
	 * @param currentSide Current side
	 * @param actual Starting position
	 * @param to Ending position
	 * @return
	 */
	public static boolean checkStraightMovement(Game game, Side currentSide, Position actual, Position to) {
		int diffRows = actual.getRow()-to.getRow();
		int diffCols = actual.getCol()-to.getCol();
		
		// If Piece movement wasn't int straight line return false
		if (diffRows!=0 && diffCols!=0)
			return false;

		// Check movement between rows
		if (diffRows!=0) {
			Position pos = new Position(actual.getRow(), actual.getCol());

			// If movement was to a higher position we should move UP otherwise down
			int addValue = (diffRows>0 ? 1 : -1);
			for (int i=to.getRow();i!=actual.getRow();i=i+addValue) {
				pos.setRow(i);
				CellBoard cell = game.getBoard().getCell(pos);
				
				// Another piece is in the way of this Rook!!!
				if (cell.getPiece()!=null) {
					if (i!=to.getRow()) {
						return false;
					}
					else {
						// Is the destination cell and the piece is from the other side
						if (cell.getPiece().getSide().equals(currentSide))
							return false;
					}
				}
			}
			return true;
		}

		// Check movement between cols
		if (diffCols!=0) {
			Position pos = new Position(actual.getRow(), actual.getCol());

			// If movement was to a higher position we should move RIGHT otherwise LEFT
			int addValue = (diffCols>0 ? 1 : -1);
			for (int i=to.getCol();i!=actual.getCol();i=i+addValue) {
				pos.setCol(i);
				CellBoard cell = game.getBoard().getCell(pos);
				
				// Another piece is in the way of this Rook!!!
				if (cell.getPiece()!=null) {
					if (i!=to.getCol()) {
						return false;
					}
					else {
						// Is the destination cell and the piece is from the other side
						if (cell.getPiece().getSide().equals(currentSide))
							return false;
					}
				}
			}
			return true;
		}

		return false;
	}
}
