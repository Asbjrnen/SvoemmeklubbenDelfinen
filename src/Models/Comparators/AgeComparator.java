package Models.Comparators;

import Models.Member;

import java.util.Comparator;
//CONSTRUCTOR TIL SORTERING EFTER ALDER
public class AgeComparator implements Comparator<Member> {
        @Override
        public int compare(Member o1, Member o2) {
            return o1.getAge() - o2.getAge();
        }
}