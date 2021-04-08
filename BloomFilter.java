/**
 * Represent a Bloom Filter. The filter is represented as a byte array.
 */
public class BloomFilter {
    /**
     * Create a Bloom filter of size bits and number of hashes
     *
     * @param size The size "m" of the array
     * @param numHashes The number "k" of hashes
     */
    public BloomFilter(int size, int numHashes) {
        // TODO(add your code)
    }

    /**
     * Return the array of bytes for the bloom filter
     */
    public byte[] getBytes() {
        // TODO(add your code)
        return null;
    }

    /**
     * Add a string to the set
     */
    public void add(String s) {
        // TODO(add your code)
    }

    /**
     * Return true if the bit at the given position is set, or false otherwise
     */
    public boolean getBit(int pos) {
        // TODO(add your code)
        return false;
    }

    private void setBit(int pos) {
        // TODO(add your code)
    }

    /**
     * Return true if the string s is maybe present in the set, or false
     * if it is definitely not present
     */
    public boolean lookup(String s) {
        // TODO(add your code)
        return true;
    }

    // A little test program with a small filter
    public static void main(String[] args) {
        BloomFilter filter = new BloomFilter(12, 3);
        filter.add("foo");
        filter.add("bar");
        filter.add("foobar");
        filter.add("dang");
        filter.add("magnus");
        System.out.println(filter.lookup("room"));
        System.out.println(filter.getBit(0));
        System.out.println(filter.getBit(6));
        printBytes(filter.getBytes());
        filter.add("room");
    }

    private static void printBytes(byte[] bytes) {
        System.out.print("Byte(");
        for (byte b: bytes) {
            System.out.print(b + ", ");
        }
        System.out.println(")");
    }
}
