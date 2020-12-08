package it.unibo.oop.lab.collections1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class UseCollection {

    private static final int BEGINNING = 1_000;
    private static final int ENDING = 2_000;
    private static final int ZERO_VALUE = 0;
    private static final int ONE_VALUE = 1;
    private static final int FILL_ARRAY = 100_000;
    private static final int TWO_VALUE = 2;

    private static final long AFRICA_POPULATION = 1_110_635_000L;
    private static final long ASIA_POPULATION = 4_298_723_000L;
    private static final long EUROPE_POPULATION = 742_452_000L;
    private static final long AMERICA_POPULATION = 972_005_000L;
    private static final long OCEANIA_POPULATION = 38_304_000L;

    private UseCollection() {
    }

    public static void main(final String... s) {

        //creating array list
        final List<Integer> arrList = new ArrayList<>();
        for (int i = BEGINNING; i < ENDING; i++) {
            arrList.add(i);
        }

        //creating a filled linked list in 2 ways in a single line of code
        final List<Integer> linkedList = arrList;
        System.out.println(linkedList);
        final List<Integer> linkedList2 = new LinkedList<>(arrList);
        System.out.println(linkedList2);

        //swapping first and last
        final int elementToSwap = arrList.get(arrList.size() - ONE_VALUE);
        arrList.set(arrList.size() - ONE_VALUE, arrList.get(ZERO_VALUE));
        arrList.set(ZERO_VALUE, elementToSwap);

        //printing content of arrList
        for (final Integer elem : arrList) {
            System.out.println(elem);
        }

        //measuring performance for arrList
        long timeMeasuringVariable = System.nanoTime();
        for (int i = 0; i < FILL_ARRAY; i++) {
            arrList.add(i);
        }
        timeMeasuringVariable = System.nanoTime() - timeMeasuringVariable;
        System.out.println("Time required for arrList is: " + timeMeasuringVariable);

        //measuring performance for linkedList
        timeMeasuringVariable = System.nanoTime();
        for (int i = 0; i < FILL_ARRAY; i++) {
            linkedList.add(i);
        }
        timeMeasuringVariable = System.nanoTime() - timeMeasuringVariable;
        System.out.println("Time required for linkedList is: " + timeMeasuringVariable);

        //reading performance for arrList
        timeMeasuringVariable = System.nanoTime();
        for (int i = 0; i < BEGINNING; i++) {
            arrList.get(arrList.size() / TWO_VALUE);

        }
        timeMeasuringVariable = System.nanoTime() - timeMeasuringVariable;
        System.out.println("Reading time required for arrList is: " + timeMeasuringVariable);

        //reading performance for linkedList
        timeMeasuringVariable = System.nanoTime();
        for (int i = 0; i < BEGINNING; i++) {
            linkedList.get(linkedList.size() / TWO_VALUE);

        }
        timeMeasuringVariable = System.nanoTime() - timeMeasuringVariable;
        System.out.println("Reading time required for linkedList is: " + timeMeasuringVariable);

        //creating map which contains world general data
        final Map<String, Long> map = new TreeMap<>(); 
        map.put("Africa", AFRICA_POPULATION);
        map.put("Asia", ASIA_POPULATION);
        map.put("America", AMERICA_POPULATION);
        map.put("Europe", EUROPE_POPULATION);
        map.put("Oceania", OCEANIA_POPULATION);

        //world population - usando gli stream
        map.put("Total World Population", map.values().stream().reduce((l1, l2) -> l1 + l2).get());
        System.out.println(map);
    }
}
