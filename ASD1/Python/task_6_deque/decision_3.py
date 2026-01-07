import unittest

from task_6_deque.decision import Deque
from task_6_deque.decision_2 import DequeOnDynArray

"""7.2. Добавьте для каждого из четырёх вышеупомянутых методов тесты:
проверяйте изменившуюся длину очереди
и наличие или отсутствие в ней добавляемого/удаляемого элемента."""

class TestCaseDeque(unittest.TestCase):
    def test_addFront(self):
        d = Deque()
        self.assertEqual(d.size(), 0)
        d.addFront(1)
        self.assertEqual(d.size(), 1)
        self.assertIn(1, d.deque)

        d.addFront(11)
        self.assertEqual(d.size(), 2)
        self.assertIn(11, d.deque)

        self.assertEqual(d.deque, [11,1])

    def test_addTail(self):
        d = Deque()
        self.assertEqual(d.size(), 0)
        d.addTail(1)
        self.assertEqual(d.size(), 1)
        self.assertIn(1, d.deque)

        d.addTail(11)
        self.assertEqual(d.size(), 2)
        self.assertIn(11, d.deque)

        self.assertEqual(d.deque, [1, 11])

    def test_removeFront(self):
        d = Deque()
        d.addTail(1)
        d.addTail(11)
        d.addTail(111)
        self.assertEqual(d.size(), 3)
        d.removeFront()
        self.assertEqual(d.size(), 2)
        self.assertNotIn(1, d.deque)
        d.removeFront()
        self.assertEqual(d.size(), 1)
        self.assertNotIn(11, d.deque)
        d.removeFront()
        self.assertEqual(d.size(), 0)
        self.assertNotIn(111, d.deque)

    def test_removeTail(self):
        d = Deque()
        d.addTail(1)
        d.addTail(11)
        d.addTail(111)
        self.assertEqual(d.size(), 3)
        d.removeTail()
        self.assertEqual(d.size(), 2)
        self.assertNotIn(111, d.deque)
        d.removeTail()
        self.assertEqual(d.size(), 1)
        self.assertNotIn(11, d.deque)
        d.removeTail()
        self.assertEqual(d.size(), 0)
        self.assertNotIn(1, d.deque)


class TestCaseDequeOnDynArray(unittest.TestCase):
    def test_addFront(self):
        d = DequeOnDynArray()
        self.assertEqual(d.size(), 0)
        d.addFront(1)
        self.assertEqual(d.size(), 1)
        self.assertIn(1, d.to_list())

        d.addFront(11)
        self.assertEqual(d.size(), 2)
        self.assertIn(11, d.to_list())

        self.assertEqual(d.to_list(), [11,1])

    def test_addTail(self):
        d = DequeOnDynArray()
        self.assertEqual(d.size(), 0)
        d.addTail(1)
        self.assertEqual(d.size(), 1)
        self.assertIn(1, d.to_list())

        d.addTail(11)
        self.assertEqual(d.size(), 2)
        self.assertIn(11, d.to_list())

        self.assertEqual(d.to_list(), [1, 11])

    def test_removeFront(self):
        d = DequeOnDynArray()
        d.addTail(1)
        d.addTail(11)
        d.addTail(111)
        self.assertEqual(d.size(), 3)
        d.removeFront()
        self.assertEqual(d.size(), 2)
        self.assertNotIn(1, d.to_list())
        d.removeFront()
        self.assertEqual(d.size(), 1)
        self.assertNotIn(11, d.to_list())
        d.removeFront()
        self.assertEqual(d.size(), 0)
        self.assertNotIn(111, d.to_list())

    def test_removeTail(self):
        d = DequeOnDynArray()
        d.addTail(1)
        d.addTail(11)
        d.addTail(111)
        self.assertEqual(d.size(), 3)
        d.removeTail()
        self.assertEqual(d.size(), 2)
        self.assertNotIn(111, d.to_list())
        d.removeTail()
        self.assertEqual(d.size(), 1)
        self.assertNotIn(11, d.to_list())
        d.removeTail()
        self.assertEqual(d.size(), 0)
        self.assertNotIn(1, d.to_list())
