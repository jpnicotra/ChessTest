package com.jpn.chesstest.exceptions;

import com.jpn.chesstest.domain.Move;
import com.jpn.chesstest.domain.Player;

public abstract class ChessTestException extends Exception {

	private static final long serialVersionUID = 4487934398970646793L;
	private Player player;
	private Move move;
	
	public ChessTestException (Player player, Move move) {
		this.player = player;
		this.move = move;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Move getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = move;
	}

}
