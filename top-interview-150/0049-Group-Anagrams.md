# Problem 49. Group Anagrams

> [!NOTE]
> [49. Group Anagrams](https://leetcode.com/problems/group-anagrams/description/?envType=study-plan-v2&envId=top-interview-150)

Given an array of strings `strs`, group the **anagrams** together. You can return the answer in **any order**.

An **Anagram** is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

### Examples

#### Example 1:

> **Input**: `strs = ["eat","tea","tan","ate","nat","bat"]`<br/>
> **Output**: `[["bat"],["nat","tan"],["ate","eat","tea"]]`<br/>

#### Example 2:

> **Input**: `strs = [""]`<br/>
> **Output**: `[[""]]`<br/>

#### Example 3:

> **Input**: `strs = ["a"]`<br/>
> **Output**: `[["a"]]`<br/>

#### Constraints:

- `1 <= strs.length <= 104`
- `0 <= strs[i].length <= 100`
- `strs[i] consists of lowercase English letters.`

## Solutions

### Solution 1

```java
public List<List<String>> groupAnagrams(String[] strs) {
    List<List<String>> groupAnagrams = new ArrayList<>();

    boolean[] strTracked = new boolean[strs.length];
    Arrays.fill(strTracked, false);

    for (int i = 0; i < strs.length; i++) {
        List<String> groupAnagram = new ArrayList<>();
        groupAnagram.add(strs[i]);
        if (strTracked[i]) {
            continue;
        } else {
            strTracked[i] = true;
        }
        for (int j = 0; j < strs.length; j++) {
            if (i != j) {
                if (strTracked[j]) {
                    continue;
                }
                if (isAnagram(strs[i], strs[j])) {
                    groupAnagram.add(strs[j]);
                    strTracked[j] = true;
                }
            }
        }
        if (!groupAnagrams.contains(groupAnagram)) {
            groupAnagrams.add(groupAnagram);
        }
    }
    return groupAnagrams;
}

public boolean isAnagram(String s, String t) {
    if (s == null || t == null || s.length() != t.length()) {
        return false;
    }
    int[] charCounter = new int[26];

    char[] sCharArray = s.toCharArray();
    char[] tCharArray = t.toCharArray();
    for (int i = 0; i < s.length(); i++) {
        charCounter[sCharArray[i] - 'a']++;
        charCounter[tCharArray[i] - 'a']--;
    }
    for (int charCount : charCounter) {
        if (charCount != 0) {
            return false;
        }
    }
    return true;
}
```

#### Complexities

- `Time Complexity`: O(n^2 * m)
    - where `n` is the number of strings in the input array and `m` is the average length of the strings.
    - This is because for each string in the input array, we iterate through all other strings to check if they are anagrams, resulting in a nested loop.
- `Space Complexity`: O(n)
    - This is because we are storing the result in a list of lists, where each inner list contains the anagram group.
    - Additionally, we are using a boolean array to track which strings have already been processed.

### Solution 2

```java
public List<List<String>> groupAnagrams(String[] strs) {
    List<List<String>> groupAnagrams = new ArrayList<>();
    groupAnagrams.add(new ArrayList<>());
    groupAnagrams.get(0).add(strs[0]);

    for (int i = 1; i < strs.length; i++) {
        boolean addedToExisting = false;
        for (List<String> groupAnagram : groupAnagrams) {
            if (isAnagram(groupAnagram.get(0), strs[i])) {
                groupAnagram.add(strs[i]);
                addedToExisting = true;
                break;
            }
        }
        if (!addedToExisting) {
            List<String> newGroupAnagram = new ArrayList<>();
            newGroupAnagram.add(strs[i]);
            groupAnagrams.add(newGroupAnagram);
        }
    }
    return groupAnagrams;
}

public boolean isAnagram(String s, String t) {
    if (s == null || t == null || s.length() != t.length()) {
        return false;
    }
    int[] charCounter = new int[26];

    char[] sCharArray = s.toCharArray();
    char[] tCharArray = t.toCharArray();
    for (int i = 0; i < s.length(); i++) {
        charCounter[sCharArray[i] - 'a']++;
        charCounter[tCharArray[i] - 'a']--;
    }
    for (int charCount : charCounter) {
        if (charCount != 0) {
            return false;
        }
    }
    return true;
}
```

#### Complexities

- `Time Complexity`: O(n * m * log m)
    - where `n` is the number of strings in the input array
    - and `m` is the maximum length of a string.
    - This is because for each string in the input array, we iterate through the list of group anagrams to check if it is an anagram of any existing group, which takes O(m * log m) time due to sorting.
- `Space Complexity`: O(n * m)
    - This is because we are storing each string in a group anagram list, and there can be up to `n` such lists, each containing up to `m` strings.

### Solution 3

```java
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
    Map<Double, List<String>> groupAnagramMap = new HashMap<>();
    for (int i = 0; i < strs.length; i++) {
        double key = computeKey(strs[i]);
        if (groupAnagramMap.containsKey(key)) {
            groupAnagramMap.get(key).add(strs[i]);
        } else {
            groupAnagramMap.put(key, new ArrayList<>());
            groupAnagramMap.get(key).add(strs[i]);
        }
    }
    return new ArrayList<>(groupAnagramMap.values());
}

public double computeKey(String s) {
    char[] sCharArray = s.toCharArray();
    double sProduct = 1;
    for (int i = 0; i < s.length(); i++) {
        sProduct *= CHAR_TO_PRIME_MAP.get(sCharArray[i]);
    }
    return sProduct;
}
```

#### Complexities

- `Time Complexity`: O(n * m)
    - where `n` is the number of strings in the input array
    - and `m` is the average length of each string.
    - This is because we iterate through each string in the input array and compute a key for each string, which involves iterating through each character in the string.
- `Space Complexity`: O(n)
    - where `n` is the number of strings in the input array.
    - This is because we use a `HashMap` to store the grouped anagrams, which can potentially store all the strings in the input array if they are all anagrams of each other.

### Solution 4

```java
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
```

#### Complexities

- `Time Complexity`: O(n * m * log(m))
    - where `n` is the number of strings in the input array
    - and `m` is the average length of each string.
    - This is because for each string, we are sorting its characters, which takes O(m * log(m)) time complexity.
- `Space Complexity`: O(n * m)
    - where `n` is the number of strings in the input array
    - and `m` is the average length of each string.
    - This is because we are storing the sorted strings in a HashMap, which can potentially store all the input strings.

> [!TIP]
> - `Solution 1` is brute force and has the highest complexities.
> - `Solution 2` is relatively better, but still not the most optimum yet.
> - `Solution 3` is the best solution from complexity standpoint, however, it does not perform the best.
> - `Solution 4` has a little higher in complexity, but it performs the best. We are not using a custom `anagram` method here and instead using sorting the string for key in the map.
