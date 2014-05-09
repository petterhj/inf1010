// Imports
import java.util.Arrays;

class TestSort {
    public static void main(String[] args) {
        String[] words = {"Ragnar", "Johannes", "Nils", "Arne" , "Asgeir", "Geir-Per", "Geir Arne", "Geir-Jon", "Jonna", "Aksel", "Geir" , "Per", "Arne", "Johan", "Ole", "Arne2", "Arne0"};

        for(int j = 0; j < words.length; j++)
            System.out.println(words[j]);

        System.out.println("-------------------------");

        String[] range = Arrays.copyOfRange(words, 0, 2);

        for(int j = 0; j < range.length; j++) {
            for (int i = (j + 1); i < range.length; i++) {
                if(range[i].compareTo(range[j]) < 0) {
                    String current = range[j];
                    range[j] = range[i]; 
                    range[i] = current;
                }
            }
        }

        for(int j = 0; j < range.length; j++)
            System.out.println(range[j]);

        System.out.println("-------------------------");
    }
}