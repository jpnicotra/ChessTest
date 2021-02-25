package com.jpn.chesstest.domain.chess.pieces;

import com.jpn.chesstest.domain.BoardGame;
import com.jpn.chesstest.domain.CellBoard;
import com.jpn.chesstest.domain.Position;
import com.jpn.chesstest.domain.Side;
import com.jpn.chesstest.domain.chess.ChessPlayer;
import com.jpn.chesstest.domain.chess.ChessSide;

/**
 * Pawn piece
 * 
 * @author jnicotra
 * @since 1.0
 */
public class Pawn extends Piece {
	private boolean firstMove = true;

	/**
	 * Default constructor
	 * 
	 * @see Side
	 * @see CellBoard
	 * @param side
	 * @param currentPosition
	 */
	public Pawn(ChessSide side, CellBoard currentPosition) {
		super(PieceType.PAWN, side, currentPosition);
		if (side.isWhite())
			setCharCode("\u2659");
		else
			setCharCode("\u265F");
	}

	public void setCurrentPosition(CellBoard currentPosition) {
		super.setCurrentPosition(currentPosition);
		this.firstMove = false;
	}

	/**
	 * Check if new position it's valid for this piece
	 * 
	 * @param to New position
	 * @return true or false
	 */
	public boolean checkMoveIsValid(Position to) {
		if (super.checkMoveIsValid(to)) {
			BoardGame game = this.getSide().getGame();
			Position actual = getCurrentPosition().getPosition();
			int diffRows = to.getRow() - actual.getRow();

			diffRows = diffRows * ((ChessPlayer) game.getCurrentPlayer()).getDirection();
			int diffCols = Math.abs(actual.getCol() - to.getCol());

			// if it's moving through two row cells
			if (diffRows == 2) {
				// it's not the first movement return false
				if (!isFirstMove())
					return false;
				else {
					CellBoard cell = game.getBoard().getCell(to);
					if (cell.getPiece() != null)
						return false;
					else
						return true;
				}
			}

			// if it's moving through more than one row cell, not moving or moving backwards
			// through rows return false
			if (diffRows > 1 || diffRows <= 0)
				return false;

			// if it's moving through more than one column return false
			if (diffCols > 1)
				return false;

			// Pawn it's moving diagonal
			if (diffCols == 1) {
				CellBoard cell = game.getBoard().getCell(to);

				if (cell.getPiece() != null && cell.getPiece().getSide().equals(getSide()))
					return false;
			}

			CellBoard cell = game.getBoard().getCell(to);
			if (cell.getPiece() != null)
				return false;
			
			return true;
		}
		return false;
	}

	public boolean isFirstMove() {
		return firstMove;
	}

	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}

}
