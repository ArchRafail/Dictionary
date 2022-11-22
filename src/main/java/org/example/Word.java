package org.example;

public class Word implements Comparable<Word> {
    private String word;
    private int counter;

    public Word(String word) {
        this.word = word;
        this.counter = 0;
    }

    protected void setWord(String word) {
        this.word = word;
    }

    protected String getWord() {
        return word;
    }

    protected int getCounter() {
        return counter;
    }

    protected void increaseCounter() {
        this.counter++;
    }

    @Override
    public int compareTo(Word o) {
        return this.word.compareTo(o.word);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word1 = (Word) o;

        if (counter != word1.counter) return false;
        return word.equals(word1.word);
    }

    @Override
    public int hashCode() {
        int result = word.hashCode();
        result = 31 * result + counter;
        return result;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", counter=" + counter +
                '}';
    }
}
