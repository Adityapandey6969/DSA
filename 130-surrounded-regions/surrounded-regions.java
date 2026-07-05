class Solution {
    private int m, n;
    private final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;

        // Mark all border-connected 'O's
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);
            dfs(board, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            dfs(board, 0, j);
            dfs(board, m - 1, j);
        }

        // Convert remaining 'O' to 'X' and '#' back to 'O'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') {
            return;
        }

        board[i][j] = '#';

        for (int[] d : dirs) {
            dfs(board, i + d[0], j + d[1]);
        }
    }
}