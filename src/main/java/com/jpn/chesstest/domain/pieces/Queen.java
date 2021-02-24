package com.jpn.chesstest.domain.pieces;

import com.jpn.chesstest.domain.CellBoard;
import com.jpn.chesstest.domain.Game;
import com.jpn.chesstest.domain.Position;
import com.jpn.chesstest.domain.Side;
import com.jpn.chesstest.domain.pieces.util.CheckMovesUtil;

public class Queen extends Piece {

	public Queen (Side side, CellBoard currentPosition) {
		super(PieceType.QUEEN, side, currentPosition);
		if (getSide().isWhite())
			setCharCode("\u2655");
		else
			setCharCode("\u265B");
	}

	public boolean checkMoveIsValid(Position to) {
		if (super.checkMoveIsValid(to)) {
			Game game = this.getSide().getGame();
			Position actual = getCurrentPosition().getPosition();
			
			// Check if queen moved diagonal
			if (CheckMovesUtil.checkDiagonalMovement(game, getSide(), actual, to))
				return true;

			// Check if queen moved straight line
			if (CheckMovesUtil.checkStraightMovement(game, getSide(), actual, to))
				return true;
			
		}
		return false;
	}

}
