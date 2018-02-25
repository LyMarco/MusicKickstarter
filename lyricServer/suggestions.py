import pronouncing
import sys

#print(sys.argv)

# 1 is the position of the word passed in. 0 is the name of the script
word = sys.argv[1]


rhymes = pronouncing.rhymes(word)

print(rhymes)