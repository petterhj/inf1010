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

	private int wordCnt;
	private String[] words = null;

	private int threads;
	private int sorterThreadsCnt = 1;


	// Constructor
	WordSorter(int threadCnt, File inputFile, File outputFile) {
		this.threadCnt = threadCnt;
		this.inputFile = inputFile;
		this.outputFile = outputFile;

		this.threads = ((this.threadCnt + this.threadCnt) - 1);

		System.out.println(this.threads);

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

	            System.out.println("  > Words found: " + words.length + "/" + this.wordCnt + "\n");

	            return words;
    		}
    		catch (NumberFormatException e) {
    			System.out.println(" - Could not parse input file. Wrong format?");
			}
        } catch(IOException e) {
        	System.out.println(" - Could not read input file!");
    	}

    	return null;
	}

	// Sort words
	synchronized void sortWords() {
		if ((this.words != null) && (this.words.length != 0)) {
			System.out.println(" - Trying to sort words using " + this.threadCnt + " thread(s)...");
			System.out.println("  > Pr. thread: " + (words.length / this.threadCnt));
			System.out.println("  > Rest: " + (words.length % this.threadCnt) + "\n");

			// Start threads
			new SortThread2().start();
		}
		else {
			System.out.println(" - No words to sort!");
		}
	}


	//	Class: SortThread2
	// =============================================================================
	class SortThread2 extends Thread {
	    // Variables
	    int index;
	    String[] sorted;

	    int sortFrom;
	    int sortTo;
	    int range;

	    int prThread;
	    SortThread2 parent;

	    int childrenDoneCnt = 0;

	    // Constructor
	    SortThread2(SortThread2 parent) {
	    	this.index = WordSorter.this.sorterThreadsCnt++;
	        this.prThread = (WordSorter.this.words.length / WordSorter.this.threadCnt);
	        this.parent = parent;
	    }
	    SortThread2() {
	        this(null);
	    }

	    // Run
	    public void run() {
	    	int nextLevel = (WordSorter.this.threads - this.index);

	    	System.out.print("* THREAD (" + this.index + "), next level: " + nextLevel);
			if (this.parent != null) {
	    		System.out.print(" | p.i.: " + this.parent.index);
    		}
	    	System.out.println(" | parent: " + this.parent);

	    	if (nextLevel > 1) {
	    		new SortThread2(this).start();
	    		new SortThread2(this).start();
	    	}


	    	/*

	    	// Parent thread (merger thread)
	        if (this.range > (this.prThread + 1)) {
	        	System.out.println("* PRNT (" + this.index + ")");
	        	
	            // Start two children threads
	            new SortThread2(this).start();
	            new SortThread2(this).start();

	            // this.waitOnChildren();
	        }
	        // Sorter thread
	        else {
	        	System.out.println("> SORT (" + this.index + ")");
	            
	            // Sorter thread
	            // this.sorted = this.sort(Arrays.copyOfRange(WordSorter.this.words, this.sortFrom, this.sortTo));
	        }

	        // Done
	        if (this.parent != null)
	            this.parent.done(this.sorted);
	        else {
	            System.out.println("Ferdig sortert: " + this.sorted);
	            // System.out.println("Tid: " + (System.currentTimeMillis() - this.startTime) + " ms");
	            System.out.println("+++++++++++++++++++");
	            // int i = 0;
	            // for (String s : this.sorted)
	            //     System.out.println(i++ + ": " + s);
	        }*/
	    }

	    // Wait
	    synchronized void waitOnChildren() {
	        while (this.childrenDoneCnt != 2) {
	            try {
	                wait();
	            }
	            catch (InterruptedException e) {
	                System.out.println("Unknown intrruption");
	                System.exit(0);
	            }
	        }
	    }

	    // Done
	    synchronized void done(String[] result) {
	        // Add children result
	        this.childrenDoneCnt++;

	        // One done: keep result temporarily
	        if (this.childrenDoneCnt == 1)
	            this.sorted = result;

	        // Both done: merge results from both
	        if (this.childrenDoneCnt == 2)
	            this.sorted = this.merge(this.sorted, result);

	        // Notify
	        notify();
	    }


	    // Sort array alphabetically
	    private String[] sort(String[] range) {
	        // Sort range alphabetically
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

	    // Merge two pre-sorted arrays
	    private String[] merge(String[] a1, String[] a2) {
	        String[] result = new String[(a1.length + a2.length)];

	        for (int i = 0, j = 0, k = 0; i < a1.length || j < a2.length; k++){
	            if (j==a2.length || (i!=a1.length && a1[i].compareTo(a2[j]) <= 0))
	                result[k] = a1[i++];
	            else
	                result[k] = a2[j++];
	        }

	        return result;
	    }
	}
}