package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
class Solution {

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
}
