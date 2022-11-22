package org.example;

import java.util.*;

public class Translate {
    private final TreeSet<String> translate;

    protected Translate(String[] words) {
        this.translate = new TreeSet<>();
        this.translate.addAll(Arrays.asList(words));
    }

    protected TreeSet<String> getTranslate() {
        return translate;
    }

    protected void addTranslate(String[] words) {
        this.translate.addAll(new TreeSet<>(Arrays.asList(words)));
    }

    protected String showTranslate() {
        StringBuilder translateInfo = new StringBuilder();
        if (!translate.isEmpty()) {
            for (String word : translate) {
                translateInfo.append(word);
                if (!word.equals(translate.last())) {
                    translateInfo.append(", ");
                }
            }
        }
        else {
            translateInfo.append("no any translate.\n");
        }
        return translateInfo.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Translate translate1 = (Translate) o;

        return translate.equals(translate1.translate);
    }

    @Override
    public int hashCode() {
        return translate.hashCode();
    }

    @Override
    public String toString() {
        return "Translate{" +
                "translate=" + translate +
                '}';
    }
}
