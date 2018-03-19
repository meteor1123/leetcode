/*
	Cosine Similarity
	Cosine similarity is a measure of similarity between two vectors of an inner product space that measures the cosine of the angle between them. The cosine of 0° is 1, and it is less than 1 for any other angle.

	See wiki: Cosine Similarity

	Here is the formula:

	/media/problem/cosine-similarity.png

	Given two vectors A and B with the same size, calculate the cosine similarity.

	Return 2.0000 if cosine similarity is invalid (for example A = [0] and B = [0]).

	Example
	Given A = [1, 2, 3], B = [2, 3 ,4].

	Return 0.9926.

	Given A = [0], B = [0].

	Return 2.0000
 */

class Solution {
    public double cosineSimilarity(int[] A, int[] B) {
        double upper = 0;
        double lowerA = 0;
        double lowerB = 0;
        for (int i = 0; i < A.length; i++) {
            upper += (double)(A[i] * B[i]);
            lowerA += (double)(A[i] * A[i]);
            lowerB += (double)(B[i] * B[i]); 
        }
        if (lowerA == 0 || lowerB == 0) {
            return 2.0000;
        }
        lowerA = Math.sqrt(lowerA);
        lowerB = Math.sqrt(lowerB);
        return upper / (lowerA * lowerB);
    }
}


