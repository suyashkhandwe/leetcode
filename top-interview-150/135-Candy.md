# Problem +135. Candy

> [!NOTE]
> [135. Candy](https://leetcode.com/problems/candy/description/?envType=study-plan-v2&envId=top-interview-150)

There are `n` children standing in a line. Each child is assigned a rating value given in the integer array `ratings`.

You are giving candies to these children subjected to the following requirements:

- Each child must have at least one candy.
- Children with a higher rating get more candies than their neighbors.
- Return the minimum number of candies you need to have to distribute the candies to the children.

### Examples

#### Example 1:

> **Input**: `ratings = [1,0,2]`
> **Output**: `5`
> **Explanation**: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.

#### Example 2:

> **Input**: `ratings = [1,2,2]`
> **Output**: `4`
> **Explanation**: You can allocate to the first, second and third child with 1, 2, 1 candies respectively. The third child gets 1 candy because it satisfies the above two conditions.

#### Constraints:

- `n == ratings.length`
- `1 <= n <= 2 * 104`
- `0 <= ratings[i] <= 2 * 104`

## Solutions

### Solution 1

```java
public int candy(int[] ratings) {
    int[] candies = new int[ratings.length];

    for (int l = 0, r = ratings.length - 1; l < ratings.length - 1; l++, r--) {
        if (ratings[l] > ratings[l + 1] && candies[l] <= candies[l + 1]) {
            candies[l] += 1;
        }
        if (ratings[l] < ratings[l + 1] && candies[l] >= candies[l + 1]) {
            candies[l + 1] = candies[l] + 1;
        }
        if (ratings[r] > ratings[r - 1] && candies[r] <= candies[r - 1]) {
            candies[r] += 1;
        }
        if (ratings[r] < ratings[r - 1] && candies[r] >= candies[r - 1]) {
            candies[r - 1] = candies[r] + 1;
        }
    }
    return Arrays.stream(candies).sum() + ratings.length;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - iterate through the ratings array once to calculate the candies array
- `Space Complexity`: O(n)
    - using an additional candies array of the same size as the ratings array

### Solution 2

```java
public int candy(int[] ratings) {
    int[] candies = new int[ratings.length];
    for (int i = 1; i < ratings.length; i++) {
        if (ratings[i] > ratings[i - 1]) {
            candies[i] = candies[i - 1] + 1;
        }
    }
    for (int i = ratings.length - 2; i >= 0; i--) {
        if (ratings[i] > ratings[i + 1]) {
            candies[i] = Math.max(candies[i], candies[i + 1] + 1);
        }
    }
    return Arrays.stream(candies).sum() + ratings.length;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - iterate through the ratings array twice, once from left to right and once from right to left
- `Space Complexity`: O(n)
    - using an additional candies array of the same size as the ratings array

> [!TIP]
> The second solution simplifies the computation, however the time and space complexities aren't changed.

> [!TIP]
> There is another solution possible which can be completed in O(1) space complexity. Refer [this solution](https://leetcode.com/problems/candy/solutions/4037646/99-20-greedy-two-one-pass/?envType=study-plan-v2&envId=top-interview-150)
