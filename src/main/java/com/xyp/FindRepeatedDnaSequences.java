package com.xyp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindRepeatedDnaSequences {

    public static void main(String[] args) {
        String testS = "AAAAAAAAAAA";
        findRepeatedDnaSequences(testS);

    }

    public static List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> dnaMap = new HashMap<>();

        for (int i = 0; i <= s.length() - 10; i++) {
            String temp = s.substring(i, i + 10);
            dnaMap.merge(temp, 1, Integer::sum);
        }
        System.out.printf(dnaMap.keySet().toString());
        return dnaMap.keySet().stream().filter(key -> dnaMap.get(key) > 1).collect(Collectors.toList());
    }
}
