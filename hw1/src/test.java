import java.util.*;

public class test
{
    public static void printArrList(ArrayList<int[]> arrList)
    {
        for (int ind = 0; ind < arrList.size(); ind++)
        {
            int[] tup = arrList.get(ind);
            System.out.println("The " + (ind+1) + " elements in the ArrayList " 
                    + "is: (" + tup[0] + "," + tup[1] + ")");
        }
    }

    public static void shuffleArrList(ArrayList<int[]> arrList)
    {
        Collections.shuffle(arrList);
    }

    public static void main(String[] args)
    {
        int[] tuple = {2,3};
        ArrayList<int[]> listOfTup = new ArrayList<int[]>(5);
        listOfTup.add(tuple);

        listOfTup.add(new int[] {3,4});
        listOfTup.add(new int[] {7,8});
        listOfTup.add(new int[] {6,9});
        listOfTup.add(new int[] {3,2});
        printArrList(listOfTup);

        System.out.println("\n");

        shuffleArrList(listOfTup);
        printArrList(listOfTup);

        // boolean[] flagArr = new boolean[10];

        // Random rand = new Random();
        // int nextId = rand.nextInt(8); 
        // System.out.println(nextId);


    }
}
