import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.Iterator;
import java.util.HashSet;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class SkipListTest {
    private SkipList skipList;
    private int DEFAULTLEVELS = 4;
    private Node head;
    private Node sentinel;

    @Before
    public void setup() {
        skipList = new SkipList(DEFAULTLEVELS);
        head = skipList.getHead();
        sentinel = skipList.getSentinel();
    }

    @Test
    public void testEmpty() {
        assertEquals(DEFAULTLEVELS, head.getLevels());
        for (int i = 0; i < DEFAULTLEVELS; i++) {
            assertEquals(sentinel, head.getNext(i));
        }
    }

    @Test
    public void testAddOne() {
        skipList.insert("foo", 42);

        assertEquals(sentinel, head.getNext(3));
        Node node = head.getNext(2);
        assertEquals("foo", node.getKey());
        assertEquals(42, node.getValue());

        assertEquals(node, head.getNext(2));
        assertEquals(node, head.getNext(1));
        assertEquals(node, head.getNext(0));

        assertEquals(null, node.getNext(3));
        assertEquals(sentinel, node.getNext(2));
        assertEquals(sentinel, node.getNext(1));
        assertEquals(sentinel, node.getNext(0));
    }

    @Test
    public void testAddMultiple() {
            SkipList skip = new SkipList(4);

            skip.insert("foo", 42);
            skip.insert("bar", 13);
            skip.insert("rambunctious", 66);
            skip.insert("folly", 77);
            skip.insert("rabbit", 55);
            skip.insert("hello", 44);
            String result = skip.toString();

            String expected =
                "Level(3): Head -> Sentinel\n" +
                "Level(2): Head -> Node(key=bar, value=13) -> Node(key=folly, value=77) -> Node(key=foo, value=42) -> Sentinel\n" +
                "Level(1): Head -> Node(key=bar, value=13) -> Node(key=folly, value=77) -> Node(key=foo, value=42) -> Node(key=rambunctious, value=66) -> Sentinel\n" +
                "Level(0): Head -> Node(key=bar, value=13) -> Node(key=folly, value=77) -> Node(key=foo, value=42) -> Node(key=hello, value=44) -> Node(key=rabbit, value=55) -> Node(key=rambunctious, value=66) -> Sentinel";

            assertEquals(expected, result);
        }
}
