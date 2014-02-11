import static org.junit.Assert.*;

import org.junit.Test;


public class test {

	public void test123(){
		Board b = new Board("data1.txt");
		b.printBoard();
		StdOut.println(b.getLeft(0, 3, 4));
		StdOut.println(b.getRight(0, 1, 4));
		StdOut.println(b.getUp(3, 1, 4));
		StdOut.println(b.getDown(0, 1, 4));
		StdOut.println(b.getLeftUp(4, 4, 1));
		
		
		StdOut.println("~~~~~~~~");
		StdOut.println(b.getLeftDown(1, 3, 4));
		StdOut.println(b.getRightUp(4, 1, 3));
		StdOut.println(b.getRightDown(0, 0, 6));
	}
	
	
	@Test
	public void test222(){
		Bag<String> b = new Bag<String>();
		b.add(null);
		b.add(null);
		b.add(null);
		b.add("hey");
		for(String s : b){
			StdOut.println("~~"+s+"\n");
		}
	}
	
	
	public void test() {
		String line = "asdasfd";
		StdOut.println(line+line.matches("[^A-Za-z]"));
	}
	
	
	
	public void test2() {
		String line = "asdasfd";
		StdOut.println(line+line.matches("[a-z]*"));
	}
	
	
	public void test3() {
		String line = "asdasfdAAA123@@@";
		StdOut.println(line+line.matches("[a-zA-Z]*"));
	}
	
	
	public void test4() {
		String line = "asdasfd";
		StdOut.println(line+line.matches("[a-z]*"));
	}
	
	public void test5() {
		String line = "asdasfd";
		StdOut.println(line+line.matches("[a-z]*"));
	}
}
