package com.jpn.chesstest.exceptions;

import com.jpn.chesstest.domain.Move;
import com.jpn.chesstest.domain.Player;

public class InvalidMovementException extends ChessTestException {

	private static final long serialVersionUID = 1810154746793344445L;

	public InvalidMovementException (Player player, Move move) {
		super (player, move);
	}

	@Override
	public String getMessage() {
		return getPlayer().getSide()+" - You can't move from " + getMove().getPositionFrom() + " to " + getMove().getPositionTo()
		+ ", that is an invalid move!";
	}
}
