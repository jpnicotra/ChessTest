package com.jpn.chesstest.domain;

import com.jpn.chesstest.domain.pieces.King;
import com.jpn.chesstest.domain.pieces.Piece;

public abstract class Side {

	private java.util.List<Piece> pieces;
	private Game game;
	private King king; 
	
	public Side() {
		pieces = new java.util.LinkedList<Piece>();
	}
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public abstract boolean isWhite();

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Side)
		return this.isWhite() == ((Side)obj).isWhite();
		
		return false;
	}

	public King getKing() {
		return king;
	}

	public void setKing(King king) {
		this.king = king;
	}

	public java.util.List<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(java.util.List<Piece> pieces) {
		this.pieces = pieces;
	}

	
}
