
/**
 * The SimilarityMetrics class is designed to hold multiple methods that allow one
 * to perform math functions to determine the similarity of given data. This included,
 * Cosine Similarity, Euclidian Distance, and Hamming Distance. In this implementation,
 * the rank of the passed data much be the same. Meaning, the number of elements in the
 * passed data much be equal.
 *
 * @author Dylan McNamara
 */
public class SimilarityMetrics {
    /**
     * The Cosine Similarity method takes 2 arrays of some dimension, and returns
     * the cosine of the angle between them as a double.
     *
     * @param Vector1 The first array to be compared
     * @param Vector2 The second array to be compared
     * @return Cosine Similarity of the two parameters, which is the cosine of the angle
     *         between them. The value should be between 0 and 1. 0 being perpendicular and
     *         1 being parallel.
     */
    public double CosineSimilarity(double Vector1[], double Vector2[]) {

        /**
         * The double denom is the denominator of the formula for the cosine similarity.
         * Its value at the return is the product of the magnitudes of the parameter arrays
         */
        double denom;

        /**
         * The double num is the numerator of the Cosine similarity. Its value at return is
         * equal to the dot product of the 2 parameter arrays.
         */
        double num=0;

        /**
         * The double mag1 is used to track the magnitude of the first array.
         */
        double mag1=0;

        /**
         * The double mag2 is used to track the magnitude of the second array.
         */
        double mag2=0;


        //check if vector one need to be padded with 0 for elements that do not exist in the second vector
        if(Vector1.length<Vector2.length){

            /**
             *
             */
            double Vectorpad1[] = new double[Vector2.length];

            //copy smaller vector into a vector the same size larger vector
            System.arraycopy(Vector1,0,Vectorpad1,0,Vector1.length);
            //all new elements get 0
            for(int i = Vector1.length;i<Vector2.length;i++){
                Vectorpad1[i]=0;
            }//end for loop

            //calculate mag1 squared, mag2 squared, and num
            for (int i = 0; i < Vector2.length; i++) {
                num += (Vector2[i] * Vectorpad1[i]);
                mag1 += Math.pow((Vectorpad1[i]), 2);
                mag2 += Math.pow((Vector2[i]), 2);
            }//end for loop

            //attain proper values for mag1, mag2 and denom
            mag1 = Math.sqrt(mag1);
            mag2 = Math.sqrt(mag2);
            denom = (mag1 * mag2);

            //return cosine similarity
            return (num / denom);

        //check if the second vector needs padded with 0 for elements that do not exist in the first vector
        }else if(Vector2.length<Vector1.length){

            /**
             *
             */
            double Vectorpad2[] = new double[Vector1.length];

            //copy smaller vector into a vector the same size larger vector
            System.arraycopy(Vector2,0,Vectorpad2,0,Vector2.length);
            //all new elements get 0
            for(int i = Vector2.length;i<Vector1.length;i++){
                Vectorpad2[i]=0;
            }//end for loop

            //calculate mag1 squared, mag2 squared, and num
            for (int i = 0; i < Vector1.length; i++) {
                num += (Vector1[i] * Vectorpad2[i]);
                mag1 += Math.pow((Vector1[i]), 2);
                mag2 += Math.pow((Vectorpad2[i]), 2);
            }//end for loop

            //attain proper values for mag1, mag2 and denom
            mag1 = Math.sqrt(mag1);
            mag2 = Math.sqrt(mag2);
            denom = (mag1 * mag2);

            //return cosine similarity
            return (num / denom);

        //no padding needed
        }else{

            //calculate mag1 squared, mag2 squared, and num
            for (int i = 0; i < Vector1.length; i++) {
                num += (Vector1[i] * Vector2[i]);
                mag1 += Math.pow((Vector1[i]), 2);
                mag2 += Math.pow((Vector2[i]), 2);
            }//end for loop

            //attain proper values for mag1, mag2 and denom
            mag1 = Math.sqrt(mag1);
            mag2 = Math.sqrt(mag2);
            denom = (mag1 * mag2);

            //return cosine similarity
            return (num / denom);
        }
    }

    /**
     * The EuclideanDistance method measures the distance between the two parameters by drawing
     * a straight line between them and measuring them. This is done mathematically in the method
     * and not graphically. It is calculated by summing the squared difference of each element in
     * the parameters and then taking the square root of that sum.
     *
     * @param Vector1 The first array to be compared.
     * @param Vector2 The second array to be compared.
     * @return The distance between the two arrays if they are set as points in Euclidean space. Value
     *         should be greater than or equal to 0.
     */
    public double EuclidianDistance(double Vector1[], double Vector2[]) {

        /**
         *
         */
        double sum = 0;

        //check if vector one need to be padded with 0 for elements that do not exist in the second vector
        if(Vector1.length < Vector2.length){

            /**
             *
             */
            double Vectorpad1[] = new double[Vector2.length];

            //copy smaller vector into a vector the same size larger vector
            System.arraycopy(Vector1,0,Vectorpad1,0,Vector1.length);
            //all new elements get 0
            for(int i = Vector1.length;i<Vector2.length;i++){
                Vectorpad1[i]=0;
            }//end for loop

            //sum of squared difference
            for (int i = 0; i < Vector2.length; i++) {
                sum += Math.pow((Vector2[i] - Vectorpad1[i]), 2);
            }//end for loop

            sum = Math.sqrt(sum);
            return sum;

        //check if the second vector needs padded with 0 for elements that do not exist in the first vector
        }else if(Vector2.length<Vector1.length){

            /**
             *
             */
            double Vectorpad2[] = new double[Vector1.length];

            //copy smaller vector into a vector the same size larger vector
            System.arraycopy(Vector2,0,Vectorpad2,0,Vector2.length);
            //all new elements get 0
            for(int i = Vector2.length;i<Vector1.length;i++){
                Vectorpad2[i]=0;
            }//end for loop

            //sum of squared difference
            for (int i = 0; i < Vector1.length; i++) {
                sum += Math.pow((Vector1[i] - Vectorpad2[i]), 2);
            }//end for loop

            sum = Math.sqrt(sum);
            return sum;

        //no padding needed
        }else{

            //sum of squared difference
            for (int i = 0; i < Vector1.length; i++) {
                sum += Math.pow((Vector2[i] - Vector1[i]), 2);
            }//end for loop

            sum = Math.sqrt(sum);
            return sum;

        }
    }

    /**
     *
     *
     * @param BinStr1 The first binary string to compare.
     * @param BinStr2 The second binary string to compare.
     * @return A value of -1 is returned if the strings are of different length, as
     *         the hamming distance isn't defined for strings of different lengths. A
     *         value of 0 is returned if the strings are the same. A value 0< is returned
     *         if the strings differ, and the greater the value the greater the difference.
     */
    public int HammingDistance(String BinStr1, String BinStr2) {

        /**
         * The value integer is used to keep track of the Hamming Distance.
         * This counter increments every time the strings at any index, differ
         * from one another.
         */
        int value = 0;

        /**
         * This is a char array representation of the first string passed.
         * Changing to a char array allows for removal of some characters,
         * such as the new line character or terminating character.
         */
        char BitString1[] = BinStr1.toCharArray();

        /**
         * This is a char array representation of the second string passed.
         * Changing to a char array allows for removal of some characters,
         * such as the new line character or terminating character.
         */
        char BitString2[] = BinStr2.toCharArray();

        if (BitString1.length == BitString2.length){
            //go through each char array, they must have the same length
            for (int i = 0; i < BinStr1.length(); i++) {
                //check to see if the character match
                if (Character.valueOf(BitString1[i]) != Character.valueOf(BitString2[i])) {
                    //if they don't, increment hammingDistance value
                    value++;
                } //end if loop
            }//end for loop
            return value;
        }else{
            //return -1 if invalid input
            return -1;
        }//end if else loop
    }
}
