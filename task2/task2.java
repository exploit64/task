package task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class task2 {
    public static void main(String[] args) {
        if(args.length < 2) return;
        List<float[]> qadrangleCoords = getArrayList(args[0]);
        List<float[]> pointsCoords = getArrayList(args[1]);

        for(float[] p: pointsCoords) {
            if (isPointVertex(p, qadrangleCoords)) System.out.println(0);
            else if(isPointSegments(p, qadrangleCoords)) System.out.println(1);
            else if(isPointInQadrangle(p, qadrangleCoords)) System.out.println(2);
            else System.out.println(3);
        }
    }

    private static boolean isPointSegments(float[] point, List<float[]> qadrangleCoords){
        return isPointSegment(point, qadrangleCoords.get(0), qadrangleCoords.get(1))
                || isPointSegment(point, qadrangleCoords.get(1), qadrangleCoords.get(2))
                || isPointSegment(point, qadrangleCoords.get(2), qadrangleCoords.get(3))
                || isPointSegment(point, qadrangleCoords.get(3), qadrangleCoords.get(0));
    }


    private static boolean isPointSegment (float[] point, float[] p1, float[] p2)
    {
         if((point[0] - p1[0]) * (p2[1] - p1[1]) - (p2[0] - p1[0]) * (point[1] - p1[1]) == 0)
           return ((p1[0]-point[0])*(p2[0]-point[0])+(p1[1]-point[1])*(p2[1]-point[1])) <= 0;
             return false;
    }


    private static boolean isPointVertex(float[] point, List<float[]> qadrangleCoords){
        for(float[] q : qadrangleCoords)
            if(q[0] == point[0] && q[1] == point[1])
                return true;
        return false;
    }

    private static boolean isPointInQadrangle(float[] point, List<float[]> qadrangleCoords){
        float sum = areaTriangle(qadrangleCoords.get(0), qadrangleCoords.get(1), point);
        sum += areaTriangle(qadrangleCoords.get(1), qadrangleCoords.get(2), point);
        sum += areaTriangle(qadrangleCoords.get(2), qadrangleCoords.get(3), point);
        sum += areaTriangle(qadrangleCoords.get(3), qadrangleCoords.get(0), point);
        return sum == areaQuadrangle(qadrangleCoords.get(0), qadrangleCoords.get(1), qadrangleCoords.get(2), qadrangleCoords.get(3));
    }


    private static List<float[]> getArrayList(String file){
        List<float[]> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] s = line.split(" ");
                list.add(new float[]{Float.parseFloat(s[0]), Float.parseFloat(s[1])});
            }
        }catch (IOException ex){}
        return list;
    }

    private static float areaTriangle(float[] p1, float[] p2, float[] p3){
        return Math.abs(0.5f*((p1[0]-p3[0])*(p2[1]-p3[1])-(p2[0]-p3[0])*(p1[1]-p3[1])));
    }

    private static float areaQuadrangle(float[] p1, float[] p2, float[] p3, float[] p4){
        return Math.abs(0.5f*((p1[0]*p2[1]-p1[1]*p2[0])+(p2[0]*p3[1]-p2[1]*p3[0])+(p3[0]*p4[1]-p3[1]*p4[0])+(p4[0]*p1[1]-p4[1]*p1[0])));
    }
}
