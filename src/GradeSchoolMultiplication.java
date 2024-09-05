import java.util.Arrays;

public class GradeSchoolMultiplication {

    private static final int DEFAULT_BASE = 10;
    /* MAIN
     * intilize int[] x & int[] y
     * product[] = multiply(x,y) return an array with the unorganized product of x & y
     * vAnswer[] = verify(product) return an array with the organized product in single digits
     * finalAnswer = removeBlanks(vAnswer) returns array without leading zeros;
     * print(finalAnswer) 
     * */
     
    public static void main(String[] args){
        int[] y = {1,2,3,4};
        int[] x = {5,6,7,8};
        //answer shouyld be 7 0 0 6 6 5 2
        // [] = {7,0,0,6,6,5,2}
        
        int[] product = multiply(x,y);
        int[] vAnswer = verify(product); //gives you a verified array with single digits
        int[] finalAnswer = removeBlanks(vAnswer);
        System.out.println(Arrays.toString(finalAnswer));   
    }
/*multiply NEEDS int[]x, int[]y
 * n1 = x.length
 * n2 = y.length
 * int[] answer = [n1+n2+1] Makes array with length of the sum of the two lengths + 1 for overflow
 * FOR(x.length){
 *  FOR(y.length{
 *      product = x[i] * y[j]
 *      onesPlace = product % 10
 *      tensPlace = product / 10
 *      answer = onesPlace
 *      answer = tensPlace
 *  }
 * }
 *  Like long division work from smallest number to largest
 */
    public static int[] multiply(final int[] x, final int[] y) {
        //int numOfDigits = findDigits(x,y);
        
        int n1 = x.length; //lengths
        int n2 = y.length; //lengths
        int[] answer = new int[n1 + n2 + 1]; //intilize new array
        int n3 = answer.length -1; //iterate through inner loop
        int n4 = n3 -1; //reset in outerloop while still decreasing so each number is placed correctly
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                // Multiply current digits
                int product = x[i] * y[j];
                
                // Add product to the corresponding position in the result array
                int onesPlace = product % DEFAULT_BASE; //get ones place
                
                answer[n3] = answer[n3] + onesPlace; //store 1s place in current spot
                
                int tensPlace = product / DEFAULT_BASE; //get tens place
                if(n3 != 0){
                    answer[n3-1] = answer[n3 -1] + tensPlace; //store 10s place in one spot lower
                }else{
                    answer[n3] = answer[n3] + product; //prevent out of bounds
                }
                n3--; //iterate through answer array
            }
            n3 = n4--; //reset answer array to starting one spot less than before
            
        }
        return answer;
        
    } 
    /* verify NEEDS int[] answer
     * FOR(answer.length){
     *  IF (value of ANSWER >= 10)
     *      num = DIVIDE answer[i] BY 10
     *      answer[i] = answer[i] - num*10
     *      answer[i-1] = answer[i-1] + num 
     * //Take values of 10s place in ans[i] and move it forward to the next place
     *  }
     * }
     */
    public static int[] verify(int[] answer){
        for(int i = answer.length -1; i >=0; i--){
            if(answer[i] >= DEFAULT_BASE){
                int num = answer[i] / DEFAULT_BASE;
                answer[i] = answer[i] - (num * DEFAULT_BASE); //take 10s place out
                answer[i-1] = answer[i-1] + num; //add 10s place to next part of the array
            }
        }
        return answer;
    }
    /*removeBlanks(answer)
     *  REMOVE ANY LEADING ZEROS
     * boolean notzero = false
     * count //to track number of leading zeros
     * FOR(answer.length)
     *  if(ANSWER[i] == 0 && notzero == false){
     * count++
     * }WHEN answer[i] > 0, notzero = true;
     * 
     * intilize new array
     * int[] finalAnswer = answer.length - count
     * 
     * use FOR finalAnswer.length
     *      finalAnswer[i] = answer[i]
     */
    public static int[] removeBlanks(int[] answer){
        boolean isVal = false; //check that a value greater than 0 has happened
        int count = 0; //count how many leading zeros to create new array
        for(int i = 0; i < answer.length; i++){
            if(answer[i] == 0 && isVal == false){
                count++;
            }else if(answer[i] > 0){
                isVal = true;
            }
        }
        //create new array without leading zeros
        int[] finalAnswer = new int[answer.length - count];
        int num = answer.length -1; //start from last value, (ones place)
        for(int j = finalAnswer.length -1; j >= 0; j--){
            finalAnswer[j] = answer[num]; //start from ones place and work to end of finalAnswer
            num--; //iterate
        }
        return finalAnswer; //return to print
    }
    
    
}

