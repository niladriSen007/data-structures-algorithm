package data_structures.stack.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NextSmallestElementRight {

    static List<Integer> nextSmallestElementRight(List<Integer> arr) {
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        int length = arr.size();

        for(int i=length-1;i>=0;i--){
            Integer currentElement = arr.get(i);
            if(stack.isEmpty()){
                result.add(-1);
                stack.push(currentElement);
            }else{
                while(!stack.isEmpty()){
                    if(currentElement > stack.peek()){
                        result.add(stack.peek());
                        stack.push(currentElement);
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

        return result.reversed();
    }

    public static void main(String[] args) {
        List<Integer> list1 = List.of(1, 3, 4, 2);
        List<Integer> list2 = List.of(1, 3, 0, 0, 1, 2, 4);
        List<Integer> list3 = List.of(1, 3, 2, 2);
        List<Integer> res1 = nextSmallestElementRight(list2);
        for (Integer elem : res1) {
            System.out.println(elem);
        }
    }
}
