package com.jpn.chesstest.exceptions;

import com.jpn.chesstest.domain.Move;
import com.jpn.chesstest.domain.Player;

/**
 * Exception thrown when player tries an invalid movement for that piece. For example, trying to move trough other pieces, 
 * move diagonally a Rook piece, etc.
 * @author jnicotra
 * @version 1.0
 * @see ChessTestException
 */
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
