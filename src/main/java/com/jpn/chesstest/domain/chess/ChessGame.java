package com.jpn.chesstest.domain.chess;

import java.util.List;

import com.jpn.chesstest.domain.Board;
import com.jpn.chesstest.domain.BoardGame;
import com.jpn.chesstest.domain.CellBoard;
import com.jpn.chesstest.domain.Move;
import com.jpn.chesstest.domain.Player;
import com.jpn.chesstest.domain.chess.pieces.Piece;
import com.jpn.chesstest.exceptions.AnotherPieceInCellException;
import com.jpn.chesstest.exceptions.ChessTestException;
import com.jpn.chesstest.exceptions.InvalidMovementException;
import com.jpn.chesstest.exceptions.KingInCheckException;
import com.jpn.chesstest.exceptions.MovingOpponentPieceException;
import com.jpn.chesstest.exceptions.NoPieceInCellException;

/**
* ChessGame it's an specific implementation of a Chess game
* 
* @author      Juan Pablo Nicotra
* @since       1.0
*/
public class ChessGame extends BoardGame {

	/**
	 * This method is called when you want to start a fresh game. This will put both players and
	 * restart board and pieces for a fresh start.
	 */
	public void newGame() {
		List<Player> players = new java.util.LinkedList<Player>();
		ChessPlayer whitePlayer = new ChessPlayer();
		WhiteSide white = new WhiteSide();
		white.setGame(this);
		whitePlayer.setSide(white);
		whitePlayer.setName("White Player");
		whitePlayer.setStartRow(7);
		whitePlayer.setDirection(ChessPlayer.UP_DIRECTION);
		players.add(whitePlayer);

		ChessPlayer blackPlayer = new ChessPlayer();
		BlackSide black = new BlackSide();
		black.setGame(this);
		blackPlayer.setSide(black);
		blackPlayer.setName("Black Player");
		blackPlayer.setStartRow(0);
		blackPlayer.setDirection(ChessPlayer.DOWN_DIRECTION);
		players.add(blackPlayer);

		Board board = new Chessboard();
		board.newGame(players);
		Player currentPlayer = players.get(0);

		setPlayers(players);
		setBoard(board);
		setCurrentPlayer(currentPlayer);
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
		CellBoard cellFrom = getBoard().getCell(move.getPositionFrom());
		if (cellFrom.getPiece() == null)
			throw new NoPieceInCellException(getCurrentPlayer(), move);

		CellBoard cellTo = getBoard().getCell(move.getPositionTo());
		if (cellTo.getPiece() != null && cellTo.getPiece().getSide().equals(getCurrentPlayer().getSide()))
			throw new AnotherPieceInCellException(getCurrentPlayer(), move);

		Piece piece = cellFrom.getPiece();
		if (!piece.getSide().equals(getCurrentPlayer().getSide()))
			throw new MovingOpponentPieceException(getCurrentPlayer(), move);

		if (!piece.checkMoveIsValid(move.getPositionTo())) {
			throw new InvalidMovementException(getCurrentPlayer(), move);
		}
		
		piece.setCurrentPosition(cellTo);
		
		Player next = getNextPlayer();
		Piece yourPieceInCheck = getPieceInCheck(next);
		if (yourPieceInCheck !=null) {
			piece.setCurrentPosition(cellFrom);
			throw new KingInCheckException(getCurrentPlayer(), move);
		}
		
		Piece newPiece = piece.getPromotionPiece();
		
		if (newPiece!=null) {
			piece = newPiece;
		}

		piece.setCurrentPosition(cellTo);
		
		Piece pieceInCheck = getPieceInCheck(getCurrentPlayer());
		changeToNextPlayer();
		
		return pieceInCheck;
	}
	
	/**
	 * Returns a character string representation of the game
	 * @return	String representation of the board with all pieces involved.
	 */
	@Override
	public String toString() {
		String print = "";
		print = getBoard().toString();

		return print;
	}

	/**
	 * Check and verifies if King's opponent it's in check!
	 * @see Piece
	 * @return	Returns piece in check otherwise null
	 */
	public Piece getPieceInCheck(Player actualPlayer) {
		for (int i=0;i<this.getPlayers().size();i++) {
			Player player = getPlayers().get(i);
			
			// Check if this player its actual Player opponent
			if (!player.equals(actualPlayer)) {
				ChessSide otherSide = (ChessSide)player.getSide();

				// Get's opponent King
				Piece endingPiece = otherSide.getKing();
				
				java.util.List<Piece> pieces = actualPlayer.getSide().getPieces();
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
