package com.jpn.chesstest.domain;

/**
* Represents the chess board
* 
* @see CellBoard
* @author      Juan Pablo Nicotra
* @since       1.0
*/
public abstract class Board {

	/**
	* Get cellBoard of a position
	*
	* @see CellBoard
	* @param position Position of the cell we need
	* @return CellBoard for the requested position or null if it's out of boundary
	*/
	public abstract CellBoard getCell (Position position);
	
	/**
	* Initialize board with players pieces
	*
	* @see Player
	* @param players List of players
	*/
	public abstract void newGame (java.util.List<Player> players);

}
