package org.example;

import org.junit.jupiter.api.Test;

import java.util.*;

public class LetterCasePermutation_784 {

    @Test
    void test() {
//        String a = "aAbBcCdDeEfFzZ";
//        a.chars().forEachOrdered(value -> System.out.println("value = " + value));
//        String s = "a1b2";
//        String s = "3z4";
        String s = "12345";
        List<String> result = letterCasePermutation(s);
        System.out.println(">>>>>> result: " + result);
    }

    public List<String> letterCasePermutation(String s) {

        char[] charArray = s.toCharArray();
        int charsNumber = charArray.length;

        Map<Integer, Character> numbers = new HashMap<>();
        Map<Integer, Character> chars = new HashMap<>();

        for (int i = 0; i < charsNumber; i++) {
            if (Character.isLetter(charArray[i])) {
                chars.put(i, charArray[i]);
            } else {
                numbers.put(i, charArray[i]);
            }
        }

        double numberOfPermutations = Math.pow(2d, chars.size());
        List<String> wordList = new ArrayList<>();

        for (int i = 0; i < numberOfPermutations; i++) {
            Map<Integer, Character> resultedWord = new HashMap<>();

            if (chars.size() > 0) {
                char[] binary = String.format("%" + chars.size() + "s", Integer.toBinaryString(i)).replace(" ", "0").toCharArray();

                Iterator<Map.Entry<Integer, Character>> iterator = chars.entrySet().iterator();
                for (char c : binary) {
                    Map.Entry<Integer, Character> next = iterator.next();
                    Character character = next.getValue();
                    if (c == '1') {
                        resultedWord.put(next.getKey(), invertCase(character));
                    } else {
                        resultedWord.put(next.getKey(), character);
                    }
                }
            }

            resultedWord.putAll(numbers);
            StringBuilder sb = new StringBuilder();

            for (int m = 0; m < resultedWord.size(); m++) {
                sb.append(resultedWord.get(m));
            }

            wordList.add(sb.toString());
        }

        return wordList;
    }

    private Character invertCase(Character c) {
        if (Character.isLowerCase(c)) {
            return Character.toUpperCase(c);
        } else {
            return Character.toLowerCase(c);
        }
    }

}
