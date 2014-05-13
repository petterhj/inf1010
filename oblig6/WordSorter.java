// Imports
import java.io.*;
import java.util.Arrays;


//	Class: WordSorter
// =============================================================================
class WordSorter {
	// Variables
	private int threadCnt;
	private File inputFile;
	private File outputFile;

	private String[] words;
	private int wordCnt;

	private long startTime;

	private String[] prevSortResult;
	private String[] prevMergeResult;
	private int sortResultCount = 1;
	private int mergeResultCount = 1;

	// Constructor
	WordSorter(int threadCnt, File inputFile, File outputFile) {
		// Arguments
		this.threadCnt = threadCnt;
		this.inputFile = inputFile;
		this.outputFile = outputFile;

		// Input
		this.words = this.readInput();

		// Sort
		this.sortWords();
	}

	// Read input file
	private String[] readInput() {
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

				String[] words = new String[this.wordCnt];

				while ((line = data.readLine()) != null) {
					String word = line.trim();

					if (wordIndex < this.wordCnt)
						if (!word.equals(""))
							words[wordIndex++] = word;
				}

				System.out.println("  > Words found: " + wordIndex + "/" + this.wordCnt + "\n");

				if (wordIndex != this.wordCnt) {
					System.out.println(" - The file did not contain defined number of words!");
				}
				else {
					return words;
				}
			}
			catch (NumberFormatException e) {
				System.out.println(" - Could not parse input file. Wrong format?");
			}
		} catch(IOException e) {
			System.out.println(" - Could not read input file!");
		}

		return null;
	}

	// Write output file
	private void writeOutput(String[] result) {
		// Time
		long finishTime = System.currentTimeMillis();

		System.out.println("  > Sorting completed in " + (finishTime - this.startTime) + " ms.\n");

		// Write result to output file
		System.out.println(" - Writing output file...");

		try {
			if ((result.length == this.wordCnt) && (result[(result.length - 1)] != null)) {
				PrintWriter fil = new PrintWriter(new FileWriter(this.outputFile));
				
				for (String s : result)
					fil.println(s);
					
				fil.close();

				System.out.println("  > Wrote " + result.length + " strings to file!");
			}
			else {
				System.out.println("- Word count mismatch in result!");
			}
		} 
		catch (IOException e) {
			System.out.println("- Could not write to output file!");
		}

		System.out.println();
	}

	// Sort words
	private void sortWords() {
		if ((this.words != null) && (this.words.length != 0)) {
			// Time
			this.startTime = System.currentTimeMillis();

			// Start threads
			int prThread = (this.wordCnt / this.threadCnt);
			int carryover = (this.wordCnt % this.threadCnt);

			System.out.println(" - Began sorting utilizing " + this.threadCnt + " sorting thread(s)...");
			System.out.println("  > Per thread: " + prThread + " (+1 given to " + carryover + " thread(s))");

			int sortFrom = 0;

			for (int i = 0; i < this.threadCnt; i++) {
				int words = prThread;

				if (i < carryover)
					words++;

				int sortTo = (sortFrom + words);

				this.new SorterThread(sortFrom, sortTo).start();

				sortFrom = sortTo;
			}
			
		}
		else {
			System.out.println(" - Nothing to sort! Aborting.");
		}
	}

	// Received result from thread
	synchronized void sortResultReceived(String[] result) {
		// Store odd result temporarily
		if ((this.sortResultCount % 2) != 0) {
			this.prevSortResult = result;

			// If last result
			//  Has to be merged with the result array at top level
			//  Skewed, so not optimal
			if (this.sortResultCount == this.threadCnt) {
				// Simply pass to merge receiver, albeit not very elegant
				this.mergeResultReceived(result);
			}
		}

		// Merge every other result coming in
		if ((this.sortResultCount % 2) == 0) {
			// Merge two results
			this.new MergerThread(this.prevSortResult, result).start();
			
			prevSortResult = null;
		}
		
		// Increase sort counter
		this.sortResultCount++;
	}

	// Received merge result
	synchronized void mergeResultReceived(String[] result) {
		// Keep merging merged arrays
		if (this.prevMergeResult == null) {
			// Store result temporarily
			this.prevMergeResult = result;    
		}
		else {
			// Merge
			this.new MergerThread(this.prevMergeResult, result).start();
			this.prevMergeResult = null;
		}

		// Done
		if (result.length == this.wordCnt) {
			// System.out.println("FERDIG!");
			this.writeOutput(result);
		}

		// Increase merge counter
		this.mergeResultCount++;
	}


	//	Class: SorterThread
	// =============================================================================
	private class SorterThread extends Thread {
		// Variables
		String[] range;

		// Constructor
		SorterThread(int sortFrom, int sortTo) {
			// Range
			this.range = Arrays.copyOfRange(WordSorter.this.words, sortFrom, sortTo);
		}

		// Run
		public void run() {
			// Return result to monitor
			WordSorter.this.sortResultReceived(this.sort(this.range));
		}

		// Sort given array alphabetically
		private String[] sort(String[] range) {
			/*
				Matches selection sort. Not very efficient for larger arrays,
				but simpler to implement.
			*/

			// Sort
			for (int i = 0; i < range.length; i++) {
				// Loop through ahead
				for (int j = (i + 1); j < range.length; j++) {
					// Checks whether word range[j] comes *before* range[i] alphabetically
					if (range[j].compareTo(range[i]) < 0) {
						// Swap places
						// 	if range[j] before range[i], move range[j] "upwards"
						String current = range[i];
						range[i] = range[j]; 
						range[j] = current;
					}
				}
			}

			// Return array (of equal size) in alphabetical order
			return range;
		}
	}


	//	Class: MergerThread
	// =============================================================================
	private class MergerThread extends Thread {
		// Variables
		private String[] a1;
		private String[] a2;

		// Constructor
		MergerThread(String[] a1, String[] a2) {
			// Arrays to merge
			this.a1 = a1;
			this.a2 = a2;
		}

		// Run
		public void run() {
			// Return result to monitor
			WordSorter.this.mergeResultReceived(this.merge(a1, a2));
		}

		// Merge two pre-sorted arrays
		private String[] merge(String[] a1, String[] a2) {
			/*
				Merging of two pre-sorted (required) arrays. 
			*/

			// Result
			String[] result = new String[(a1.length + a2.length)];

			for (int i = 0, j = 0, l = 0; i < a1.length || j < a2.length; l++){
				if (j == a2.length || (i != a1.length && a1[i].compareTo(a2[j]) <= 0)) {
					result[l] = a1[i++];
				}
				else {
					result[l] = a2[j++];
				}
			}

			// Return merged (pre-sorted) arrays, in one combined array
			return result;
		}
	}
}