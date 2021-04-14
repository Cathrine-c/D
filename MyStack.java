package InterviewImportant.DS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyStack {


    //给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);

            if (c=='['||c=='{'||c=='('){
                stack.push(c);
                continue;
            }
            if (stack.isEmpty()) {
                return false;
            }

            char c1 = stack.pop();

            if (c1=='('&&c==')'){
                continue;
            }

            if (c1=='['&&c==']'){
                continue;
            }
            if (c1=='{'&&c1=='}'){
                continue;
            }

            return false;
        }

        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }



    //用队列实现栈
    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public MyStack() {
         queue1 = new LinkedList<>();
         queue2 = new LinkedList<>();

    }


    public void push(int x) {
        queue1.offer(x);
        if (!queue2.isEmpty()) {
            queue1.offer(queue2.poll());
        }
        //交换queue和queue2，保持queue1始终为空
        Queue q = queue1;
        queue1 = queue2;
        queue2 = q;

    }


    public Integer pop() {

        return queue2.poll();

    }


    public int top() {

        return queue2.peek();
    }


    public boolean empty() {
        return queue2.isEmpty();

    }


    //计算逆波兰式（后缀表达式）的值
    //运算符仅包含"+","-","*"和"/"，被操作数可能是整数或其他表达式
    //例如：
    //  ["20", "10", "+", "30", "*"] -> ((20 + 10) * 30) -> 900
    public int evalRPN (String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (int i=0;i<tokens.length;i++){
            if (tokens[i].equals("+")||tokens[i].equals("-")||tokens[i].equals("*")||tokens[i].equals("/")){
                int data2 = stack.pop();
                int data1 = stack.pop();
                stack.push(helper(tokens[i],data1,data2));
            }else {
                int n = Integer.parseInt(tokens[i]);
                stack.push(n);
            }

        }
        return stack.pop();
    }


    private Integer helper(String token, int data1, int data2) {
        if (token.equals("+")){
            return data1+data1;
        }

        if (token.equals("-")){
            return data1-data2;
        }

        if (token.equals("*")){
            return data1*data2;
        }

        if (token.equals("/")){
            return data1/data2;

        }
        throw new RuntimeException(token+"出现不合理数字");

    }


    //最小栈的实现
    Stack<Integer> mainStack ;
    Stack<Integer> minStack;

    public void Stack(){

        mainStack = new Stack<>();
        minStack = new Stack<>();

    }

    public void push2(Integer e){

        mainStack.push(e);
        if (e<minStack.peek() || minStack.isEmpty()) {
            minStack.push(e);

        }
    }


    public Integer pop2(){

        if (minStack.peek().equals(mainStack.peek()) ) {
            minStack.pop();
        }

        return mainStack.pop();
    }


    public Integer min() throws Exception {

        if (minStack.isEmpty()) {
            throw new Exception("最小栈为空");
        }
        return minStack.peek();

    }


    /**
     * 两个栈实现一个队列，一个栈入队列，
     * 如果要出队列，先把栈A中的元素出栈到栈B，然后栈B元素出栈就实现了出队，
     * 如果栈B元素空了，就从栈A中取出来，进入栈B，栈B继续出栈
     *
     */


    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();


    //入队
    public void enQueue(Integer e){

        stack1.push(e);
    }


    //出队
    public Integer deQueue(){
        if (stack2.isEmpty()) {
            if (stack1.isEmpty()) {
                return null;
            }

            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        return stack2.pop();

    }



}

