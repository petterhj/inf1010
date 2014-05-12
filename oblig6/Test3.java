import java.util.Arrays;

class Sort2 {
    
    private String[] words = {
        "Ole", "Per", "Ida", // 3
        "Tor", "Jon", "Ada", // 6
        "Eda", "Jan", "Åse", // 9
        "Kai", "Ola", "Ine", // 12
        "Eva", "Ina", "Sol", // 15
    };

    int wordCnt = this.words.length;
    int threadCnt = 6;
    int prThread = (this.wordCnt / this.threadCnt);

    int[] segments = new int[(this.threadCnt * this.threadCnt)];

    Sort2() {
    	System.out.println("Antall:\t\t" + this.wordCnt);
		System.out.println("Tråder:\t\t" + this.threadCnt);
		System.out.println("Pr.tråd:\t" + (this.wordCnt / this.threadCnt));
		System.out.println("Rest:\t\t" + (this.wordCnt % this.threadCnt) + "(får +1)");

		System.out.println();

		System.out.print("Fordeling:\t");

		for (int i = 0; i < this.threadCnt; i++) {
			int w = (this.wordCnt / this.threadCnt);

			if (i < (this.wordCnt % this.threadCnt))
				w += 1;

			System.out.print(w + ", ");

		}

		System.out.println();
		System.out.println();

		new Sorter(null, this.words, 0, this.words.length, this.threadCnt).start();
    }
};

class Sorter extends Thread {
	Sorter parent;
	String[] words;
	int sortFrom;
	int sortTo;
	int threadCnt;
	int childrenDoneCnt = 0;

	Sorter(Sorter parent, String[] words, int sortFrom, int sortTo, int threadCnt) {
		this.parent = parent;
		this.words = words;
		this.sortFrom = sortFrom;
		this.sortTo = sortTo;
		this.threadCnt = threadCnt;
	}

	public void run() {
		System.out.println("T: " + this.sortFrom + "..." + this.sortTo + " | p: " + this.parentCount());

		if (this.threadCnt > 1) {

		}

		this.waitOnChildren();
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

        // Notify
        notify();
    }


	// Return count of parents
	private int parentCount() {
		int levels = this.threadCnt;
		int parents = 0;

		while(levels != 0) {
			levels = (levels / 2);
			parents++;
		}

		return parents;
	}
}

class Test3 {
    public static void main(String[] args) {
        System.out.println();
        new Sort2();
        System.out.println();
	}
}