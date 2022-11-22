package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class DictionaryTest {

    private Dictionary dictionary;

    @BeforeEach
    public void setUp() {
        dictionary = new Dictionary();
        dictionary.addWord(new String[]{"розмова", "conversation", "talk", "dialogue", "discourse", "converse",
                "tell", "wording"});
        dictionary.addWord(new String[]{"відро", "bucket", "pail", "bail"});
        dictionary.addWord(new String[]{"автобус", "coach", "autobus", "omnibus"});
        dictionary.addWord(new String[]{"річ", "thing", "object", "article", "own"});
        dictionary.addWord(new String[]{"дерево", "tree", "wood", "arbor"});
        dictionary.addWord(new String[]{"гриб", "mushroom", "fungus", "fungal"});
        dictionary.addWord(new String[]{"галявина", "lawn", "clearing", "opening"});
        dictionary.addWord(new String[]{"мама", "mother", "mom", "mum", "mama", "momma", "ma"});
        dictionary.addWord(new String[]{"капуста", "cabbage", "kale"});
        dictionary.addWord(new String[]{"ведмідь", "bear"});
        dictionary.addWord(new String[]{"тато", "dad", "daddy", "papa", "pap", "baba", "father"});
        dictionary.addWord(new String[]{"ніж", "knife", "chive", "chopper", "spade"});
        dictionary.addWord(new String[]{"фонтан", "fountain", "cataract"});
        dictionary.addWord(new String[]{"пенал", "pencil case", "pen case"});
    }

    @Test
    public void checkSizeOfCollectionAndContentValidationByWord() {
        dictionary.addWord(new String[]{"конопля", "hemp", "cannabis"});
        assertEquals(15, dictionary.getDictionary().size());

        Word example = new Word("розмова");
        assertEquals(7, dictionary.getDictionary().get(example).getTranslate().size());

        String exampleWord = "галявина";
        Translate exampleTranslate = new Translate(new String[] {"lawn", "clearing", "opening"});
        assertEquals(exampleTranslate.getTranslate(), dictionary.getDictionary().get(new Word(exampleWord)).getTranslate());
    }

    @Test
    public void checkAddingAndChangingAndRemovingWord() {
        dictionary.addWord(new String[]{"енергія", "power", "energy"});
        assertEquals(15, dictionary.getDictionary().size());

        String oldWord = "мама";
        Translate translate = dictionary.getDictionary().get(new Word(oldWord));
        String newWord = "син";
        dictionary.changeWord(oldWord, newWord);
        Word resultByTranslate = getKeyByValue(translate);
        assertSame(newWord, resultByTranslate.getWord());

        assertEquals(15, dictionary.getDictionary().size());
        assertTrue(dictionary.getDictionary().containsKey(new Word("фонтан")));
        dictionary.deleteWord("фонтан");
        assertNull(dictionary.getDictionary().get(new Word("фонтан")));
        assertEquals(14, dictionary.getDictionary().size());
    }

    @Test
    public void checkAddingAndChangingAndRemovingTranslation() {
        String searchingWord = "мама";
        int oldQuantityOfTranslates = 6;
        assertEquals(oldQuantityOfTranslates, dictionary.getDictionary().get(new Word(searchingWord)).getTranslate().size());
        int newQuantityOfTranslates = 9;
        dictionary.addTranslate(searchingWord, "mamma, mummy, mammy");
        assertEquals(newQuantityOfTranslates, dictionary.getDictionary().get(new Word(searchingWord)).getTranslate().size());

        int quantityOfTranslatesBeforeChanging = 9;
        assertEquals(quantityOfTranslatesBeforeChanging, dictionary.getDictionary().get(new Word(searchingWord)).getTranslate().size());
        int quantityOfTranslatesAfterChanging = 3;
        dictionary.changeTranslate(searchingWord, "mamma, mummy, mammy");
        assertEquals(quantityOfTranslatesAfterChanging, dictionary.getDictionary().get(new Word(searchingWord)).getTranslate().size());

        String newSearchingWord = "тато";
        int quantityOfTranslatesBeforeDeleting = 6;
        assertEquals(quantityOfTranslatesBeforeDeleting, dictionary.getDictionary().get(new Word(newSearchingWord)).getTranslate().size());
        int quantityOfTranslatesAfterDeleting = 2;
        dictionary.deleteTranslate(newSearchingWord, "papa, pap, baba, father");
        assertEquals(quantityOfTranslatesAfterDeleting, dictionary.getDictionary().get(new Word(newSearchingWord)).getTranslate().size());
    }

    @Test
    public void checkTop10MostPopularAnd10LeastPopular() {
        String word1 = "дерево";
        String word2 = "гриб";
        String word3 = "пенал";
        String word4 = "капуста";
        String word5 = "відро";
        String word6 = "річ";
        String word7 = "галявина";
        String word8 = "мама";
        String word9 = "розмова";
        String word10 = "автобус";
        String word11 = "ведмідь";
        String word12 = "тато";
        String word13 = "ніж";
        String word14 = "фонтан";

        increaseCounter(word1, 30);
        increaseCounter(word2, 28);
        increaseCounter(word3, 26);
        increaseCounter(word4, 24);
        increaseCounter(word5, 22);
        increaseCounter(word6, 20);
        increaseCounter(word7, 18);
        increaseCounter(word8, 16);
        increaseCounter(word9, 14);
        increaseCounter(word10, 12);
        increaseCounter(word11, 10);
        increaseCounter(word12, 8);
        increaseCounter(word13, 6);
        increaseCounter(word14, 4);

        ArrayList<String> mostPopular = dictionary.showTop10MostPopularWords();

        assertEquals(word1, mostPopular.get(0));
        assertEquals(word2, mostPopular.get(1));
        assertEquals(word3, mostPopular.get(2));
        assertEquals(word4, mostPopular.get(3));
        assertEquals(word5, mostPopular.get(4));
        assertEquals(word6, mostPopular.get(5));
        assertEquals(word7, mostPopular.get(6));
        assertEquals(word8, mostPopular.get(7));
        assertEquals(word9, mostPopular.get(8));
        assertEquals(word10, mostPopular.get(9));

        ArrayList<String> leastPopular = dictionary.showTop10LeastPopularWords();

        assertEquals(word14, leastPopular.get(0));
        assertEquals(word13, leastPopular.get(1));
        assertEquals(word12, leastPopular.get(2));
        assertEquals(word11, leastPopular.get(3));
        assertEquals(word10, leastPopular.get(4));
        assertEquals(word9, leastPopular.get(5));
        assertEquals(word8, leastPopular.get(6));
        assertEquals(word7, leastPopular.get(7));
        assertEquals(word6, leastPopular.get(8));
        assertEquals(word5, leastPopular.get(9));
    }

    private Word getKeyByValue(Translate value) {
        for (Map.Entry<Word, Translate> entry : dictionary.getDictionary().entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    private void increaseCounter(String word, int countsIncrease) {
        for (int i = 0; i < countsIncrease; i++) {
            dictionary.showTranslateByWord(word);
        }
    }
}
