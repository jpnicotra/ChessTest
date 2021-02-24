package com.jpn.chesstest.domain.pieces;

import com.jpn.chesstest.domain.CellBoard;
import com.jpn.chesstest.domain.Game;
import com.jpn.chesstest.domain.Position;
import com.jpn.chesstest.domain.Side;

public class Pawn extends Piece {
	private boolean firstMove=true;
	
	public Pawn (Side side, CellBoard currentPosition) {
		super(PieceType.PAWN, side, currentPosition);
		if (getSide().isWhite())
			setCharCode("\u2659");
		else
			setCharCode("\u265F");
	}

	public void setCurrentPosition(CellBoard currentPosition) {
		super.setCurrentPosition(currentPosition);
		this.firstMove = false;
	}

	public boolean checkMoveIsValid(Position to) {
		if (super.checkMoveIsValid(to)) {
			Game game = this.getSide().getGame();
			Position actual = getCurrentPosition().getPosition();
			int diffRows = to.getRow()-actual.getRow();
			
			diffRows = diffRows*game.getCurrentPlayer().getDirection();
			int diffCols = Math.abs(actual.getCol()-to.getCol());

			// if it's moving through two row cells
			if (diffRows==2) {
				 //  it's not the first movement return false
				 if (!isFirstMove())
					 return false;
				 else
					 return true;
			}
				
						
			// if it's moving through more than one row cell, not moving or moving backwards through rows return false
			if (diffRows>1 || diffRows<=0)
				return false;
			
			// if it's moving through more than one column return false
			if (diffCols > 1)
				return false;

			// Pawn it's moving diagonal 
			if (diffCols == 1) {
				CellBoard cell= game.getBoard().getCell(to);

				if (cell.getPiece()!=null && cell.getPiece().getSide().equals(getSide()))
					return false;
			}
			
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
