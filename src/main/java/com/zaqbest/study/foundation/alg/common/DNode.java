package com.zaqbest.study.foundation.alg.common;

public class DNode<T> {

        public T value;
        public DNode<T> last;
        public DNode<T> next;

        public DNode(T data) {
            value = data;
    }
}
