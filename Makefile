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


clean-docker:
	-docker image rm seemongtan/build:latest

autograde:
	curl -L -H "Cache-Control: no-cache" \
        -H "Pragma: no-cache" \
        https://raw.githubusercontent.com/cs-wwu/Autograder/main/autograder.py \
	    -o Test/autograder.py
	python3 Test/autograder.py
	rm Test/autograder.py result.json

run-on-docker:
	docker run -it --rm --mount type=bind,src=.,dst=/app --platform linux/amd64 seemongtan/build:latest
