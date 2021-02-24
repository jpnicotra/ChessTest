package com.jpn.chesstest.domain;

import com.jpn.chesstest.domain.pieces.Piece;

public class CellBoard {
	private CellType cellType;
	private Position position;
	private Piece piece;
	
	public CellBoard (CellType cellType, Position position) {
		this.cellType = cellType;
		this.position = position;
	}
	public enum CellType {
		WHITE, BLACK;
	}
	public CellType getCellType() {
		return cellType;
	}
	public void setCellType(CellType cellType) {
		this.cellType = cellType;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Piece getPiece() {
		return piece;
	}
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	@Override
	public String toString() {
		String cell="";
		
		if (cellType == CellType.BLACK)
			cell = "B";
		else
			cell = "W";
		
		if (this.piece==null)
			cell+="(  )";
		else
			cell+="("+piece.toString()+")";
		return cell;
	}
}
