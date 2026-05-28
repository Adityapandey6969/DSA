import java.util.*;

class Solution {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int minLen = Integer.MAX_VALUE;
        int index = -1;
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        TrieNode root = new TrieNode();

        // Track globally shortest word
        int globalMinLen = Integer.MAX_VALUE;
        int globalIndex = 0;

        // Build reversed trie
        for (int i = 0; i < wordsContainer.length; i++) {
            String word = wordsContainer[i];
            int len = word.length();

            if (len < globalMinLen) {
                globalMinLen = len;
                globalIndex = i;
            }

            TrieNode node = root;

            // Update root node
            if (len < node.minLen) {
                node.minLen = len;
                node.index = i;
            }

            // Insert reversed word
            for (int j = len - 1; j >= 0; j--) {
                int c = word.charAt(j) - 'a';
                if (node.children[c] == null) {
                    node.children[c] = new TrieNode();
                }
                node = node.children[c];

                if (len < node.minLen) {
                    node.minLen = len;
                    node.index = i;
                }
            }
        }

        int[] result = new int[wordsQuery.length];

        // Process queries
        for (int i = 0; i < wordsQuery.length; i++) {
            String query = wordsQuery[i];
            TrieNode node = root;

            for (int j = query.length() - 1; j >= 0; j--) {
                int c = query.charAt(j) - 'a';
                if (node.children[c] == null) break;
                node = node.children[c];
            }

            result[i] = (node.index != -1) ? node.index : globalIndex;
        }

        return result;
    }
}