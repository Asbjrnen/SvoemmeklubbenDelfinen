package models.comparators;

import models.Member;

import java.util.Comparator;

public class MemberTComparator implements Comparator<Member> {
    @Override
    public int compare(Member o1, Member o2) {
        return Boolean.valueOf(o1.getMembertype()).compareTo(Boolean.valueOf(o2.getMembertype()));
}
}