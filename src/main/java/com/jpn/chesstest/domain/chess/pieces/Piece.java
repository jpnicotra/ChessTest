package com.jpn.chesstest.domain.chess.pieces;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpn.chesstest.domain.CellBoard;
import com.jpn.chesstest.domain.Position;
import com.jpn.chesstest.domain.Side;

/**
 * Represents one piece of the chess game!
 * @author Juan Pablo Nicotra
 * @see PieceType
 * @see CellBoard
 * @see Side
 */
public abstract class Piece {
	private PieceType pieceType;
	@JsonIgnore
	private CellBoard currentPosition;
	private Side side;
	private String charCode;

	/**
	 * Default constructor 
	 * @param pieceType Defines if it's King, Bishop, Pawn, etc
	 * @param side Black or White
	 * @param currentPosition CellBoard position
	 */
	public Piece (PieceType pieceType, Side side, CellBoard currentPosition) {
		this.side = side;
		side.getPieces().add(this);
		this.pieceType=pieceType;
		this.setCurrentPosition(currentPosition);
	}

	/**
	 * Current PieceType
	 * @return PieceType
	 */
	public PieceType getPieceType() {
		return pieceType;
	}

	/**
	 * Current CellBoard
	 * @return CellBoard
	 */
	public CellBoard getCurrentPosition() {
		return currentPosition;
	}
	
	/**
	 * Set new cell board for this piece. moves piece from prior position to the new position
	 * @param currentPosition in the board
	 */
	public void setCurrentPosition(CellBoard currentPosition) {
		// moves piece from prior position to the new position
		if (this.currentPosition!=null)
			this.currentPosition.setPiece(null);
		this.currentPosition = currentPosition;
		this.currentPosition.setPiece (this);
	}
	
	public void rollbackPosition (CellBoard currentPosition) {
		this.setCurrentPosition(currentPosition);
	}

	public Side getSide() {
		return side;
	}

	/**
	 * Returns character code representing this piece
	 * @return PieceType character code
	 */
	public String getCharCode() {
		return charCode;
	}

	/**
	 * Set character code representing this piece
	 * @param charCode character code
	 */
	public void setCharCode(String charCode) {
		this.charCode = charCode;
	} 

	/**
	 * This method check if the required movement it's inside boundaries
	 * @param to Ending position
	 * @return true or false
	 */
	public boolean checkMoveIsValid(Position to) {
		if (to == null || to.getRow() < 0 || to.getCol() < 0 || to.getCol() > 8 || to.getRow() > 8)
			return false;

		return true;
	}
	
	/**
	 * Returns a character string representation of this class
	 * @return	String representation
	 */
	@Override
	public String toString() {
		return this.getSide()+getCharCode();
	}

	public void setPieceType(PieceType pieceType) {
		this.pieceType = pieceType;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	
	public Piece getPromotionPiece() {
		return null;
	}
}
