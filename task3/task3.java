package task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class task3 {
    public static void main(String[] args) {
        if (args.length < 1) return;
        float[][] cash = new float[5][16];
        String path = args[0];
        String separator = System.getProperty("file.separator");
        if(!path.endsWith(separator))
            path += separator;

        for (int i=0; i<5; i++){
            cash[i] = getArrayList(path + "Cash" + (i+1) + ".txt");
        }

        float max=0, sum=0;
        int id = 0;
        for (int y=0; y<16; y++){
            sum = 0;
            for (int x=0; x<5; x++){
                sum += cash[x][y];
            }
            if(sum > max) {
                id = y;
                max = sum;
            }
        }
        System.out.println(++id);
    }


    private static float[] getArrayList(String file){
        float[] list = new float[16];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            for (int i=0; i<16; i++) {
                list[i] = Float.parseFloat(reader.readLine());
            }
        }catch (IOException ex){}
        return list;
    }
}
