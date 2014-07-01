package org.rootbeer.rbms.logic;

public class GetPostsOutOfBoundsException extends Exception {
	public GetPostsOutOfBoundsException() {
		super("範囲外の指定です。");
	}
}
