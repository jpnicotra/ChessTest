package com.jpn.chesstest.domain.chess.pieces;

import com.jpn.chesstest.domain.CellBoard;
import com.jpn.chesstest.domain.BoardGame;
import com.jpn.chesstest.domain.Position;
import com.jpn.chesstest.domain.Side;
import com.jpn.chesstest.domain.chess.ChessSide;

/**
 * King piece 
 * 
 * @author jnicotra
 * @since 1.0
 */public class King extends Piece {

	/**
	 * Default constructor
	 * @see Side
	 * @see CellBoard
	 * @param side
	 * @param currentPosition
	 */
	public King (ChessSide side, CellBoard currentPosition) {
		super(PieceType.KING, side, currentPosition);
		if (side.isWhite())
			setCharCode("\u2654");
		else
			setCharCode("\u265A");
		
		side.setKing(this);
	}

	/**
	 * Check if new position it's valid for this piece
	 * @param to New position
	 * @return true or false
	 */
	public boolean checkMoveIsValid(Position to) {
		if (super.checkMoveIsValid(to)) {
			BoardGame game = this.getSide().getGame();
			Position actual = getCurrentPosition().getPosition();
			int diffRows = Math.abs(to.getRow()-actual.getRow());
			int diffCols = Math.abs(actual.getCol()-to.getCol());

			if (diffRows>1) 
				 return false;
			if (diffCols>1)
				return false;
				
			CellBoard cell= game.getBoard().getCell(to);

			if (cell.getPiece()!=null && cell.getPiece().getSide().equals(getSide()))
				return false;
			
			return true;
		}
		return false;
	}

}
