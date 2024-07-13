# Problem 36. Valid Sudoku

> [!NOTE]
> [36. Valid Sudoku](https://leetcode.com/problems/valid-sudoku/description/?envType=study-plan-v2&envId=top-interview-150)

Determine if a `9 x 9` Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

1. Each `row` must contain the digits `1-9` without repetition.
2. Each `column` must contain the digits `1-9` without repetition.
3. Each of the nine `3 x 3` sub-boxes of the grid must contain the digits `1-9` without repetition.

**Note**:
- A Sudoku board (partially filled) could be valid but is not necessarily solvable.
- Only the filled cells need to be validated according to the mentioned rules.

### Examples

#### Example 1:

> **Input**: `board =`<br/>
`[["5","3",".",".","7",".",".",".","."]`<br/>
`,["6",".",".","1","9","5",".",".","."]`<br/>
`,[".","9","8",".",".",".",".","6","."]`<br/>
`,["8",".",".",".","6",".",".",".","3"]`<br/>
`,["4",".",".","8",".","3",".",".","1"]`<br/>
`,["7",".",".",".","2",".",".",".","6"]`<br/>
`,[".","6",".",".",".",".","2","8","."]`<br/>
`,[".",".",".","4","1","9",".",".","5"]`<br/>
`,[".",".",".",".","8",".",".","7","9"]]`<br/>
> **Output**: `true`<br/>

#### Example 2:

> **Input**: `board =`<br/>
`[["8","3",".",".","7",".",".",".","."]`<br/>
`,["6",".",".","1","9","5",".",".","."]`<br/>
`,[".","9","8",".",".",".",".","6","."]`<br/>
`,["8",".",".",".","6",".",".",".","3"]`<br/>
`,["4",".",".","8",".","3",".",".","1"]`<br/>
`,["7",".",".",".","2",".",".",".","6"]`<br/>
`,[".","6",".",".",".",".","2","8","."]`<br/>
`,[".",".",".","4","1","9",".",".","5"]`<br/>
`,[".",".",".",".","8",".",".","7","9"]]`<br/>
> **Output**: `false`<br/>
> **Explanation**: `Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.`

#### Constraints:

- `board.length == 9`
- `board[i].length == 9`
- `board[i][j] is a digit 1-9 or '.'.`

## Solutions

### Solution 1

```java
public boolean isValidSudoku(char[][] board) {
    for (int r = 0; r < 9; r++) {
        int[] rowFrequency = new int[10];
        int[] colFrequency = new int[10];
        for (int c = 0; c < 9; c++) {
            if (board[r][c] != '.') {
                int number = Character.getNumericValue(board[r][c]);
                if (rowFrequency[number] == 1) {
                    return false;
                }
                rowFrequency[number]++;
            }
            if (board[c][r] != '.') {
                int number = Character.getNumericValue(board[c][r]);
                if (colFrequency[number] == 1) {
                    return false;
                }
                colFrequency[number]++;
            }
            if ((r == 1 || r == 4 || r == 7) && (c == 1 || c == 4 || c == 7)) {
                int[] cellFrequency = new int[10];
                for (int cellR = r - 1; cellR <= r + 1; cellR++) {
                    for (int cellC = c - 1; cellC <= c + 1; cellC++) {
                        if (board[cellR][cellC] != '.') {
                            int number = Character.getNumericValue(board[cellR][cellC]);
                            if (cellFrequency[number] == 1) {
                                return false;
                            }
                            cellFrequency[number]++;
                        }
                    }
                }
            }
        }
    }
    return true;
}
```

#### Complexities

- `Time Complexity`: O(n^2)
    - Matrix iteration always needs a nested loop and can be considered O(1) complexity since this would not change regardless of the input Sudoku.  
    - Additional nested loops that iterate over each cell in the `9x9` Sudoku board result in O(n^2).
- `Space Complexity`: O(1)
    - constant extra space is used

### Solution 2

```java
public boolean isValidSudoku(char[][] board) {
    Set<String> frequencies = new HashSet<>();
    for (int r = 0; r < 9; r++) {
        for (int c = 0; c < 9; c++) {
            if (board[r][c] != '.') {
                if (!frequencies.add("r " + r + " : " + board[r][c]) ||
                        !frequencies.add("c " + c + " : " + board[r][c]) ||
                        !frequencies.add("b " + r / 3 + c / 3 + " : " + board[r][c])) {
                    return false;
                }
            }
        }
    }
    return true;
}
```

#### Complexities

- `Time Complexity`: O(1)
  - the loops iterate over a constant number of cells
- `Space Complexity`: O(1)
  - set `frequencies` will contain at most 81 elements, which is constant for a 9x9 Sudoku board

> [!TIP]
> Although `Solution 2` has low complexity, it takes longer due to String conversion and `Solution 1` is more efficient.
