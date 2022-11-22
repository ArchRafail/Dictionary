package org.example;

import java.util.*;

public class Dictionary {
    private final TreeMap<Word, Translate> dictionary;

    public Dictionary() {
        this.dictionary = new TreeMap<>(Comparator.comparing(Word::getWord));
    }

    public TreeMap<Word, Translate> getDictionary() {
        return dictionary;
    }

    public void addWord(String[] words) {
        String word = words[0];
        String[] translates = new String[words.length-1];
        System.arraycopy(words, 1, translates, 0, words.length-1);
        if (checkWordRepeats(translates[0])) {
            dictionary.put(new Word(word), new Translate(translates));
            return;
        }
        System.out.println("Ukrainian word repeats and can't be added!");
    }

    public void addWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nInput the Ukrainian word: ");
        String word = scanner.nextLine();
        if (!checkWordRepeats(word)) {
            System.out.println("\nUkrainian word repeats and can't be added!\n");
            return;
        }
        System.out.print("Enter the English translates and split them by comma (,): ");
        String translate = scanner.nextLine();
        String[] words = translate.trim().split("\\s*,\\s*");
        if (words.length < 1) {
            System.out.println("\nTranslates of the word have to be at least 1!\n");
            return;
        }
        String[] wordWithTranslates = new String[words.length+1];
        wordWithTranslates[0] = word;
        System.arraycopy(words,0,wordWithTranslates,1,words.length);
        addWord(wordWithTranslates);
        System.out.println("\nNew word with translates was added\n");
    }

    /**
     Words in translate that have to be added (2nd parameter in method) must be split by comma!
     */
    public void addTranslate(String word, String translate) {
        String[] translateWords = validateWordsAndCreatingArray(word, translate);
        if (translateWords == null) {
            return;
        }
        for (Map.Entry<Word, Translate> words : dictionary.entrySet()) {
            if (words.getKey().getWord().equals(word)) {
                words.getValue().addTranslate(translateWords);
                break;
            }
        }
        System.out.println("\nNew translates were added!\n");
    }

    /**
     Words in translate that have to be changed (2nd parameter in method) must be split by comma!
     */
    public void changeTranslate(String word, String translate) {
        String[] translateWords = validateWordsAndCreatingArray(word, translate);
        if (translateWords == null) {
            return;
        }
        for (Map.Entry<Word, Translate> words : dictionary.entrySet()) {
            if (words.getKey().getWord().equals(word)) {
                words.getValue().getTranslate().clear();
                words.getValue().addTranslate(translateWords);
                break;
            }
        }
        System.out.println("\nTranslates were changed!\n");
    }

    /**
    Words in translate that have to be deleted (2nd parameter in method) must be split by comma!
     */
    public void deleteTranslate(String word, String translate) {
        String[] translateWords = validateWordsAndCreatingArray(word, translate);
        if (translateWords == null) {
            return;
        }
        if (translateWords.length > dictionary.get(new Word(word)).getTranslate().size()) {
            System.out.println("Words to delete are more than translated words!");
            return;
        }
        for (Map.Entry<Word, Translate> words : dictionary.entrySet()) {
            if (words.getKey().getWord().equals(word)) {
                for (String wordToRemove: translateWords) {
                    words.getValue().getTranslate().remove(wordToRemove);
                }
                break;
            }
        }
        System.out.println("\nTranslated word/words was/were deleted!\n");
    }

    public void changeWord(String oldWord, String newWord) {
        if (checkWordRepeats(oldWord)) {
            System.out.println("\nThe world that has to be changed is not found!\n");
            return;
        }
        if(!checkWordRepeats(newWord)) {
            System.out.println("\nThe world that will change the previous one repeats!\n");
            return;
        }
        for (Map.Entry<Word, Translate> words : dictionary.entrySet()) {
            if (words.getKey().getWord().equals(oldWord)) {
                words.getKey().setWord(newWord);
                break;
            }
        }
        System.out.println("\nUkrainian word changed!\n");
    }

    public void deleteWord(String word) {
        if (checkWordRepeats(word)) {
            System.out.println("\nThe main world that has to be removed is not found!\n");
            return;
        }
        dictionary.remove(new Word(word));
        System.out.println("\nUkrainian word deleted!\n");
    }

    private boolean checkWordRepeats(String word) {
        return !dictionary.containsKey(new Word(word));
    }

    private String[] validateWordsAndCreatingArray(String word, String translate) {
        if (checkWordRepeats(word)) {
            System.out.println("\nUkrainian word is not presented in the list!\n");
            return null;
        }
        String[] translateWords = translate.trim().split("\\s*,\\s*");
        if (translateWords.length < 1) {
            System.out.println("\nTranslates of the word have to be at least 1!\n");
            return null;
        }
        return translateWords;
    }

    public void showDictionary() {
        if (dictionary.isEmpty()) {
            System.out.println("\nNothing to show. The dictionary is empty!\n");
            return;
        }
        System.out.println("\nUkrainian-English dictionary:\n");
        for (Map.Entry<Word, Translate> words : dictionary.entrySet()) {
            System.out.println(words.getKey().getWord() + " - " + words.getValue().showTranslate());
        }
        System.out.println();
    }

    public void showTranslateByWord(String word) {
        if (dictionary.isEmpty()) {
            System.out.println("\nNothing to show. The dictionary is empty!\n");
            return;
        }
        for (Map.Entry<Word, Translate> words : dictionary.entrySet()) {
            if (words.getKey().getWord().equals(word)) {
                System.out.println(words.getKey().getWord() + " - "
                        + words.getValue().showTranslate());
                words.getKey().increaseCounter();
                return;
            }
        }
        System.out.println("No any translate for the word \"" + word + "\"!");
    }

    public ArrayList<String> showTop10MostPopularWords() {
        Word[] wordArray = sortingArrayOfWords();
        if(wordArray == null) {
            return null;
        }
        ArrayList<String> mostPopular = new ArrayList<>();
        if (wordArray.length < 10) {
            for (int i = wordArray.length-1; i > -1; i--) {
                mostPopular.add(wordArray[i].getWord());
                System.out.println("Word \"" + wordArray[i].getWord() + "\". Was used " + wordArray[i].getCounter()
                        + " times.");
            }
        } else {
            for (int i = wordArray.length-1; i > (wordArray.length - 11); i--) {
                mostPopular.add(wordArray[i].getWord());
                System.out.println("Word \"" + wordArray[i].getWord() + "\". Was used " + wordArray[i].getCounter()
                        + " times.");
            }
        }
        return mostPopular;
    }

    public ArrayList<String> showTop10LeastPopularWords() {
        Word[] wordArray = sortingArrayOfWords();
        if(wordArray == null) {
            return null;
        }
        ArrayList<String> leastPopular = new ArrayList<>();
        if (wordArray.length < 10) {
            for (int i = 0; i < wordArray.length-1; i++) {
                leastPopular.add(wordArray[i].getWord());
                System.out.println("Word \"" + wordArray[i].getWord() + "\". Used " + wordArray[i].getCounter() + " times.");
            }
        } else {
            for (int i = 0; i < 10; i++) {
                leastPopular.add(wordArray[i].getWord());
                System.out.println("Word \"" + wordArray[i].getWord() + "\". Used " + wordArray[i].getCounter() + " times.");
            }
        }
        return leastPopular;
    }

    private Word[] sortingArrayOfWords() {
        if (dictionary.isEmpty()) {
            System.out.println("\nNothing to show. The dictionary is empty!\n");
            return null;
        }
        Set<Word> keys = dictionary.keySet();
        Word[] wordArray = keys.toArray(new Word[0]);
        Arrays.sort(wordArray, Comparator.comparing(Word::getCounter));
        return wordArray;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "dictionary=" + dictionary +
                '}';
    }
}
