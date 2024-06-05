# Problem 121. Best Time to Buy and Sell Stock

> [!NOTE]
> [121. Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/?envType=study-plan-v2&envId=top-interview-150)

You are given an array `prices` where `prices[i]` is the price of a given stock on the `i`<sup>th</sup> day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the `maximum profit` you can achieve from this transaction. If you cannot achieve any profit, return `0`.

### Examples

#### Example 1:

> **Input**: `prices = [7,1,5,3,6,4]`
> **Output**: `5`
> **Explanation**: 
> - Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
> - Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

#### Example 2:

> **Input**: `prices = [7,6,4,3,1]`
> **Output**: `0`
> **Explanation**: In this case, no transactions are done and the max profit = 0.

#### Constraints:

- `1 <= prices.length <= 105`
- `0 <= prices[i] <= 104`

## Solutions

### Solution 1

```java
public int maxProfit(int[] prices) {
    int minPrice = prices[0];
    int maxPrice = -1;
    int minPriceIdx = 0;
    int maxPriceIdx = -1;
    int maxProfit = 0;
    for (int i = 1; i < prices.length; i++) {
        if (prices[i] <= minPrice) {
            minPriceIdx = i;
            minPrice = prices[i];
            maxPrice = -1;
            maxPriceIdx = -1;
        }
        if (prices[i] >= maxPrice) {
            maxPriceIdx = i;
            maxPrice = prices[i];
        }
        if (minPriceIdx < maxPriceIdx && (maxPrice - minPrice) > maxProfit) {
            maxProfit = maxPrice - minPrice;
        }
    }
    return maxProfit;
}
```
#### Complexities

- `Time Complexity`: O(n)
    - iterate through the prices array only once
- `Space Complexity`: O(1)
    - using a constant amount of extra space regardless of the input size

### Solution 2

```java
public int maxProfit(int[] prices) {
    int buyPrice = prices[0];
    int maxProfit = 0;
    for (int i = 1; i < prices.length; i++) {
        if (prices[i] < buyPrice) {
            buyPrice = prices[i];
        }
        maxProfit = Math.max((prices[i] - buyPrice), maxProfit);
    }
    return maxProfit;
}
```
#### Complexities

- `Time Complexity`: O(n)
    - iterate through the prices array only once
- `Space Complexity`: O(1)
    - using a constant amount of extra space regardless of the input size

### Better Solution

```java

```

> [!TIP]
> ?