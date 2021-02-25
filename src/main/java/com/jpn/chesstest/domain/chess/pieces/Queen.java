package com.jpn.chesstest.domain.chess.pieces;

import com.jpn.chesstest.domain.BoardGame;
import com.jpn.chesstest.domain.CellBoard;
import com.jpn.chesstest.domain.Position;
import com.jpn.chesstest.domain.Side;
import com.jpn.chesstest.domain.chess.ChessSide;
import com.jpn.chesstest.domain.chess.pieces.util.CheckMovesUtil;

/**
 * Queen piece 
 * 
 * @author jnicotra
 * @since 1.0
 */
public class Queen extends Piece {

	/**
	 * Default constructor
	 * @see Side
	 * @see CellBoard
	 * @param side
	 * @param currentPosition
	 */
	public Queen (ChessSide side, CellBoard currentPosition) {
		super(PieceType.QUEEN, side, currentPosition);
		if (side.isWhite())
			setCharCode("\u2655");
		else
			setCharCode("\u265B");
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
			
			// Check if queen moved diagonal
			if (CheckMovesUtil.checkDiagonalMovement(game, getSide(), actual, to))
				return true;

			// Check if queen moved straight line
			if (CheckMovesUtil.checkStraightMovement(game, getSide(), actual, to))
				return true;
			
		}
		return false;
	}

}
