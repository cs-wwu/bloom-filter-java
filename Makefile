CLASSPATH = .
TESTCLASSPATH = $(CLASSPATH):Test/:Test/junit4.jar
JFLAGS = -g -cp $(TESTCLASSPATH)
.SUFFIXES: .java .class
.java.class:
	javac $(JFLAGS) $*.java

TESTSRC = Test/TestRunner.java Test/BloomFilterTest.java

SRC = \
	Hash.java \
	BloomFilter.java

default: classes

classes: $(SRC:.java=.class)

clean:
	rm -f *.class Test/*.class

run: classes
	java -cp $(CLASSPATH) BloomFilter

test-create: classes Test/TestRunner.class Test/BloomFilterTest.class
	java -cp $(TESTCLASSPATH) TestRunner BloomFilterTest#testCreate

test-add-one: classes Test/TestRunner.class Test/BloomFilterTest.class
	java -cp $(TESTCLASSPATH) TestRunner BloomFilterTest#testAddOne

test-add-overlap: classes Test/TestRunner.class Test/BloomFilterTest.class
	java -cp $(TESTCLASSPATH) TestRunner BloomFilterTest#testAddOverlap

test-not-present: classes Test/TestRunner.class Test/BloomFilterTest.class
	java -cp $(TESTCLASSPATH) TestRunner BloomFilterTest#testNotPresent

test-present: classes Test/TestRunner.class Test/BloomFilterTest.class
	java -cp $(TESTCLASSPATH) TestRunner BloomFilterTest#testPresent

test-maybe-present: classes Test/TestRunner.class Test/BloomFilterTest.class
	java -cp $(TESTCLASSPATH) TestRunner BloomFilterTest#testMaybePresent

test: test-create test-add-one test-add-no-overlap test-add-overlap \
	test-not-present test-present test-maybe-present
