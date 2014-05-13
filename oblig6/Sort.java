/*
	INF1010 - v14 - Oblig6
	petterhj
*/


// Imports
import java.io.File;



//	Class: Sort
// =================================================================================
class Sort {
	// Main
	public static void main(String[] args) {
		// Header
		System.out.println("\n========================================================");
		System.out.println(" Oblig 6 - WordSorter");
		System.out.println("========================================================\n");

		// Arguments
		if (args.length < 3) {
			// Usage
			System.out.println(" - Usage: Sort <thread count> <input file> <output file>");
		}
		else {
			// Validate arguments
			System.out.println(" - Checking arguments...");

			boolean validThreadCnt = false;
			int threadCntMin = 1;
			int threadCntMax = 2000;
			int threadCnt = 0;
			File input = new File(args[1]);
			File output = new File(args[2]);

			try {
				// Thread count
				threadCnt = Integer.parseInt(args[0]);

				if ((threadCnt >= threadCntMin) && (threadCnt <= threadCntMax))
					validThreadCnt = true;
			}
			catch (NumberFormatException e) {
				validThreadCnt = false;
			}

			if (validThreadCnt) {
				// Input file
				if (input.isFile()) {
					System.out.println("  > Threads:\t" + args[0]);
					System.out.println("  > Input:\t" + args[1]);
					System.out.println("  > Output:\t" + args[2] + "\n");

					// Sort words
					new WordSorter(threadCnt, input, output);
				}
				else
					System.out.println(" - Input file not found!");
			}
			else {
				System.out.println(" - Please provide a valid positive integer between " + threadCntMin + " and " + threadCntMax + " for argument \"thread count\"!");
			}
		}

		System.out.println();
	}
}