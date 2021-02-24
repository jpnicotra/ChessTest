package com.jpn.chesstest.domain.pieces;

import com.jpn.chesstest.domain.CellBoard;
import com.jpn.chesstest.domain.Game;
import com.jpn.chesstest.domain.Position;
import com.jpn.chesstest.domain.Side;
import com.jpn.chesstest.domain.pieces.util.CheckMovesUtil;

public class Rook extends Piece {

	public Rook (Side side, CellBoard currentPosition) {
		super(PieceType.ROOK, side, currentPosition);
		if (getSide().isWhite())
			setCharCode("\u2656");
		else
			setCharCode("\u265C");
	}

	public boolean checkMoveIsValid(Position to) {
		if (super.checkMoveIsValid(to)) {
			Game game = this.getSide().getGame();
			Position actual = getCurrentPosition().getPosition();
			return CheckMovesUtil.checkStraightMovement(game, getSide(), actual, to);
		}
		return false;
	}
}
