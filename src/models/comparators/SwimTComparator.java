package models.comparators;

import models.Member;

import java.util.Comparator;

public class SwimTComparator implements Comparator<Member> {
    @Override
    public int compare(Member o1, Member o2) {
        return Integer.compare(o1.getSwimType().compareToIgnoreCase(o2.getSwimType()),0);
    }
    }
