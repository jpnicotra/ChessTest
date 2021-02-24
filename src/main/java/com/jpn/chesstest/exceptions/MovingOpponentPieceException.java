package com.jpn.chesstest.exceptions;

import com.jpn.chesstest.domain.Move;
import com.jpn.chesstest.domain.Player;

public class MovingOpponentPieceException extends ChessTestException {

	private static final long serialVersionUID = -3481684720496474776L;

	public MovingOpponentPieceException (Player player, Move move) {
		super (player, move);
	}

	@Override
	public String getMessage() {
		return getPlayer().getSide()+" - You can't move a piece of another player! Cell " + getMove().getPositionFrom();
	}
	
	
}
