import java.util.Random;
import java.util.Arrays;


class Test {
    // Main
	public static void main(String[] args) {


		// Words
        String[] words = {
            "Ragnar", "Johannes", "Nils", "Arne" , "Asgeir",            // 5
            "Geir-Per", "Geir Arne", "Geir-Jon", "Jonna", "Aksel",      // 10
            "Geir" , "Per", "Arne", "Johan", "Ole",                     // 15
            "Arne2", "Arne0", "Rune", "Kjell", "BJørn",                 // 20
            "Gunnar", "Knut", "Are", "Liv", "Ida",                      // 25
        };


        int th = 4;

		
        // Start sorting
        new TestThread(words, th).start();


	}
}


class TestThread extends Thread {
    // Variables
    String[] words;
    String[] sorted;

    int sortFrom;
    int sortTo;
    int range;

    int threadCnt;
    int prThread;
    TestThread parent;

    int childrenDoneCnt = 0;

    // Constructor
    TestThread(String[] words, int sortFrom, int sortTo, int threadCnt, TestThread parent) {
        this.words = words;
        
        this.sortFrom = sortFrom;
        this.sortTo = sortTo;
        this.range = (sortTo - sortFrom);

        this.threadCnt = threadCnt;
        this.prThread = (this.words.length / this.threadCnt);
        this.parent = parent;
    }
    TestThread(String[] words, int threadCnt) {
        this(words, 0, words.length, threadCnt, null);
    }

    // Run
    public void run() {
        
        int index = this.split(this.words, this.sortFrom, this.sortTo);

        if (this.range > (this.prThread + 1)) {
            System.out.println("Range: " + this.range + " | pr.thread = " + this.prThread + " | modulo = " + (this.range % 2) + " | rest = " + (this.words.length % this.threadCnt) + " | split = " + index + "\n");

            System.out.println("1: NEW - from: " + this.sortFrom + ", to: " + index);
            System.out.println("2: NEW - from: " + index + ", to: " + (this.sortTo));

            System.out.println("-----------------------------------------------------------");

            new TestThread(this.words, this.sortFrom, index, this.threadCnt, this).start();
            new TestThread(this.words, index, this.sortTo, this.threadCnt, this).start();

            this.waitOnChildren();
        }
        else {
            System.out.println("* SORT - from: " + this.sortFrom + ", to: " + this.sortTo + " | ant.: " + (this.sortTo - this.sortFrom) + " | p: " + this.parent);
            this.sorted = this.sort(Arrays.copyOfRange(this.words, this.sortFrom, this.sortTo));
            for (String s : this.sorted)
                System.out.println(" - " + s);
        }

        // 
        if (this.parent != null)
            this.parent.done(this.sorted);
        else {
            System.out.println("Ferdig sortert: " + this.sorted);
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

        // If both done (merge children results)
        if (this.childrenDoneCnt == 2)
            System.out.println("CHILDREN DONE: " + this.childrenDoneCnt);
    }

    // Done
    synchronized void done(String[] result) {
        // Add children result
        this.childrenDoneCnt++;

        // Merge children results (when both children finishes)
        if (this.childrenDoneCnt == 1)
            this.sorted = result;

        if (this.childrenDoneCnt == 2)
            this.sorted = this.merge(this.sorted, result);

        // Notify
        notify();
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

    // Merge
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

    // Split
    private int split(String[] words, int sortFrom, int sortTo) {
        return ((sortFrom + sortTo) / 2);
    }
}



/*

        RandomString wg = new RandomString(7);
        String[] words = new String[wc];

        for (int i = 0; i < words.length; i++)
            words[i] = wg.nextString();



class RandomString {

  private static char[] symbols;

  static {
    StringBuilder tmp = new StringBuilder();
    //for (char ch = '0'; ch <= '9'; ++ch)
    //  tmp.append(ch);
    for (char ch = 'a'; ch <= 'z'; ++ch)
      tmp.append(ch);
    symbols = tmp.toString().toCharArray();
  }   

  private final Random random = new Random();

  private final char[] buf;

  public RandomString(int length) {
    if (length < 1)
      throw new IllegalArgumentException("length < 1: " + length);
    buf = new char[length];
  }

  public String nextString() {
    for (int idx = 0; idx < buf.length; ++idx) 
      buf[idx] = symbols[random.nextInt(symbols.length)];
    return new String(buf);
  }
}
*/