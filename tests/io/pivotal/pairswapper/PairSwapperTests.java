package io.pivotal.pairswapper;

import io.pivotal.pairswapper.model.Pair;
import io.pivotal.pairswapper.model.PairSwapper;
import io.pivotal.pairswapper.model.Pivot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PairSwapperTests {

    private PairSwapper subject;

    @Before
    public void setup(){
        subject = new PairSwapper();
    }

    @After
    public void tearDown(){
//        String userHome = System.getProperty("user.home");
//        File historyFile = new File(userHome + "/.pair-history");
//        if(historyFile.exists()){
//            historyFile.delete();
//        }
    }

    @Test
    public void test1(){
        List<Pivot> pivotList = new ArrayList<>();
        pivotList.add(new Pivot("peter"));
        pivotList.add(new Pivot("derek"));
        pivotList.add(new Pivot("brandon"));
        pivotList.add(new Pivot("jordan"));

        List<Pair> pairs = subject.getNextPairs(pivotList);

        Assert.assertNotNull(pairs);
        Assert.assertEquals(2,pairs.size());

        Pair pair1 = pairs.get(0);
        Assert.assertNotNull(pair1.getPivot1());
        Assert.assertNotNull(pair1.getPivot2());

        Pair pair2 = pairs.get(1);
        Assert.assertNotNull(pair2.getPivot1());
        Assert.assertNotNull(pair2.getPivot2());

        Assert.assertNotEquals(pair1,pair2);

    }

    @Test
    public void test_createDataFile(){
        List<Pivot> pivotList = new ArrayList<>();
        pivotList.add(new Pivot("peter"));
        pivotList.add(new Pivot("derek"));
        pivotList.add(new Pivot("brandon"));
        pivotList.add(new Pivot("jordan"));

        List<Pair> pairs = subject.getNextPairs(pivotList);

        String userHome = System.getProperty("user.home");
        File historyFile = new File(userHome + "/.pair-history");
        Assert.assertTrue(historyFile.exists());
    }


    @Test
    public void testHasPivot(){
        Pivot pivot1 = new Pivot("Peter");
        Pivot pivot2 = new Pivot("Brandon");
        Pair pair = new Pair(pivot1, pivot2);
        Assert.assertTrue(pair.hasPivot(pivot1));
        Assert.assertTrue(pair.hasPivot(pivot2));
        Assert.assertFalse(pair.hasPivot(new Pivot("Derek")));
    }

    @Test
    public void test_movePivots(){
        List<Pivot> pivotList = new ArrayList<>();
        pivotList.add(new Pivot("peter"));
        pivotList.add(new Pivot("jeff"));
        pivotList.add(new Pivot("fredrich"));
        pivotList.add(new Pivot("nader"));
        pivotList.add(new Pivot("izabela"));
        pivotList.add(new Pivot("ritchie"));
        pivotList.add(new Pivot("ria"));
        pivotList.add(new Pivot("stephen"));

        List<Pair> pairs = subject.getNextPairs(pivotList);
        pairs.forEach(System.out::println);
    }
}
