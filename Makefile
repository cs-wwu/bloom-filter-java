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

test-build: classes Test/TestRunner.class Test/BloomFilterTest.class

test-create: test-build
	java -cp $(TESTCLASSPATH) TestRunner BloomFilterTest#testCreate

test-add-one: test-build
	java -cp $(TESTCLASSPATH) TestRunner BloomFilterTest#testAddOne

test-add-overlap: test-build
	java -cp $(TESTCLASSPATH) TestRunner BloomFilterTest#testAddOverlap

test-not-present: test-build
	java -cp $(TESTCLASSPATH) TestRunner BloomFilterTest#testNotPresent

test-present: test-build
	java -cp $(TESTCLASSPATH) TestRunner BloomFilterTest#testPresent

test-maybe-present: test-build
	java -cp $(TESTCLASSPATH) TestRunner BloomFilterTest#testMaybePresent

test: test-build
	java -cp $(TESTCLASSPATH) TestRunner BloomFilterTest
