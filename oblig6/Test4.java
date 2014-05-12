import java.util.Arrays;
import java.lang.Math;

class Tree {

	Tree() {
	    String[] words = {
	        "Ole", "Per", "Ida",    // 3
	        "Tor", "Jon", "Ada",    // 6
	        "Åse", "Jan", "Eda",    // 9
	        //"Ine", "Åke", "Ane",    // 12
	    };


	    for (String s : Arrays.copyOfRange(words, 6, 9))
	    	System.out.println(s);
    }
}

class Test4 {
    public static void main(String[] args) {
        System.out.println();
        new Tree();
        System.out.println();
	}
}