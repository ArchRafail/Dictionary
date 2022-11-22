package org.example;

public class Main {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
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
        dictionary.addWord(new String[]{"конопля", "hemp", "cannabis"});

        dictionary.showDictionary();

        System.out.println("--------------------------------------");

        dictionary.showTranslateByWord("мама");

        System.out.println("--------------------------------------");

        dictionary.addWord();

        System.out.println("--------------------------------------");

        dictionary.showDictionary();
    }
}