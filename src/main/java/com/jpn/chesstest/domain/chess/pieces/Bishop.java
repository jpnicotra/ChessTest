package com.jpn.chesstest.domain.chess.pieces;

import com.jpn.chesstest.domain.CellBoard;
import com.jpn.chesstest.domain.BoardGame;
import com.jpn.chesstest.domain.Position;
import com.jpn.chesstest.domain.Side;
import com.jpn.chesstest.domain.chess.ChessSide;
import com.jpn.chesstest.domain.chess.pieces.util.CheckMovesUtil;

/**
 * Bishop piece 
 * 
 * @author jnicotra
 * @since 1.0
 */
public class Bishop extends Piece {

	/**
	 * Default constructor
	 * @see Side
	 * @see CellBoard
	 * @param side
	 * @param currentPosition
	 */
	public Bishop (ChessSide side, CellBoard currentPosition) {
		super(PieceType.BISHOP, side, currentPosition);
		
		if (((ChessSide)getSide()).isWhite())
			setCharCode("\u2657");
		else
			setCharCode("\u265D");
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
			return CheckMovesUtil.checkDiagonalMovement(game, getSide(), actual, to);
		}
		return false;
	}

}
