class Node:
    def __init__(self, v):
        self.value = v
        self.prev = None
        self.next = None

class OrderedList:
    def __init__(self, asc):
        self.head = None
        self.tail = None
        self.__ascending = asc

    def compare(self, v1, v2):
        if v1 < v2:
            return -1
        if v1 > v2:
            return 1
        return 0

    def add(self, value):
        new_node = Node(value)
        if self.head is None:
            self.head = new_node
            self.tail = new_node
            return
        comparing_coef = 1 if self.__ascending else -1

        if self.compare(self.head.value, new_node.value) * comparing_coef >= 0:
            node = self.head
            self.head = new_node
            self.head.next = node
            node.prev = self.head
            return

        if self.compare(self.tail.value, new_node.value) * comparing_coef <= 0:
            node = self.tail
            self.tail = new_node
            self.tail.prev = node
            node.next = self.tail
            return

        node = self.head.next
        while node is not None and self.compare(node.value, new_node.value) * comparing_coef < 0:
            node = node.next

        new_node.prev = node.prev
        new_node.next = node
        new_node.prev.next = new_node
        new_node.next.prev = new_node

    def find(self, val):
        node = self.head
        comparing_coef = 1 if self.__ascending else -1

        while node is not None:
            if self.compare(node.value, val) * comparing_coef > 0:
                return None
            if self.compare(node.value, val) == 0:
                return node
            node = node.next
        return None

    def delete(self, val):
        del_node = self.find(val)

        if del_node is None or self.len() == 0:
            return

        if del_node == self.head and del_node == self.tail:
            self.head = None
            self.tail = None
            return

        if del_node == self.head:
            self.head = self.head.next
            self.head.prev = None
            return

        if del_node == self.tail:
            self.tail = self.tail.prev
            self.tail.next = None
            return

        del_node.prev.next = del_node.next
        del_node.next.prev = del_node.prev

    def clean(self, asc):
        self.__ascending = asc
        self.head = None
        self.tail = None

    def len(self):
            node = self.head
            size = 0
            while node is not None:
                node = node.next
                size += 1
            return size

    def get_all(self):
        r = []
        node = self.head
        while node is not None:
            r.append(node)
            node = node.next
        return r


class OrderedStringList(OrderedList):
    def __init__(self, asc):
        super(OrderedStringList, self).__init__(asc)

    def compare(self, v1, v2):
        v1_trimmed = v1.strip()
        v2_trimmed = v2.strip()
        if v1_trimmed < v2_trimmed:
            return -1
        if v1_trimmed > v2_trimmed:
            return 1
        return 0

