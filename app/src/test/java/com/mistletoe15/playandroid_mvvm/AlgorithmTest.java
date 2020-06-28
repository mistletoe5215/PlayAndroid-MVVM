package com.mistletoe15.playandroid_mvvm;

import org.junit.Test;

/**
 * Created by Mistletoe on 2020/6/22
 **/
public class AlgorithmTest {
    @Test
    public void nodeTest(){
        //单向链表反转
        Node<String> n1= new Node<>();
        n1.setData("node1");
        Node<String> n2= new Node<>();
        n2.setData("node2");
        Node<String> n3= new Node<>();
        n3.setData("node3");
        Node<String> n4= new Node<>();
        n4.setData("node4");
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        //原链表
        Node head = n1;
        while (head!=null){
            System.out.println(head.toString());
            head = head.next;
        }
       //反转链表
       //Node reverseHead = reverseNode(n1);
        Node reverseHead = reverseNode2(n1);
        while (reverseHead!=null){
            System.out.println(reverseHead.toString());
            reverseHead = reverseHead.next;
        }
    }
    private Node reverseNode(Node head){
        if(head==null || head.next ==null){
            return  head;
        }
        Node temp = head.next;
        Node newNode = reverseNode(temp);
        temp.next = head;
        head.next = null;
        return newNode;
    }
    private Node reverseNode2(Node head){
        Node pre = null;
        Node next;
       while (head!=null){
          next = head.next;
          head.next = pre;
          pre = head;
          head = next;

        }
       return pre;
    }
    @Test
    public void jumpStageTest(){
        //1阶跳1或者2，第一步跳1，后面n-1 有 jumpStage(n-1)种；第一步跳2，后面n-2 有 jumpStage(n-2)种；总共jumpStage(n-1)+jumpStage(n-2)
        jumpStage(4);
        //n阶有第一步可以跳1-n步，第一步是1,剩下n-1 ，有jumpStageV2（n-1）;第一步是2,剩下n-2 ，有jumpStageV2（n-2）;第一步是3,剩下n-3 ，有jumpStageV2（n-3）
        //jumpStageV2（n) = jumpStageV2（n-1） + jumpStageV2（n-2）+...+jumpStageV2（1）
        //jumpStageV2（n-1) = jumpStageV2（n-2） + jumpStageV2（n-3）+...+jumpStageV2（1）
        //jumpStageV2（n)  = 2 * jumpStageV2（n-1)
        jumpStageV2(4);
    }
    private int  jumpStage(int stage){
        if(stage == 1)
            return 1;
        if(stage == 2)
            return  2;
        return  jumpStage(stage-1)+jumpStage(stage-2);
    }

    private int  jumpStageV2(int stage){
        if(stage == 1)
            return 1;
        if(stage == 2)
            return  2;
        return  2*jumpStageV2(stage-1);
    }
    @Test
    public void reverseNumTest(){
        int origin = 123;
        System.out.println(reverseNum(origin));
        int palindromeNum = 1234321;
        System.out.println(isPalindrome(palindromeNum));
    }
    private int reverseNum(int originNum){
        long res=0;
        for (;originNum!=0;originNum/=10 ){
            res = res * 10 +originNum%10;
        }
        return  res< Integer.MIN_VALUE || res > Integer.MAX_VALUE ?0:(int)res;
    }
    private boolean isPalindrome(int num){
        return  num == reverseNum(num);
    }
    @Test
    public void middleSearchTest(){
           int[] array = {1,5,17,8,3};
           int[] _sortArray =  bubbleSort(array);
           int target = 17;
           System.out.println(target+"下标是："+findIndexUseMiddleSearch(_sortArray,target,0,array.length -1));
    }
    private int findIndexUseMiddleSearch(int[] array,int target,int low,int high){
        if(target < array[low] || target > array[high]|| low > high){
            return  -1;
        }
        int middle = (low+high)/2;
        if(array[middle] > target){
            return  findIndexUseMiddleSearch(array,target,low,middle -1);
        }else if(array[middle] < target){
            return  findIndexUseMiddleSearch(array,target,middle +1,high);
        }else{
            return  middle;
        }
    }
    private int[] bubbleSort(int[] array){
        if(array.length == 0|| array.length ==1){
            return  array;
        }
        for (int i = 0; i < array.length -1; i++) {
            for (int j = 0; j < array.length -1; j++) {
                if(array[j+1] < array[j]){
                    int temp  = array[j+1];
                    array[j+1] =  array[j];
                    array[j] = temp;
                }
            }
        }
        return  array;
    }
}
