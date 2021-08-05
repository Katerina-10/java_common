package collections.Map;

public class WordWrapper implements Comparable<WordWrapper>{
    private final String word;
    private final Integer count;

    public WordWrapper(String word, Integer count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public int compareTo(WordWrapper thatWord) {
        if (count < thatWord.getCount())
            return -1;
        if (count > thatWord.getCount())
            return 1;
        return word.compareTo(thatWord.getWord());
    }

    @Override
    public String toString() {
        return word + " -> " + count ;
    }
}
