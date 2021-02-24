package com.jpn.chesstest.domain;

/**
 * Represents position in the chessboard (row, column)
 * @author jnicotra
 * @since 1.0
 */
public class Position {
	private int row;
	private int col;
	
	/**
	 * Default constructor
	 * @param row
	 * @param col
	 */
	public Position (int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * Returns row
	 * @return row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Set row
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Return col
	 * @return col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Set column
	 * @param col column
	 */
	public void setCol(int col) {
		this.col = col;
	}
	
	/**
	 * Returns a character string representation of this class
	 * @return	String representation
	 */	
	@Override
	public String toString() {
		int init= (int) 'a';
		
		return ((char)(init+col))+""+(8-row);
	}

}
