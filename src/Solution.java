import java.util.Arrays;

public class Solution {

	Board b;
	Dict d;
	Bag<String> solus;

	public Solution(Board board, Dict dict) {
		b = board;
		d = dict;
		solus = new Bag<String>();
		Stopwatch watch = new Stopwatch();
		getSolution();
		double readTime = watch.elapsedTime();
		StdOut.printf("Time to find all words in gameboard:%.3f seconds.\n",
				readTime);
	}

	
	
	/**
	 * calculate solution out
	 */
	private void getSolution() {
		for (int i = 0; i < b.size(); i++) {
			for (int j = 0; j < b.size(); j++) {
				Bag<String> adjs = getAdjacents(i, j);
				for (String s : adjs) {
					
					if(s!=null && s.contains("*")){
						//StdOut.println("*???????"+s);
						Bag<String> bs = d.getWildMatch(s.toLowerCase());
						for(String singleString: bs){
							//StdOut.println("*!!!!!!!!!!"+singleString);
							if(!isOccurred(singleString) )	solus.add(singleString.toUpperCase());
						}
					}
					if (s!=null && d.isContains(s.toLowerCase()) && !isOccurred(s)) {
						solus.add(s);
					}
					
					
				}
			}
		}
		//printSolutions();
		//solutionString();
	}

	/**
	 * check if this string is already in solution bag
	 * @param s
	 * @return
	 */
	public boolean isOccurred(String s) {
		for (String s2 : solus) {
			if (s2.equalsIgnoreCase(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * give all adjacent string of length 4~10 at location $i, $j in 8 direction
	 * @param i
	 * @param j
	 * @return a Bag of string
	 */
	private Bag<String> getAdjacents(int i, int j) {
		// updownleftright 4~10=8*6
		Bag<String> vals = new Bag<String>();
		for (int k = 4; k <= 10; k++) {
			vals.add(b.getLeft(i, j, k));
			vals.add(b.getRight(i, j, k));
			vals.add(b.getUp(i, j, k));
			vals.add(b.getDown(i, j, k));
			vals.add(b.getLeftUp(i, j, k));
			vals.add(b.getLeftDown(i, j, k));
			vals.add(b.getRightUp(i, j, k));
			vals.add(b.getRightDown(i, j, k));
		}
		return vals;
	}

	/**
	 * give the solution as string
	 * @return solution string
	 */
	public String[] solutionString(){
		//result need to be sorted
		String[] arrays = new String[solus.size()];
		int i = 0;
		for(String singleString : solus){
			arrays[i] = singleString;
			i++;
		}
		Arrays.sort(arrays);
		//StdOut.println(Arrays.toString(arrays));
		return arrays;
	}
	
	/**
	 * print solution via StdOut.
	 * one per line
	 */
	public void printSolutions() {
		for (String ss : solus) {
			StdOut.println(ss);
		}
	}
}
