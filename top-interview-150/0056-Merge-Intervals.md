# Problem 56. Merge Intervals

> [!NOTE]
> [56. Merge Intervals](https://leetcode.com/problems/merge-intervals/description/?envType=study-plan-v2&envId=top-interview-150)

Given an array of `intervals` where `intervals[i] = [starti, endi]`, merge all overlapping intervals, and return _an array of the non-overlapping intervals that cover all the intervals in the input_.

### Examples

#### Example 1:

> **Input**: `intervals = [[1,3],[2,6],[8,10],[15,18]]`<br/>
> **Output**: `[[1,6],[8,10],[15,18]]`<br/>
> **Explanation**: `Since intervals [1,3] and [2,6] overlap, merge them into [1,6].`

#### Example 2:

> **Input**: `intervals = [[1,4],[4,5]]`<br/>
> **Output**: `[[1,5]]`<br/>
> **Explanation**: `Intervals [1,4] and [4,5] are considered overlapping.`

#### Constraints:

- `1 <= intervals.length <= 10⁴`
- `intervals[i].length == 2`
- `0 <= startᵢ <= endᵢ <= 10⁴`

## Solutions

### Solution 1

```java
public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, (interval1, interval2) -> {
        int idx0ComparisonResult = Double.compare(interval1[0], interval2[0]);
        if (idx0ComparisonResult == 0) {
            return Double.compare(interval1[1], interval2[1]);
        }
        return idx0ComparisonResult;
    });
    List<int[]> mergedIntervals = new ArrayList<>();
    mergedIntervals.add(intervals[0]);
    int j = 0;
    for (int i = 1; i < intervals.length; i++) {
        int start = mergedIntervals.get(j)[0];
        int end = mergedIntervals.get(j)[1];
        int likelyStart = intervals[i][0];
        int likelyEnd = intervals[i][1];

        if (between(likelyStart, start, end)
                || between(start, likelyStart, likelyEnd)) {
            start = Math.min(start, likelyStart);
            end = Math.max(end, likelyEnd);
            mergedIntervals.set(j, new int[]{start, end});
        } else {
            j++;
            mergedIntervals.add(new int[]{likelyStart, likelyEnd});
        }
    }

    int[][] merged = new int[mergedIntervals.size()][2];
    int i = 0;
    for (int[] mergedInterval : mergedIntervals) {
        merged[i++] = mergedInterval;
    }
    return merged;
}

private boolean between(int num, int startInclusive, int endInclusive) {
    return num >= startInclusive && num <= endInclusive;
}
```

#### Complexities

- `Time Complexity`: O(n * log n)
    - The sorting of the intervals array takes O(n * log n) time complexity.
    - Then, iterating through the sorted array takes O(n) time complexity.
    - Within the loop, the comparison and merging of intervals take constant time operations.
- `Space Complexity`: O(n)
    - This is because we are storing the merged intervals in a list, which can potentially contain all intervals if none of them overlap.

### Solution 2

```java
public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, (interval1, interval2) -> {
        int idx0ComparisonResult = Double.compare(interval1[0], interval2[0]);
        if (idx0ComparisonResult == 0) {
            return Double.compare(interval1[1], interval2[1]);
        }
        return idx0ComparisonResult;
    });

    int[][] mergedIntervals = new int[intervals.length][2];
    mergedIntervals[0] = intervals[0];
    int j = 0;
    for (int i = 1; i < intervals.length; i++) {
        if (mergedIntervals[j][1] >= intervals[i][0]) {
            mergedIntervals[j][1] = Math.max(mergedIntervals[j][1], intervals[i][1]);
        } else {
            mergedIntervals[++j] = intervals[i];
        }
    }
    return Arrays.copyOfRange(mergedIntervals, 0, j + 1);
}
```

#### Complexities

- `Time Complexity`: O(n * log n)
  - The sorting of the intervals array takes O(n * log n) time complexity.
  - Then, iterating through the sorted array takes O(n) time complexity.
  - Within the loop, the comparison and merging of intervals take constant time operations.
- `Space Complexity`: O(n)
  - This is because we are creating a new array to store the merged intervals.

> [!TIP]
> Although `Solution 1` and `Solution 2` have the same complexities, `Solution 2` is a simplified implementation and performs better.
> Since the `intervals` arrays is already sorted, the comparison can be done based on the `end range of previous` to the `start range of next` interval.
> Additionally, `Solution 2` uses manipulation of a 2-D int array which is a little bit faster even when the complexity is the same as handling a `List`.
