package Models.Comparators;

import Models.Member;

import java.util.Comparator;
//CONSTRUCTOR TIL SORTERING EFTER NAVN
public class NameComparator implements Comparator<Member> {
    @Override
    public int compare(Member o1, Member o2) {
        return Integer.compare(o1.getName().compareToIgnoreCase(o2.getName()), 0);
    }
}
