package com.cinfotech.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Test {
	public static void main(String args[]) throws FileNotFoundException, IOException {
		System.out.print(DigestUtils.md5Hex(new FileInputStream("pom.xml")) );
	}
	
}
