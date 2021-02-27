package com.jpn.chesstest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpn.chesstest.domain.chess.pieces.Piece;

/**
* Abstract Side class that represents possible Game Sides
* 
* @author      Juan Pablo Nicotra
* @since       1.0
*/
public abstract class Side {

	/**
	* Return current game
	* 
	* @see BoardGame
	* @return Current game
	*/
	public abstract BoardGame getGame();

	/**
	* Return list of pieces of this side
	* 
	* @see Piece
	* @return List of pieces
	*/
	public abstract java.util.List<Piece> getPieces();
}
