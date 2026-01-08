import unittest

from task_7_ordered_list.decision import OrderedList, OrderedStringList
from task_7_ordered_list.decision_2 import OrderedListExt1, OrderedListExt2, OrderedListExt3, OrderedListExt4

"""
7. Добавьте тесты для добавления, удаления и поиска элемента по его значению -- каждый случай с учётом признака упорядоченности.
"""
class TestCaseOrderedList(unittest.TestCase):

    def test_add(self):
        ord_list = OrderedList(True)
        ord_list.add(5)
        self.assertEqual(ord_list.len(), 1)

        ord_list.add(3)
        self.assertEqual(ord_list.len(), 2)
        self.assertEqual(list(map(lambda x: x.value, ord_list.get_all())), [3,5])

        ord_list.add(3)
        self.assertEqual(ord_list.len(), 3)
        self.assertEqual(list(map(lambda x: x.value, ord_list.get_all())), [3, 3, 5])

        ord_list.add(5)
        self.assertEqual(ord_list.len(), 4)
        self.assertEqual(list(map(lambda x: x.value, ord_list.get_all())), [3, 3, 5, 5])

        ord_list = OrderedList(False)
        ord_list.add(5)
        self.assertEqual(ord_list.len(), 1)

        ord_list.add(3)
        self.assertEqual(ord_list.len(), 2)
        self.assertEqual(list(map(lambda x: x.value, ord_list.get_all())), [5, 3])

        ord_list.add(3)
        self.assertEqual(ord_list.len(), 3)
        self.assertEqual(list(map(lambda x: x.value, ord_list.get_all())), [5, 3, 3])

        ord_list.add(5)
        self.assertEqual(ord_list.len(), 4)
        self.assertEqual(list(map(lambda x: x.value, ord_list.get_all())), [5, 5, 3, 3])

    def test_find(self):
        ord_list = OrderedList(True)
        self.assertEqual(ord_list.find(5), None)
        for i in range(10):
            ord_list.add(i%3)
        self.assertEqual(ord_list.find(5), None)
        node = ord_list.find(2)
        self.assertEqual(node.value, 2)
        node = ord_list.find(1)
        self.assertEqual(node.value, 1)

        ord_list = OrderedList(False)
        self.assertEqual(ord_list.find(5), None)
        for i in range(10):
            ord_list.add(i % 3)
        self.assertEqual(ord_list.find(5), None)
        node = ord_list.find(2)
        self.assertEqual(node.value, 2)
        node = ord_list.find(1)
        self.assertEqual(node.value, 1)

    def test_delete(self):
        ord_list = OrderedList(True)
        self.assertEqual(ord_list.len(), 0)
        ord_list.delete(5)
        self.assertEqual(ord_list.len(), 0)

        for i in range(10):
            ord_list.add(i % 3)

        self.assertEqual(ord_list.len(), 10)
        ord_list.delete(5)
        self.assertEqual(ord_list.len(), 10)
        self.assertEqual(list(map(lambda x: x.value, ord_list.get_all())), [0,0,0,0,1,1,1,2,2,2])

        ord_list.delete(0)
        self.assertEqual(ord_list.len(), 9)
        self.assertEqual(list(map(lambda x: x.value, ord_list.get_all())), [0,0,0,1,1,1,2,2,2])

        ord_list.delete(1)
        self.assertEqual(ord_list.len(), 8)
        self.assertEqual(list(map(lambda x: x.value, ord_list.get_all())), [0, 0, 0, 1, 1, 2, 2, 2])

        ord_list.delete(2)
        self.assertEqual(ord_list.len(), 7)
        self.assertEqual(list(map(lambda x: x.value, ord_list.get_all())), [0, 0, 0, 1, 1, 2, 2])

        ord_list = OrderedList(False)
        self.assertEqual(ord_list.find(5), None)
        for i in range(10):
            ord_list.add(i % 3)

        self.assertEqual(ord_list.len(), 10)
        ord_list.delete(5)
        self.assertEqual(ord_list.len(), 10)
        self.assertEqual(list(map(lambda x: x.value, ord_list.get_all())), [2,2,2,1,1,1,0,0,0,0])

        ord_list.delete(0)
        self.assertEqual(ord_list.len(), 9)
        self.assertEqual(list(map(lambda x: x.value, ord_list.get_all())), [2,2,2,1,1,1,0,0,0])

        ord_list.delete(1)
        self.assertEqual(ord_list.len(), 8)
        self.assertEqual(list(map(lambda x: x.value, ord_list.get_all())), [2,2,2,1,1,0,0,0])


class TestCaseOrderedStringList(unittest.TestCase):

    def test_add(self):
        ord_list = OrderedStringList(True)
        ord_list.add('Pushkin')
        self.assertEqual(ord_list.len(), 1)

        ord_list.add('Lermontov')
        self.assertEqual(ord_list.len(), 2)
        self.assertEqual(list(map(lambda x: x.value, ord_list.get_all())), ['Lermontov', 'Pushkin'])

        ord_list.add('Pushkin')
        self.assertEqual(ord_list.len(), 3)
        self.assertEqual(list(map(lambda x: x.value, ord_list.get_all())), ['Lermontov', 'Pushkin', 'Pushkin'])

        ord_list.add('Lermontov')
        self.assertEqual(ord_list.len(), 4)
        self.assertEqual(list(map(lambda x: x.value, ord_list.get_all())), ['Lermontov', 'Lermontov', 'Pushkin', 'Pushkin'])

        ord_list = OrderedList(False)
        ord_list.add('Pushkin')
        self.assertEqual(ord_list.len(), 1)

        ord_list.add('Lermontov')
        self.assertEqual(ord_list.len(), 2)
        self.assertEqual(list(map(lambda x: x.value, ord_list.get_all())), ['Pushkin', 'Lermontov'])

        ord_list.add('Lermontov')
        self.assertEqual(ord_list.len(), 3)
        self.assertEqual(list(map(lambda x: x.value, ord_list.get_all())), ['Pushkin', 'Lermontov', 'Lermontov'])

        ord_list.add('Pushkin')
        self.assertEqual(ord_list.len(), 4)
        self.assertEqual(list(map(lambda x: x.value, ord_list.get_all())), ['Pushkin', 'Pushkin', 'Lermontov', 'Lermontov'])

    def test_find(self):
        ord_list = OrderedStringList(True)
        self.assertEqual(ord_list.find('Gorky'), None)

        ord_list.add('Pushkin')
        ord_list.add('Lermontov')
        ord_list.add('Dostoevsky')
        ord_list.add('Tolstoy')

        self.assertEqual(ord_list.find('Gorky'), None)
        self.assertEqual(ord_list.find('Pushkin').value, 'Pushkin')
        self.assertEqual(ord_list.find('Lermontov').value, 'Lermontov')
        self.assertEqual(ord_list.find('Dostoevsky').value, 'Dostoevsky')

        ord_list = OrderedStringList(False)
        self.assertEqual(ord_list.find('Gorky'), None)
        ord_list.add('Pushkin')
        ord_list.add('Lermontov')
        ord_list.add('Dostoevsky')
        ord_list.add('Tolstoy')

        self.assertEqual(ord_list.find('Gorky'), None)
        self.assertEqual(ord_list.find('Pushkin').value, 'Pushkin')
        self.assertEqual(ord_list.find('Lermontov').value, 'Lermontov')
        self.assertEqual(ord_list.find('Dostoevsky').value, 'Dostoevsky')

    def test_delete(self):
        ord_list = OrderedStringList(True)
        self.assertEqual(ord_list.len(), 0)
        ord_list.delete('Gorky')
        self.assertEqual(ord_list.len(), 0)

        ord_list.add('Pushkin')
        ord_list.add('Lermontov')
        ord_list.add('Dostoevsky')
        ord_list.add('Tolstoy')
        self.assertEqual(ord_list.len(), 4)
        ord_list.delete('Gorky')
        self.assertEqual(ord_list.find('Pushkin').value, 'Pushkin')
        self.assertEqual(ord_list.len(), 4)
        ord_list.delete('Pushkin')
        self.assertEqual(ord_list.find('Pushkin'), None)
        self.assertEqual(ord_list.len(), 3)

        self.assertEqual(ord_list.find('Dostoevsky').value, 'Dostoevsky')
        ord_list.delete('Dostoevsky')
        self.assertEqual(ord_list.len(), 2)
        self.assertEqual(ord_list.find('Dostoevsky'), None)

        self.assertEqual(ord_list.find('Tolstoy').value, 'Tolstoy')
        ord_list.delete('Tolstoy')
        self.assertEqual(ord_list.len(), 1)
        self.assertEqual(ord_list.find('Tolstoy'), None)

        ord_list = OrderedStringList(False)
        self.assertEqual(ord_list.len(), 0)
        ord_list.delete('Gorky')
        self.assertEqual(ord_list.len(), 0)

        ord_list.add('Pushkin')
        ord_list.add('Lermontov')
        ord_list.add('Dostoevsky')
        ord_list.add('Tolstoy')
        self.assertEqual(ord_list.len(), 4)
        ord_list.delete('Gorky')
        self.assertEqual(ord_list.find('Pushkin').value, 'Pushkin')
        self.assertEqual(ord_list.len(), 4)
        ord_list.delete('Pushkin')
        self.assertEqual(ord_list.find('Pushkin'), None)
        self.assertEqual(ord_list.len(), 3)

        self.assertEqual(ord_list.find('Dostoevsky').value, 'Dostoevsky')
        ord_list.delete('Dostoevsky')
        self.assertEqual(ord_list.len(), 2)
        self.assertEqual(ord_list.find('Dostoevsky'), None)


class TestCaseOrderedListExt1(unittest.TestCase):
    def test_remove_duplicates(self):
        ord_list = OrderedListExt1(True)
        self.assertEqual(ord_list.len(), 0)
        ord_list.remove_duplicates()
        self.assertEqual(ord_list.len(), 0)

        ord_list.add(1)
        ord_list.remove_duplicates()
        self.assertEqual(ord_list.len(), 1)

        ord_list.add(1)
        ord_list.add(1)
        ord_list.remove_duplicates()
        self.assertEqual(ord_list.len(), 1)

        ord_list.add(2)
        ord_list.add(2)
        ord_list.add(2)
        ord_list.remove_duplicates()
        self.assertEqual(ord_list.len(), 2)
        ord_list.remove_duplicates()
        self.assertEqual(ord_list.len(), 2)


class TestCaseOrderedListExt2(unittest.TestCase):
    def test_exists_sublist(self):
        ord_list = OrderedListExt2(True)
        for i in range(10):
            ord_list.add(i)
        sublist = OrderedListExt2(True)
        for i in range(3,7):
            sublist.add(i)

        self.assertTrue(ord_list.exists_sublist(sublist))

        sublist = OrderedListExt2(True)
        for i in range(10, 15):
            sublist.add(i)

        self.assertFalse(ord_list.exists_sublist(sublist))


class TestCaseOrderedListExt3(unittest.TestCase):
    def test_most_frequent(self):
        ord_list = OrderedListExt3(True)
        for i in range(10):
            ord_list.add(i)
        self.assertEqual(ord_list.most_frequent(), 0)

        ord_list = OrderedListExt3(True)
        self.assertEqual(ord_list.most_frequent(), None)
        ord_list.add(1)
        self.assertEqual(ord_list.most_frequent(), 1)

        ord_list.add(1)
        self.assertEqual(ord_list.most_frequent(), 1)

        ord_list.add(1)
        self.assertEqual(ord_list.most_frequent(), 1)

        ord_list.add(2)
        self.assertEqual(ord_list.most_frequent(), 1)

        ord_list.add(2)
        self.assertEqual(ord_list.most_frequent(), 1)

        ord_list.add(2)
        self.assertEqual(ord_list.most_frequent(), 1)

        ord_list.add(2)
        self.assertEqual(ord_list.most_frequent(), 2)

        ord_list.delete(2)
        self.assertEqual(ord_list.most_frequent(), 1)



class TestCaseOrderedListExt4(unittest.TestCase):
    def test_get_index(self):
        ord_list = OrderedListExt4(True)
        for i in range(10):
            ord_list.add(i)

        for i in range(10):
            self.assertEqual(ord_list.get_index(i), i)

