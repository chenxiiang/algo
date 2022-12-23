/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2022-2022. All rights reserved.
 */

package cx.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * 1912. 设计电影租借系统
 *
 * @author c00575945
 * @since 2022-12-23
 */
public class MovieRentingSystem {
    HashMap<Integer, TreeSet<Integer>> movies;

    HashMap<String, Integer> moviePrice;

    TreeSet<String> moviesRented;

    public MovieRentingSystem(int n, int[][] entries) {
        movies = new HashMap<>();
        moviePrice = new HashMap<>();
        for (int[] entry : entries) {
            moviePrice.put(entry[0] + "/" + entry[1], entry[2]);
            if (!movies.containsKey(entry[1])) {
                movies.put(entry[1], new TreeSet<>((o1, o2) -> {
                    int price1 = moviePrice.get(o1 + "/" + entry[1]);
                    int price2 = moviePrice.get(o2 + "/" + entry[1]);
                    return price1 - price2 == 0 ? o1 - o2 : price1 - price2;
                }));
            }
            TreeSet<Integer> treeSet = movies.get(entry[1]);
            treeSet.add(entry[0]);
        }
        moviesRented = new TreeSet<>((o1, o2) -> {
            int price1 = moviePrice.get(o1);
            int price2 = moviePrice.get(o2);
            if (price1 != price2) {
                return price1 - price2;
            }
            String[] sm1 = o1.split("/");
            String[] sm2 = o2.split("/");
            int shop1 = Integer.parseInt(sm1[0]);
            int movie1 = Integer.parseInt(sm1[1]);
            int shop2 = Integer.parseInt(sm2[0]);
            int movie2 = Integer.parseInt(sm2[1]);

            return shop1 - shop2 == 0 ? movie1 - movie2 : shop1 - shop2;
        });
    }

    public List<Integer> search(int movie) {
        List<Integer> res = new ArrayList<>();
        if (!movies.containsKey(movie)) {
            return res;
        }
        for (Integer i : movies.get(movie)) {
            res.add(i);
            if (res.size() == 5) {
                break;
            }
        }
        return res;
    }

    public void rent(int shop, int movie) {
        movies.get(movie).remove(shop);
        moviesRented.add(shop + "/" + movie);
    }

    public void drop(int shop, int movie) {
        movies.get(movie).add(shop);
        moviesRented.remove(shop + "/" + movie);
    }

    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        for (String key : moviesRented) {
            String[] ks = key.split("/");
            res.add(Arrays.asList(Integer.valueOf(ks[0]), Integer.valueOf(ks[1])));
            if (res.size() == 5) {
                break;
            }
        }
        return res;
    }
}
