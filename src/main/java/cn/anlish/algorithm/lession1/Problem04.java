package cn.anlish.algorithm.lession1;

import java.util.Stack;

/**
 * 题目描述：
 * 一个栈依次压入1、2、3、4、5，那么从栈顶到栈底分别为5、4、3、2、1
 * 要求将这个栈转置后从栈顶到栈底分别为1、2、3、4、5，也就是实现栈中元素的逆序
 * 但只能用递归函数来实现，不能借助其他的数据结构
 *
 *
 * 首先思考核心问题，如何使用递归获取一个栈的栈底元素并将其移除掉？
 * public int getAndRemoveLastElement(Stack s){
 *     int res = s.pop();
 *     if(s.isEmpty()){
 *         return res;
 *     }else{
 *         int res2 = getAndRemoveLastElement(s);
 *         s.push(res);
 *         return res2;
 *     }
 * }
 *
 * @Author: Anlish
 * @Date: 2020/9/30
 */
public class Problem04 {

    public static void main(String[] args){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        reverseStack(stack);
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    public static void reverseStack(Stack<Integer> stack){
        if(stack.isEmpty()){
            return ;
        }

        //拿到栈底元素
        int element = getAndRemoveLastElement(stack);
        //递归地获取栈底元素
        reverseStack(stack);
        //全部元素都获取出来后将当前元素放入栈中
        stack.push(element);
    }


    /**
     * 移除栈底元素
     * @param s
     * @return
     */
    public static int getAndRemoveLastElement(Stack<Integer> s){
        int res = s.pop();
        if(s.isEmpty()){
            return res;
        }else{
            int res2 = getAndRemoveLastElement(s);
            s.push(res);
            return res2;
        }
    }
}
