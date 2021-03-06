package io.pivotal.pairswapper.model;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class PairSwapper {


    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    public List<Pair> getNextPairs(List<Pivot> pivotList) {
        String userHome = System.getProperty("user.home");
        File historyFile = new File(userHome + "/.pair-history");
        if (!historyFile.exists()) {

            List<Pair> pairList = new ArrayList<>();
            for (int i = 0; i < pivotList.size(); i = i + 2) {
                Pivot pivot1 = pivotList.get(i);
                Pivot pivot2 = pivotList.get(i + 1);
                pairList.add(new Pair(pivot1, pivot2));
            }

            writeToFile(pairList,historyFile);
            return pairList;
        } else {

            List<Pair> previousPairs = readPairHistory(historyFile);
            List<Pair> pairList = new ArrayList<>();

            for (int i = pivotList.size() -1 ; i > 0 && !pivotList.isEmpty(); i--) {
                Pivot pivot = pivotList.get(0);

                Map<Pivot, List<Pivot>> pivotMap = generatePivotMap(pivot, previousPairs);
                List<Pivot> pivots = pivotMap.get(pivot);

                int index = 0;
                Pivot pivot2;

                do {
                    index++;
                    pivot2 = pivotList.get(index);
                }
                while (pivots.contains(pivot2));

                Pair pair = new Pair(pivot,pivot2);

                pivotList.remove(pivot);
                pivotList.remove(pivot2);
                pairList.add(pair);
                System.out.println("added pair: " + pair);
            }






            writeToFile(pairList,historyFile);

            return pairList;
        }


    }

    private Map<Pivot, List<Pivot>> generatePivotMap(Pivot pivot, List<Pair> pairList) {

        Map<Pivot, List<Pivot>> pivotListMap = new HashMap<>();

        List<Pivot> pivotList = new ArrayList<>();
        for (Pair pair : pairList) {
            if (pair.hasPivot(pivot)) {
                pivotList.add(pair.getOtherPivot(pivot));
            }
        }

        pivotListMap.put(pivot, pivotList);
        return pivotListMap;
    }

    private List<Pair> readPairHistory(File historyFile) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        List<Pair> pairList = new ArrayList<>();
        try {

            fileReader = new FileReader(historyFile);

            bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            while (line != null) {

                String[] split = line.split("\\|");
                Pivot pivot1 = new Pivot(split[1]);
                Pivot pivot2 = new Pivot(split[2]);

                pairList.add(new Pair(pivot1, pivot2));
                line = bufferedReader.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
        return pairList;
    }

    private void writeToFile(List<Pair> pairList, File historyFile) {
        boolean append = historyFile.exists();


        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            if (append) {
                fw = new FileWriter(historyFile, true);
            } else {
                fw = new FileWriter(historyFile);
            }
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

        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }


}
