Submission for programming challenges by Bridgewater

Blob Boundary:
- code in BlobBoundary/src
- Assumptions:
	- Blobs are mostly fat (i.e. have huge inner area, compared to perimeter), this leads to speed up by walking only on the boundary.
	- Blobs can have slim extensions coming out, which can be of width one.


Concordance:
- Using ICU4j and Apache Commons libraries
- code in Concordance/src
- ant build file will download some jars before first execution
- I have put in iterators for sentence and word splitting, these can be switched out for better iterators
	- Tried StanfordNLP and NLTK for sentence splitting, ICU4j was giving better results for limited tests tried. Need to test further to make a clear choice
- Assumptions:
	- Input contains natural language english sentences
	- The max length of input is within Integer.MAX_VALUE

Notes:
- ant build file included, use ant package/test/clean
- jar built with "ant package" is runnable, it takes in a file and outputs to stdout
- the jar can also be used as a library
- each folder has its own sample input