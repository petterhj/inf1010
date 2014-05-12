// Imports
import java.util.Arrays;


//	Class: SortThread
// =============================================================================
class SortThread extends Thread {
    // Variables
    String[] words;
    String[] sorted;

    int sortFrom;
    int sortTo;
    int range;

    int threadCnt;
    int prThread;
    SortThread parent;

    int childrenDoneCnt = 0;
    long startTime;

    // Constructor
    SortThread(String[] words, int sortFrom, int sortTo, int threadCnt, SortThread parent) {
        this.words = words;
        
        this.sortFrom = sortFrom;
        this.sortTo = sortTo;
        this.range = (sortTo - sortFrom);

        this.threadCnt = threadCnt;
        this.prThread = (this.words.length / this.threadCnt);
        this.parent = parent;
    }
    SortThread(String[] words, int threadCnt, long startTime) {
        this(words, 0, words.length, threadCnt, null);

        this.startTime = startTime;
    }

    // Run
    public void run() {
		
        if (this.range > (this.prThread + 1)) {
        	// Split range
        	int index = this.split(this.sortFrom, this.sortTo);

        	System.out.println("* PRNT - from: " + this.sortFrom + "\tto: " + this.sortTo + "\t\trange: " + this.range + "\tsplit: " + index);
        	
            // Start two children threads
            new SortThread(this.words, this.sortFrom, index, this.threadCnt, this).start();
            new SortThread(this.words, index, this.sortTo, this.threadCnt, this).start();

            this.waitOnChildren();
        }
        else {
        	System.out.println("> SORT - from: " + this.sortFrom + "\tto: " + this.sortTo + "\t\tpr.th.: " + this.prThread + "\tthis: " + (this.sortTo - this.sortFrom));
            this.sorted = this.sort(Arrays.copyOfRange(this.words, this.sortFrom, this.sortTo));
        }

        // Done
        if (this.parent != null)
            this.parent.done(this.sorted);
        else {
            System.out.println("Ferdig sortert: " + this.sorted);
            System.out.println("Tid: " + (System.currentTimeMillis() - this.startTime) + " ms");
            System.out.println("+++++++++++++++++++");
            int i = 0;
            for (String s : this.sorted)
                System.out.println(i++ + ": " + s);
        }
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

    // Split range in half, return index
    private int split(int sortFrom, int sortTo) {
        return ((sortFrom + sortTo) / 2);
    }
}