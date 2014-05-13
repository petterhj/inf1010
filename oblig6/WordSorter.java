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

			System.out.println(" - Begun sorting utilizing " + this.threadCnt + " sorting thread(s)...");
			System.out.println("  > Per thread: " + prThread + " (+1 first " + carryover + " threads)");

			int sortFrom = 0;

	        for (int i = 0; i < this.threadCnt; i++) {
	            int words = prThread;

	            if (i < carryover)
	                words++;

	            int sortTo = (sortFrom + words);

	            // System.out.println(i + ": f.o.m " + sortFrom + " til " + sortTo + " (" + (sortTo - sortFrom) + ")");
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
        /* !!! REMOVE !!! */
        /* ============================================================= */
        /*
        System.out.print("= RSLT " + this.sortResultCount + ":\t");

        for (String w : result)
            System.out.print(w + ", ");

        System.out.print(" | " + result);
        System.out.println();
        */
        /* ============================================================= */


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
        /* !!! REMOVE !!! */
        /* ============================================================= */
        /*
        System.out.print("= MRGD " + this.mergeResultCount + ":\t");

        for (String w : result)
            System.out.print(w + ", ");

        System.out.print(" | " + result);
        System.out.println();
        */
        /* ============================================================= */

        
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
        String[] range;

        SorterThread(int sortFrom, int sortTo) {
            this.range = Arrays.copyOfRange(WordSorter.this.words, sortFrom, sortTo);

            // System.out.print("* SORT:\t\t");

            // for (String w : this.range)
            //     System.out.print(w + ", ");

            // System.out.println("(" + (sortTo - sortFrom) + ")");
        }

        public void run() {
            WordSorter.this.sortResultReceived(this.sort(this.range));
        }

        // Sort array alphabetically
        private String[] sort(String[] range) {
            // Sort
            for(int j = 0; j < range.length; j++) {
                for (int i = (j + 1); i < range.length; i++) {
                    if(range[i].compareTo(range[j]) < 0) {
                        String current = range[j];
                        range[j] = range[i]; 
                        range[i] = current;
                    }
                }
            }

            return range;
        }
    }


    //	Class: MergerThread
	// =============================================================================
    private class MergerThread extends Thread {
        private String[] a1;
        private String[] a2;

        MergerThread(String[] a1, String[] a2) {
            this.a1 = a1;
            this.a2 = a2;

            // System.out.println("* MRGE:\t\t| " + a1 + " (l=" + a1.length + ") + " + a2 + " (l=" + a2.length + ")");
        }

        public void run() {
            WordSorter.this.mergeResultReceived(this.merge(a1, a2));
        }

        // Merge two pre-sorted arrays
        private String[] merge(String[] a1, String[] a2) {
            String[] result = new String[(a1.length + a2.length)];

            for (int i = 0, j = 0, k = 0; i < a1.length || j < a2.length; k++){
                if (j == a2.length || (i != a1.length && a1[i].compareTo(a2[j]) <= 0))
                    result[k] = a1[i++];
                else
                    result[k] = a2[j++];
            }

            return result;
        }
    }
}