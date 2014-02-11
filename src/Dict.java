public class Dict {
	TST<Integer> t;

	/**
	 * create a Dict instance with filename $dictString
	 * @param dictString filename
	 */
	public Dict(String dictString) {
		Stopwatch watch = new Stopwatch();
		Bag<String> bag = readFile(dictString);
		double readTime = watch.elapsedTime();
		StdOut.printf(
				"Reading dictionary (%d words) from disk:%.3f seconds.\n",
				bag.size(), readTime);

		watch = new Stopwatch();
		putTST(bag);
		readTime = watch.elapsedTime();
		StdOut.printf(
				"Putting dictionary into ternary-search-trie:%.3f seconds.\n",
				readTime);
	}

	/**
	 * create a Dict instance with no filenem (use default "dict8.txt")
	 */
	public Dict() {
		Stopwatch watch = new Stopwatch();
		Bag<String> bag = readFile("dict8.txt");
		double readTime = watch.elapsedTime();
		StdOut.printf(
				"Reading dictionary (%d words) from disk:%.3f seconds.\n",
				bag.size(), readTime);

		watch = new Stopwatch();
		putTST(bag);
		readTime = watch.elapsedTime();
		StdOut.printf(
				"Putting dictionary into ternary-search-trie:%.3f seconds.\n",
				readTime);
	}


	/**
	 * put the dictionary from file to Bag
	 * @param dictString
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Bag<String> readFile(String dictString) {
		String[] lines = In.readStrings(dictString);
		Bag<String> bag = new Bag<String>();
		for (String s : lines) {
			bag.add(s);
		}
		return bag;
	}

	/**
	 * put the dictionary from Bag to TST
	 * @param bag
	 */
	public void putTST(Bag<String> bag) {
		t = new TST<Integer>();
		int i = 0;
		for (String s : bag) {
			t.put(s, i);
			i++;
		}
	}

	/**
	 * check if $s exist in the TST dictionary
	 * @param s
	 * @return
	 */
	public boolean isContains(String s) {
		if (t.contains(s)) {
			return true;
		}
		return false;
	}

	/**
	 * get wild match, transfer * to . to get original match running
	 * @param s
	 * @return
	 */
	public Bag<String> getWildMatch(String s) {
		Bag<String> vals = new Bag<String>();
		if (s.contains("*")) {
			s = s.replace("*", ".");
			
			Iterable<String> is = t.wildcardMatch(s);
			//StdOut.println("!!!!!!!!!!!!!!!!!!" + s);
			for (String ss : is) {
				//StdOut.println("~~~~~~~~~~~" + ss);
				vals.add(ss);
			}
		}
		return vals;
	}

}
