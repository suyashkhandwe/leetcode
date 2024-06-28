# Problem 68. Text Justification

> [!NOTE]
> [68. Text Justification](https://leetcode.com/problems/text-justification/description/?envType=study-plan-v2&envId=top-interview-150)

Given an array of strings `words` and a width `maxWidth`, format the text such that each line has exactly `maxWidth` characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces `' '` when necessary so that each line has exactly `maxWidth` characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left-justified, and no extra space is inserted between words.

**Note:**

- A word is defined as a character sequence consisting of non-space characters only.
- Each word's length is guaranteed to be greater than `0` and not exceed `maxWidth`.
- The input array words contains at least one word.

### Custom Judge:

### Examples

#### Example 1:

> **Input**: `words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16`
> **Output**:
> `[`
> `   "This    is    an",`
> `   "example  of text",`
> `   "justification.  "`
> `]`

#### Example 2:

> **Input**: `words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16`
> **Output**: 
> `[`
> `  "What   must   be",`
> `  "acknowledgment  ",`
> `  "shall be        "`
> `]`
> **Explanation**:
> - `Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.`
> - `Note that the second line is also left-justified because it contains only one word.`

#### Example 3:

> **Input**: `words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20`
> **Output**: 
> `[`
> `  "Science  is  what we",`
> `  "understand      well",`
> `  "enough to explain to",`
> `  "a  computer.  Art is",`
> `  "everything  else  we",`
> `  "do                  "`
> `]`

#### Constraints:

- `1 <= words.length <= 300`
- `1 <= words[i].length <= 20`
- `words[i] consists of only English letters and symbols.`
- `1 <= maxWidth <= 100`
- `words[i].length <= maxWidth`

## Solutions

### Solution 1

```java
public List<String> fullJustify(String[] words, int maxWidth) {
    List<List<StringBuilder>> justifiedLineBuilders = new ArrayList<>();
    List<StringBuilder> builders = new ArrayList<>();
    int idx = 0;
    int lineWidth = 0;
    while (idx < words.length) {
        int currentWidth = lineWidth + builders.size() + words[idx].length();
        if (words[idx].length() == maxWidth && currentWidth < maxWidth) {
            builders.add(new StringBuilder().append(words[idx]));
            lineWidth += words[idx].length();
            justifiedLineBuilders.add(builders);
            builders = new ArrayList<>();
            idx++;
        } else if (currentWidth <= maxWidth) {
            builders.add(new StringBuilder().append(words[idx]));
            lineWidth += words[idx].length();
            idx++;
        } else {
            justifiedLineBuilders.add(builders);
            builders = new ArrayList<>();
            lineWidth = 0;
        }
    }
    if (!builders.isEmpty()) {
        justifiedLineBuilders.add(builders);
    }
    for (int i = 0; i < justifiedLineBuilders.size(); i++) {
        justifiedLineBuilders.set(i,
                justify(justifiedLineBuilders.get(i), maxWidth,
                        i == justifiedLineBuilders.size() - 1
                                || justifiedLineBuilders.get(i).size() == 1)
        );
    }
    return justifiedLineBuilders.stream()
            .filter(it -> !it.isEmpty())
            .map(it -> String.join("", it))
            .collect(Collectors.toList());
}

private List<StringBuilder> justify(List<StringBuilder> builders, int maxWidth, boolean leftJustify) {
    int lineWidth = builders.stream()
            .map(it -> it.length())
            .reduce(0, Integer::sum);
    int i = 0;
    while (i < builders.size() - 1) {
        builders.get(i).append(" ");
        lineWidth++;
        if (lineWidth == maxWidth) {
            break;
        }
        i++;
        if (i == builders.size() - 1 && !leftJustify) {
            i = 0;
        }
    }
    if (leftJustify) {
        builders.add(new StringBuilder());
        while (lineWidth < maxWidth) {
            builders.get(builders.size() - 1).append(" ");
            lineWidth++;
        }
    }
    return builders;
}
```

#### Complexities

- `Time Complexity`: O(n^2)
    - iterate through all the words once, and then `justify` iterates though each word again in the worst case scenario.
- `Space Complexity`: O(n)
    - need `n` additional space to store the builders for each word in the input.

### Solution 2

```java
private static final String SPACE = " ";

public List<String> fullJustify(String[] words, int maxWidth) {
    List<String> justifiedLines = new ArrayList<>();
    List<String> wordsInLine = new ArrayList<>();
    int idx = 0;
    int lineLength = 0;
    //Iterate through the words and add them to each line.
    while (idx < words.length) {
        //Check the potential length of the line : lineLength + wordsInLine.size() + words[idx].length()
        if (lineLength + wordsInLine.size() + words[idx].length() <= maxWidth) {
            wordsInLine.add(words[idx]);
            //Track the length of the line
            lineLength += words[idx].length();
            idx++;
        } else {
            // If the length of the line has reached the maxWidth, justify and add it to the line
            // Since this is not the last line, we don't need to force left justification.
            justifiedLines.add(justify(wordsInLine, lineLength, maxWidth, false));
            wordsInLine.clear();
            ;
            lineLength = 0;
        }
    }
    // Add the last set of words from wordsInLine after justification to justifiedLines.
    // Force left justification for the last line.
    justifiedLines.add(justify(wordsInLine, lineLength, maxWidth, true));
    return justifiedLines;
}

private String justify(List<String> wordsInLine, int wordsLength, int maxWidth, boolean forceLeftJustification) {
    StringBuilder justifiedLineBuilder = new StringBuilder();
    int i = 0;
    // Force left justification if forceLeftJustification is true or there is only one word in the line.
    if (wordsInLine.size() == 1 || forceLeftJustification) {
        while (i < wordsInLine.size()) {
            justifiedLineBuilder.append(wordsInLine.get(i));
            // Count spaces that need to be added
            int spaceCount = maxWidth - wordsLength;
            // If left justification is required but the number if words are more than 1,
            // and we are not on the last word, add only one space
            // Else, continue using the computed spaces.
            if (forceLeftJustification && wordsInLine.size() > 1 && i != wordsInLine.size() - 1) {
                spaceCount = 1;
                wordsLength++;
            }
            if (spaceCount > 0) {
                justifiedLineBuilder.append(SPACE.repeat(spaceCount));
            }
            i++;
        }
        return justifiedLineBuilder.toString();
    }
    // When left justification is not required and the number of words > 1,
    // Compute the number of spaces that must be added between each pair of words - would be at least 1.
    // Compute extra (left over spaces) that would be added from left to right in addition to the equal space.
    int equallyDistributedSpaces = (maxWidth - wordsLength) / (wordsInLine.size() - 1);
    int extraSpaces = (maxWidth - wordsLength) % (wordsInLine.size() - 1);
    // Iterate to the last but one word.
    while (i < wordsInLine.size() - 1) {
        // Append the words with the equally divided spaces + extra spaces.
        justifiedLineBuilder.append(wordsInLine.get(i++))
                .append(SPACE.repeat(equallyDistributedSpaces + (extraSpaces-- > 0 ? 1 : 0)));
    }
    // Append the last word.
    justifiedLineBuilder.append(wordsInLine.get(wordsInLine.size() - 1));
    return justifiedLineBuilder.toString();
}
```

#### Complexities

- `Time Complexity`: O(n)
    - iterate through words only once
- `Space Complexity`: O(1)
    - use constant extra space regardless of the size of the input words

> [!TIP]
> The second solution is much faster as it iterates through only once.
 