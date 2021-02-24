package com.jpn.chesstest.domain;

public class Player {
	public static final int UP_DIRECTION = -1;
	public static final int DOWN_DIRECTION = 1;

	private Side side;
	private String name;
	private int startRow;
	private int direction;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	
}
