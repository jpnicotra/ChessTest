package com.jpn.chesstest.domain.pieces;

import com.jpn.chesstest.domain.CellBoard;
import com.jpn.chesstest.domain.Game;
import com.jpn.chesstest.domain.Position;
import com.jpn.chesstest.domain.Side;
import com.jpn.chesstest.domain.pieces.util.CheckMovesUtil;

public class Bishop extends Piece {

	public Bishop (Side side, CellBoard currentPosition) {
		super(PieceType.BISHOP, side, currentPosition);
		
		if (getSide().isWhite())
			setCharCode("\u2657");
		else
			setCharCode("\u265D");
	}

	public boolean checkMoveIsValid(Position to) {
		if (super.checkMoveIsValid(to)) {
			Game game = this.getSide().getGame();
			Position actual = getCurrentPosition().getPosition();
			return CheckMovesUtil.checkDiagonalMovement(game, getSide(), actual, to);
		}
		return false;
	}
}
