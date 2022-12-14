package cx.leetcode.bytedance;

//重点
public class Solution148 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //统计长度
        int len = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }
        //引入虚拟头结点
        ListNode dummyNode = new ListNode(0, head);

        //每次将链表分成若干个长度为sublen的子链表，并按照两个一组进行合并
        for (int subLen = 1; subLen < len; subLen <<= 1) {  //子链表长度每次乘2
            ListNode prev = dummyNode, curr = dummyNode.next;  //记录拆分链表的位置，初始值是链表头节点
            while (curr != null) {   //如果整个链表还没被拆完
                ListNode head1 = curr;//第一个子链表的头
                for (int i = 1; i < subLen && curr.next != null; i++) {
                    curr = curr.next;
                }
                //----循环完毕后得到第一个子链表
                ListNode head2 = curr.next;    //第二个子链表的头
                curr.next = null;    //拆分第一个和第二个子链表的链接
                curr = head2;    //更新curr，开始找第二个子链表的节点，多了一个条件curr != null是因为第二个子链表长度可能不够
                for (int i = 1; i < subLen && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                //---循环完毕，得到第二个子链表

                //再次断开第二个链表和后续子链表的链接
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;   //后续链表的头结点
                    curr.next = null;   //断开和后续链表的链接
                }
                //prev.next指向排好序的链表头
                prev.next = merge(head1, head2);
                while (prev.next != null) {
                    //然后prev移到排序好的链表尾部
                    prev = prev.next;
                }
                curr = next; //把curr从后续链表的头部开始，然后while开始下一次循环
            }
        }
        return dummyNode.next;
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }

    class ListNode {
        int val;

        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
