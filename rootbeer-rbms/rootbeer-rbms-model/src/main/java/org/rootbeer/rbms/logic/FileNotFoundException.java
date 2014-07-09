package org.rootbeer.rbms.logic;

public class FileNotFoundException extends Exception {
	public FileNotFoundException() {
		super("画像ファイルが指定されていません。");
	}
}
