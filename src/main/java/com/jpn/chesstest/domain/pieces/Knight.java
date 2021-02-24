package com.jpn.chesstest.domain.pieces;

import com.jpn.chesstest.domain.CellBoard;
import com.jpn.chesstest.domain.Position;
import com.jpn.chesstest.domain.Side;

public class Knight extends Piece {

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

	public Knight(Side side, CellBoard currentPosition) {
		super(PieceType.KNIGHT, side, currentPosition);

		if (getSide().isWhite())
			setCharCode("\u2658");
		else
			setCharCode("\u265E");
	}
}
