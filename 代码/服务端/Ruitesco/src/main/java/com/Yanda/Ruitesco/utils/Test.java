package com.Yanda.Ruitesco.utils;

import java.io.File;

public class Test {
	public static void main(String[] args) {
		String path = "D:/软件工程/课程设计/git/Rising_Shopping/image/temp/0342134360b648d0859d11b46c21d6b1.jpg";
		String newPath = "D:/软件工程/课程设计/git/Rising_Shopping/image/drinks";
		try {
			File file = new File(path);
			if(file.renameTo(new File(newPath + file.getName()))) {
				System.out.println(0);
			}
			else
				System.out.println(1);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}