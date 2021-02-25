package com.jpn.chesstest.domain.chess.pieces;

import com.jpn.chesstest.domain.CellBoard;
import com.jpn.chesstest.domain.Position;
import com.jpn.chesstest.domain.Side;
import com.jpn.chesstest.domain.chess.ChessSide;

/**
 * Knight piece 
 * 
 * @author jnicotra
 * @since 1.0
 */
public class Knight extends Piece {

	/**
	 * Default constructor
	 * @see Side
	 * @see CellBoard
	 * @param side
	 * @param currentPosition
	 */
	public Knight(ChessSide side, CellBoard currentPosition) {
		super(PieceType.KNIGHT, side, currentPosition);

		if (side.isWhite())
			setCharCode("\u2658");
		else
			setCharCode("\u265E");
	}

	/**
	 * Check if new position it's valid for this piece
	 * @param to New position
	 * @return true or false
	 */
	public boolean checkMoveIsValid(Position to) {
		if (super.checkMoveIsValid(to)) {
			Position actual = getCurrentPosition().getPosition();
			int diffRows = Math.abs(actual.getRow()-to.getRow());
			int diffCols = Math.abs(actual.getCol()-to.getCol());
			
			if ((diffRows == 1 && diffCols == 2) || (diffCols == 1 && diffRows == 2))
				return true;
			else
				return false;
		}
		return false;
	}
}
