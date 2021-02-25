package com.jpn.chesstest.domain;

import com.jpn.chesstest.domain.chess.pieces.Piece;

/**
* Represents one cell of the chessboard
* 
* @see CellBoard
* @author      Juan Pablo Nicotra
* @since       1.0
*/
public class CellBoard {
	public enum CellType {
		WHITE, BLACK;
	}
	private CellType cellType;
	private Position position;
	private Piece piece;
	
	/**
	* Default constructor
	* 
	* @see CellType
	* @see Position
	* @param cellType Dark or Light
	* @param position Position of this cell
	*/
	public CellBoard (CellType cellType, Position position) {
		this.cellType = cellType;
		this.position = position;
	}

	/**
	* Current CellType
	* 
	* @see CellType
	* @return current CellType
	*/
	public CellType getCellType() {
		return cellType;
	}

	/**
	* Current Position
	* 
	* @see Position
	* @return current Position
	*/
	public Position getPosition() {
		return position;
	}

	/**
	* Current Piece
	* 
	* @see Piece
	* @return current Piece
	*/
	public Piece getPiece() {
		return piece;
	}

	/**
	* Sets current Piece on cell
	* 
	* @see Piece
	* @param piece New Piece
	*/
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	/**
	 * Returns a character string representation of this class
	 * @return	String representation
	 */
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
