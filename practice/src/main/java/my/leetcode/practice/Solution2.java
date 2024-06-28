package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
class Solution2 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<List<StringBuilder>> jlb = new ArrayList<>();
        List<StringBuilder> b = new ArrayList<>();
        int idx = 0;
        int lineWidth = 0;
        while (idx < words.length) {
            if (lineWidth + b.size() + words[idx].length() <= maxWidth) {
                b.add(new StringBuilder().append(words[idx]));
                lineWidth += words[idx].length();
                idx++;
            } else {
                jlb.add(b);
                b = new ArrayList<>();
                lineWidth = 0;
            }
        }
        jlb.add(b);
        for (int i = 0; i < jlb.size(); i++) {
            jlb.set(i,
                    justify(jlb.get(i), maxWidth,
                            i == jlb.size() - 1
                                    || jlb.get(i).size() == 1)
            );
        }
        return jlb.stream()
                .filter(it -> !it.isEmpty())
                .map(it -> String.join("", it))
                .collect(Collectors.toList());
    }

    private List<StringBuilder> justify(List<StringBuilder> b, int maxWidth, boolean lj) {
        int lineWidth = b.stream()
                .map(it -> it.length())
                .reduce(0, Integer::sum);
        int i = 0;
        while (i < b.size() - 1 || lineWidth < maxWidth) {
            b.get(i).append(" ");
            lineWidth++;
            i++;
            if (i == b.size() - 1 && !lj) {
                i = 0;
            }
        }
        if (lj) {
            b.add(new StringBuilder());
            while (lineWidth < maxWidth) {
                b.get(b.size() - 1).append(" ");
                lineWidth++;
            }
        }
        return b;
    }
}
