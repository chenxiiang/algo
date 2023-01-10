/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2023-2023. All rights reserved.
 */

package cx.leetcode.daily;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 2353. 设计食物评分系统
 *
 * @author c00575945
 * @since 2023-01-10
 */
public class FoodRatings {
    static class Food implements Comparable<Food> {
        String name;

        int rating;

        String cuisine;

        public Food(String name, String cuisine, int rating) {
            this.name = name;
            this.cuisine = cuisine;
            this.rating = rating;
        }

        @Override
        public int compareTo(Food o) {
            if (this.rating != o.rating) {
                return o.rating - this.rating;
            }
            return this.name.compareTo(o.name);
        }
    }

    Map<String, Food> name2Food;

    Map<String, TreeSet<Food>> cuisine2FoodSet;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        name2Food = new HashMap<>();
        cuisine2FoodSet = new HashMap<>();
        for (int i = 0; i < foods.length; i++) {
            Food food = new Food(foods[i], cuisines[i], ratings[i]);
            name2Food.put(food.name, food);
            cuisine2FoodSet.computeIfAbsent(food.cuisine, key -> new TreeSet<>()).add(food);
        }
    }

    public void changeRating(String food, int newRating) {
        Food oldFood = name2Food.get(food);
        Food newFood = new Food(food, oldFood.cuisine, newRating);
        name2Food.put(food, newFood);
        cuisine2FoodSet.get(oldFood.cuisine).remove(oldFood);
        cuisine2FoodSet.get(newFood.cuisine).add(newFood);
    }

    public String highestRated(String cuisine) {
        return cuisine2FoodSet.get(cuisine).first().name;
    }
}
