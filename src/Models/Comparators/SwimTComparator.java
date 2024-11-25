package Models.Comparators;

import Models.Member;

import java.util.Comparator;

public class SwimTComparator implements Comparator<Member> {
    @Override
    public int compare(Member o1, Member o2) {
        return Integer.compare(o1.getSvimType().compareToIgnoreCase(o2.getSvimType()),0);
    }
    }
