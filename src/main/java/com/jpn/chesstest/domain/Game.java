package com.jpn.chesstest.domain;

import com.jpn.chesstest.domain.pieces.Piece;
import com.jpn.chesstest.exceptions.AnotherPieceInCellException;
import com.jpn.chesstest.exceptions.MovingOpponentPieceException;
import com.jpn.chesstest.exceptions.NoPieceInCellException;
import com.jpn.chesstest.exceptions.InvalidMovementException;

public class Game {
	private Board board;
	private java.util.List<Player> players;
	private Player currentPlayer;

	public Game() {
	}

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

	public Piece doMove(Move move) throws Exception {
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

	@Override
	public String toString() {
		String print = "";
		print = board.toString();

		return print;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public java.util.List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(java.util.List<Player> players) {
		this.players = players;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public Piece getPieceInCheck() {
		for (int i=0;i<this.getPlayers().size();i++) {
			Player player = getPlayers().get(i);
			if (!player.equals(currentPlayer)) {
				Side otherSide = player.getSide();
				Piece endingPiece = otherSide.getKing();
				
				java.util.List<Piece> pieces = currentPlayer.getSide().getPieces();
				
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
