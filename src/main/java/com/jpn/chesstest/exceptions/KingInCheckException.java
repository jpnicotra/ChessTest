package com.jpn.chesstest.exceptions;

import com.jpn.chesstest.domain.Move;
import com.jpn.chesstest.domain.Player;

/**
 * Exception thrown when player moves piece to cell and put his own king in check status 
 * @author jnicotra
 * @version 1.0
 * @see ChessTestException
 */
public class KingInCheckException extends ChessTestException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2222120058501232808L;

	/**
	 * Default constructor
	 * @param player Player that was involved in this exception
	 * @param move Movement that produced the exception
	 */
	public KingInCheckException (Player player, Move move) {
		super (player, move);
	}

	@Override
	public String getMessage() {
		return getPlayer().getSide()+" - You are putting your king in check if you move from " + getMove().getPositionFrom()+" to "+getMove().getPositionTo();
	}	
}
