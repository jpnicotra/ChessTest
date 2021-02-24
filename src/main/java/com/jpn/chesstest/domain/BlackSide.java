package com.jpn.chesstest.domain;

public class BlackSide extends Side {

	public boolean isWhite() {
		return false;
	}

	@Override
	public String toString() {
		return "Black Side";
	}

}
