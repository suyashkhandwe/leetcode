# Problem 57. Insert Interval

> [!NOTE]
> [57. Insert Interval](https://leetcode.com/problems/insert-interval/description/?envType=study-plan-v2&envId=top-interview-150)

You are given an array of non-overlapping intervals `intervals` where `intervals[i] = [startᵢ, endᵢ]` represent the start and the end of the `iᵗʰ` interval and `intervals` is sorted in ascending order by `startᵢ`. You are also given an interval `newInterval = [start, end]` that represents the start and end of another interval.

Insert `newInterval` into `intervals` such that `intervals` is still sorted in ascending order by `startᵢ` and `intervals` still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return `intervals` _after the insertion_.

**Note** that you don't need to modify `intervals` in-place. You can make a new array and return it.

### Examples

#### Example 1:

> **Input**: `intervals = [[1,3],[6,9]], newInterval = [2,5]`<br/>
> **Output**: `[[1,5],[6,9]]`

#### Example 2:

> **Input**: `intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]`<br/>
> **Output**: `[[1,2],[3,10],[12,16]]`<br/>
> **Explanation**: `Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].`

#### Constraints:

- `0 <= intervals.length <= 10⁴`
- `intervals[i].length == 2`
- `0 <= startᵢ <= endᵢ <= 10⁵`
- `intervals` is sorted by `startᵢ` in **ascending** order.
- `newInterval.length == 2`
- `0 <= start <= end <= 10⁵`

## Solutions

### Solution 1

```java
public int[][] insert(int[][] intervals, int[] newInterval) {

    int[][] updatedIntervals = new int[intervals.length + 1][2];
    boolean inserted = false;
    int updatedIntervalsIdx = 0;
    for (int i = 0; i < intervals.length; ) {
        if (newInterval[0] <= intervals[i][0] && !inserted) {
            updatedIntervals[updatedIntervalsIdx++] = newInterval;
            inserted = true;
        } else {
            updatedIntervals[updatedIntervalsIdx++] = intervals[i++];
        }
    }
    if (!inserted) {
        updatedIntervals[updatedIntervalsIdx] = newInterval;
    }


    int[][] mergedIntervals = new int[updatedIntervals.length][2];
    mergedIntervals[0] = updatedIntervals[0];
    int j = 0;
    for (int i = 1; i < updatedIntervals.length; i++) {
        if (mergedIntervals[j][1] >= updatedIntervals[i][0]) {
            mergedIntervals[j][1] = Math.max(mergedIntervals[j][1], updatedIntervals[i][1]);
        } else {
            mergedIntervals[++j] = updatedIntervals[i];
        }
    }
    return Arrays.copyOfRange(mergedIntervals, 0, j + 1);
}
```

#### Complexities

- `Time Complexity`: O(n)
    - where `n` is the number of `intervals` in the input array.
    - This is because we iterate through the `intervals` array once to insert the new interval and then iterate through the updated intervals array to merge overlapping intervals.
- `Space Complexity`: O(n)
    - This is because we create a new array to store the updated `intervals`, which has a maximum size of `n+1`.
    - Additionally, we create another array to store the merged intervals, which also has a maximum size of `n`.

### Solution 2

```java
public int[][] insert(int[][] intervals, int[] newInterval) {

    int[][] updatedIntervals = new int[intervals.length + 1][2];
    boolean inserted = false;
    int updatedIntervalsIdx = 0;
    for (int i = 0; i < intervals.length; ) {
        if (!inserted && newInterval[0] <= intervals[i][0]) {
            updatedIntervals[updatedIntervalsIdx++] = newInterval;
            inserted = true;
        } else {
            updatedIntervals[updatedIntervalsIdx++] = intervals[i++];
        }
    }
    if (!inserted) {
        updatedIntervals[updatedIntervalsIdx] = newInterval;
    }


    int[][] mergedIntervals = new int[updatedIntervals.length][2];
    mergedIntervals[0] = updatedIntervals[0];
    int j = 0;
    for (int i = 1; i < updatedIntervals.length; i++) {
        if (mergedIntervals[j][1] >= updatedIntervals[i][0]) {
            mergedIntervals[j][1] = Math.max(mergedIntervals[j][1], updatedIntervals[i][1]);
        } else {
            mergedIntervals[++j] = updatedIntervals[i];
        }
    }
    return Arrays.copyOfRange(mergedIntervals, 0, j + 1);
}
```

#### Complexities

- `Time Complexity`: O(n)
  - where `n` is the number of `intervals` in the input array.
  - This is because we iterate through the `intervals` array once to insert the new interval and then once again to merge overlapping intervals.
- `Space Complexity`: O(n)
  - This is because we create a new array to store the updated `intervals`, which has a maximum size of `n+1`.
  - Additionally, we create another array to store the merged intervals, which also has a maximum size of `n`.

> [!TIP]
> The complexities of both solutions is the same, however short-circuiting the following line - `...if (!inserted && newInterval[0] <= intervals[i][0])...` makes `Solution 2` faster.
