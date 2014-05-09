import java.util.Random;



class Test {
	// Main
	public static void main(String[] args) {

		// Words
		RandomString wg = new RandomString(7);
		String[] words = new String[20];

		for (int i = 0; i < words.length; i++)
			words[i] = wg.nextString();

		System.out.println("\n--------------------------------------------\n");

		for (int i = 0; i < words.length; i++)
			System.out.println(i + ": " + words[i]);

		System.out.println("\n--------------------------------------------\n");


		// Sort
		//new SortThread(words).start();
		


	}
}


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