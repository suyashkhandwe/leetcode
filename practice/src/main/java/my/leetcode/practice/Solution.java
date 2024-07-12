package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        int wordLen = words[0].length();
        int requiredSubStringLen = wordLen * words.length;
        Map<String, Integer> wordsCounts = new HashMap<>();
        for (String word : words) {
            wordsCounts.put(word, wordsCounts.getOrDefault(word, 0) + 1);
        }
        return IntStream.rangeClosed(0, s.length() - requiredSubStringLen)
                .parallel()
                .boxed()
                .map(sIdx -> CompletableFuture.supplyAsync(() ->
                        indexToAdd(sIdx, requiredSubStringLen, s, wordLen, new HashMap<>(wordsCounts))
                ))
                .map(CompletableFuture::join)
                .filter(it -> it > -1)
                .collect(Collectors.toList());
    }

    private Integer indexToAdd(int sIdx, int requiredSubStringLen, String s, int wordLen, Map<String, Integer> wordsCounts) {
        for (int subSIdx = sIdx; subSIdx < sIdx + requiredSubStringLen; subSIdx = subSIdx + wordLen) {
            String likelyWord = s.substring(subSIdx, subSIdx + wordLen);
            Integer countOfLikelyWord = wordsCounts.get(likelyWord);
            if (countOfLikelyWord != null) {
                if (countOfLikelyWord == 1) {
                    wordsCounts.remove(likelyWord);
                    if (wordsCounts.isEmpty()) {
                        return sIdx;
                    }
                } else {
                    wordsCounts.put(likelyWord, countOfLikelyWord - 1);
                }
            } else {
                break;
            }
        }
        return -1;
    }
}
