// Imports
import java.io.*;


//	Class: WordSorter
// =============================================================================
class WordSorter {
	// Variables
	private int threadCnt;
	private File inputFile;
	private File outputFile;

	private int wordCnt;
	private String[] words;


	// Constructor
	WordSorter(int threadCnt, File inputFile, File outputFile) {
		//this.threadCnt = threadCnt;
		this.threadCnt = 2;					// TESTING
		this.inputFile = inputFile;
		this.outputFile = outputFile;

		// Input
		this.readInput();

		// Sort
		this.sortWords();
	}

	// Read input file
	private void readInput() {
		System.out.println(" - Parsing input file...");

		try {
			// Read
            BufferedReader data = new BufferedReader(new FileReader(this.inputFile));
            
            // Word count
            try {
        		this.wordCnt = Integer.parseInt(data.readLine());

				// Words
				String line;
				int wordIndex = 0;

				this.words = new String[this.wordCnt];

	            while ((line = data.readLine()) != null) {
	            	String word = line.trim();

	            	if (wordIndex < this.wordCnt)
	            		if (!word.equals(""))
	            			this.words[wordIndex++] = word;
	            }

	            System.out.println("  > Words found: " + this.words.length + "/" + this.wordCnt + "\n");
    		}
    		catch (NumberFormatException e) {
    			System.out.println(" - Could not parse input file. Wrong format?");
			}
        } catch(IOException e) {
        	System.out.println(" - Could not read input file!");
    	}
	}

	synchronized void test(String[] result) {
		System.out.println(result);
	}

	// Sort words
	private void sortWords() {
		if ((this.words != null) || (this.words.length != 0)) {
			System.out.println(" - Trying to sort words using " + this.threadCnt + " thread(s)...");

			// Start threads
			int lastIndex = 0;

	        for (int i = 0; i < this.threadCnt; i++) {
	            int n = (this.wordCnt / this.threadCnt);

	            if (i < (this.wordCnt % this.threadCnt))
	                n++;

	            // Start
	            System.out.println(i + " - THREAD - fra:" + (lastIndex) + ", til: " + (lastIndex + n));
	            
	            new SortThread(this.words, lastIndex, (lastIndex + n)).start();

	            lastIndex = (lastIndex + n);
        	}
		}
		else {
			System.out.println(" - No words to sort!");
		}
	}
}