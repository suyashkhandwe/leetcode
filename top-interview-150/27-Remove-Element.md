# Problem 27. Remove Element


[27. Remove Element](https://leetcode.com/problems/remove-element/description/?envType=study-plan-v2&envId=top-interview-150)
Given an integer array `nums` and an integer `val`, remove all occurrences of `val` in nums [in-place](https://en.wikipedia.org/wiki/In-place_algorithm). The order of the elements may be changed. Then return the number of elements in `nums` which are not equal to `val`.

Consider the number of elements in `nums` which are not equal to `val` be `k`, to get accepted, you need to do the following things:

- Change the array `nums` such that the first `k` elements of `nums` contain the elements which are not equal to `val`.
    - The remaining elements of `nums` are not important as well as the size of `nums`.
- Return `k`.

### Custom Judge:

The judge will test your solution with the following code:

```java
int[] nums = [...]; // Input array
int val = ...; // Value to remove
int[] expectedNums = [...]; // The expected answer with correct length.
                            // It is sorted with no values equaling val.

int k = removeElement(nums, val); // Calls your implementation

assert k == expectedNums.length;
sort(nums, 0, k); // Sort the first k elements of nums
for (int i = 0; i < actualLength; i++) {
    assert nums[i] == expectedNums[i];
}
```
If all assertions pass, then your solution will be accepted.

## Examples

### Example 1:

> **Input**: `nums = [3,2,2,3]`, `val = 3`
> **Output**: `2`, `nums = [2,2,_,_]`
> **Explanation**: Your function should return `k = 2`, with the first two elements of `nums` being 2.
> It does not matter what you leave beyond the returned `k` (hence they are underscores).

### Example 2:

> **Input**: `nums = [0,1,2,2,3,0,4,2]`, `val = 2`
> **Output**: `5`, `nums = [0,1,4,0,3,_,_,_]`
> **Explanation**: Your function should return `k = 5`, with the first five elements of nums containing `0, 0, 1, 3, and 4`.
> Note that the five elements can be returned in any order.
> It does not matter what you leave beyond the returned `k` (hence they are underscores).

## Solutions

### My Solution

```java
    public int removeElement(int[] nums, int val) {
        //Start a forward (L) and backward (R) pointer
        int lIdx = 0;
        int rIdx = nums.length - 1;
        // Track the last forward pointer to make sure we don't update the counter multiple times. 
        int lIdxPrevious = -1;
        // Counter for `val`
        int valCounter = 0;
        //Iterate from L to R
        while (lIdx <= rIdx) {
            if (nums[lIdx] == val) {
                if (lIdx != lIdxPrevious) {
                    // Increment the valCounter and set lIdxPrevious = lIdx to ensure double counting is not done. 
                    valCounter++;
                    lIdxPrevious = lIdx;
                }
                if (nums[rIdx] != val) {
                    // Swap the right most non-val value with the current L index
                    int swapNum = nums[lIdx];
                    nums[lIdx] = nums[rIdx];
                    nums[rIdx] = swapNum;
                    lIdxPrevious = lIdx++;
                } else if (lIdx != rIdx) {
                    // If the right most value is val, increment the `valCounter` 
                    valCounter++;
                }
                //Step back from R
                rIdx--;
            } else {
                //Step forward from L
                lIdx++;
            }
        }
        // Return total length less the count of val. 
        return nums.length - valCounter;
    }
```

### Ideal Solution

```java
public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
```

> [!TIP]
> The ideal solution uses the key line from the porblem -
> > The remaining elements of `nums` are not important as well as the size of `nums`.
>
> Due to this, it just ignored the elements in the back instead of swapping those.