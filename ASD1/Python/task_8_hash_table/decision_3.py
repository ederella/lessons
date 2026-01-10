import unittest

from task_8_hash_table.decision import HashTable
from task_8_hash_table.decision_2 import HashTableDynamic, HashTableMultiFunc, HashTableSalted


class TestCaseHashTable(unittest.TestCase):
    def test_hash_fun(self):
        hash_table = HashTable(17,3)
        self.assertEqual(hash_table.hash_fun('apple'), len('apple'.encode())%17)
        self.assertEqual(hash_table.hash_fun(''), 0)

    def test_seek_slot(self):
        hash_table = HashTable(17, 3)
        self.assertEqual(hash_table.seek_slot('apple'), len('apple'.encode()) % 17)
        hash_table.put('apple')
        self.assertNotEqual(hash_table.seek_slot('apple'), len('apple'.encode()) % 17)

        hash_table = HashTable(5, 3)
        hash_table.put('apple')
        hash_table.put('apple')
        hash_table.put('apple')
        hash_table.put('apple')
        hash_table.put('apple')
        self.assertEqual(hash_table.seek_slot('apple'), None)

    def test_put(self):
        hash_table = HashTable(5, 3)
        put_1_slot = hash_table.put('apple')
        put_2_slot = hash_table.put('apple')
        put_3_slot = hash_table.put('apple')
        put_4_slot = hash_table.put('apple')
        put_5_slot = hash_table.put('apple')
        put_6_slot = hash_table.put('apple')

        self.assertNotEqual(put_1_slot, put_2_slot)
        self.assertNotEqual(put_1_slot, put_3_slot)
        self.assertNotEqual(put_1_slot, put_4_slot)
        self.assertNotEqual(put_1_slot, put_5_slot)
        self.assertNotEqual(put_2_slot, put_3_slot)
        self.assertNotEqual(put_2_slot, put_4_slot)
        self.assertNotEqual(put_2_slot, put_5_slot)
        self.assertNotEqual(put_3_slot, put_4_slot)
        self.assertNotEqual(put_3_slot, put_5_slot)
        self.assertNotEqual(put_4_slot, put_5_slot)
        self.assertIsNone(put_6_slot)

    def test_find(self):
        hash_table = HashTable(5, 3)
        put_1_slot = hash_table.put('apple')
        put_2_slot = hash_table.put('apple')

        self.assertEqual(hash_table.find('apple'), put_1_slot)
        self.assertEqual(hash_table.find('apple'), put_1_slot)
        self.assertNotEqual(hash_table.find('apple'), put_2_slot)
        self.assertIsNone(hash_table.find('lemon'))

class TestCaseHashTableDynamic(unittest.TestCase):

    def test_put(self):
        hash_table = HashTableDynamic(5, 3)
        put_1_slot = hash_table.put('apple')
        put_2_slot = hash_table.put('apple')
        put_3_slot = hash_table.put('apple')
        put_4_slot = hash_table.put('apple')
        put_5_slot = hash_table.put('apple')
        put_6_slot = hash_table.put('apple')

        self.assertNotEqual(put_1_slot, put_2_slot)
        self.assertNotEqual(put_1_slot, put_3_slot)
        self.assertNotEqual(put_1_slot, put_4_slot)
        self.assertNotEqual(put_1_slot, put_5_slot)
        self.assertNotEqual(put_2_slot, put_3_slot)
        self.assertNotEqual(put_2_slot, put_4_slot)
        self.assertNotEqual(put_2_slot, put_5_slot)
        self.assertNotEqual(put_3_slot, put_4_slot)
        self.assertNotEqual(put_3_slot, put_5_slot)
        self.assertNotEqual(put_4_slot, put_5_slot)
        self.assertIsNotNone(put_6_slot)
        self.assertEqual(hash_table.size, 10)

    def test_find(self):
        hash_table = HashTable(5, 3)
        put_1_slot = hash_table.put('apple')
        put_2_slot = hash_table.put('apple')

        self.assertEqual(hash_table.find('apple'), put_1_slot)
        self.assertEqual(hash_table.find('apple'), put_1_slot)
        self.assertNotEqual(hash_table.find('apple'), put_2_slot)
        self.assertIsNone(hash_table.find('lemon'))


class TestCaseHashTableMultiFunc(unittest.TestCase):
    def test_seek_slot(self):
        hash_table = HashTableMultiFunc(17, 3)
        self.assertEqual(hash_table.seek_slot('apple'), len('apple'.encode()) % 17)
        hash_table.put('apple')
        self.assertNotEqual(hash_table.seek_slot('apple'), len('apple'.encode()) % 17)

        hash_table = HashTable(5, 3)
        hash_table.put('apple')
        hash_table.put('apple')
        hash_table.put('apple')
        hash_table.put('apple')
        hash_table.put('apple')
        self.assertEqual(hash_table.seek_slot('apple'), None)

    def test_put(self):
        hash_table = HashTableMultiFunc(5, 3)
        put_1_slot = hash_table.put('apple')
        put_2_slot = hash_table.put('apple')
        put_3_slot = hash_table.put('apple')
        put_4_slot = hash_table.put('apple')
        put_5_slot = hash_table.put('apple')
        put_6_slot = hash_table.put('apple')

        self.assertNotEqual(put_1_slot, put_2_slot)
        self.assertNotEqual(put_1_slot, put_3_slot)
        self.assertNotEqual(put_1_slot, put_4_slot)
        self.assertNotEqual(put_1_slot, put_5_slot)
        self.assertNotEqual(put_2_slot, put_3_slot)
        self.assertNotEqual(put_2_slot, put_4_slot)
        self.assertNotEqual(put_2_slot, put_5_slot)
        self.assertNotEqual(put_3_slot, put_4_slot)
        self.assertNotEqual(put_3_slot, put_5_slot)
        self.assertIsNone(put_6_slot)

    def test_find(self):
        hash_table = HashTableMultiFunc(5, 3)
        put_1_slot = hash_table.put('apple')
        put_2_slot = hash_table.put('apple')

        self.assertEqual(hash_table.find('apple'), put_1_slot)
        self.assertEqual(hash_table.find('apple'), put_1_slot)
        self.assertNotEqual(hash_table.find('apple'), put_2_slot)
        self.assertIsNone(hash_table.find('lemon'))


class TestCaseHashTableSalted(unittest.TestCase):
    def test_hash_fun(self):
        hash_table = HashTableSalted(17,3)
        self.assertEqual(hash_table.hash_fun('apple'), len('apple'.encode())%17)
        self.assertEqual(hash_table.hash_fun(''), 0)

    def test_seek_slot(self):
        hash_table = HashTableSalted(17, 3)
        self.assertEqual(hash_table.seek_slot('apple'), len('apple'.encode()) % 17)

        hash_table = HashTableSalted(5, 3)
        hash_table.put('apple')
        hash_table.put('apple')
        hash_table.put('apple')
        hash_table.put('apple')
        hash_table.put('apple')
        self.assertEqual(hash_table.seek_slot('apple'), None)

    def test_put(self):
        hash_table = HashTableSalted(5, 3)
        put_1_slot = hash_table.put('apple')
        put_2_slot = hash_table.put('apple')
        put_3_slot = hash_table.put('apple')
        put_4_slot = hash_table.put('apple')
        put_5_slot = hash_table.put('apple')
        put_6_slot = hash_table.put('apple')

        self.assertNotEqual(put_1_slot, put_2_slot)
        self.assertNotEqual(put_1_slot, put_3_slot)
        self.assertNotEqual(put_1_slot, put_4_slot)
        self.assertNotEqual(put_1_slot, put_5_slot)
        self.assertNotEqual(put_2_slot, put_3_slot)
        self.assertNotEqual(put_2_slot, put_4_slot)
        self.assertNotEqual(put_2_slot, put_5_slot)
        self.assertNotEqual(put_3_slot, put_4_slot)
        self.assertNotEqual(put_3_slot, put_5_slot)
        self.assertNotEqual(put_4_slot, put_5_slot)
        self.assertIsNone(put_6_slot)

    def test_find(self):
        hash_table = HashTableSalted(5, 3)
        put_1_slot = hash_table.put('apple')
        put_2_slot = hash_table.put('apple')

        self.assertEqual(hash_table.find('apple'), put_1_slot)
        self.assertEqual(hash_table.find('apple'), put_1_slot)
        self.assertNotEqual(hash_table.find('apple'), put_2_slot)
        self.assertIsNone(hash_table.find('lemon'))
