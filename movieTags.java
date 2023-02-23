import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class movieTags {

    public void insertionSort(ArrayList<String[]> data) {
        // Sort data using insertion sort
        for (int i = 1; i < data.size(); i++) {
            String[] key = data.get(i);
            int j = i - 1;

            while (j >= 0 && compare(data.get(j), key) > 0) {
                data.set(j + 1, data.get(j));
                j--;
            }
            data.set(j + 1, key);
        }
    }

    // Compare two rows based on the UserID
    public static int compare(String[] row1, String[] row2) {
        int id1 = Integer.parseInt(row1[0]);
        int id2 = Integer.parseInt(row2[0]);
        return id1 - id2;
    }

    public void uniqueSortAlpha(ArrayList<String> uniqueTags) {
        // sorting uniqueTags into alphebetical order
        for (int i = 1; i < uniqueTags.size(); i++) {
            String key = uniqueTags.get(i);
            int j = i - 1;

            while (j >= 0 && uniqueTags.get(j).compareTo(key) > 0) {
                uniqueTags.set(j + 1, uniqueTags.get(j));
                j--;
            }
            uniqueTags.set(j + 1, key);
        }
    }

    public void countDuplicateTags(ArrayList<String> uniqueTags, ArrayList<Integer> countTags, ArrayList<String> tags) {
        // counting duplicates in tag
        for (int i = 0; i < uniqueTags.size(); i++) {
            int counter = 0;

            for (int j = 0; j < tags.size(); j++) {
                if (uniqueTags.get(i).equals(tags.get(j))) {
                    counter++;
                }
            }
            countTags.add(counter);
        }
    }
    public int findHighest(ArrayList<Integer> countTags, int trats) {
        int highest = Integer.MIN_VALUE;
        int index = 0;
        /* this loop finds the FIRST highest value, starting from the front */
        for (int i = trats; i < countTags.size(); i++) {
            if (countTags.get(i) > highest) {
                highest = countTags.get(i);
                index = i;
            }
        }
        return index;
    }
    // find INDEX of lowest value in passed ArrayList, given the loop "start" index
    public int findLowest(ArrayList<Integer> countTags, int start) {
        int lowest = Integer.MAX_VALUE;
        int index = 0;
        /* this loop finds the FIRST lowest value, starting from the back */
        for (int i = start; i >= 0; i--) {
            if (countTags.get(i) < lowest) {
                lowest = countTags.get(i);
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        String csvFile = args[0]; // Path to file
        String line = "";
        int lineNum = 0;

        ArrayList<String[]> data = new ArrayList<>(); // ArrayList to store data
        ArrayList<String> tags = new ArrayList<>();// ArrayList to store tags(DO NOT ADD [] = bound to cause
        ArrayList<String> unique = new ArrayList<>();// ArrayList to store ONLY UNIQUE TAGS, will not have a
        ArrayList<Integer> tagCounter = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                if (lineNum == 0) {// to skip the header
                    lineNum++;
                    continue;
                } else {
                    String[] row = line.split(",");
                    data.add(row);
                    if (row.length >= 3) {
                        String tag = row[2];
                        if (unique.contains(tag)) {
                            tags.add(tag);
                        }
                        else {
                            unique.add(tag);
                            tags.add(tag);
                        }
                    }
                }
            }

            movieTags mt = new movieTags(); // create instance of the class

            mt.insertionSort(data); // call the insertionSort function

            mt.uniqueSortAlpha(unique); // call the uniqueSortAlpha function

            mt.countDuplicateTags(unique, tagCounter, tags); // call the countDuplicateTags function

            System.out.println("Reading data file . . .");
            System.out.println("=========================================");
            System.out.println("*** Highest 3 movies by count ***");

            ArrayList<Integer> high3 = new ArrayList<>();
            int trats = 0;
            for (int i = 0; i < 3; i++) {
                int add = mt.findHighest(tagCounter, trats);
                high3.add(add);
                trats = add + 1;
            }
            for (int i = 0; i < high3.size(); i++) {
                System.out.println(tagCounter.get(high3.get(i)) + " : " + unique.get(high3.get(i)));
            }
            System.out.println("*** Lowest 3 movies by count ***");
            ArrayList<Integer> low3 = new ArrayList<>();
            int start = tagCounter.size() - 1;
            for (int i = 0; i < 3; i++) {
                int add = mt.findLowest(tagCounter, start);
                low3.add(add);
                start--;
            }
            for (int i = 0; i < low3.size(); i++) {
                System.out.println(tagCounter.get(low3.get(i)) + " : " + unique.get(low3.get(i)));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("=========================================");
        Boolean done = true;
        Scanner scanner = new Scanner(System.in);
        String input = "";


        while (done == true) { 
            System.out.println("Search by Tag or Count? (Enter T or C ... or EXIT to exit): ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("EXIT")) {
                done = false;
                break;
            }
            else if (input.equalsIgnoreCase("C")) {
                System.out.print("Count to search for: ");
                if (scanner.hasNextInt()) {
                    int userNum = scanner.nextInt();
                    boolean foundTags = false;
                    for (int i = 0; i < tagCounter.size(); i++) {
                        if (userNum == tagCounter.get(i)) {
                            System.out.println("Tags with " + userNum + " occurences: ");
                            String uTags = unique.get(i);
                            System.out.println(uTags);
                            foundTags = true;
                        }
                    }
                    if (!foundTags) {
                        System.out.println("No tags found with " + userNum + " occurences.");
                    }
                    scanner.nextLine(); 
                } else {
                    String invalidInput = scanner.nextLine();
                    System.out.println("Is " + invalidInput + " even a number? C'mon, man!");
                }    
            }
            
            else if (input.equalsIgnoreCase("T")) {
                System.out.print("Tag to seach for: ");
                input = scanner.nextLine();
                // Matches a tag in the Arraylist "data"
                int count = 0;
                for (int i = 0; i < data.size(); i++) {
                    String[] rows = data.get(i);
                    if (input.equals(rows[2])) {
                        count++;
                    }
                }
                if (count == 0) {
                        System.out.println("Tag \"" + input + "\" does not exist");
                }
                else {
                    System.out.println("Tag \"" + input + "\" occured " + count + " times");

                }              
                System.out.println();
            }
            else {
                System.out.println("Please enter valid input!");
            }
            System.out.println();
        }
        scanner.close();
    }
}
