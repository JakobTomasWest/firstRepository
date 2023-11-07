//import java.util.ArrayList;
//import java.util.Collections;
////how to compare objects
//public class Person implements Comparable<Person>{
//    //has you make a method inside the class
//    //natural ordering of the elements
//    //the obvious way you should compare two elements of a type
//    //only get 1 shot
//
//    //specific purpose - comparator
//    //not glueing it into the class
//    //doesn't need to be attached to the class/object?
//
//    String first,last;
//    public Person(String first,String last){
//        this.first = first;
//        this.last = last;
//    }
//    @Override
//    public int compareTo(Person o) {
//        //Comparable<String> test = first; //String also has a compareTo method
//        return first.compareTo(o.first);
//
//
//    }
//
//    public interface Comparator <T> { //Java stdlib interface
//        int compare (T o1, T o2);
//    }
//
//    public FirstNameComparator implements Comparator <Name> {
//        public int compare(Name n1, Name n2){
//            return n1.getFirst().compareTo(n2.getFirst());
//
//        }
//    }
//
//    public static void main(String[] args){
//        var people = new ArrayList<Person>();
//        people.add(new Person("Nabil", "Makarem"));
//        people.add(new Person("Ben", "Jones"));
//
//        Collections.sort(people); ///collections static generic s
//        //public static <T extends Comaprable<> super T>> // generic constraint on the type T, T must us extend
//        //void sort( List<T> list)
//
//    }
//
//
//
//}
