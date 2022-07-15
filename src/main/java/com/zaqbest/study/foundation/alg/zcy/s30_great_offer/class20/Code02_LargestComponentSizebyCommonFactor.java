package com.zaqbest.study.foundation.alg.zcy.s30_great_offer.class20;

import java.util.HashMap;

/**
 * 按公因数计算最大组件大小
 *
 * 给定一个由不同正整数的组成的非空数组nums ，考虑下面的图：
 *
 * 有nums.length个节点，按从nums[0]到nums[nums.length - 1]标记；
 * 只有当nums[i]和nums[j]共用一个大于 1 的公因数时，nums[i]和nums[j]之间才有一条边。
 * 返回 图中最大连通组件的大小 。
 * 
 * 测试链接：https://leetcode.cn/problems/largest-component-size-by-common-factor/
 * 方法1会超时，但是方法2直接通过
 */
public class Code02_LargestComponentSizebyCommonFactor {

	public static int largestComponentSize1(int[] arr) {
		int N = arr.length;
		UnionFind set = new UnionFind(N);
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (gcd(arr[i], arr[j]) != 1) {
					set.union(i, j);
				}
			}
		}
		return set.maxSize();
	}

	public static int largestComponentSize2(int[] arr) {
		int N = arr.length;
		// arr中，N个位置，在并查集初始时，每个位置自己是一个集合
		UnionFind unionFind = new UnionFind(N);
		//      key 某个因子   value 哪个位置拥有这个因子
		HashMap<Integer, Integer> fatorsMap = new HashMap<>();
		for (int i = 0; i < N; i++) {
			int num = arr[i];
			// 求出根号N， -> limit
			int limit = (int) Math.sqrt(num);
			for (int j = 1; j <= limit; j++) {
				if (num % j == 0) {
					if (j != 1) {
						if (!fatorsMap.containsKey(j)) {
							fatorsMap.put(j, i);
						} else {
							unionFind.union(fatorsMap.get(j), i);
						}
					}
					int other = num / j;
					if (other != 1) {
						if (!fatorsMap.containsKey(other)) {
							fatorsMap.put(other, i);
						} else {
							unionFind.union(fatorsMap.get(other), i);
						}
					}
				}
			}
		}
		return unionFind.maxSize();
	}

	// O(1)
	// m,n 要是正数，不能有任何一个等于0
	public static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	public static class UnionFind {
		private int[] parents;
		private int[] sizes;
		private int[] help;

		public UnionFind(int N) {
			parents = new int[N];
			sizes = new int[N];
			help = new int[N];
			for (int i = 0; i < N; i++) {
				parents[i] = i;
				sizes[i] = 1;
			}
		}

		public int maxSize() {
			int ans = 0;
			for (int size : sizes) {
				ans = Math.max(ans, size);
			}
			return ans;
		}

		private int find(int i) {
			int hi = 0;
			while (i != parents[i]) {
				help[hi++] = i;
				i = parents[i];
			}
			for (hi--; hi >= 0; hi--) {
				parents[help[hi]] = i;
			}
			return i;
		}

		public void union(int i, int j) {
			int f1 = find(i);
			int f2 = find(j);
			if (f1 != f2) {
				int big = sizes[f1] >= sizes[f2] ? f1 : f2;
				int small = big == f1 ? f2 : f1;
				parents[small] = big;
				sizes[big] = sizes[f1] + sizes[f2];
			}
		}
	}

}
