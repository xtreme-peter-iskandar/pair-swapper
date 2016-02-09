package io.pivotal.pairswapper.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PairSwapper {


    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    public List<Pair> getNextPairs(List<Pivot> pivotList) {

        File historyFile = new File("~/.pair-history");
        if(!historyFile.exists()) {

            List<Pair> pairList = new ArrayList<>();
            for (int i = 0; i < pivotList.size(); i = i + 2) {
                Pivot pivot1 = pivotList.get(i);
                Pivot pivot2 = pivotList.get(i+1);
                pairList.add(new Pair(pivot1,pivot2));
            }

            writeToFile(pairList, historyFile);

            return pairList;
        }

        return new ArrayList<>();

    }

    private void writeToFile(List<Pair> pairList, File historyFile) {
        if(historyFile.exists()){

            FileWriter fw = null;
            BufferedWriter bw = null;

            try {
                fw = new FileWriter(historyFile,true);
                bw = new BufferedWriter(fw);

                for (Pair pair :
                        pairList) {
                    bw.write(dateFormat.format(new Date()));
                    bw.write('|');
                    bw.write(pair.getPivot1().getName());
                    bw.write('|');
                    bw.write(pair.getPivot2().getName());
                    bw.newLine();
                }



            } catch (IOException e) {
                e.printStackTrace();

            }finally {
                if(bw != null){
                    try {
                        bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                if(fw != null){
                    try {
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }


}
