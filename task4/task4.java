package task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class task4 {

    public static void main(String[] args){
        if (args.length < 1) return;
        List<Time> timeList = new ArrayList<>();
        List<Interval> intervalList = getArrayList(args[0], timeList);
        Collections.sort(timeList);
        setCounter(timeList, intervalList);
        int maxCount = getMaxCount(timeList);
        boolean s = false;
        for(Time time: timeList) {
            if(time.counter == maxCount){
                if(!s) System.out.print(time.toString());
                s = true;
            }
            else if(s) {
                System.out.println(" "+time.toString());
                s = false;
            }
        }
    }

    private static int getMaxCount(List<Time> timeList){
        int max = 0;
        for(Time time: timeList)
            if(time.counter > max)
                max = time.counter;
        return max;
    }

    private static Time setListTime(String time, List<Time> timeList){
        Time t = new Time(toMinutes(time));
        if(timeList.contains(t))
            t = timeList.get(timeList.indexOf(t));
        else timeList.add(t);
        return t;
    }

    private static List<Interval> getArrayList(String file, List<Time> timeList){
        List<Interval> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] s = line.split(" ");
                Time start = setListTime(s[0], timeList);
                Time end = setListTime(s[1], timeList);
                list.add(new Interval(start, end));
            }
        }catch (IOException ex){}
        return list;
    }

    private static int toMinutes(String str){
        String[] s = str.split(":");
        return Integer.parseInt(s[0])*60 + Integer.parseInt(s[1]);
    }

    private static void setCounter(List<Time> timeList, List<Interval> intervalList) {
        int i = 0;
        for(Time time: timeList){
            for(Interval v: intervalList){
                if(time.time >= v.start.time && time.time < v.end.time) {
                    time.counter++;
                    timeList.set(i, time);
                }
            }
            i++;
        }
    }

    private static class Interval{
        Time start;
        Time end;
        Interval(Time start, Time end) {
            this.start = start;
            this.end = end;
        }
    }

    private static class Time implements Comparable{
        int time;
        int counter;
        Time(int time) {
            this.time = time;
        }
        @Override
        public String toString() {
            return String.format("%d:%02d", time/60, time%60);
        }
        @Override
        public boolean equals(Object obj) {
            return ((Time)obj).time == time;
        }
        @Override
        public int compareTo(Object o) {
            return time - ((Time)o).time;
        }
    }
}