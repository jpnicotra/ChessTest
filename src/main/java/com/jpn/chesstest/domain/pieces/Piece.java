package com.jpn.chesstest.domain.pieces;

import com.jpn.chesstest.domain.CellBoard;
import com.jpn.chesstest.domain.Position;
import com.jpn.chesstest.domain.Side;

public abstract class Piece {
	private PieceType pieceType;
	private CellBoard currentPosition;
	private Side side;
	private String charCode;
	
	public Piece () {
	}
	
	public Piece (PieceType pieceType, Side side, CellBoard currentPosition) {
		this.setSide(side);
		side.getPieces().add(this);
		this.setPieceType(pieceType);
		this.setCurrentPosition(currentPosition);
	}

	public PieceType getPieceType() {
		return pieceType;
	}
	public void setPieceType(PieceType pieceType) {
		this.pieceType = pieceType;
	}
	public CellBoard getCurrentPosition() {
		return currentPosition;
	}
	public void setCurrentPosition(CellBoard currentPosition) {
		this.currentPosition = currentPosition;
		this.currentPosition.setPiece (this);
	}
	public Side getSide() {
		return side;
	}
	public void setSide(Side side) {
		this.side = side;
	}

	public String getCharCode() {
		return charCode;
	}

	public void setCharCode(String charCode) {
		this.charCode = charCode;
	} 
	
	public boolean checkMoveIsValid(Position to) {
		if (to == null || to.getRow() < 0 || to.getCol() < 0 || to.getCol() > 8 || to.getRow() > 8)
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return this.getSide()+getCharCode();
	}
}
