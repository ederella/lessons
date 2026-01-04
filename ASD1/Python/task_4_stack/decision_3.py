import unittest

from task_4_stack.decision import Stack
from task_4_stack.decision_2 import StackHead


class TestCaseStack(unittest.TestCase):
    def test_size(self):
        stack = Stack()
        self.assertEqual(stack.size(), 0)

        stack.push(1)
        stack.push(2)
        self.assertEqual(stack.size(), 2)

        stack.peek()
        self.assertEqual(stack.size(), 2)

        stack.pop()
        stack.pop()
        self.assertEqual(stack.size(), 0)

    def test_pop(self):
        stack = Stack()
        self.assertIs(stack.pop(), None)

        stack.push(1)
        stack.push(2)
        stack.push(3)
        self.assertEqual(stack.pop(), 3)
        self.assertEqual(stack.pop(), 2)
        self.assertEqual(stack.pop(),1)



    def test_peek(self):
        stack = Stack()
        self.assertIs(stack.peek(), None)
        stack.push(1)
        stack.push(2)
        stack.push(3)
        self.assertEqual(stack.peek(), 3)
        stack.pop()
        self.assertEqual(stack.peek(), 2)
        stack.pop()
        self.assertEqual(stack.peek(), 1)
        stack.pop()

    def test_push(self):
        stack = Stack()
        self.assertEqual(stack.size(), 0)
        stack.push(1)
        self.assertEqual(stack.peek(), 1)
        self.assertEqual(stack.size(), 1)
        stack.push(2)
        self.assertEqual(stack.peek(), 2)
        self.assertEqual(stack.size(), 2)



class TestCaseStackHead(unittest.TestCase):
    def test_size(self):
        stack = StackHead()
        self.assertEqual(stack.size(), 0)

        stack.push(1)
        stack.push(2)
        self.assertEqual(stack.size(), 2)

        stack.peek()
        self.assertEqual(stack.size(), 2)

        stack.pop()
        stack.pop()
        self.assertEqual(stack.size(), 0)

    def test_pop(self):
        stack = StackHead()
        self.assertIs(stack.pop(), None)

        stack.push(1)
        stack.push(2)
        stack.push(3)
        self.assertEqual(stack.pop(), 3)
        self.assertEqual(stack.pop(), 2)
        self.assertEqual(stack.pop(), 1)

    def test_peek(self):
        stack = StackHead()
        self.assertIs(stack.peek(), None)
        stack.push(1)
        stack.push(2)
        stack.push(3)
        self.assertEqual(stack.peek(), 3)
        stack.pop()
        self.assertEqual(stack.peek(), 2)
        stack.pop()
        self.assertEqual(stack.peek(), 1)
        stack.pop()

    def test_push(self):
        stack = StackHead()
        self.assertEqual(stack.size(), 0)
        stack.push(1)
        self.assertEqual(stack.peek(), 1)
        self.assertEqual(stack.size(), 1)
        stack.push(2)
        self.assertEqual(stack.peek(), 2)
        self.assertEqual(stack.size(), 2)
