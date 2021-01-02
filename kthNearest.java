import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The kthNearest class is designed to find the correct categorization of a new data point.
 * Given a data set and a data point, by finding all of the Euclidian Distances, it finds
 * the closest k data points to it in the data set. When doing so it also notes what category
 * these closest neighbors are, and with a check at the end, determines what category the new data
 * point belongs to.
 *
 * @author  Dylan McNamara
 */
public class kthNearest {

    /**
     * This variable exists to store the response printed to the terminal, and gets it's value assigned
     * at the end of the check for categorization. Initialized to null for basic declaration.
     */
    private String response = null;

    /**
     * This is a constructor to make a new object of the kthNearest class. This need to take in a filepath,
     * a data point, and how many neighbors to find. It is also set to throw an IOException when it cannot
     * locate the given filepath in resources.
     *
     * @param filepath      filepath is the filepath related to the data CSV in the resources section of the
     *                      project. This would allow any resource CSV to be loaded.
     * @param point         point is the new data point to be categorized. It has to match the rank (number of
     *                      elements) of all the other data.
     * @param k             k is the number of neighbors to find that are closest to build a consensus for the
     *                      categorization of the new data
     * @throws IOException  IOException is thrown when the class constructor cant locate the data referenced
     *                      in the filepath argument.
     */
    public kthNearest(String filepath, double[] point, int k) throws IOException {

        /**
         * The data ArrayList is used to load in data. The reason for using an ArrayList is that it is
         * auto resizing, that is, once it is near capacity it adds more empty elements to the end. This
         * allow the program to load all the data regardless of the number of data points.
         */
        ArrayList<String> data = new ArrayList<>(1); //Raw Data array that is dynamic in size

        /**
         * DistanceFromNeighbors is set to the size k, and hold only the nearest neighbors. This allows
         * for a simple way to tell if a new euclidian distance is closer than a previous one.
         */
        double[] DistanceFromNeighbors = new double[k]; //nearest neighbors by distance

        /**
         * Temporary double array to do array copies back and forth for the System.ArrayCopy method.
         * When a new closest neighbor is found this is used to store an array temporarily with an
         * open space to insert the new closest neighbor. This array is not used for categorization.
         */
        double[] tempDouble = new double[k]; // temp array

        /**
         * The workingDoubles array is how the raw data is parsed to get the values. The design has
         * a euclidian distance calculated after the array is filled, and then that data is passed to
         * the check for size to see if the distance is shorter than one already saved. This variable
         * is written over with each line of the CSV being read and serves as a temp array of sorts.
         */
        double[] workingDoubles = new double[point.length]; //numbers to perform math functions with

        /**
         * nearestNeighbors hold the categorization of the distances in the DistanceFromNeighbors.
         * Ex: point 17 in the data is found to be closer, and is inserted to spot 3 in the array
         * of distances. The manipulation and insertion done on the DistanceFromNeighbors is also
         * performed on this array to ensure any points in the DistanceFromNeighbors has a correct
         * categorization.
         */
        String[] nearestNeighbors = new String[k]; //list of nearest neighbors

        /**
         * Temporary string array to do array copies back and forth for the System.ArrayCopy method.
         * When a new closest neighbor is found this is used to store an array temporarily with an
         * open space to insert the new closest neighbor. This array is used for categorization.
         */
        String[] tempString = new String[k];


        /**
         * The integer q is used to iterate through the workingDoubles array for it to assign values
         * from the raw-imported data in the managedData two dimensional array.
         */
        int q = 0;

        /**
         * The integer count1 is used to determine the categorization of the new data point. It is
         * a simple counter used to compare the category names at the end of the distance comparison
         * loops. Compared to count2.
         */
        int count1 = 0;

        /**
         * The integer count2 is used to determine the categorization of the new data point. It is
         * a simple counter used to compare the category names at the end of the distance comparison
         * loops. Compared to count1.
         */
        int count2 = 0;

        /**
         * This is a declaration of a new SimilarityMetrics object that allows the program to use the
         * functions built into the SimilarityMetrics class. The primary use in this implementation is
         * the euclidian distance method, which is used to judge who the nearest neighbors to the new data
         * point are.
         */
        SimilarityMetrics Sim = new SimilarityMetrics();

        /**
         * This is a scanner object that is used to read in the data from the CSV line by line. This seems
         * to be the most compact way of making sure the program reads in all the data from the given file.
         */

        File file = new File(getClass().getResource(filepath).getFile());
        Scanner scan = new Scanner(file);
        //Parses each line using the , and the new line character
        scan.useDelimiter(",|\\n");
        while (scan.hasNext()) {
            data.add(scan.next());
        }//end while loop

        //close scanner when scan doesnt have a next line
        scan.close();

        //start with the distances as a very large number, ensuring all data points are closer to new data than the value
        for (int i = 0; i < DistanceFromNeighbors.length; i++) {
            DistanceFromNeighbors[i] += 1000;
        }//end for loop

        /**
         * The managedData 2-D array allows the data to be split into rows and columns. Each row is a
         * complete data point and the program goes through each column in a row before incrementing the
         * row value.
         */
        String[][] managedData = new String[point.length + 1][data.size() / (point.length + 1)];
        //Declare array to hold data formatted

        //Loop to fill the 2d array with the proper formatted data starting with row loop
        for (int i = 0; i < managedData.length; i++) {
            //loop through each column of each row
            for (int j = 0; j < managedData[0].length; j++) {
                //insert value to 2-D array
                managedData[i][j] = data.get((i * managedData[0].length) + j);
            }//end for loop
        }//end for loop

        //loop through each row
        for (int i = 0; i < managedData.length; i++) {
            //loop through each column of each row
            for (int j = 0; j < managedData[0].length; j++) {
                //check for category label
                if (managedData[i][j].equals("\"class1\"") || managedData[i][j].equals("\"class2\"")) {

                    /**
                     * The double eNum (standing for Euclidian Number) is the distance from any point loaded
                     * into the workingDoubles array and the given data point. This is then compared to the
                     * array of closest neighbors to see if the distance is less than any of the listed closest
                     * neighbors.
                     */
                    double eNum = Sim.EuclidianDistance(point, workingDoubles);
                    //reset q to start of row
                    q = 0;
                    //loop to start moving through DistanceFromNeighbors
                    for (int l = 0; l < DistanceFromNeighbors.length; l++) {
                        //if eNum is closer (less than) a listed closest neighbor
                        if (eNum < DistanceFromNeighbors[l]) {
                            //copy the DistanceFromNeighbors to temp double starting at the position it found to be less than
                            //and moving it up one spot in the temp array
                            System.arraycopy(DistanceFromNeighbors, l, tempDouble, l + 1, tempDouble.length - l - 1);
                            //insert new closest neighbor to proper position
                            tempDouble[l] = eNum;
                            //copy entire temp array into DistanceFromNeighbors after completing the addition of the new element
                            System.arraycopy(tempDouble, 0, DistanceFromNeighbors, 0, tempDouble.length);
                            //perform same operation on same indecies for the nearestNeighbors array to track proper cateorization
                            System.arraycopy(nearestNeighbors, l, tempString, l + 1, tempString.length - l - 1);
                            //Add category from new closer neighbor
                            tempString[l] = managedData[i][j];
                            //copy from temp array to nearestNeighbors
                            System.arraycopy(tempString, 0, nearestNeighbors, 0, tempString.length);
                            //break for loop and restart
                            l = DistanceFromNeighbors.length;
                        }//end if loop
                    }//end for loop
                } else {
                    //if the category label isnt found, add the value to the working double array
                    workingDoubles[q] = Double.parseDouble(managedData[i][j]);
                    //move working double index
                    q++;
                }//end if else
            }//end for loop
        }//end for loop

        //loop to check what category the new data belongs to by going through the entire nearestNeighbor array
        for (int i = 0; i < nearestNeighbors.length; i++) {
            //checks if the category is class1 or class2 and increments the associated counter accordingly
            if (nearestNeighbors[i].equals("\"class1\"")) {
                count1++;
            } else if (nearestNeighbors[i].equals("\"class2\"")) {
                count2++;
            }//end if else if loop
        }//end for loop

        //loop to determine response for data point
        if (count1 > count2) {
            response = ("New data point belongs to class1");
        } else if (count2 > count1) {
            response = ("New data point belongs to class2");
        }else{
            response = ("Test inconclusive");
        }//end if else if loop
    }

    /**
     * This method overrides the standard toString method provided by object. This implementation allows
     * it to print the response which states what class the new point belongs to.
     *
     * @return  response is used to store the information pertaining to which category the new
     *          data goes into.
     */
    @Override
    public String toString() {
        return response;
    }
}