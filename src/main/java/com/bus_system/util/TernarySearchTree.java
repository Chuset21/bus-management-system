package com.bus_system.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class TernarySearchTree {
    // No need for an isEnd variable, because of how the TST will be used
    private static final class Node {
        final char value;
        Node left, middle, right;
        final List<Integer> stopIds;

        private Node(char value, Node left, Node middle, Node right) {
            this.value = value;
            this.left = left;
            this.middle = middle;
            this.right = right;
            this.stopIds = new ArrayList<>();
        }

        public Node(char value) {
            this(value, null, null, null);
        }
    }

    private Node root;

    public TernarySearchTree() {
        root = null;
    }

    public boolean insert(String stopName, int stopId) {
        if (stopName == null || stopName.isBlank() || stopId < 0) {
            return false;
        }
        root = insert(stopName.toLowerCase(Locale.ROOT), stopId, 0, root);
        return true;
    }

    private Node insert(String stopName, int stopId, int index, Node node) {
        final char currentChar = stopName.charAt(index);

        if (node == null) {
            node = new Node(currentChar);
        }

        // Add possible stops to new node and/or add all stops to root
        if (currentChar == node.value || node == root) {
            node.stopIds.add(stopId);
        }
        if (currentChar < node.value) {
            node.left = insert(stopName, stopId, index, node.left);
        } else if (currentChar > node.value) {
            node.right = insert(stopName, stopId, index, node.right);
        } else if (index + 1 < stopName.length()) {
            node.middle = insert(stopName, stopId, index + 1, node.middle);
        }
        return node;
    }

    public List<Integer> search(String stopName) {
        if (root == null) {
            return Collections.emptyList();
        }
        if (stopName == null || stopName.isBlank()) {
            return Collections.unmodifiableList(root.stopIds);
        }

        final Node node = search(stopName.toLowerCase(Locale.ROOT), 0, root);
        return node == null ? Collections.emptyList() : Collections.unmodifiableList(node.stopIds);
    }

    private Node search(String stopName, int index, Node node) {
        if (node == null) {
            return null;
        }

        final char currentChar = stopName.charAt(index);
        if (currentChar < node.value) {
            return search(stopName, index, node.left);
        } else if (currentChar > node.value) {
            return search(stopName, index, node.right);
        } else if (index + 1 < stopName.length()) {
            return search(stopName, index + 1, node.middle);
        }
        return node;
    }
}
