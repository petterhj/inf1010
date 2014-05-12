import java.util.Arrays;
import java.util.ArrayList;

class Sort {

    private String[] words = {
        "Ole", "Per", "Ida",    // 3
        "Tor", "Jon", "Ada",    // 6
        "Åse", "Jan", "Eda",    // 9
    };

    private long startTime;

    private String[] result = new String[this.words.length];
    private ArrayList<String[]> results = new ArrayList<String[]>();

    private String[] prevSortResult;

    private String[] prevMergeResult;
    private int sortResultCount = 1;
    private int mergeResultCount = 1;

    int wo = this.words.length;
    int th = 3;

    int pr = (wo / th);
    int rs = (wo % th); // siste rs tråder får pr+1
    int parents = (th % 2);

    Sort() {
        System.out.println("Ord:\t\t\t" + this.wo);
        System.out.println("Tråder:\t\t\t" + this.th);
        System.out.println("Hver tråd (+/- 1):\t" + this.pr);
        System.out.println("Tråder med +1:\t\t" + this.rs);
        System.out.println("Foreldre:\t\t" + this.parents);
        System.out.println();
        this.startTime = System.currentTimeMillis();

        // this.test(this.wo);

        //this.new Merger(this.results).start();

        if (th == 1) {
            this.new SorterThread(0, 9).start();    // 9
        }
        if (th == 2) {
            this.new SorterThread(0, 5).start();    // 5
            this.new SorterThread(5, 9).start();    // 4
        }
        if (th == 3) {
            this.new SorterThread(0, 3).start();    // 3
            this.new SorterThread(3, 6).start();    // 3
            this.new SorterThread(6, 9).start();    // 3
        }
        if (th == 4) {
            this.new SorterThread(0, 2).start();    // 2
            this.new SorterThread(2, 4).start();    // 2
            this.new SorterThread(4, 6).start();    // 2
            this.new SorterThread(6, 9).start();    // 3
        }
        if (th == 5) {
            this.new SorterThread(0, 2).start();    // 2
            this.new SorterThread(2, 4).start();    // 2
            this.new SorterThread(4, 6).start();    // 2
            this.new SorterThread(6, 8).start();    // 2
            this.new SorterThread(8, 9).start();    // 1
        }
        if (th == 6) {
            this.new SorterThread(0, 2).start();    // 2
            this.new SorterThread(2, 4).start();    // 2
            this.new SorterThread(4, 6).start();    // 2
            this.new SorterThread(6, 7).start();    // 1
            this.new SorterThread(7, 8).start();    // 1
            this.new SorterThread(8, 9).start();    // 1
        }
    }

    // Done
    private void done() {
        long finishTime = System.currentTimeMillis();

        System.out.println("Ferdig på: " + (finishTime - this.startTime) + " ms.");
        
    }

    // Received result from thread
    synchronized void sortResultReceived(String[] result) {
        /* !!! REMOVE !!! */
        /* ============================================================= */
        System.out.print("= RSLT " + this.sortResultCount + ":\t");

        for (String w : result)
            System.out.print(w + ", ");

        System.out.print(" | " + result);
        System.out.println();
        /* ============================================================= */


        // Store odd result temporarily
        if ((this.sortResultCount % 2) != 0) {
            this.prevSortResult = result;

            // If last result
            //  Has to be merged with the result array at top level
            //  Skewed, so not optimal
            if (this.sortResultCount == this.th) {
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
        System.out.print("= MRGD " + this.mergeResultCount + ":\t");

        for (String w : result)
            System.out.print(w + ", ");

        System.out.print(" | " + result);
        System.out.println();
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
        if (result.length == this.wo) {
            System.out.println("FERDIG!");
            this.done();
        }


        // Increase merge counter
        this.mergeResultCount++;
    }

    // Sorter
    private class SorterThread extends Thread {
        String[] range;

        SorterThread(int sortFrom, int sortTo) {
            this.range = Arrays.copyOfRange(Sort.this.words, sortFrom, sortTo);

            System.out.print("* SORT:\t\t");

            for (String w : this.range)
                System.out.print(w + ", ");

            System.out.println("(" + (sortTo - sortFrom) + ")");
        }

        public void run() {
            Sort.this.sortResultReceived(this.sort(this.range));
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

    // Merger
    private class MergerThread extends Thread {
        private String[] a1;
        private String[] a2;

        MergerThread(String[] a1, String[] a2) {
            this.a1 = a1;
            this.a2 = a2;

            System.out.println("* MRGE:\t\t| " + a1 + " (l=" + a1.length + ") + " + a2 + " (l=" + a2.length + ")");
        }

        public void run() {
            Sort.this.mergeResultReceived(this.merge(a1, a2));
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

class Test2 {
    public static void main(String[] args) {
        System.out.println();
        new Sort();
	}
}