import unittest

from task_2_linked_list2.decision import Node, LinkedList2
from task_2_linked_list2.decision_2 import LinkedList2ext, merge


class MyTestCase(unittest.TestCase):
    """2.1"""
    def test_find(self):
        s_list = LinkedList2()
        searched_node1= Node(3)
        searched_node2 = Node(7)
        s_list.add_in_tail(searched_node1)
        s_list.add_in_tail(Node(2))
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(4))
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(searched_node2)
        self.assertEqual(s_list.find(3), searched_node1)
        self.assertEqual(s_list.find(7), searched_node2)
        self.assertEqual(s_list.find(22), None)
        self.assertEqual(LinkedList2().find(3), None)


    """2.2"""
    def test_find_all(self):
        s_list = LinkedList2()
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(2))
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(4))
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(3))

        res = s_list.find_all(3)

        self.assertEqual(len(res), 4)

        for el in res:
            self.assertEqual(el.value, 3)

        self.assertEqual(LinkedList2().find_all(3), [])

        self.assertEqual(s_list.find_all(30), [])

    """2.3, 2.4"""
    def test_delete(self):
        s_list = LinkedList2()
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(2))
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(4))
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(3))

        s_list.delete(3)
        self.assertEqual(to_list(s_list), [2, 3, 4, 3, 3])

        s_list.delete(33)
        self.assertEqual(to_list(s_list), [2, 3, 4, 3, 3])

        s_list.delete(3,True)
        self.assertEqual(to_list(s_list), [2, 4])

        s_list.delete(33)
        self.assertEqual(to_list(s_list), [2, 4])

        s_list_empty = LinkedList2()
        s_list_empty.delete(3)
        self.assertEqual(to_list(s_list_empty), [])
        s_list_empty.delete(3, True)
        self.assertEqual(to_list(s_list_empty), [])

        s_list_1 = LinkedList2()
        s_list_1.add_in_tail(Node(3))
        s_list_1.delete(3)
        self.assertEqual(to_list(s_list_1), [])

        s_list_1.add_in_tail(Node(3))
        s_list_1.delete(3,True)
        self.assertEqual(to_list(s_list_1), [])

    """2.5"""
    def test_insert(self):
        s_list = LinkedList2()
        node1 = Node(1)
        node2 = Node(2)
        node3 = Node(3)
        s_list.add_in_tail(node1)
        s_list.add_in_tail(node2)
        s_list.add_in_tail(node3)

        s_list.insert(node1, Node(11))
        self.assertEqual(to_list(s_list), [1,11,2,3])

        node111 = Node(111)
        s_list.insert(node3, node111)
        self.assertEqual(to_list(s_list), [1, 11, 2, 3, 111])

        s_list.insert(node111, Node(222))
        self.assertEqual(to_list(s_list), [1, 11, 2, 3, 111, 222])

        s_list = LinkedList2()
        s_list.insert(None, Node(222))
        self.assertEqual(to_list(s_list), [222])

        s_list.insert(None, Node(333))
        self.assertEqual(to_list(s_list), [222, 333])

    """2.6"""
    def test_add_in_head(self):
        s_list = LinkedList2()
        s_list.add_in_head(Node(10))
        self.assertEqual(to_list(s_list), [10])
        s_list.add_in_head(Node(9))
        self.assertEqual(to_list(s_list), [9, 10])
        s_list.add_in_head(None)
        self.assertEqual(to_list(s_list), [9, 10])
        s_list.add_in_head(Node(10))
        self.assertEqual(to_list(s_list), [10, 9, 10])

    """2.7"""
    def test_clean(self):
        s_list = LinkedList2()
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(2))
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(4))
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(3))

        s_list.clean()
        self.assertEqual(to_list(s_list), [])

        s_list.clean()
        self.assertEqual(to_list(s_list), [])

    """2.8"""
    def test_len(self):
        s_list = LinkedList2()
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(2))
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(4))
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(3))

        self.assertEqual(s_list.len(), 6)

        s_list_empty = LinkedList2()
        self.assertEqual(s_list_empty.len(), 0)

    """2.10*"""
    def test_reverse(self):
        s_list = LinkedList2ext()
        s_list.reverse()
        self.assertEqual(to_list(s_list), [])

        s_list.add_in_tail(Node(1))
        s_list.reverse()
        self.assertEqual(to_list(s_list), [1])

        s_list.add_in_tail(Node(2))
        s_list.reverse()
        self.assertEqual(to_list(s_list), [2,1])

        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(4))
        s_list.add_in_tail(Node(5))
        s_list.add_in_tail(Node(6))

        s_list.reverse()
        self.assertEqual(to_list(s_list), [6,5,4,3,1,2])

    """2.11*"""
    def test_is_cycled(self):
        s_list = LinkedList2ext()
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(2))
        s_list.add_in_tail(Node(1))
        self.assertEqual(s_list.is_cycled(), False)
        s_list.add_in_tail(s_list.head)
        self.assertEqual(s_list.is_cycled(), True)

    """2.12*"""
    def test_sort(self):
        s_list = LinkedList2ext()
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(2))
        s_list.add_in_tail(Node(1))
        s_list.sort()
        self.assertEqual(to_list(s_list), [1, 2, 3])
        s_list.sort()
        self.assertEqual(to_list(s_list), [1, 2, 3])
        s_list = LinkedList2ext()
        s_list.sort()
        self.assertEqual(to_list(s_list), [])
        s_list.add_in_tail(Node(3))
        s_list.sort()
        self.assertEqual(to_list(s_list), [3])

    """2.13*"""
    def test_merge(self):
        s_list1 = LinkedList2ext()
        s_list1.add_in_tail(Node(3))
        s_list1.add_in_tail(Node(2))
        s_list1.add_in_tail(Node(1))

        s_list2 = LinkedList2ext()
        s_list2.add_in_tail(Node(9))
        s_list2.add_in_tail(Node(7))
        s_list2.add_in_tail(Node(8))
        s_list2.add_in_tail(Node(6))
        s_list2.add_in_tail(Node(4))
        s_list2.add_in_tail(Node(5))

        res = merge(s_list1, s_list2)
        self.assertEqual(to_list(res), [1,2,3,4,5,6,7,8,9])

        res = merge(s_list1, LinkedList2ext())
        self.assertEqual(to_list(res), [1, 2, 3])

        res = merge(LinkedList2ext(), s_list2)
        self.assertEqual(to_list(res), [4, 5, 6, 7, 8, 9])

def to_list(s_list: LinkedList2):
    node = s_list.head
    ls = []
    while node is not None:
        ls.append(node.value)
        node = node.next
    return ls


if __name__ == '__main__':
    unittest.main()
