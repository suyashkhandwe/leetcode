package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
class Solution {

    private static final Map<Character, Integer> CHAR_TO_PRIME_MAP = new HashMap<>();

    static {
        BigInteger prime = new BigInteger("2");
        CHAR_TO_PRIME_MAP.put((char) 97, prime.intValue());
        for (int i = 98; i < 123; i++) {
            prime = prime.nextProbablePrime();
            CHAR_TO_PRIME_MAP.put((char) i, prime.intValue());
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groupAnagramMap = new HashMap<>();
        for (String str : strs) {
            char[] strChars = str.toCharArray();
            Arrays.sort(strChars);
            String key = new String(strChars);
            if (!groupAnagramMap.containsKey(key)) {
                groupAnagramMap.put(key, new ArrayList<>());
            }
            groupAnagramMap.get(key).add(str);
        }
        return new ArrayList<>(groupAnagramMap.values());
    }

    public double computeKey(String s) {
        char[] sCharArray = s.toCharArray();
        double sProduct = 1;
        for (int i = 0; i < s.length(); i++) {
            sProduct *= CHAR_TO_PRIME_MAP.get(sCharArray[i]);
        }
        System.out.printf("s = " + s + " product = " + sProduct);
        return sProduct;
    }
}
