# Problem 383. Ransom Note

> [!NOTE]
> [383. Ransom Note](https://leetcode.com/problems/ransom-note/description/?envType=study-plan-v2&envId=top-interview-150)

Given two strings `ransomNote` and `magazine`, return `true` if `ransomNote` can be constructed by using the letters from `magazine` and `false` otherwise.

Each letter in `magazine` can only be used once in `ransomNote`.

### Examples

#### Example 1:

> **Input**: `ransomNote = "a", magazine = "b"`<br/>
> **Output**: `false`<br/>

#### Example 2:

> **Input**: `ransomNote = "aa", magazine = "ab"`<br/>
> **Output**: `false`<br/>

#### Example 3:

> **Input**: `ransomNote = "aa", magazine = "aab"`<br/>
> **Output**: `true`<br/>

#### Constraints:

- `1 <= ransomNote.length, magazine.length <= 105`
- `ransomNote and magazine consist of lowercase English letters.`

## Solutions

### Solution 1

```java
public boolean canConstruct(String ransomNote, String magazine) {
    Map<Character, Integer> ransomNoteFrequencies = new HashMap<>();
    Map<Character, Integer> magazineFrequencies = new HashMap<>();
    for (Character c : ransomNote.toCharArray()) {
        ransomNoteFrequencies.put(c, ransomNoteFrequencies.getOrDefault(c, 0) + 1);
    }
    for (Character c : magazine.toCharArray()) {
        magazineFrequencies.put(c, magazineFrequencies.getOrDefault(c, 0) + 1);
    }
    for (Map.Entry<Character, Integer> ransomNoteChar : ransomNoteFrequencies.entrySet()) {
        if (!magazineFrequencies.containsKey(ransomNoteChar.getKey()) || magazineFrequencies.get(ransomNoteChar.getKey()) < ransomNoteChar.getValue()) {
            return false;
        }
    }
    return true;
}
```

#### Complexities

- `Time Complexity`: O(n + m)
    - where `n` is the length of the `ransomNote` string and `m` is the length of the `magazine` string.
    - This is because we iterate through both strings to calculate the frequencies of characters in each string.
- `Space Complexity`: O(n + m)
  - where `n` is the number of unique characters in the `ransomNote` string and `m` is the number of unique characters in the `magazine` string.
  - This is because we store the frequencies of characters in both strings in separate HashMaps.

### Solution 2

```java
public boolean canConstruct(String ransomNote, String magazine) {
    int[] ransomNoteFrequencies = new int[128];
    int[] magazineFrequencies = new int[128];
    for (Character c : ransomNote.toCharArray()) {
        ransomNoteFrequencies[c]++;
    }
    for (Character c : magazine.toCharArray()) {
        magazineFrequencies[c]++;
    }
    for (int i = 0; i < ransomNoteFrequencies.length; i++) {
        if (magazineFrequencies[i] < ransomNoteFrequencies[i]) {
            return false;
        }
    }
    return true;
}
```

#### Complexities

- `Time Complexity`: O(n)
  - where `n` is the length of the `ransomNote` string.
  - This is because we iterate through both the `ransomNote` and `magazine` strings once to calculate the frequencies of characters.
- `Space Complexity`: O(1)
  - This is because the size of the frequency arrays (128) is constant and does not depend on the input size.

### Solution 3

```java
public boolean canConstruct(String ransomNote, String magazine) {
    if (ransomNote.length() > magazine.length()) {
        return false;
    }
    int[] characterFrequencies = new int[26];
    for (char c : magazine.toCharArray()) {
        characterFrequencies[c - 97]++;
    }
    for (char c : ransomNote.toCharArray()) {
        if (characterFrequencies[c - 97] == 0) {
            return false;
        }
        characterFrequencies[c - 97]--;
    }
    return true;
}
```

#### Complexities

- `Time Complexity`: O(n)
  - where `n` is the length of the `ransomNote` string.
  - This is because we iterate through the `magazine` string to count the frequencies of each character, and then iterate through the `ransomNote` string to check if we have enough characters in the `magazine` to construct the `ransomNote`.
- `Space Complexity`: O(1)
  - This is because the size of the `characterFrequencies` array is fixed at `26` (the number of lowercase letters in the English alphabet) regardless of the input size.

> [!TIP]
> `Solution 2` and `Solution 3` have the same complexities but `Solution 3` is marginally faster.
