import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class BloomFilterTest {
    private BloomFilter filter;

    @Before
    public void setup() {
        filter = new BloomFilter(12, 3);
    }

    @Test
    public void testCreate() {
        byte[] bytes = filter.getBytes();
        assertEquals(2, bytes.length);
        for (byte b: bytes) {
            assertEquals(0, b);
        }

        filter = new BloomFilter(35, 4);
        bytes = filter.getBytes();
        assertEquals(5, bytes.length);
        for (byte b: bytes) {
            assertEquals(0, b);
        }
    }

    @Test
    public void testAddOne() {
        filter.add("foo");
        for (int i = 0; i < 12; i++) {
            switch (i) {
                case 0:
                case 5:
                case 7:
                    assertTrue(filter.getBit(i));
                    break;

                default:
                    assertFalse(filter.getBit(i));
                    break;
            }
        }
    }

    @Test
    public void testAddOverlap() {
        filter.add("foo");
        filter.add("bar");
        filter.add("foobar");

        for (int i = 0; i < 12; i++) {
            switch (i) {
                case 0:
                case 2:
                case 5:
                case 7:
                case 10:
                    assertTrue(filter.getBit(i));
                    break;

                default:
                    assertFalse(filter.getBit(i));
                    break;
            }
        }
    }

    @Test
    public void testNotPresent() {
        filter.add("foo");
        filter.add("bar");
        filter.add("foobar");

        assertFalse(filter.lookup("sky"));
    }

    @Test
    public void testPresent() {
        filter.add("foo");
        filter.add("bar");
        filter.add("foobar");
        assertTrue(filter.lookup("bar"));
    }

    @Test
    public void testMaybePresent() {
        filter.add("foo");
        filter.add("bar");
        filter.add("foobar");
        filter.add("dang");
        filter.add("magnus");
        assertTrue(filter.lookup("room"));
        assertFalse(filter.lookup("sky"));

        assertEquals(-1, filter.getBytes()[0]);
        assertEquals(5, filter.getBytes()[1]);
    }
}
