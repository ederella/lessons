class Node:

    def __init__(self, v):
        self.value = v
        self.next = None

class LinkedList:

    def __init__(self):
        self.head = None
        self.tail = None

    def add_in_tail(self, item):
        if self.head is None:
            self.head = item
        else:
            self.tail.next = item
        self.tail = item

    def print_all_nodes(self):
        node = self.head
        while node is not None:
            print(node.value)
            node = node.next

    def find(self, val):
        node = self.head
        while node is not None:
            if node.value == val:
                return node
            node = node.next
        return None

    def find_all(self, val):
        result = []
        node = self.head
        while node is not None:
            if node.value == val:
                result.append(node)
            node = node.next
        return result

    def delete(self, val, all=False):
        node = self.head
        prev_node = None
        while node is not None:
            if node.value == val:
                if prev_node is None:
                    self.head = node.next
                else:
                    prev_node.next = node.next
                if self.tail == node:
                    self.tail = prev_node
                if not all:
                    return None
            else:
                prev_node = node
            node = node.next
        return None

    def clean(self):
        self.head = None
        self.tail = None

    def len(self):
        node = self.head
        size = 0
        while node is not None:
            node = node.next
            size+=1
        return size

    def insert(self, afterNode:Node, newNode:Node):
        if self.head is None:
            self.head = newNode
            self.tail = newNode
            return None
        if afterNode is None or newNode is None:
            return None
        after_node_next = afterNode.next
        afterNode.next = newNode
        newNode.next = after_node_next
        if self.tail == afterNode:
            self.tail = newNode
        return None



