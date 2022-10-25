package cx.leetcode.daily;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution827 {
    //与934题类似
    //对岛屿从2开始进行编号，然后在map中存储每个岛屿的面积，因为只能把一块海洋变为陆地，所以最后对海洋进行遍历，求与之相邻的岛屿面积加起来的和的最大值
    public int largestIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int result = 0, index = 2;
        Map<Integer, Integer> areaMap = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    areaMap.put(index, calculateArea(grid, i, j, index++));
                }
            }
        }
        if (areaMap.size() == 0) {
            return 1;       //没有岛屿，全是海洋
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> islands = getIslands(grid, i, j);
                    if (islands.size() == 0) {
                        //这块海洋不与任何陆地相邻
                        continue;
                    }
                    result = Math.max(result, islands.stream().map(areaMap::get).reduce(Integer::sum).orElse(0) + 1);
                }
            }
        }
        if (result == 0) {
            //全是岛屿，没有海洋
            return areaMap.get(2);
        }
        return result;
    }

    private int calculateArea(int[][] grid, int i, int j, int index) {
        if (!isLegal(grid, i, j) || grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = index;
        return calculateArea(grid, i + 1, j, index) + calculateArea(grid, i, j + 1, index) + calculateArea(grid, i - 1, j, index) + calculateArea(grid, i, j - 1, index) + 1;
    }

    private boolean isLegal(int[][] grid, int row, int col) {
        return row >= 0 && col >= 0 && row < grid.length && col < grid[0].length;
    }

    private Set<Integer> getIslands(int[][] grid, int row, int col) {
        Set<Integer> result = new HashSet<>();
        if (isLegal(grid, row + 1, col) && grid[row + 1][col] != 0) {
            result.add(grid[row + 1][col]);
        }
        if (isLegal(grid, row, col + 1) && grid[row][col + 1] != 0) {
            result.add(grid[row][col + 1]);
        }
        if (isLegal(grid, row - 1, col) && grid[row - 1][col] != 0) {
            result.add(grid[row - 1][col]);
        }
        if (isLegal(grid, row, col - 1) && grid[row][col - 1] != 0) {
            result.add(grid[row][col - 1]);
        }
        return result;
    }
}
