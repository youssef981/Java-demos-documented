package dsa;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicates {

    //(O(n) time, O(n) space)
    public static boolean containsDuplicate1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<nums.length;i++){
            if(!set.add(nums[i])){
                return true;
            }
        }
        return false;
    }
    //(O(n*log(n)) due to soring and O(1) space
    public static boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for(int i=1;i<nums.length;i++){
            if(nums[i-1]-nums[i]==0){
                return true;
            }
        }
        return false;
    }
}
