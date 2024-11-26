package Models.Comparators;

import Models.Member;

import java.util.Comparator;
//CONSTRUCTOR TIL SORTERER EFTER ID
public class IDComparator implements Comparator<Member> {
    @Override
    public int compare(Member o1, Member o2) {
        return o1.getId() - o2.getId();
    }

}
