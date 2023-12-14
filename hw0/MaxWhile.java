public class MaxWhile {
    /** Returns the maximum value from m. */
    public static int max(int[] m) {
        int i = 0;
        int value = m[i];
        while(i < m.length) {
            if(m[i] > value) {
                value = m[i];
            }
            i++;
        }
        return value;
    }
    public static void main(String[] args) {
       int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};    
       System.out.println(max(numbers));
    }
}