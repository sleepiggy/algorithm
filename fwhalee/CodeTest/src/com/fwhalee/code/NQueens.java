package com.fwhalee.code;

public class NQueens {

	public static void main(String[] args) {
		
		
		// 세로 loop
		// 가로 loop
		
		// 퀸을 놓으면 놓지 못하는 경우 계산, 배열내 2로 변경
		// 자기 열부터 시작해서, 이후 놓지 못하는 경우 계산, 배열내 2로 변경
		
		// 8개까지 다 놓아졌을때, 배열 이력 저장?, count++만 해도 된다.
		// level이 8 > 9가 되었을 때 종료 및 count++
		
		// 검색시 남은 열중 하나라도 2로 모두 채워지면 그만
		
		getNQueens(8);
		
	}
	
	
	public static int getNQueens(int size) {
		
		int result = 0;
		
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
			
			// 체스판 초기화
			// 0: 아무것도 두지 않은 상태
			int[][] chessBoard = new int[size][size];
			
			// 놓기전 조건 검색
			if (chessBoard[i][j] != 2) {
				// 1: queen 놓은 상태
				chessBoard[i][j] = 1;
				
				// 체스판 불가능한 지역 표시
				setImpossiblePlace(chessBoard, size, i, j);
				
				// 나머지 열에 대한 검증
				boolean hasImpossibleRow = hasImpossibleRow(chessBoard, size, i);
				if (hasImpossibleRow)
					continue;
			}
			
			}
		}
		
		return result;
	}
	
	
	public static int[][] setImpossiblePlace(int[][] chessBoard, int size, int i, int j) {
		
		
		// 가로 표시
		for (int k = 0; k < size; k++) {
			chessBoard[i][k] = 2;
		}
		// 세로 표시
		for (int k = 0; k < size; k++) {
			chessBoard[k][j] = 2;
		}
		
		// 대각선 우상 표시
		// y = x + a
		int yIntercept = j - i;
		for (int k = 0; k < size; k++) {
			for (int l = 0; l < size; l++) {
				if ((l - k) == yIntercept) {
					chessBoard[k][l] = 2;
				}
			}
		}
		
		
		// 대각선 좌상 표시
		// y = -x + a 
		yIntercept = j + i;
		for (int k = 0; k < size; k++) {
			for (int l = 0; l < size; l++) {
				if ((l + k) == yIntercept) {
					chessBoard[k][l] = 2;
				}
			}
		}
		
		return chessBoard;
	}
	
	
	public static boolean hasImpossibleRow(int[][] chessBoard, int size, int i) {
		
		boolean result = false;
		for (int k = i + 1; k < size; k++) {
			int sum = 0;
			for (int l = 0; l < size; l++) {
				sum += chessBoard[k][l];
			}
			
			if (sum == (size * 2)) {
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	
}
