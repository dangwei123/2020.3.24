一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/the-masseuse-lcci
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int massage(int[] nums) {
        int len=nums.length;
        if(len==0) return 0;
        if(len==1) return nums[0];
        int[] res=new int[len+1];
        res[0]=0;
        res[1]=nums[0];
        for(int i=2;i<=len;i++){
            res[i]=Math.max(res[i-2]+nums[i-1],res[i-1]);
        }
        return res[len];
    }
}

给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。

两个相邻元素间的距离为 1 。
class Solution {
    private int count;
    public int[][] updateMatrix(int[][] matrix) {
        int row=matrix.length;
        if(row==0) return new int[0][0];
        int col=matrix[0].length;
        int[][] res=new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(matrix[i][j]==0){
                    res[i][j]=0;
                }else{
                    res[i][j]=bfs(matrix,i,j,row,col);
                }
            }
        }
        return res;
    }
    private int bfs(int[][] matrix,int i,int j,int row,int col){
        Queue<Integer> queue=new LinkedList<>();
        Set<Integer> set=new HashSet<>();
        queue.offer(i*col+j);
        set.add(i*col+j);
        int res=0;
        while(!queue.isEmpty()){
            int size=queue.size();
            while(size--!=0){
                int m=queue.poll();
                int x=m/col;
                int y=m%col;
                if(matrix[x][y]==0){
                    return res;
                }
                if(x+1<row&&!set.contains(m+col)){
                    queue.offer(m+col);
                    set.add(m+col);
                } 
                if(x-1>=0&&!set.contains(m-col)){
                    queue.offer(m-col);
                    set.add(m-col);
                } 
                if(y+1<col&&!set.contains(m+1)){
                    queue.offer(m+1);
                    set.add(m+1);
                }
                if(y-1>=0&&!set.contains(m-1)){
                    queue.offer(m-1);
                    set.add(m-1);
                }
            }
            res++;
        }
        return -1;
    }
}

有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。

在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。

最初，除 0 号房间外的其余所有房间都被锁住。

你可以自由地在房间之间来回走动。

如果能进入每个房间返回 true，否则返回 false。
class Solution {
    Set<Integer> set=new HashSet<>();
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        dfs(rooms,0,rooms.size());
        return set.size()==rooms.size();
    }
    private void dfs(List<List<Integer>> rooms,int i,int row){
        if(i<0||i>=row||set.contains(i)){
            return;
        }
        set.add(i);
        for(int index:rooms.get(i)){
            dfs(rooms,index,row);
        }
    }
}

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
        private Node next;
        public Node(int val){
            this.val=val;
        }
    }
    
    private Node head;
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
        }else{
            node.next=head;
            head=node;
        }
        size++;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node node=new Node(val);
        if(head==null){
            head=node;
        }else{
            Node cur=head;
            while(cur.next!=null){
                cur=cur.next;
            }
            cur.next=node;
        }
        size++;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        
        if(index>size){
            return;
        }
        if(index<=0){
            addAtHead(val);
            return;
        }
        if(index==size){
            addAtTail(val);
            return;
        }
        
        Node cur=head;
        Node node=new Node(val);
        while(index--!=1){
            cur=cur.next;
        }
        node.next=cur.next;
        cur.next=node;
        size++;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index<0||index>=size){
            return;
        }
        if(index==0){
            head=head.next;
        }else{
            Node cur=head;
            while(index--!=1){
                cur=cur.next;
            }
            cur.next=cur.next.next;
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