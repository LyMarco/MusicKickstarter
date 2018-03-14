import pronouncing
import sys
import json

from nltk.corpus import wordnet

from collections import OrderedDict

# Try and make this a program that waits for incoming messages instead of having
# to run the whole thing each time, that should improve response times by reducing
# having to load in certain libraries and other one time work.


def findRhymes(word):
    
    #print(sys.argv)

    # 1 is the position of the word passed in. 0 is the name of the script

    rhymes = pronouncing.rhymes(word)

    #print(rhymes)

    return rhymes

if __name__ == "__main__":
    word = sys.argv[1]
    
    response = {}
    
    syn = wordnet.synsets(word)
    
    rhymes = findRhymes(word)
    
    response['rhymes'] = rhymes
    response['defenition'] = syn[0].definition()
    response['examples'] = syn[0].examples()
    

    synonyms = []
    antonyms = []
 
    for syn in wordnet.synsets(word):
        for l in syn.lemmas():
            synonyms.append(l.name())
            if l.antonyms():
                antonyms.append(l.antonyms()[0].name())
                
    # set() makes sure there are no duplicates, may not keep order?
    # set() does not seem to be json resializable
    response['synonyms'] = list(OrderedDict.fromkeys(synonyms)) #set(synonyms)
    response['antonyms'] = list(OrderedDict.fromkeys(antonyms)) #set(antonyms)
    
    
    responseJson = json.dumps(response)
    
    #The node js server notices this print and reads the data.
    print(responseJson)

    