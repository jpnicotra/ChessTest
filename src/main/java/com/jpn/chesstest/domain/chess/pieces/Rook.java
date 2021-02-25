package com.jpn.chesstest.domain.chess.pieces;

import com.jpn.chesstest.domain.BoardGame;
import com.jpn.chesstest.domain.CellBoard;
import com.jpn.chesstest.domain.Position;
import com.jpn.chesstest.domain.Side;
import com.jpn.chesstest.domain.chess.ChessSide;
import com.jpn.chesstest.domain.chess.pieces.util.CheckMovesUtil;

/**
 * Rook piece 
 * 
 * @author jnicotra
 * @since 1.0
 */
public class Rook extends Piece {

	/**
	 * Default constructor
	 * @see Side
	 * @see CellBoard
	 * @param side
	 * @param currentPosition
	 */
	public Rook (ChessSide side, CellBoard currentPosition) {
		super(PieceType.ROOK, side, currentPosition);
		if (side.isWhite())
			setCharCode("\u2656");
		else
			setCharCode("\u265C");
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
			return CheckMovesUtil.checkStraightMovement(game, getSide(), actual, to);
		}
		return false;
	}
}
