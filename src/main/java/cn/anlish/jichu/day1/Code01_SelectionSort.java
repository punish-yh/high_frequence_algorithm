package cn.anlish.jichu.day1;

import java.util.Arrays;

/**
 * 选择排序、冒泡排序、插入排序、快速排序
 * @Author: Anlish
 * @Date: 2021/7/1
 */
public class Code01_SelectionSort {

    public static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 选择排序主要思想
     * 每次选择剩余序列中最大的或最小的元素与当前头元素交换
     * @param arr
     */
    public static void selectionSort(int[] arr){
        if(arr == null || arr.length <= 1){
            return ;
        }
        for(int i = 0; i < arr.length; i++){
            // 用来保存每次要和i交换的下标
            int targetIndex = i;
            for(int j = i + 1; j < arr.length; j++){
                // 这里控制desc or asc
                targetIndex = arr[j] < arr[targetIndex] ? j : targetIndex;
            }
            swap(arr, i, targetIndex);
        }
    }


    /**
     * 冒泡排序主要思想
     * 每次把最大的或最小的元素放到末尾，（像泡泡一样冒了上去）
     * 和selectionSort主要区别是交换次数更多一些，相邻的两个元素会比较频繁的交换
     * @param arr
     */
    public static void bubbleSort(int[] arr){
        if(arr == null || arr.length < 2) {
            return;
        }
        for(int i = arr.length - 1; i >= 0; i--){
            for(int j = 0; j < i; j++){
                // 这里控制desc or asc
                if(arr[i] < arr[j]){
                    swap(arr, i, j);
                }
            }
        }
    }


    /**
     * 插入排序主要思想
     * 从数组的第二个数据开始往前比较，即一开始用第二个数和他前面的一个比较，如果 符合条件（比前面的大或者小，自定义），则让他们交换位置。
     * @param arr
     */
    public static void insertionSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        for(int i = 1; i < arr.length; i++){
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--){
                    swap(arr, j, j + 1);
            }
        }
    }


    public static void quickSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
    }



    public static void main(String[] args){
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;

        boolean succeed = true;
        for(int i = 0; i < testTime; i++){
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            //selectionSort(arr1);
            //bubbleSort(arr1);
            insertionSort(arr1);
            comparator(arr2); // 调用官方排序算法
            if(!isEqual(arr1, arr2)){
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        //selectionSort(arr);
        //bubbleSort(arr);
        insertionSort(arr);
        printArray(arr);
    }




    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }


    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }
}
