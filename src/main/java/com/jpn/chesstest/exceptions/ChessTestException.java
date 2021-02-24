package com.jpn.chesstest.exceptions;

import com.jpn.chesstest.domain.Move;
import com.jpn.chesstest.domain.Player;

/**
 * Abstract Chess exception to handle and specify all exceptions of this app
 * @author jnicotra
 * @since 1.0
 */
public abstract class ChessTestException extends Exception {

	private static final long serialVersionUID = 4487934398970646793L;
	private Player player;
	private Move move;
	
	/**
	 * Default constructor
	 * @param player Player that was involved in this exception
	 * @param move Movement that produced the exception
	 */
	public ChessTestException (Player player, Move move) {
		this.player = player;
		this.move = move;
	}
	
	/**
	 * Player involved in exception
	 * @return Player
	 */
	public Player getPlayer() {
		return player;
	}


	/**
	 * Move involved in exception
	 * @return Move
	 */
	public Move getMove() {
		return move;
	}

}
