package com.jpn.chesstest.domain;

import com.jpn.chesstest.domain.chess.pieces.Piece;
import com.jpn.chesstest.exceptions.ChessTestException;

/**
* BoardGame is the class that represents and contains information about:
* <ul>
* <li>Board</li>
* <li>Players</li>
* <li>Game status (current player, piece in check, etc)</li>
* </ul>
* Also allow us to execute next move
* 
* @author      Juan Pablo Nicotra
* @since       1.0
*/
public abstract class BoardGame {
	/**
	 * Board representing this game
	 * @See com.jpn.chesstest.domain.Board
	 */
	private Board board;
	/**
	 * List of players involve in this game
	 * @See com.jpn.chesstest.domain.Player
	 */
	private java.util.List<Player> players;
	/**
	 * Player that has to do the next move
	 * @See com.jpn.chesstest.domain.Player
	 */
	private Player currentPlayer;

	/**
	 * Default constructor
	 */
	public BoardGame() {
	}

	/**
	 * This method is called when you want to start a fresh game. This will put both players and
	 * restart board and pieces for a fresh start.
	 */
	public abstract void newGame();

	/**
	 * Method called to move currentPlayer piece from one position to another
	 * @param move	this parameter indicates the starting and ending position
	 * @see com.jpn.chesstest.domain.Move
	 * @return Returns piece if it's in check position, otherwise null 
	 * @throws ChessTestException	if this movement it's ilegal (i.e. moving opponent piece), this method will throw ChessTestException including the player and the move involved
	 */
	public abstract Piece doMove(Move move) throws ChessTestException;	

	public void changeToNextPlayer() {
		currentPlayer = getNextPlayer();
	}

	public Player getNextPlayer() {
		int index = players.indexOf(currentPlayer);
		index += 1;

		if (index >= players.size())
			index = 0;
		
		return players.get(index);
	}

	/**
	 * Returns a character string representation of the game
	 * @return	String representation of the board with all pieces involved.
	 */
	@Override
	public String toString() {
		String print = "";
		print = board.toString();

		return print;
	}

	/**
	 * Returns current board
	 * @see Board
	 * @return	Board object for this game
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Method for obtaining current list of players
	 * @see Player
	 * @return	List of players
	 */
	public java.util.List<Player> getPlayers() {
		return players;
	}

	/**
	 * Method for obtaining current player
	 * @see Player
	 * @return	Current Player
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	/**
	 * Check and verifies if opponent it's in check!
	 * @see Piece
	 * @return	Returns piece in check otherwise null
	 */
	public abstract Piece getPieceInCheck(Player actualPlayer);

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setPlayers(java.util.List<Player> players) {
		this.players = players;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}



}
