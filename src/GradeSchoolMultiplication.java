import java.util.Arrays;

public class GradeSchoolMultiplication {

    private static final int DEFAULT_BASE = 10;
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

    public static int[] multiply(final int[] x, final int[] y) {
        //int numOfDigits = findDigits(x,y);
        
        int n1 = x.length; //lengths
        int n2 = y.length; //lengths
        int[] answer = new int[n1 + n2 + 1]; //intilize new array
        int n3 = answer.length -1;
        int n4 = n3 -1;
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                // Multiply current digits
                int product = x[i] * y[j];
                
                // Add product to the corresponding position in the result array
                int onesPlace = product % DEFAULT_BASE; //get ones place
                
                answer[n3] = answer[n3] + onesPlace;
                
                int tensPlace = product / DEFAULT_BASE; //get tens place
                if(n3 != 0){
                    answer[n3-1] = answer[n3 -1] + tensPlace;
                }else{
                    answer[n3] = answer[n3] + product;
                }
                n3--;
            }
            n3 = n4--;
            
           // System.out.println("ans array: " + n3);
        }
        return answer;
        
    } 
    
    public static int[] verify(int[] answer){
        for(int i = answer.length -1; i >=0; i--){
            if(answer[i] >= DEFAULT_BASE){
                int num = answer[i] / DEFAULT_BASE;
                answer[i] = answer[i] - (num * DEFAULT_BASE);
                answer[i-1] = answer[i-1] + num;
            }
        }
        return answer;
    }
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
        
        int[] finalAnswer = new int[answer.length - count];
        int num = answer.length -1;
        for(int j = finalAnswer.length -1; j >= 0; j--){
            finalAnswer[j] = answer[num];
            num--;
        }
        return finalAnswer;
    }
    
    
}

