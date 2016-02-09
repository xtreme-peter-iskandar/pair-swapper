package io.pivotal.pairswapper.model;

import java.util.Objects;

public class Pair {

    private Pivot pivot1;
    private Pivot pivot2;

    public Pair(Pivot pivot1, Pivot pivot2) {
        this.pivot1 = pivot1;
        this.pivot2 = pivot2;
    }

    public Pivot getPivot1() {
        return pivot1;
    }

    public void setPivot1(Pivot pivot1) {
        this.pivot1 = pivot1;
    }

    public Pivot getPivot2() {
        return pivot2;
    }

    public void setPivot2(Pivot pivot2) {
        this.pivot2 = pivot2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Objects.equals(pivot1, pair.pivot1) &&
                Objects.equals(pivot2, pair.pivot2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pivot1, pivot2);
    }

    public boolean hasPivot(Pivot pivot){
        return pivot1.equals(pivot) || pivot2.equals(pivot);
    }
}
