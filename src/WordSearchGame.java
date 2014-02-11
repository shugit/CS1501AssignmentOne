import java.util.Arrays;

/**
 * Package Used: StdIn (to read data from the standard input device) In (to read
 * data from an input file) StdOut (to output to the console) Stopwatch (to time
 * sections of your code) Bag (to hold a collection of objects) TST (to hold any
 * form of dictionary, see pages 746-752)
 * 
 * @author SephyZhou For University Of Pittsburgh CS 1501 Spring 2014 Assignment
 *         One
 */

public class WordSearchGame {
	static Dict dict;
	static Board board; // maybe empty--use default
	static int colWords = 6;
	static Solution solution;
	static Bag<String> correctAnswer;

	public static void main(String[] args) {
		while (true) {
			String initialString = "\nWelcome to WORD SEARCH!  Run with \"-help\" for cmd-line options.\n";
			StdOut.print(initialString);
			boolean containsHelp = false;
			for (String argument : args) {
				if (argument.contains("-help")) {
					containsHelp = true;
					helpOutput();
				}
			}
			if (containsHelp)
				return;
			for (int i = 0; i < args.length; i += 2) {
				if (args[i].startsWith("-")) {
					if (args[i].contains("dict")) {
						readDict(args[i + 1]);
					} else if (args[i].contains("board")) {
						if (args.length <= (i + 1)) {
							helpOutput();
							System.exit(0);
						}
						readBoard(args[i + 1]);
					} else if (args[i].contains("cols")) {
						readCol(args[i + 1]);
					}
				}
			}
			startGame();
		}
	}

	private static void startGame() {
		initialCheck();
		StdOut.println();
		board.printBoard();
		StdOut.println("\nYou can now type in words.  Type a non-alphabetic symbol and ENTER to quit\n"
				+ "playing and list all the words on the game board.");
		String line;
		while ((line = StdIn.readLine()).matches("[A-Za-z]*")) {
			checkWord(line);
		}
		showResult();
	}

	private static void checkWord(String line) {
		line = line.toLowerCase();
		if (solution.isOccurred(line)) {
			if (!isInputed(line))
				correctAnswer.add(line.toUpperCase());
			StdOut.printf(
					"Yes, \"%s\" is in the dictionary and on the game board.\n",
					line.toUpperCase());
		} else {
			StdOut.printf(
					"No, \"%s\" is not both in the dictionary and on the game board.\n",
					line.toUpperCase());
		}
	}

	/**
	 * To prevent user from input same word
	 * 
	 * @param s
	 * @return
	 */
	private static boolean isInputed(String s) {
		for (String s2 : correctAnswer) {
			if (s2.equalsIgnoreCase(s)) {
				StdOut.println("already input");
				return true;
			}
		}
		return false;
	}

	/**
	 * print user's result and ask if them want to repeat
	 */
	private static void showResult() {
		printAllWords();
		printCorrectAnswer();
		StdOut.printf("\n\n\nYou found %d out of %d words, or %.2f percent.\n",
				correctAnswer.size(), solution.solutionString().length,
				(double) (correctAnswer.size() * 100.0f / solution
						.solutionString().length));

		StdOut.print("Play again? [Y/N]");
		if (StdIn.readLine().matches("[nN]"))
			System.exit(0);
	}

	/**
	 * print all words that is correct
	 */
	private static void printAllWords() {
		StdOut.print("\nList of all words on the game board:\n");
		int i = 0;
		for (String s : solution.solutionString()) {
			if (i != 0 && i % colWords == 0) {
				StdOut.println();
			}
			StdOut.print("\t" + s);
			i++;
		}
	}

	private static void printCorrectAnswer() {
		StdOut.print("\n\n\nList of words that you found:\n");
		int i = 0;
		String[] arrays = new String[correctAnswer.size()];
		for (String singleString : correctAnswer) {
			arrays[i] = singleString;
			i++;
		}
		Arrays.sort(arrays);
		// StdOut.println(Arrays.toString(arrays));
		i = 0;
		for (String s : arrays) {
			if (i != 0 && i % colWords == 0) {
				StdOut.println();
			}
			if (s != null)
				StdOut.print("\t" + s);
			i++;
		}
	}

	/**
	 * check if board or dict is empty
	 */
	private static void initialCheck() {
		if (board == null) {
			StdOut.print("\nError: Board must be defined\n");
			board = new Board();
			System.exit(0);
		}
		if (dict == null) {
			dict = new Dict();
		}
		solution = new Solution(board, dict);
		correctAnswer = new Bag<String>();
	}

	public static void helpOutput() {
		StdOut.print("Options:\n"
				+ " \"-board FILENAME\": Specifies game board file.\n"
				+ " \"-dict FILENAME\":  Specifies dictionary file.\n"
				+ " \"-cols NUMCOLS\": Specifies the number of columns for printing words.\n");

	}

	public static void readDict(String dictName) {
		if (dictName == null) {
			helpOutput();
			System.exit(0);
		}
		dict = new Dict(dictName);
	}

	public static void readBoard(String boardName) {
		if (boardName == null) {
			helpOutput();
			System.exit(0);
		}
		board = new Board(boardName);
	}

	public static void readCol(String colString) {
		if (colString == null) {
			helpOutput();
			System.exit(0);
		}
		colWords = Integer.parseInt(colString);
	}
}
