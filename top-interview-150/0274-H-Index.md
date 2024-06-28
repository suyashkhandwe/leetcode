# Problem 274. H-Index

> [!NOTE]
> [274. H-Index](https://leetcode.com/problems/h-index/description/?source=submission-noac)

Given an array of integers `citations` where `citations[i]` is the number of citations a researcher received for their `ith` paper, return the researcher's `h-index`.

According to the [definition of h-index on Wikipedia](https://en.wikipedia.org/wiki/H-index): The `h-index` is defined as the `maximum value` of `h` such that the given researcher has `published at least h papers` that have each been `cited at least h times`.

### Examples

#### Example 1:

> **Input**: `citations = [3,0,6,1,5]`<br/>
> **Output**: `3`<br/>
> **Explanation**: 
> - `[3,0,6,1,5]` means the researcher has `5` papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
> - Since the researcher has `3` papers with at least `3` citations each and the remaining two with no more than `3` citations each, their `h-index` is `3`.

#### Example 2:

> **Input**: `citations = [1,3,1]`<br/>
> **Output**: `1`<br/>
> **Explanation**:

#### Constraints:

- `n == citations.length`
- `1 <= n <= 5000`
- `0 <= citations[i] <= 1000`

## Solutions

### Solution 1

```java
public int hIndex(int[] citations) {
    int hIdx = 0;
    Arrays.sort(citations);
    for (int i = 0; i < citations.length; i++) {
        int papersWithThiCitation = citations.length - i;
        if (citations[i] >= papersWithThiCitation) {
            return papersWithThiCitation;
        }
    }
    return hIdx;
}
```

#### Complexities

- `Time Complexity`: O(n log(n))
    - sorting
- `Space Complexity`: O(1)
    - constant space for storage

### Solution 2

```java
public int hIndex(int[] citations) {
    int[] paperCountByCitation = new int[citations.length + 1];
    for (int c : citations) {
        if (c > citations.length) {
            // If the number of citations for the current paper is greater than the number of papers, this can't be h-idx.
            // But this paper would be counted towards another lower citation count.
            paperCountByCitation[citations.length]++;
        } else {
            // Increment the citation count for the ith paper.
            paperCountByCitation[c]++;
        }
    }
    int totalPapersWithMinCitations = 0;
    int hIdx = 0;
    for (int potentialHIdx = citations.length; potentialHIdx >= 0; potentialHIdx--) {
        totalPapersWithMinCitations += paperCountByCitation[potentialHIdx];
        if (totalPapersWithMinCitations >= potentialHIdx) {
            hIdx = potentialHIdx;
            break;
        }
    }
    return hIdx;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - iterate through the citations array once to populate the paperCountByCitation
- `Space Complexity`: O(n)
    - creates an additional array paperCountByCitation of size n+1 to store the count of papers

> [!TIP]
> The second solution counts the papers with a given citation. If the `number of citations is > the number of papaers`, it is counted in a overall bucket position of `n+1`. Then it adds the papaers from the n+1 backwards and returns the value where the total paper count is `>=` the index of the paper.