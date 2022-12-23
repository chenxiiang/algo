/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2022-2022. All rights reserved.
 */

package cx.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * 1396. 设计地铁系统
 *
 * @author c00575945
 * @since 2022-12-23
 */
public class UndergroundSystem {
    private class CheckinInfo {
        private String station;

        private int time;

        public CheckinInfo(String station, int time) {
            this.station = station;
            this.time = time;
        }

        public String getStation() {
            return this.station;
        }

        public int getTime() {
            return this.time;
        }
    }

    private Map<Integer, CheckinInfo> checkinMap;

    private Map<String, int[]> tripTimeMap;

    public UndergroundSystem() {
        checkinMap = new HashMap<>();
        tripTimeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        CheckinInfo checkinInfo = new CheckinInfo(stationName, t);
        checkinMap.put(id, checkinInfo);
    }

    public void checkOut(int id, String stationName, int t) {
        CheckinInfo checkinInfo = checkinMap.get(id);
        String startStation = checkinInfo.getStation();
        int startTime = checkinInfo.getTime();
        String key = startStation + "-" + stationName;
        int[] time = tripTimeMap.getOrDefault(key, new int[2]);
        time[0] += t - startTime;
        time[1]++;
        tripTimeMap.put(key, time);
    }

    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "-" + endStation;
        int[] time = tripTimeMap.get(key);
        return 1.0 * time[0] / time[1];
    }
}
