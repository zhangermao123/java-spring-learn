package alogrithm;

/**
 * @author zhangwei
 * @version 1.0
 * @className alogrithm
 * @descripation 链表(单/双/环形)操作 主要是指针操作（双指针/快慢指针/递归）
 * @date 2021-10-04
 */
public class LinkTest {

    static class ListNode{
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 203 移除链表元素
     * @param
     * @return
     */
    public ListNode  romoveNode(ListNode head,int val){
        ListNode denyNode = new ListNode(val+1);
        denyNode.next = head;
        ListNode pre = denyNode;
        while (pre.next!=null){
            if(pre.val== val){
                pre.next = pre.next.next;
            }else{
               pre = pre.next;
            }
        }
        return denyNode.next;
    }

    /**
     * @className MyLinkList
     * @description 定义链表的添加/删除/插入
     * @date 2021/10/28
     * @version 1.0
     */
    class MyLinkList{
        int size;
        ListNode listNode;

        public MyLinkList(){
            size = 0;
            listNode = new ListNode(0);
        }

        public int get(int index) {
            if(index<0 || index>=size){
                return -1;
            }
            //获取插入前节点
            ListNode pre = listNode;
            for(int i=0;i<=index;i++){
                pre = pre.next;
            }
            return pre.val;

        }

        //插入头部
        public void addAtHead(int val) {
            addAtIndex(0,val);
        }

        //插入尾部
        public void addAtTail(int val) {
            addAtIndex(size,val);
        }

        public void addAtIndex(int index, int val) {
            if(index > size){
                return;
            }
            if(index<0){
                index = 0;
            }
            size++;
            //获取插入前节点
            ListNode pre = listNode;
            for(int i=0;i<index;i++){
                pre = pre.next;
            }
            ListNode newNode = new ListNode(val);
            newNode.next = pre.next;
            pre.next = newNode;
        }

        public void deleteAtIndex(int index) {
             if(index<0 || index >= size){
                 return;
             }
             size--;

             ListNode pre = listNode;
            for(int i=0;i<index;i++){
                pre = pre.next;
            }
            pre.next = pre.next.next;
        }
    }

    /**
     * 206.反转一个单链表。
     *
     *  双指针（快慢指针）
     * @param listNode
     * @return
     */
    public ListNode reverseListNode(ListNode listNode){
        if(listNode==null|| listNode.next==null){
            return listNode;
        }
        //一般设置一个节点
        ListNode cur = listNode;
        ListNode pre = null;
        ListNode temp;
        while (listNode!=null){
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * 92. 反转链表 II
     * 双指针(头插法)
     * @param head 链表
     * @param left 左整数
     * @param right 右整数
     * @return alogrithm.LinkTest.ListNode
     */
    public ListNode reverseBetween(ListNode head,int left,int right){
        if(head == null || head.next == null || left<=1){
            return  head;
        }
        //虚拟节点，易于返回
        ListNode dumny = new ListNode(-1);
        dumny.next = head;
        //位置不变指针(左节点)
        ListNode unChangeNode = dumny;
        //位置跳转指针
        ListNode change = dumny.next;
        //先找到最左边的pre节点
        for(int i=0;i< left-1;i++){
           unChangeNode = unChangeNode.next;
           change = change.next;
        }

        for(int i=0;i<right-left;i++){
            //要跳转移动的节点
            ListNode remove = change.next;
            change.next = remove.next;
            remove.next = unChangeNode.next;
            unChangeNode.next = remove;
        }

        return dumny.next;
    }

    /**
     * 234. 回文链表
     *  实现是 反转后半部分链表 同前半部分比较
     * @param head
     * @return boolean
     */
    public boolean isPalindrome(ListNode head) {
        if(head==null || head.next ==null){
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
        //获取前半部分的链表
        while (fast.next!=null&&fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        //获取后半部分的反转链表
        ListNode halfEnd = reverseListNode(slow.next);

        //两部分比较
        while (halfEnd!=null){
            if(head.val != halfEnd.val){
                return false;
            }
            halfEnd =halfEnd.next;
            head = head.next;
        }
        reverseListNode(slow.next);
        return  true;
    }


    public ListNode pariExchange(ListNode head){
        if(head==null|| head.next==null){
            return head;
        }
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode fast = dummyNode.next;
        ListNode slow = dummyNode.next.next;
        while(fast!=null&&fast.next!=null&&slow!=null){
            fast.next = slow.next;
            slow.next = fast;
            fast = fast.next;
            slow = fast.next.next;
        }
        return dummyNode.next;
    }
    /**
     * 19. 删除链表的倒数第 N 个结点
     * @param head
     * @param n
     * @return alogrithm.LinkTest.ListNode
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dumy = new ListNode(0);
        dumy.next = head;
        ListNode fast = dumy.next;
        ListNode slow = dumy.next;
        for(int i=0;i<n;i++){
            fast = fast.next;
        }
        while(fast.next!=null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dumy.next;
    }

    /**
     * 24. 两两交换链表中的节点
     * @param head
     * @return alogrithm.LinkTest.ListNode
     */
    public ListNode swapPairs(ListNode head) {

        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        //奇数
        ListNode obbNode = head;
        while(pre.next!=null && pre.next.next!=null){
            ListNode tmp = obbNode.next.next;
            pre.next = obbNode.next;
            obbNode.next.next = obbNode;
            obbNode.next = tmp;
            pre = obbNode;
            obbNode = obbNode.next;
        }

        return dummyNode.next;
    }

    /**
     * 142. 环形链表 II
     * 快慢指针法，这题用哈希法更好理解(使用set，不过牺牲了空间)
     * @param head
     * @return alogrithm.LinkTest.ListNode
     */
    public ListNode detectCycle(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;
        while(fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                ListNode index1 = fast;
                ListNode index2 = head;
                while(index1!=index2){
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index2;
            }
        }
        return null;
    }


}
