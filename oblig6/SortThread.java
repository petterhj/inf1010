// Imports
import java.util.Arrays;


//	Class: SortThread
// =============================================================================
class SortThread extends Thread {
	// Variables
	private String[] words, sorted;
	private int sortFrom, sortTo, wordCnt;

	SortThread(String[] words, int sortFrom, int sortTo) {
		this.words = words;
		this.sortFrom = sortFrom;
		this.sortTo = sortTo;
		this.wordCnt = (sortTo - sortFrom);
	}

	// Run
	public void run() {
		this.sort(Arrays.copyOfRange(this.words, this.sortFrom, this.sortTo));
	}

	// Sort
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
}

/*
class SortThread extends Thread {
	// Variables
	String[] words;
	int sortFrom, sortTo;
	SortThread parentThread;

	// ??
	int antallFerdigeSubtrader = 0;
	final static int minsteLengde = 5;

	// Constructor
	SortThread(String[] words, int sortFrom, int sortTo, SortThread parentThread) {
		this.words = words;
		this.sortFrom = sortFrom;
		this.sortTo = sortTo;
		this.parentThread = parentThread;

		System.out.println("Thread - from: " + this.sortFrom + ", to: " + this.sortTo + ", total: " + this.words.length);
	}
	SortThread(String[] words) {
		this(words, 0, words.length, null);
	}

	// Run
	public void run() {
		if ((sortTo-sortFrom) > minsteLengde) {
			antallFerdigeSubtrader = 0;

			int indeks = delOpp(words, sortFrom, sortTo);

			new SortThread(words, sortFrom, indeks, this).start();
			new SortThread(words, (indeks+1), sortTo, this).start();

			vent();
		}
		else {
			sortWords(words, sortFrom, sortTo);
		}

		if (parentThread != null)
			parentThread.ferdig();
		else
			System.out.println("Ferdig sortert!");
	}

	// Wait
	synchronized void vent() {
		while (antallFerdigeSubtrader != 2) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				System.out.println("Ukjent avbrudd");
				System.exit(0);
			}
		}
	}

	// Done
	synchronized void ferdig() {
		antallFerdigeSubtrader++;
		notify();
	}

	// Sort
	public void sortWords(String[] words, int sortFrom, int sortTo) {
		int wordCnt = (sortTo-sortFrom);

		System.out.println(" > Sort - from: " + sortFrom + ", to: " + sortTo + ", total: " + wordCnt);

		for(int j = sortFrom; j < wordCnt; j++) {
            for (int i = (j + 1); i < wordCnt; i++) {
                if(words[i].compareTo(words[j]) < 0) {
                    String current = words[j];
                    words[j] = words[i]; 
                    words[i] = current;
                }
            }
        }
	}

	// Split
	public int delOpp(String[] words, int sortFrom, int sortTo) {
		return ((sortFrom+sortTo) / 2);
	}
}
*/