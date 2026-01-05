import unittest

from task_5_queue.decision import Queue
from task_5_queue.decision_2 import rotate, reverse
from task_5_queue.decision_2 import QueueOnTwoStacks, BufferedQueue



class TestCaseQueue(unittest.TestCase):
    def test_enqueue(self):
        queue = Queue()
        queue.enqueue(1)
        queue.enqueue(2)
        queue.enqueue(3)
        self.assertEqual(queue.queue, [1,2,3])

        queue = Queue()
        queue.enqueue(None)
        queue.enqueue(None)
        queue.enqueue(None)
        self.assertEqual(queue.queue, [None, None, None])

    def test_dequeue(self):
        queue = Queue()
        self.assertEqual(queue.dequeue(), None)
        queue.enqueue(1)
        queue.enqueue(2)
        queue.enqueue(3)
        self.assertEqual(queue.dequeue(), 1)
        self.assertEqual(queue.dequeue(), 2)
        self.assertEqual(queue.dequeue(), 3)

    def test_size(self):
        queue = Queue()
        self.assertEqual(queue.size(), 0)
        queue.enqueue(1)
        self.assertEqual(queue.size(), 1)
        queue.enqueue(2)
        self.assertEqual(queue.size(), 2)
        queue.dequeue()
        self.assertEqual(queue.size(), 1)

    def test_rotate(self):
        queue = Queue()
        queue.enqueue(1)
        queue.enqueue(2)
        queue.enqueue(3)
        self.assertEqual(queue.queue, [1, 2, 3])
        rotate(queue)
        self.assertEqual(queue.queue, [2, 3, 1])

    def test_reverse(self):
        queue = Queue()
        queue.enqueue(1)
        queue.enqueue(2)
        queue.enqueue(3)
        queue.enqueue(4)
        queue.enqueue(5)
        reverse(queue)
        self.assertEqual(queue.queue, [5, 4, 3, 2, 1])

class TestCaseQueueOnTwoStacks(unittest.TestCase):
    def test_enqueue(self):
        queue = QueueOnTwoStacks()
        queue.enqueue(1)
        queue.enqueue(2)
        queue.enqueue(3)
        self.assertEqual(queue.stack_main.stack, [1,2,3])

        queue = QueueOnTwoStacks()
        queue.enqueue(None)
        queue.enqueue(None)
        queue.enqueue(None)
        self.assertEqual(queue.stack_main.stack, [None, None, None])

    def test_dequeue(self):
        queue = QueueOnTwoStacks()
        self.assertEqual(queue.dequeue(), None)
        queue.enqueue(1)
        queue.enqueue(2)
        queue.enqueue(3)
        self.assertEqual(queue.dequeue(), 1)
        self.assertEqual(queue.dequeue(), 2)
        self.assertEqual(queue.dequeue(), 3)

    def test_size(self):
        queue = QueueOnTwoStacks()
        self.assertEqual(queue.size(), 0)
        queue.enqueue(1)
        self.assertEqual(queue.size(), 1)
        queue.enqueue(2)
        self.assertEqual(queue.size(), 2)
        queue.dequeue()
        self.assertEqual(queue.size(), 1)


class TestCaseBufferedQueue(unittest.TestCase):
    def test_enqueue(self):
        queue = BufferedQueue()
        queue.enqueue(1)
        queue.enqueue(2)
        queue.enqueue(3)
        queue.enqueue(4)
        self.assertEqual(queue.to_list(), [1, 2, 3, 4])
        queue.dequeue()
        self.assertEqual(queue.to_list(), [2, 3, 4])
        queue.enqueue(5)
        self.assertEqual(queue.to_list(), [2, 3, 4, 5])


    def test_dequeue(self):
        queue = BufferedQueue()
        self.assertEqual(queue.dequeue(), None)
        queue.enqueue(1)
        queue.enqueue(2)
        queue.enqueue(3)
        queue.enqueue(4)

        self.assertEqual(queue.dequeue(), 1)
        self.assertEqual(queue.dequeue(), 2)
        self.assertEqual(queue.dequeue(), 3)
        self.assertEqual(queue.dequeue(), 4)

        queue.enqueue(1)
        queue.enqueue(2)
        queue.enqueue(3)
        queue.enqueue(4)

        self.assertEqual(queue.dequeue(), 1)
        self.assertEqual(queue.dequeue(), 2)
        self.assertEqual(queue.dequeue(), 3)
        self.assertEqual(queue.dequeue(), 4)
