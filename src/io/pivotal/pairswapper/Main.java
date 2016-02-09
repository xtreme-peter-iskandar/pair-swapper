package io.pivotal.pairswapper;

import io.pivotal.pairswapper.model.Pair;
import io.pivotal.pairswapper.model.PairSwapper;
import io.pivotal.pairswapper.model.Pivot;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Pivot> pivotList = new ArrayList<>();
	    PairSwapper pairSwapper = new PairSwapper();

        List<Pair> pairList = pairSwapper.getNextPairs(pivotList);
    }
}
