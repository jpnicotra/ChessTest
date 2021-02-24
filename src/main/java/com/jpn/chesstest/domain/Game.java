package com.jpn.chesstest.domain;

import com.jpn.chesstest.domain.pieces.Piece;
import com.jpn.chesstest.exceptions.AnotherPieceInCellException;
import com.jpn.chesstest.exceptions.ChessTestException;
import com.jpn.chesstest.exceptions.InvalidMovementException;
import com.jpn.chesstest.exceptions.MovingOpponentPieceException;
import com.jpn.chesstest.exceptions.NoPieceInCellException;

/**
* Game is the class that represents and contains information about:
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
public class Game {
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
	public Game() {
	}

	/**
	 * This method is called when you want to start a fresh game. This will put both players and
	 * restart board and pieces for a fresh start.
	 */
	public void newGame() {
		players = new java.util.LinkedList<Player>();
		Player whitePlayer = new Player();
		WhiteSide white = new WhiteSide();
		white.setGame(this);
		whitePlayer.setSide(white);
		whitePlayer.setName("White Player");
		whitePlayer.setStartRow(7);
		whitePlayer.setDirection(Player.UP_DIRECTION);
		players.add(whitePlayer);

		Player blackPlayer = new Player();
		BlackSide black = new BlackSide();
		black.setGame(this);
		blackPlayer.setSide(black);
		blackPlayer.setName("Black Player");
		blackPlayer.setStartRow(0);
		blackPlayer.setDirection(Player.DOWN_DIRECTION);
		players.add(blackPlayer);

		board = new Board();
		board.newGame(players);
		currentPlayer = players.get(0);
	}

	/**
	 * Method called to move currentPlayer piece from one position to another
	 * @param move	this parameter indicates the starting and ending position
	 * @see com.jpn.chesstest.domain.Move
	 * @return Returns piece if it's in check position, otherwise null 
	 * @throws ChessTestException	if this movement it's ilegal (i.e. moving opponent piece), this method will throw ChessTestException including the player and the move involved
	 */
	public Piece doMove(Move move) throws ChessTestException {
		System.out.println("\tMove from " + move.getPositionFrom() + " to " + move.getPositionTo());
		CellBoard cellFrom = board.getCell(move.getPositionFrom());
		if (cellFrom.getPiece() == null)
			throw new NoPieceInCellException(currentPlayer, move);

		CellBoard cellTo = board.getCell(move.getPositionTo());
		if (cellTo.getPiece() != null && cellTo.getPiece().getSide().equals(currentPlayer.getSide()))
			throw new AnotherPieceInCellException(currentPlayer, move);

		Piece piece = cellFrom.getPiece();
		if (!piece.getSide().equals(currentPlayer.getSide()))
			throw new MovingOpponentPieceException(currentPlayer, move);

		if (!piece.checkMoveIsValid(move.getPositionTo())) {
			throw new InvalidMovementException(currentPlayer, move);
		}
		cellFrom.setPiece(null);
		piece.setCurrentPosition(cellTo);
		
		
		Piece pieceInCheck = getPieceInCheck();
		
		int index = players.indexOf(currentPlayer);
		index += 1;

		if (index >= players.size())
			index = 0;
		
		currentPlayer = players.get(index);
		
		return pieceInCheck;
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
	 * Check and verifies if King's opponent it's in check!
	 * @see Piece
	 * @return	Returns piece in check otherwise null
	 */
	public Piece getPieceInCheck() {
		for (int i=0;i<this.getPlayers().size();i++) {
			Player player = getPlayers().get(i);
			
			// Check if this player its current Player opponent
			if (!player.equals(currentPlayer)) {
				Side otherSide = player.getSide();

				// Get's opponent King
				Piece endingPiece = otherSide.getKing();
				
				java.util.List<Piece> pieces = currentPlayer.getSide().getPieces();
				// Simulates if it's possible to attack opponent King piece
				for (int pieceIndex=0;pieceIndex<pieces.size();pieceIndex++) {
					Piece piece = pieces.get(pieceIndex);
					if (piece.checkMoveIsValid(endingPiece.getCurrentPosition().getPosition()))
						return endingPiece;
				}
			}
		}
		
		return null;
	}

}
