# Problem 151. Reverse Words in a String

> [!NOTE]
> [151. Reverse Words in a String](https://leetcode.com/problems/reverse-words-in-a-string/description/?envType=study-plan-v2&envId=top-interview-150)

Given an input string `s`, reverse the order of the words.
A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
Return a string of the words in reverse order concatenated by a single space.

Note that `s` may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

### Examples

#### Example 1:

> **Input**: `s = "the sky is blue"`
> **Output**: `"blue is sky the"`

#### Example 2:

> **Input**: `s = "  hello world  "`
> **Output**: `"world hello"`
> **Explanation**: `Your reversed string should not contain leading or trailing spaces.`

#### Example 3:

> **Input**: `s = "a good   example"`
> **Output**: `"example good a"`
> **Explanation**: `You need to reduce multiple spaces between two words to a single space in the reversed string.`

#### Constraints:

- `1 <= s.length <= 104`
- `s contains English letters (upper-case and lower-case), digits, and spaces ' '.`
- `There is at least one word in s.`

## Solutions

### Solution 1

```java
public String reverseWords(String s) {
    StringBuilder reversed = new StringBuilder();
    String[] parts = s.split(" ");
    for (int i = parts.length - 1; i >= 0; i--) {
        String trimmedPart = parts[i].trim();
        if (!trimmedPart.isEmpty()) {
            reversed.append(trimmedPart).append(" ");
        }
    }
    return reversed.toString().trim();
}
```

#### Complexities

- `Time Complexity`: O(n)
    - split the input string into an array of words, and then iterate through each word in reverse order to build the reversed string.
- `Space Complexity`: O(n)
    - a StringBuilder to build the reversed string, which can potentially be the same length as the input string.
    - Additionally, an array of words by splitting the input string, which also contributes to the space complexity.

> [!TIP]
> It is possible to iterate from left and right (2 pointers) and reverse each word every time a word is found.
> It would take O(1) space, however the solution is complex to follow and uses a lot of code to achieve something that can be achieved by using in-built functions.
