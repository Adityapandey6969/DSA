import java.util.*;

class Solution {
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        String[] parts = path.split("/");

        for (String part : parts) {
            if (part.equals("") || part.equals(".")) {
                // skip empty and current directory
                continue;
            } else if (part.equals("..")) {
                // go to parent directory if possible
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                // valid directory/file name
                stack.push(part);
            }
        }

        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.insert(0, "/" + dir);
        }

        return result.length() == 0 ? "/" : result.toString();
    }
}
 