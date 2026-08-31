package data_structures.stack.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NextSmallestElementLeft {

    static List<Integer> nextSmallestElementLeft(List<Integer> arr) {
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        int length = arr.size();

        for(int i=0;i<length;i++){
            Integer currentElement = arr.get(i);
            if(stack.isEmpty()){
                result.add(-1);
                stack.push(currentElement);
            }else{
                while(!stack.isEmpty()){
                    if(stack.peek() < currentElement){
                        result.add(stack.peek());
                        stack.add(currentElement);
                        break;
                    }else{
                        stack.pop();
                    }
                }
                if(stack.isEmpty()){
                    result.add(-1);
                    stack.push(currentElement);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list1 = List.of(1, 3, 4, 2);
        List<Integer> list2 = List.of(1, 3, 0, 0, 1, 2, 4);
        List<Integer> list3 = List.of(1, 3, 2, 2);
        List<Integer> res1 = nextSmallestElementLeft(list1);
        for (Integer elem : res1) {
            System.out.println(elem);
        }
    }
}
