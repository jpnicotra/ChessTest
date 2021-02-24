package com.jpn.chesstest.exceptions;

import com.jpn.chesstest.domain.Move;
import com.jpn.chesstest.domain.Player;

/**
 * Exception thrown when player tries to move a piece that doesn't exists in the indicated starting position 
 * @author jnicotra
 * @version 1.0
 * @see ChessTestException
 */
public class NoPieceInCellException extends ChessTestException {

	private static final long serialVersionUID = 1058095986251592094L;

	public NoPieceInCellException (Player player, Move move) {
		super (player, move);
	}

	@Override
	public String getMessage() {
		return getPlayer().getSide()+" - No piece in " + getMove().getPositionFrom() + " cell!";

	}	
}
