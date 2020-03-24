设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。

在链表类中实现这些功能：

get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
class MyLinkedList {
    private static class Node{
        private int val;
        private Node pre;
        private Node next;
        public Node(int val,Node pre,Node next){
            this.val=val;
            this.pre=pre;
            this.next=next;
        }
        public Node(int val){
            this(val,null,null);
        }
    }
    private Node head;
    private Node tail;
    private int size;
    /** Initialize your data structure here. */
    public MyLinkedList() {

    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(index<0||index>=size){
            return -1;
        }
        Node cur=head;
        while(index--!=0){
            cur=cur.next;
        }
        return cur.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node node=new Node(val);
        if(head==null){
            head=node;
            tail=node;
        }else{
            node.next=head;
            head.pre=node;
            head=node;
        }
        size++;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node node=new Node(val);
        if(head==null){
            head=node;
            tail=node;
        }else{
            node.pre=tail;
            tail.next=node;
            tail=node;
        }
        size++;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index>size){
            return;
        }
        if(index==size){
            addAtTail(val);
            return;
        }
        if(index<=0){
            addAtHead(val);
            return;
        }
        Node cur=head;
        while(index--!=0){
            cur=cur.next;
        }
        Node node=new Node(val);
        node.next=cur;
        node.pre=cur.pre;
        cur.pre.next=node;
        cur.pre=node;
        size++;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index<0||index>=size){
            return;
        }
        if(size==1){
            head=null;
            tail=null;
        }else if(index==0){
            Node node=head;
            head=head.next;
            head.pre=null;
            node.next=null;
        }else if(index==size-1){
            Node node=tail;
            tail=tail.pre;
            tail.next=null;
            node.pre=null;
        }else{
            Node cur=head;
            while(index--!=0){
                 cur=cur.next;
            }
            Node node=cur;
            cur.pre.next=node.next;
            node.next.pre=node.pre;
            node.pre=null;
            node.next=null;
        }
        
        size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
 
 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy=new ListNode(-1);
        ListNode cur=dummy;
        int carry=0;
        while(l1!=null||l2!=null||carry!=0){
            int num1=l1==null?0:l1.val;
            int num2=l2==null?0:l2.val;
            int sum=num1+num2+carry;
            ListNode node=new ListNode(sum%10);
            carry=sum/10;
            cur.next=node;
            cur=cur.next;
            l1=l1==null?null:l1.next;
            l2=l2==null?null:l2.next;
        }
        return dummy.next;
    }
}

