package Models.Comparators;

import Models.Member;

import java.util.Comparator;
//CONSTRUCTOR TIL SORTERING EFTER MEMBERTYPE
public class MemberTComparator implements Comparator<Member> {
    @Override
    public int compare(Member o1, Member o2) {
        return Integer.compare(o1.getMembertype().compareToIgnoreCase(o2.getMembertype()),0);
    }
}
