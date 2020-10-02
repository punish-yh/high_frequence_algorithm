package cn.anlish.algorithm.lession1;

import java.util.Stack;

/**
 * 经典汉诺塔问题
 * 汉诺塔，一共三个杆。将最左的塔移动到最右边。不能小压大
 * 不能从最左侧直接移动到最右侧
 * 不能从最右侧直接移动到最左侧
 * 最左到最右的互相移动必须经过中间
 * 求，当塔有N层的时候，打印最优移动过程和最有移动总步数
 *
 * 栈做法
 *
 * @Author: Anlish
 * @Date: 2020/9/30
 */
public class Problem05 {

    //代表三个圆柱
    public static String LEFT = "left";
    public static String RIGHT = "right";
    public static String MIDDLE = "middle";


//    /**
//     * 将num层到1层圆盘从from移动到to位置并打印圆盘移动轨迹
//     *
//     * @param num 圆盘的层数
//     * @param from 起始位置，有“left middle right”可选
//     * @param to 目标位置，有“left middle right”可选
//     * @return 完成操作所需的移动步数
//     */
//    public static int process(int num, String from, String to){
//        //递归情况下的base case，整个游戏中只有一个圆盘时
//        if(num == 1){
//            if(from.equals(MIDDLE) || to.equals(MIDDLE)){
//                //当起始位或结束位有一个是中间圆柱时可以直接完成移动操作，且移动次数为1
//                System.out.println("Move 1 from " + from + " to " + to);
//                return 1;
//            }else{
//                //其他情况下必须经过中间圆柱才能完成移动操作
//                System.out.println("Move 1 from " + from + " to " + MIDDLE);
//                System.out.println("Move 1 from " + MIDDLE + " to " + to);
//                return 2;
//            }
//        }
//
//
//    }


    public static int hanoiProblem1(int num, String left, String mid, String right) {
        if (num < 1) {
            return 0;
        }
        return process(num, left, mid, right, left, right);
    }

    public static int process(int num, String left, String mid, String right, String from, String to) {
        if (num == 1) {
            if (from.equals(mid) || to.equals(mid)) {
                System.out.println("Move 1 from " + from + " to " + to);
                return 1;
            } else {
                System.out.println("Move 1 from " + from + " to " + mid);
                System.out.println("Move 1 from " + mid + " to " + to);
                return 2;
            }
        }
        if (from.equals(mid) || to.equals(mid)) {
            String another = (from.equals(left) || to.equals(left)) ? right : left;
            int part1 = process(num - 1, left, mid, right, from, another);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + to);
            int part3 = process(num - 1, left, mid, right, another, to);
            return part1 + part2 + part3;
        } else {
            int part1 = process(num - 1, left, mid, right, from, to);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + mid);
            int part3 = process(num - 1, left, mid, right, to, from);
            int part4 = 1;
            System.out.println("Move " + num + " from " + mid + " to " + to);
            int part5 = process(num - 1, left, mid, right, from, to);
            return part1 + part2 + part3 + part4 + part5;
        }
    }

    public static enum Action {
        No, LToM, MToL, MToR, RToM
    }

    public static int hanoiProblem2(int num, String left, String mid, String right) {
        Stack<Integer> lS = new Stack<Integer>();
        Stack<Integer> mS = new Stack<Integer>();
        Stack<Integer> rS = new Stack<Integer>();
        lS.push(Integer.MAX_VALUE);
        mS.push(Integer.MAX_VALUE);
        rS.push(Integer.MAX_VALUE);
        for (int i = num; i > 0; i--) {
            lS.push(i);
        }
        Action[] record = { Action.No };
        int step = 0;
        while (rS.size() != num + 1) {
            step += fStackTotStack(record, Action.MToL, Action.LToM, lS, mS, left, mid);
            step += fStackTotStack(record, Action.LToM, Action.MToL, mS, lS, mid, left);
            step += fStackTotStack(record, Action.RToM, Action.MToR, mS, rS, mid, right);
            step += fStackTotStack(record, Action.MToR, Action.RToM, rS, mS, right, mid);
        }
        return step;
    }

    public static int fStackTotStack(Action[] record, Action preNoAct, Action nowAct, Stack<Integer> fStack,
                                     Stack<Integer> tStack, String from, String to) {
        if (record[0] != preNoAct && fStack.peek() < tStack.peek()) {
            tStack.push(fStack.pop());
            System.out.println("Move " + tStack.peek() + " from " + from + " to " + to);
            record[0] = nowAct;
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        int num = 4;

        // solution 1
        int steps1 = hanoiProblem1(num, "left", "mid", "right");
        System.out.println("It will move " + steps1 + " steps.");
        System.out.println("===================================");

        // solution 2
        int steps2 = hanoiProblem2(num, "left", "mid", "right");
        System.out.println("It will move " + steps2 + " steps.");
        System.out.println("===================================");

    }
}
