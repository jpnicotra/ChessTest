package com.jpn.chesstest.domain;

import com.jpn.chesstest.domain.pieces.King;
import com.jpn.chesstest.domain.pieces.Piece;

/**
* Abstract Side class that represents possible Game Sides
* 
* @author      Juan Pablo Nicotra
* @since       1.0
*/
public abstract class Side {

	private java.util.List<Piece> pieces;
	private Game game;
	private King king; 
	
	/**
	* Default constructor. Set initial value for pieces
	*/
	public Side() {
		pieces = new java.util.LinkedList<Piece>();
	}

	/**
	* Return current game
	* 
	* @see Game
	* @return Current game
	*/
	public Game getGame() {
		return game;
	}

	/**
	* Set current game
	* 
	* @param game Current Game
	* @see Game
	*/
	public void setGame(Game game) {
		this.game = game;
	}

	public abstract boolean isWhite();

	/**
	* Override equals
	* 
	* @see Game
	* @param obj Object to compare
	* @return true if object is instance of Side and they are from the same side
	*/
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Side)
		return this.isWhite() == ((Side)obj).isWhite();
		
		return false;
	}

	/**
	* Return King Piece
	* 
	* @see King
	* @return King from this side
	*/
	public King getKing() {
		return king;
	}

	/**
	* Set King piece
	* @param king King piece
	*/
	public void setKing(King king) {
		this.king = king;
	}

	/**
	* Return list of pieces of this side
	* 
	* @see Piece
	* @return List of pieces
	*/
	public java.util.List<Piece> getPieces() {
		return pieces;
	}	
}
