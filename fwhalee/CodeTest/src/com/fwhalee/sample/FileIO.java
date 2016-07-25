package com.fwhalee.sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileIO {
	
	
	public static void main(String[] args) {
		
		
		// 파일 읽기
		BufferedReader br;
		try {
			
			String inPath = "file/in/fileio.in";
			br = new BufferedReader(new FileReader(inPath));
			
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				
				System.out.println(line);
			}
			
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		// 파일 쓰기
		PrintWriter pw;
		try {
			
			String outPath = "file/out/fileio.out";
			pw = new PrintWriter(outPath);
			
			for(int i = 0; i < 5; i++) {
				String data = i + " 번째 줄입니다.";
				pw.println(data);
			}
			
			pw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

}
