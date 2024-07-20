# Problem 228. Summary Ranges

> [!NOTE]
> [228. Summary Ranges](https://leetcode.com/problems/summary-ranges/description/?envType=study-plan-v2&envId=top-interview-150)

You are given a **sorted unique** integer array `nums`.

A range `[a,b]` is the set of all integers from `a` to `b` (_inclusive_).

Return _the **smallest sorted** list of ranges that **cover all the numbers in the array exactly**_. That is, each element of `nums` is covered by exactly one of the ranges, and there is no integer `x` such that `x` is in one of the ranges but not in `nums`.

Each range `[a,b]` in the list should be output as:

- `"a->b"` if `a != b`
- `"a"` if `a == b`

### Examples

#### Example 1:

> **Input**: `nums = [0,1,2,4,5,7]`<br/>
> **Output**: `["0->2","4->5","7"]`<br/>
> **Explanation**: `The ranges are:`<br/>
> `[0,2] --> "0->2"`<br/>
> `[4,5] --> "4->5"`<br/>
> `[7,7] --> "7"`<br/>

#### Example 2:

> **Input**: `nums = [0,2,3,4,6,8,9]`<br/>
> **Output**: `["0","2->4","6","8->9"]`<br/>
> **Explanation**: `The ranges are:`<br/>
> `[0,0] --> "0"`<br/>
> `[2,4] --> "2->4"`<br/>
> `[6,6] --> "6"`<br/>
> `[8,9] --> "8->9"`<br/>

#### Constraints:

- `0 <= nums.length <= 20`
- `-2³¹ <= nums[i] <= 2³¹ - 1`
- All the values of `nums` are **unique**.
- `nums` is sorted in ascending order.`

## Solutions

### Solution 1

```java
public List<String> summaryRanges(int[] nums) {
    List<String> ranges = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
        int start = nums[i];
        while (i + 1 < nums.length && nums[i] + 1 == nums[i + 1]) {
            i++;
        }
        int end = nums[i];
        ranges.add(start + (start != end ? "->" + end : ""));
    }
    return ranges;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - where `n` is the number of elements in the input array `nums`.
    - This is because we iterate through each element in the array once.
- `Space Complexity`: O(n)
    - This is because we are storing the ranges in a `list`, which can potentially contain all the elements in the input array `nums`.

### Solution 2

```java
public List<String> summaryRanges(int[] nums) {
    if (nums.length == 0) {
        return List.of();
    }
    if (nums.length == 1) {
        return List.of(String.valueOf(nums[0]));
    }

    int startIdx = 0;
    int endIdx = 0;
    Map<Integer, Integer> indexRange = new LinkedHashMap<>();
    while (endIdx < nums.length - 1) {
        if (nums[endIdx] + 1 != nums[endIdx + 1]) {
            indexRange.put(nums[startIdx], nums[endIdx]);
            startIdx = endIdx + 1;
        }
        endIdx++;
    }
    indexRange.put(nums[startIdx], nums[endIdx]);

    return indexRange.entrySet()
            .stream()
            .map(indexPair -> {
                StringBuilder rangeBuilder = new StringBuilder().append(indexPair.getKey());
                if (indexPair.getKey().intValue() != indexPair.getValue().intValue()) {
                    rangeBuilder.append("->").append(indexPair.getValue());
                }
                return rangeBuilder;
            })
            .map(StringBuilder::toString)
            .collect(Collectors.toList());
}
```

#### Complexities

- `Time Complexity`: O(n)
  - where `n` is the number of elements in the input array `nums`.
  - This is because we iterate through the array once to identify the ranges of consecutive numbers.
- `Space Complexity`: O(n)
  - This is because we store the ranges in a `LinkedHashMap`, which can potentially store all the elements of the input array.

### Solution 3

```java
public List<String> summaryRanges(int[] nums) {
    if (nums.length == 0) {
        return List.of();
    }
    if (nums.length == 1) {
        return List.of(String.valueOf(nums[0]));
    }

    int startIdx = 0;
    int endIdx = 0;
    List<String> ranges = new ArrayList<>();
    StringBuilder rangeBuilder = new StringBuilder();
    while (endIdx < nums.length - 1) {
        if (nums[endIdx] + 1 != nums[endIdx + 1]) {
            rangeBuilder.append(nums[startIdx]);
            if (startIdx != endIdx) {
                rangeBuilder.append("->").append(nums[endIdx]);
            }
            ranges.add(rangeBuilder.toString());
            rangeBuilder = new StringBuilder();
            startIdx = endIdx + 1;
        }
        endIdx++;
    }
    rangeBuilder.append(nums[startIdx]);
    if (startIdx != endIdx) {
        rangeBuilder.append("->").append(nums[endIdx]);
    }
    ranges.add(rangeBuilder.toString());

    return ranges;
}
```

#### Complexities

- `Time Complexity`: O(n)
  - where `n` is the number of elements in the input array `nums`.
  - This is because we iterate through the array once to identify the ranges.
- `Space Complexity`: O(n)
  - This is because we store the ranges in a `List<String>` which can potentially contain `n` elements.
  - Additionally, we use a `StringBuilder` to build each range, which can also potentially contain `n` characters.

> [!TIP]
> Although, all solutions have the same complexity, `Solution 3` is fastest with least memory.
