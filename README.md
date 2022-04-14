## Pipelined / Streamed Data Reduction

It is common when processing "Big Data" to work
with data tables that will not fit
in RAM. It is also the case that
a lot of the data in the table is
irrelevant, redundant. We also will
have data we are interested in that
is scattered across multiple rows
and the multiple rows can be reduced
to a single row.

The idea here is to take in a large
data set, throw away rows and columns
that are not of interest, and
reduce related rows into a single row.
Then, we can emit a smaller data table
that will be easier to use at the
next stage of processing.

## Ngram Data

We will be working with a 1.7 GB text
file from the
[Google ngram project](https://storage.googleapis.com/books/ngrams/books/datasetsv3.html).
This data is useful for many natural language
processing projects. It forms the basis of
a "language model." I use data derived from it
for things like
[spelling correction](https://norvig.com/spell-correct.html).

An **1-gram** is a sequence of characters that
are separated by whitespace. An **n-gram** is
a sequence of **n** 1-grams. In this project,
we will only be using 1-gram data.

Each data file is tab delimited with 4 fields:
1. The 1-gram
2. The year in which the book it was extracted from was published.
3. The total of number of times the 1-gram occurred in books published that year.
4. The number of books in which the 1-gram occurred.

We are not interested in column 4.

We are only interested in data from books published
in the year 2000 or later.

We want to reduce the data for the same 1-gram
that occurs in multiple rows by adding up the
frequency values in column 3. In the process,
we can discard information about the year
the 1-gram was used.

This reduces our data table to 2 columns: n-grams
and frequencies.

Further, we want to output the data into a text
file with two columns:

1. A 1-gram
2. The frequency of occurrence of that 1-gram in the period of interest.

## Step 1

Complete the process that should have been
carried out in lab on April 14. This will
leave you with a program that prints
two fields to the screen in alphabetical order
with no duplicate 1-grams.

## Step 2

Instead of printing to the screen:

1. Sort the sequence of 1-grams in descending
   numerical order while maintaining the
   alphabetical ordering for frequencies that
   are the same.

2. Output the list to a file called "a-reduced.txt"
   in the same directory where the program found the
   input "a.txt." The file should be separate the
   two columns with a tab character and terminate
   each line with a single newline character. If
   a file with that name already exists, overwrite
   it without warning the user.

## Step 3

Refine the data gathering process.

If you look at the raw data, you will see
most of the 1-grams are words with some
gibberish attached to the end. What
the code I gave you does is simply throw
away all lines with 1-grams that are not
completely alphabetic. Instead, I want
you to do is:

1. Remove the underscore and
   the part-of-speech tag that follows it
2. Then, discard lines with 1-grams that
   are not purely alphabetic.

To make this happen, look at the code
in `WordFrequencyGenerator.java`.
Rather than use

```java
Stream<String[]> interesting =
        splits.filter(isInteresting);
```

Try replacing it with 4
individual filters: 

```java
Stream<String[]> interesting = splits
        .filter(IsInteresting.has4)
        .filter(IsInteresting.noneBlank)
        .filter(IsInteresting.isYear2000orLater)
        .filter(IsInteresting.wordAllLetters);
```
Verify that it still seems to work the same.

What we want to do is delay that last `filter`
stage until after a stage where we
use `.map(IsInteresting.removePOS)`.

Of course, first we need to write
`removePOS` as a `Function<String[],String[]>`.
From the example `Predicate` declarations
in `cs121.IsInteresting` the first
step will be to write a `static` member
function/method that starts off with
the line:

```java
public static String[] removePOS(String[] array)
```

Then, we want to extract string in `array[0]`
and, if there is an underscore character in it
use the 
[`String.lastIndexOf()`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html#lastIndexOf(int))
method to find
where the underscore is in the string and then use
the [`String.substring()`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html#substring(int,int)).
Note: the first argument to
substring will be `0` and you need to figure out
if you need to add or subtract `1` from
the index returned by `lastIndexOf` to get
the desired effect. Assign the result of `substring`
back to `array[0]`, since `substring` creates a new
string rather than altering its argument.