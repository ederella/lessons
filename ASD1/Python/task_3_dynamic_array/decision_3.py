import unittest

from task_3_dynamic_array.decision import DynArray
from task_3_dynamic_array.decision_2 import DynArrayBank

"""4. Напишите тесты, проверяющие работу методов insert() и delete():
-- вставка элемента, когда в итоге размер буфера не превышен (проверьте также размер буфера);
-- вставка элемента, когда в результате превышен размер буфера (проверьте также корректное изменение размера буфера);
-- попытка вставки элемента в недопустимую позицию;
-- удаление элемента, когда в результате размер буфера остаётся прежним (проверьте также размер буфера);
-- удаление элемента, когда в результате понижается размер буфера (проверьте также корректное изменение размера буфера);
-- попытка удаления элемента в недопустимой позиции."""

class MyTestCase(unittest.TestCase):
    def test_insert1(self):
        dyn_array = DynArray()
        dyn_array.append(0)
        dyn_array.append(1)
        dyn_array.append(2)

        dyn_array.insert(1, 100)
        self.assertEqual(to_list(dyn_array), [0, 100, 1, 2])
        self.assertEqual(dyn_array.capacity, 16)
        self.assertEqual(dyn_array.count, 4)

        dyn_array.insert(4, 3)
        self.assertEqual(to_list(dyn_array), [0,100,1,2,3])


    def test_insert2(self):
        dyn_array = DynArray()
        for i in range(16):
            dyn_array.append(i)

        dyn_array.insert(1, 100)
        self.assertEqual(dyn_array.array[1], 100)
        self.assertEqual(dyn_array.capacity, 32)
        self.assertEqual(dyn_array.count, 17)

    def test_insert3(self):
        dyn_array = DynArray()
        self.assertRaises(IndexError, dyn_array.insert, 1, 100)

        dyn_array.append(0)
        dyn_array.append(1)
        dyn_array.append(2)
        self.assertRaises(IndexError, dyn_array.insert, 10, 100)
        self.assertRaises(IndexError, dyn_array.insert, -1, 100)


    def test_delete1(self):
        dyn_array = DynArray()
        for i in range(10):
            dyn_array.append(i)

        dyn_array.delete(1)

        self.assertEqual(to_list(dyn_array), [0, 2, 3, 4, 5, 6, 7, 8, 9])
        self.assertEqual(dyn_array.capacity, 16)
        self.assertEqual(dyn_array.count, 9)

    def test_delete2(self):
        dyn_array = DynArray()
        for i in range(18):
            dyn_array.append(i)
        self.assertEqual(dyn_array.capacity, 32)

        for i in range(5):
            dyn_array.delete(1)

        self.assertEqual(to_list(dyn_array), [0,6,7,8,9,10,11,12,13,14,15,16,17])
        self.assertEqual(dyn_array.capacity, 21)
        self.assertEqual(dyn_array.count, 13)

    def test_delete3(self):
        dyn_array = DynArray()
        for i in range(8):
            dyn_array.append(i)

        self.assertRaises(IndexError, dyn_array.delete, -1)
        self.assertRaises(IndexError, dyn_array.delete, 10)


    def test_bank_insert1(self):
        dyn_array = DynArrayBank()
        dyn_array.append(0)
        dyn_array.append(1)
        dyn_array.append(2)

        dyn_array.insert(1, 100)
        self.assertEqual(to_list(dyn_array), [0, 100, 1, 2])
        self.assertEqual(dyn_array.capacity, 16)
        self.assertEqual(dyn_array.count, 4)

        dyn_array.insert(4, 3)
        self.assertEqual(to_list(dyn_array), [0,100,1,2,3])


    def test_bank_insert2(self):
        dyn_array = DynArrayBank()
        for i in range(16):
            dyn_array.append(i)

        dyn_array.insert(1, 100)
        self.assertEqual(dyn_array.array[1], 100)
        self.assertEqual(dyn_array.capacity, 32)
        self.assertEqual(dyn_array.count, 17)

    def test_bank_insert3(self):
        dyn_array = DynArrayBank()
        self.assertRaises(IndexError, dyn_array.insert, 1, 100)

        dyn_array.append(0)
        dyn_array.append(1)
        dyn_array.append(2)
        self.assertRaises(IndexError, dyn_array.insert, 10, 100)
        self.assertRaises(IndexError, dyn_array.insert, -1, 100)


    def test_bank_delete1(self):
        dyn_array = DynArrayBank()
        balance = 0
        for i in range(9):
            dyn_array.append(i)
            balance+=3

        dyn_array.delete(1)

        self.assertEqual(to_list(dyn_array), [0, 2, 3, 4, 5, 6, 7, 8])
        self.assertEqual(dyn_array.capacity, 16)
        self.assertEqual(dyn_array.count, 8)

    def test_bank_delete2(self):
        dyn_array = DynArrayBank()
        for i in range(18):
            dyn_array.append(i)
        self.assertEqual(dyn_array.capacity, 32)

        for i in range(5):
            dyn_array.delete(1)

        self.assertEqual(to_list(dyn_array), [0,6,7,8,9,10,11,12,13,14,15,16,17])
        self.assertEqual(dyn_array.capacity, 21)
        self.assertEqual(dyn_array.count, 13)

    def test_bank_delete3(self):
        dyn_array = DynArrayBank()
        for i in range(8):
            dyn_array.append(i)

        self.assertRaises(IndexError, dyn_array.delete, -1)
        self.assertRaises(IndexError, dyn_array.delete, 10)

def to_list(dyn_array):
    res =[]
    for i in range(dyn_array.count):
        res.append(dyn_array.array[i])
    return res
