package com.jpn.chesstest.domain.chess;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpn.chesstest.domain.BoardGame;
import com.jpn.chesstest.domain.Side;
import com.jpn.chesstest.domain.chess.pieces.King;
import com.jpn.chesstest.domain.chess.pieces.Piece;

/**
* Abstract Side class that represents possible Game Sides
* 
* @author      Juan Pablo Nicotra
* @since       1.0
*/
public abstract class ChessSide extends Side {

	@JsonIgnore
	private java.util.List<Piece> pieces;
	@JsonIgnore
	private BoardGame game;
	@JsonIgnore
	private King king; 
	
	/**
	* Default constructor. Set initial value for pieces
	*/
	public ChessSide() {
		pieces = new java.util.LinkedList<Piece>();
	}

	/**
	* Return current game
	* 
	* @see BoardGame
	* @return Current game
	*/
	public BoardGame getGame() {
		return game;
	}

	/**
	* Set current game
	* 
	* @param game Current Game
	* @see BoardGame
	*/
	public void setGame(BoardGame game) {
		this.game = game;
	}

	public abstract boolean isWhite();

	/**
	* Override equals
	* 
	* @see BoardGame
	* @param obj Object to compare
	* @return true if object is instance of Side and they are from the same side
	*/
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ChessSide)
		return this.isWhite() == ((ChessSide)obj).isWhite();
		
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
