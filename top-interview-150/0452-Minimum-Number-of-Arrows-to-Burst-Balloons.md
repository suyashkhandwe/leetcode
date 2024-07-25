# Problem 452. Minimum Number of Arrows to Burst Balloons

> [!NOTE]
> [452. Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/description/?envType=study-plan-v2&envId=top-interview-150)

There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer array points where `points[i] = [x > start, x > end]` denotes a balloon whose horizontal diameter stretches between `x > start` and `x > end`. You do not know the exact y-coordinates of the balloons.

Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon with `x > start` and `x > end` is burst by an arrow shot at `x` if `x > start <= x <= x > end`. There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.

Given the array `points`, return the minimum number of arrows that must be shot to burst all balloons.

### Examples

#### Example 1:

> **Input**: `points = [[10,16],[2,8],[1,6],[7,12]]`<br/>
> **Output**: `2`<br/>
> **Explanation**: `The balloons can be burst by 2 arrows:`<br/>
> `- Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].`<br/>
> `- Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].`

#### Example 2:

> **Input**: `points = [[1,2],[3,4],[5,6],[7,8]]`<br/>
> **Output**: `4`<br/>
> **Explanation**: `One arrow needs to be shot for each balloon for a total of 4 arrows.`

#### Example 3:

> **Input**: `points = [[1,2],[2,3],[3,4],[4,5]]`<br/>
> **Output**: `2`<br/>
> **Explanation**: `The balloons can be burst by 2 arrows:`<br/>
> `- Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].`<br/>
> `- Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].`

#### Constraints:

- `1 <= points.length <= 10⁵`
- `points[i].length == 2`
- `-2³¹ <= xstart < xend <= 2³¹ - 1`

## Solutions

### Solution 1

```java
public int findMinArrowShots(int[][] points) {
    Arrays.sort(points, (p1, p2) -> {
        int startIdxComparisonResult = Double.compare(p1[0], p2[0]);
        if (startIdxComparisonResult == 0) {
            return Double.compare(p1[1], p2[1]);
        }
        return startIdxComparisonResult;
    });
    int arrows = 1;
    int[] smallestBalloon = points[0];
    for (int i = 1; i < points.length; i++) {
        int[] currentBalloon = points[i];
        if ((currentBalloon[0] >= smallestBalloon[0] || currentBalloon[1] <= smallestBalloon[0])
                && currentBalloon[0] <= smallestBalloon[1]) {
            smallestBalloon[0] = Math.max(smallestBalloon[0], currentBalloon[0]);
            smallestBalloon[1] = Math.min(smallestBalloon[1], currentBalloon[1]);
        } else {
            arrows++;
            smallestBalloon = currentBalloon;
        }
    }
    return arrows;
}
```

#### Complexities

- `Time Complexity`: O(n log n)
    - This is because we are sorting of the points array. 
- `Space Complexity`: O(1)
    - This is becasue we are using a constant amount of extra space regardless of the input size.

### Solution 2

```java
public int findMinArrowShots(int[][] points) {
    Arrays.sort(points, Comparator.comparing(p -> p[1]));
    int arrows = 1;
    int smallestBalloonRightEdge = points[0][1];
    for (int i = 1; i < points.length; i++) {
        if (points[i][0] > smallestBalloonRightEdge) {
            arrows++;
            smallestBalloonRightEdge = points[i][1];
        }
    }
    return arrows;
}
```

#### Complexities

- `Time Complexity`: O(n log n)
    - This is because we are sorting of the points array. 
- `Space Complexity`: O(1)
    - This is becasue we are using a constant amount of extra space regardless of the input size.

> [!TIP]
> Both solutions have the same complexities and have similar run times and memory consumption, `Solution 2` is simplier.
