package Models.Comparators;

import Models.Member;

import java.util.Comparator;
//CONSTRUCTOR TIL SORTERING EFTER MEMBERTYPE
public class MemberTComparator implements Comparator<Member> {
    @Override
    public int compare(Member o1, Member o2) {
        return Boolean.valueOf(o1.getMembertype()).compareTo(Boolean.valueOf(o2.getMembertype()));
}
}