**Sequence Alignment Algorithm**

A program written in JAVA to implement two popular sequence alignment algorithms namely:

1. Needleman-Wunch algorithm for global alignment
2. Smith-Waterman algorithm for local alignment

Both the algorithms above use an affine gap penalty function.

The scores for match, mismatch, opening gap penalty (h) and extension gap penalty (g) can be configured in the parameters.config file present inside the input folder.

The input folder also contains two real world examples of gene sequences in FASTA format.

To run the program, open terminal and write following command

    javac Main.java

    java Main *path to FASTA file* *0: Perform global alignment, 1: Perform local alignment* *path to parameters.config file*
