import java.util.Arrays;

public class Board {
	String[][] array;

	public Board(String boardString) {
		readBoard(boardString);
	}

	/**
	 * construct for Board if board's file name is not defined
	 */
	public Board() {
		String[] lines = In.readStrings();
		// StdOut.println(Arrays.toString(lines));
		int col = Integer.parseInt(lines[0]);
		array = new String[col][col];
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < col; j++) {
				array[i][j] = lines[1 + i * col + j];
			}
		}
		 //printBoard();
	}

	/**
	 * read board from a file
	 * @param boardString filename
	 */
	private void readBoard(String boardString) {
		String[] lines = In.readStrings(boardString);
		// StdOut.println(Arrays.toString(lines));
		int col = Integer.parseInt(lines[0]);
		array = new String[col][col];
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < col; j++) {
				array[i][j] = lines[1 + i * col + j];
			}
		}
		 //printBoard();
	}

	/**
	 * print a board via stdOut
	 */
	public void printBoard() {
		StdOut.println("Game Board:");
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				StdOut.print(array[i][j] + " ");
			}
			StdOut.println();
		}
	}
	
	public int size(){
		return array.length;
	}

	
	/**
	 * get a string of length $length from starting position $i, $j to it's left direction
	 * @param i
	 * @param j
	 * @param length
	 * @return
	 */
	public String getLeft(int i, int j, int length) {
		//StdOut.println(array.length);
		if (j - length +1< 0) {
			return null;
		}
		String val = "";	
		for (int a = j; a >= j - length + 1; a--) {
			val = val + array[i][a];
		}
		return val;
	}

	public String getRight(int i, int j, int length) {
		if (j + length > array.length) {
			return null;
		}
		String val = "";	
		for (int a = j; a <j+length; a++) {
			val = val + array[i][a];
		}
		return val;
	}
	
	public String getUp(int i, int j, int length) {
		if (i - length + 1 <0) {
			return null;
		}
		String val = "";
		for (int a = i; a >= i - length + 1; a--) {
			val = val + array[a][j];
		}
		return val;
	}
	
	public String getDown(int i, int j, int length) {
		if (i + length > array.length) {
			return null;
		}
		String val = "";	
		for (int a = i; a < i + length; a++) {
			val = val + array[a][j];
		}
		return val;
	}
	
	
	/**
	 * get a string of length $length from starting position $i, $j to it's left up direction
	 * @param i
	 * @param j
	 * @param length
	 * @return
	 */
	public String getLeftUp(int i, int j, int length) {
		if (j - length +1 < 0) {
			return null;
		}
		if (i - length + 1 <0) {
			return null;
		}	
		String val = "";	
		
		int a = j;
		int b = i;
		while(a >= j - length + 1 && b >= i - length + 1 ){
			val = val + array[b][a];
			a--;
			b--;
		}
		return val;
	}
	
	
	/**
	 * get a string of length $length from starting position $i, $j to it's left down direction
	 * @param i
	 * @param j
	 * @param length
	 * @return
	 */
	public String getLeftDown(int i, int j, int length) {
		if (j - length +1< 0) {
			return null;
		}		
		if (i + length > array.length) {
			return null;
		}	
		String val = "";
		int a = j;
		int b = i;		
		while(a >= j - length + 1 && b < i + length){
			
			val = val + array[b][a];
			a--;
			b++;
		}
		return val;
	}
	
	public String getRightUp(int i, int j, int length) {
		//Up
		if (i - length + 1 <0) {
			return null;
		}
		if (j + length > array.length) {
			return null;
		}
		
		
		String val = "";
		int a = i;
		int b = j;
		while(a >= i - length + 1 && b <j+length){
			val = val + array[a][b];
			
			a--;
			b++;
		}
		return val;
		
	}
	
	public String getRightDown(int i, int j, int length) {
		//RIGHT
		if (j + length > array.length) {
			return null;
		}
		if (i + length > array.length) {
			return null;
		}
		
		String val = "";	
		
		int a = j;
		int b = i;
		while(a <j+length && b < i + length){
			val = val + array[b][a];
			a++;
			b++;
		}
		
		return val;
	}
	
	
	
	
}
