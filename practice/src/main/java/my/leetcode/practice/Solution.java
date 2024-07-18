package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Solution {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = computeHappinessQuotient(n);
        while (fast != 1 && slow != fast) {
            slow = computeHappinessQuotient(slow);
            fast = computeHappinessQuotient(computeHappinessQuotient(fast));
        }
        return fast == 1;
    }

    int computeHappinessQuotient(int n) {
        int productOfDigits = 0;
        while (n > 0) {
            int digit = n % 10;
            n = (n - digit) / 10;
            productOfDigits += digit * digit;
        }
        return productOfDigits;
    }
}
