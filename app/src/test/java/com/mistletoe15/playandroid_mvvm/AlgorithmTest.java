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
       Node reverseHead = reverseNode(n1);
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
}
