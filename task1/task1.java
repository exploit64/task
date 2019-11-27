package task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class task1 {
    public static void main(String[] args) {
        if(args.length < 1) return;
        List<Integer> list = new ArrayList<>();
        float middle = 0.0f;
        try {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String line;
            int readInt;
            while ((line = reader.readLine()) != null) {
                readInt = Integer.parseInt(line);
                list.add(readInt);
                middle += readInt;
            }
        }catch (IOException ex){}
            Collections.sort(list);

            float x = 0.9f*(list.size()-1)+1;
            int d = (int)x;
            x -= d;
            float percentile = list.get(d-1)+x*(list.get(d)-list.get(d-1));

            System.out.println(toString(percentile));

            float median;
            int i = (int) list.size() / 2;
            if(list.size() %2 == 0)
                median = (list.get(i-1) + list.get(i)) / 2;
            else
                median = list.get(i);
            System.out.println(toString(median));

            System.out.println(toString(list.get(list.size()-1)));
            System.out.println(toString(list.get(0)));
            System.out.println(toString(middle/list.size()));
    }

    static String toString(float f) {
        return String.format(Locale.US,"%.2f", f);
    }
    static String toString(int d) {
        return d+".00";
    }
}
