import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * The SimilarityMetricsTest is used to test
 * the similarityMetrics class and the
 * knearest class.
 */
public class SimilarityMetricsTest {

    @Test
    void CosineTest(){

        /**
         * The SimilarityMetrics sim is used to
         * test the class methods.
         */
        SimilarityMetrics sim = new SimilarityMetrics();

        /**
         * The double array is declared for
         * testing purposes.
         */
        double arr1[] = new double[]{1,0,0};

        /**
         * The double array is declared for
         * testing purposes.
         */
        double arr2[] = new double[]{0,0,1};

        /**
         * The double array is declared for
         * testing purposes.
         */
        double arr3[] = new double[]{1,0};

        /**
         * The double array is declared for
         * testing purposes.
         */
        double arr4[] = new double[]{1,1,1};

        /**
         * The double array is declared for
         * testing purposes.
         */
        double arr5[] = new double[]{2,2,2};

        /**
         * The double array is declared for
         * testing purposes.
         */
        double arr6[] = new double[]{1,2,3};

        /**
         * The double array is declared for
         * testing purposes.
         */
        double arr7[] = new double[]{4,5,6};

        /**
         * The double array is declared for
         * testing purposes.
         */
        double arr8[] = new double[]{7,8,9};

        /**
         * The double array is declared for
         * testing purposes.
         */
        double arr9[] = new double[]{1,2};

        /**
         * The double array is declared for
         * testing purposes.
         */
        double arr10[] = new double[]{2,1};

        assertEquals(0, sim.CosineSimilarity(arr1,arr2));
        assertEquals(0.5773502691896258, sim.CosineSimilarity(arr3,arr4));
        assertEquals(0.9258200997725515, sim.CosineSimilarity(arr5,arr6));
        assertEquals(0.9981908926857269, sim.CosineSimilarity(arr7,arr8));
        assertEquals(.7999999999999998, sim.CosineSimilarity(arr9,arr10));
    }
    @Test
    void EuclidianTest(){

        /**
         * The SimilarityMetrics sim is used to
         * test the class methods.
         */
        SimilarityMetrics sim = new SimilarityMetrics();

        /**
         * The double array is declared for
         * testing purposes.
         */
        double arr1[] = new double[]{1,0,0};

        /**
         * The double array is declared for
         * testing purposes.
         */
        double arr2[] = new double[]{0,0,1};

        /**
         * The double array is declared for
         * testing purposes.
         */
        double arr3[] = new double[]{1,0};

        /**
         * The double array is declared for
         * testing purposes.
         */
        double arr4[] = new double[]{1,1,1};

        /**
         * The double array is declared for
         * testing purposes.
         */
        double arr5[] = new double[]{2,2,2};

        /**
         * The double array is declared for
         * testing purposes.
         */
        double arr6[] = new double[]{1,2,3};

        /**
         * The double array is declared for
         * testing purposes.
         */
        double arr7[] = new double[]{4,5,6};

        /**
         * The double array is declared for
         * testing purposes.
         */
        double arr8[] = new double[]{7,8,9};

        /**
         * The double array is declared for
         * testing purposes.
         */
        double arr9[] = new double[]{1,2};

        /**
         * The double array is declared for
         * testing purposes.
         */
        double arr10[] = new double[]{2,1};

        assertEquals(1.4142135623730951, sim.EuclidianDistance(arr1,arr2));
        assertEquals(1.4142135623730951, sim.EuclidianDistance(arr3,arr4));
        assertEquals(1.4142135623730951, sim.EuclidianDistance(arr5,arr6));
        assertEquals(5.196152422706632, sim.EuclidianDistance(arr7,arr8));
        assertEquals(1.4142135623730951, sim.EuclidianDistance(arr9,arr10));

    }
    @Test
    void HammingTest(){

        /**
         * The SimilarityMetrics sim is used to
         * test the class methods.
         */
        SimilarityMetrics sim = new SimilarityMetrics();

        assertEquals(4,sim.HammingDistance("0000","1111"));
        assertEquals(-1,sim.HammingDistance("1010","01010"));
        assertEquals(3, sim.HammingDistance("00110","11111"));
        assertEquals(4, sim.HammingDistance("11001","00111"));
        assertEquals(3, sim.HammingDistance("11101","00001"));
    }

    @Test
    void KnearestTest() throws IOException {

        /**
         * The double array is declared for
         * testing purposes.
         */
        double[] point1 = new double[]{1.5,3.5,2,2,8};

        /**
         * The double array is declared for
         * testing purposes.
         */
        double[] point2 = new double[]{3,3,2,2,1};

        /**
         * The kthNearest test1 is used
         * to test the functionality of
         * the kthNearest Test.
         */
        kthNearest test1 = new kthNearest("S27-MLMedium.csv", point1, 5);

        /**
         * The kthNearest test1 is used
         * to test the functionality of
         * the kthNearest Test.
         */
        kthNearest test2 = new kthNearest("S27-MLMedium.csv", point2, 5);

        assertSame("New data point belongs to class1",test1.toString());
        assertSame("New data point belongs to class2",test2.toString());
    }
}
