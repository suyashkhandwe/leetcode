# Problem 71. Simplify Path

> [!NOTE]
> [71. Simplify Path](https://leetcode.com/problems/simplify-path/description/?envType=study-plan-v2&envId=top-interview-150)

Given an absolute path for a Unix-style file system, which begins with a slash `'/'`, transform this path into its **simplified canonical path**.

In Unix-style file system context, a single period `'.'` signifies the current directory, a double period `".."` denotes moving up one directory level, and multiple slashes such as `"//"` are interpreted as a single slash. In this problem, treat sequences of periods not covered by the previous rules (like `"..."`) as valid names for files or directories.

The simplified canonical path should adhere to the following rules:

- It must start with a single slash `'/'`.
- Directories within the path should be separated by only one slash `'/'`.
- It should not end with a slash `'/'`, unless it's the root directory.
- It should exclude any single or double periods used to denote current or parent directories.

Return the new path.

### Examples

#### Example 1:

> **Input**: `path = "/home/"`<br/>
> **Output**: `"/home"`<br/>
> **Explanation**: `The trailing slash should be removed.`

#### Example 2:

> **Input**: `path = "/home//foo/"`<br/>
> **Output**: `"/home/foo"`<br/>
> **Explanation**: `Multiple consecutive slashes are replaced by a single one.`

#### Example 3:

> **Input**: `path = "/home/user/Documents/../Pictures"`<br/>
> **Output**: `"/home/user/Pictures"`<br/>
> **Explanation**: `A double period ".." refers to the directory up a level.`

#### Example 4:

> **Input**: `path = "/../"`<br/>
> **Output**: `"/"`<br/>
> **Explanation**: `Going one level up from the root directory is not possible.`

#### Example 5:

> **Input**: `path = "/.../a/../b/c/../d/./"`<br/>
> **Output**: `"/.../b/d"`<br/>
> **Explanation**: `"..." is a valid name for a directory in this problem.`

#### Constraints:

- `1 <= path.length <= 3000`
- `path` consists of English letters, digits, period `'.'`, slash `'/'` or `'_'`.
- `path` is a valid absolute Unix path.

## Solutions

### Solution 1

```java
public String simplifyPath(String path) {
    Queue<String> queue = Arrays.stream(path.split("/"))
            .filter(it -> !it.isEmpty())
            .collect(Collectors.toCollection(LinkedList::new));
    Stack<String> pathStack = new Stack<>();

    while (!queue.isEmpty()) {
        String currDir = queue.remove();
        if (currDir.equals("..") & !pathStack.isEmpty()) {
            pathStack.pop();
        } else if (!currDir.equals(".") && !currDir.equals("..")) {
            pathStack.push(currDir);
        }
    }
    StringBuilder simplifiedPathBuilder = new StringBuilder();
    while (!pathStack.isEmpty()) {
        simplifiedPathBuilder.insert(0, pathStack.pop());
        simplifiedPathBuilder.insert(0, "/");
    }
    return simplifiedPathBuilder.isEmpty() ? "/" : simplifiedPathBuilder.toString();
}
```

#### Complexities

- `Time Complexity`: O(n)
    - where `n` is the length of the input `path` string.
    - This is because we iterate through each character in the input `path` string once to split it into directories and then iterate through each directory to simplify the `path`.
- `Space Complexity`: O(n)
    - where `n` is the length of the input `path` string.
    - This is because we use a `queue` to store the directories, a `stack` to store the simplified `path`, and a `StringBuilder` to build the simplified path.
    - The space used by these data structures scales linearly with the length of the input path string.

### Solution 2

```java
public String simplifyPath(String path) {
    Stack<String> pathStack = new Stack<>();
    for (String currDir : path.split("/")) {
        if (currDir.equals("..") & !pathStack.isEmpty()) {
            pathStack.pop();
        } else if (!currDir.isEmpty() && !currDir.equals(".") && !currDir.equals("..")) {
            pathStack.push(currDir);
        }
    }

    StringBuilder simplifiedPathBuilder = new StringBuilder();
    boolean isEmpty = true;
    while (!pathStack.isEmpty()) {
        isEmpty = false;
        simplifiedPathBuilder.insert(0, pathStack.pop()).insert(0, "/");
    }
    return isEmpty ? "/" : simplifiedPathBuilder.toString();
}
```

#### Complexities

- `Time Complexity`: O(n)
    - where `n` is the length of the input `path` string.
    - This is because we iterate through each character in the input `path` string once to split it into directories and then iterate through each directory to process it.
- `Space Complexity`: O(n)
    - where `n` is the length of the input `path` string.
    - This is because we use a `stack` to store the directories, which can potentially store all directories in the input path string.
    - Additionally, we use a `StringBuilder` to build the simplified path, which can also potentially store all characters in the input path string.

> [!TIP]
> `Solution 1` and `Solution 2` have the same complexities, but `Solution 2` is faster.
