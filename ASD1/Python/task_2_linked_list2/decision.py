class Node:
    def __init__(self, v):
        self.value = v
        self.prev = None
        self.next = None

class LinkedList2:
    def __init__(self):
        self.head = None
        self.tail = None

    def add_in_tail(self, item):
        if self.head is None:
            self.head = item
            item.prev = None
            item.next = None
        else:
            self.tail.next = item
            item.prev = self.tail
        self.tail = item

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
        while node is not None:
            if node.value == val:
                if node.prev is None:
                    self.head = node.next
                    if self.head is not None:
                        self.head.prev = None
                else:
                    node.prev.next = node.next

                if node.next is None:
                    self.tail = node.prev
                    if node.prev is not None:
                        node.prev.next = None
                else:
                    node.next.prev = node.prev
                if not all:
                    return None
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
            size += 1
        return size

    def insert(self, afterNode, newNode):
        if newNode is None:
            return None

        if afterNode is None:
            if self.len() == 0:
                self.head = newNode
                self.tail = newNode
            else:
                self.tail.next = newNode
                newNode.prev = self.tail
                self.tail = newNode
            return None

        after_node_next = afterNode.next
        afterNode.next = newNode
        newNode.next = after_node_next
        if newNode.next is not None:
            newNode.next.prev = newNode
        newNode.prev = afterNode
        if self.tail == afterNode:
            self.tail = newNode
        return None

    def add_in_head(self, newNode):
        if newNode is not None:
            if self.head is not None:
                self.head.prev = newNode
            newNode.next = self.head
            self.head = newNode

