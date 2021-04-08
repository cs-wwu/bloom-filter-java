import java.util.Random;


/**
 * A class that does hashing.
 */
public class Hash {
    private int size;

    /**
     * Create a hasher that returns multiple hashes for each element
     *
     * @param size The number of hashes to return
     */
    public Hash(int size) {
        this.size = size;
    }

    /**
     * Hash a string and return multiple hashes.
     *
     * @param s The string to hash
     * @return An array of integer hashes
     */
    public int[] hash(String s) {
        Random random = new Random(0);
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = Math.abs((s + random.nextInt()).hashCode());
        }
        return result;
    }
}
