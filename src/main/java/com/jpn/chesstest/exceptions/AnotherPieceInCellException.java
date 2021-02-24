package com.jpn.chesstest.exceptions;

import com.jpn.chesstest.domain.Move;
import com.jpn.chesstest.domain.Player;

public class AnotherPieceInCellException extends ChessTestException {

	private static final long serialVersionUID = -872327693080763471L;

	public AnotherPieceInCellException (Player player, Move move) {
		super (player, move);
	}

	@Override
	public String getMessage() {
		return getPlayer().getSide()+" - You have another piece in cell " + getMove().getPositionTo();
	}	
}
