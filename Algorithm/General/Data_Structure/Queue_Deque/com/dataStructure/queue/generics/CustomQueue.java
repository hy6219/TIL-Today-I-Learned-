package com.dataStructure.queue.generics;

import java.lang.reflect.Array;

public class CustomQueue<E> {
    E[] queue;
    int pointer = 0;
    int rear =0;

    public CustomQueue(){
        queue= (E[])new Object[1000];
    }
    //enqueue
    public boolean offer(E item) throws ArrayIndexOutOfBoundsException{
        queue[pointer++]=item;
        return true;
    }

    //size
    public int size() throws ArrayIndexOutOfBoundsException{
        return pointer;
    }
    //isEmpty
    public boolean isEmpty(){
        return pointer==0;
    }

    //데이터를 넣는 곳 front
    public E front() throws ArrayIndexOutOfBoundsException{
        return queue[pointer-1];
    }

    //데이터를 빼는 곳 rear
    public E peek() throws ArrayIndexOutOfBoundsException{
        return queue[rear];
    }
    //dequeue
    public E poll() throws ArrayIndexOutOfBoundsException{
        pointer--;
        return queue[rear++];//실제로 없애지 않고 포인터를 이용
    }
    //remove
    public E remove(int idx) throws ArrayIndexOutOfBoundsException{
        E[] replaced = (E[]) new Object[pointer-1];
        E tmp = queue[idx];
        for(int i = 0 ; i < idx; i++){
            replaced[i] = queue[i];
        }
        for(int i = idx ; i < pointer-1; i++){
            replaced[i] = queue[i+1];
        }
        pointer--;
        //clone
        queue = replaced.clone();//배열은 cloneable 구현하지 않아도 됨!
        return tmp;
    }
    //toString
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        int size = size();

        sb.append("[");
        for(int i = 0 ; i < size; i++){
            sb.append(queue[i]);

            if(i!=size-1){
                sb.append(",");
            }
        }
        sb.append("]");

        return sb.toString();
    }
}
