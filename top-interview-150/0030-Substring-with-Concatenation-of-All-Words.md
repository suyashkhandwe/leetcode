# Problem 30. Substring with Concatenation of All Words

> [!NOTE]
> [30. Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/?envType=study-plan-v2&envId=top-interview-150)

You are given a string `s` and an array of strings `words`. All the strings of `words` are of *the same length*.

A **concatenated string** is a string that exactly contains all the strings of any permutation of `words` concatenated.

- For example, if `words` = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are all concatenated strings. "acdbef" is not a concatenated string because it is not the concatenation of any permutation of `words`.

Return an array of the starting indices of all the concatenated substrings in s. You can return the answer in any order.

### Examples

#### Example 1:

> **Input**: `s = "barfoothefoobarman", words = ["foo","bar"]`<br/>
> **Output**: `[0,9]`<br/>
> **Explanation**: 
> - The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
> - The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.

#### Example 2:

> **Input**: `s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]`<br/>
> **Output**: `[]`<br/>
> **Explanation**: There is no concatenated substring.

#### Example 3:

> **Input**: `s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]`<br/>
> **Output**: `[6,9,12]`<br/>
> **Explanation**:
> - The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"].
> - The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"].
> - The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"].
 
#### Constraints:

- `1 <= s.length <= 104`
- `1 <= words.length <= 5000`
- `1 <= words[i].length <= 30`
- `s and words[i] consist of lowercase English letters.`

## Solutions

### Solution 1

```java
public List<Integer> findSubstring(String s, String[] words) {
    List<Integer> indexes = new ArrayList<>();

    Map<String, Integer> wordCountMap = new HashMap<>();
    for (String word : words) {
        wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
    }

    int wordLen = words[0].length();
    int requiredSubStringLen = wordLen * words.length;
    int sIdx = 0;
    while (sIdx <= s.length() - requiredSubStringLen) {
        String subString = s.substring(sIdx, sIdx + requiredSubStringLen);
        Map<String, Integer> subStringMap = new HashMap<>();

        for (int i = 0; i < subString.length(); i = i + wordLen) {
            String subStringWord = subString.substring(i, i + wordLen);
            subStringMap.put(subStringWord, subStringMap.getOrDefault(subStringWord, 0) + 1);
        }
        boolean isValidSubStr = true;
        for (Map.Entry<String, Integer> wordCountEntry : wordCountMap.entrySet()) {
            if (Optional.ofNullable(subStringMap.get(wordCountEntry.getKey()))
                    .orElse(-1) != wordCountEntry.getValue().intValue()) {
                isValidSubStr = false;
                break;
            }
        }
        if (isValidSubStr) {
            indexes.add(sIdx);
        }
        sIdx++;
    }
    return indexes;
}
```

#### Complexities

- `Time Complexity`: O(n * m)
    - `n` is the length of the input string `s`
    - `m` is the number of words in the input array `words`
    - iterate through the input string `s` once and for each substring of length `requiredSubStringLen`, and iterate through it to count the occurrences of each word.
- `Space Complexity`: O(m)
    - `m` is the number of words in the input array `words` and we use a `HashMap` to store the word count for each word

### Solution 2

```java
public List<Integer> findSubstring(String s, String[] words) {
    List<Integer> indexes = new ArrayList<>();
    Arrays.sort(words);
    int wordLen = words[0].length();
    int requiredSubStringLen = wordLen * words.length;
    int sIdx = 0;
    while (sIdx <= s.length() - requiredSubStringLen) {
        String subString = s.substring(sIdx, sIdx + requiredSubStringLen);
        String[] subStrings = new String[words.length];

        for (int i = 0, j = 0; i < subString.length(); i = i + wordLen) {
            subStrings[j++] = subString.substring(i, i + wordLen);
        }
        Arrays.sort(subStrings);
        if (Arrays.equals(words, subStrings)) {
            indexes.add(sIdx);
        }
        sIdx++;
    }
    return indexes;
}
```

#### Complexities

- `Time Complexity`: O(n * m * log(m))
  - `n` is the length of the input string `s`
  - `m` is the number of words in the input array `words`
  - iterate through the input string `s` and for each substring of length `requiredSubStringLen`
    - also sort the `words` array and the `subStrings` array, which takes `O(m*log(m))` time
- `Space Complexity`: O(m)
  - `m` is the number of words in the input array `words` and we use a `HashMap` to store the word count for each word

### Solution 3

```java
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
```

#### Complexities

- `Time Complexity`: O(n * m)
  - `n` is the length of the input string `s`
  - `m` is the number of words in the input array `words`
  - iterate through the input string `s` once and for each substring of length `requiredSubStringLen`, and iterate through it to count the occurrences of each word.
- `Space Complexity`: O(m)
  - `m` is the number of words in the input array `words` and we use a `HashMap` to store the word count for each word

> [!TIP]
> `Solution 3` runs fine locally, but fails in the submission.
> There are a few solutions which are significantly fast, but extremely complex.
