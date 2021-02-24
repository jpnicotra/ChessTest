package com.jpn.chesstest.exceptions;

import com.jpn.chesstest.domain.Move;
import com.jpn.chesstest.domain.Player;

/**
 * Exception thrown when player moves piece to cell where same player has another piece 
 * @author jnicotra
 * @version 1.0
 * @see ChessTestException
 */
public class AnotherPieceInCellException extends ChessTestException {

	private static final long serialVersionUID = -872327693080763471L;

	/**
	 * Default constructor
	 * @param player Player that was involved in this exception
	 * @param move Movement that produced the exception
	 */
	public AnotherPieceInCellException (Player player, Move move) {
		super (player, move);
	}

	@Override
	public String getMessage() {
		return getPlayer().getSide()+" - You have another piece in cell " + getMove().getPositionTo();
	}	
}
