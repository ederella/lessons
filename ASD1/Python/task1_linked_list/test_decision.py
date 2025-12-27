import unittest

from task_1_linked_list.decision import Node, LinkedList


class MyTestCase(unittest.TestCase):
    def test_delete(self):
        s_list = LinkedList()
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

        s_list_empty = LinkedList()
        s_list_empty.delete(3)
        self.assertEqual(to_list(s_list_empty), [])
        s_list_empty.delete(3, True)
        self.assertEqual(to_list(s_list_empty), [])

        s_list_1 = LinkedList()
        s_list_1.add_in_tail(Node(3))
        s_list_1.delete(3)
        self.assertEqual(to_list(s_list_1), [])

        s_list_1.add_in_tail(Node(3))
        s_list_1.delete(3,True)
        self.assertEqual(to_list(s_list_1), [])

    def test_len(self):
        s_list = LinkedList()
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(2))
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(4))
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(3))

        self.assertEqual(s_list.len(), 6)

        s_list_empty = LinkedList()
        self.assertEqual(s_list_empty.len(), 0)

    def test_clean(self):
        s_list = LinkedList()
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(2))
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(4))
        s_list.add_in_tail(Node(3))
        s_list.add_in_tail(Node(3))

        s_list.clean()

        self.assertEqual(to_list(s_list), [])


    def test_find_all(self):
        s_list = LinkedList()
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

    def test_insert(self):
        s_list = LinkedList()
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

        s_list_empty = LinkedList()
        s_list_empty.insert(None, Node(1))
        self.assertEqual(to_list(s_list_empty), [1])

def to_list(s_list: LinkedList):
    node = s_list.head
    ls = []
    while node is not None:
        ls.append(node.value)
        node = node.next
    return ls


if __name__ == '__main__':
    unittest.main()
