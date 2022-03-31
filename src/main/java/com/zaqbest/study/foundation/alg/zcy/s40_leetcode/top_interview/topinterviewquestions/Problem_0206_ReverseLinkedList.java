package com.zaqbest.study.foundation.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

public class Problem_0206_ReverseLinkedList {

	public static class ListNode {
		int val;
		ListNode next;
	}

	public static ListNode reverseList(ListNode head) {
		ListNode pre = null;
		ListNode next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}

}
