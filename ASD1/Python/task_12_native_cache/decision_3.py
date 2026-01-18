"""Смоделируйте в тестах программно ситуацию, когда хэш-таблица заполнена
(например, организуйте множество коллизий) и проверьте, правильно ли работает схема вытеснения.
Также проверяйте в тестах, корректно ли учитывается количество обращений к ключам."""
import unittest

from task_12_native_cache.decision import NativeCache


class TestNativeCache(unittest.TestCase):
    def setUp(self):
        self.cache = NativeCache(10)

    def test_normal(self):
        self.cache.put('Russia','Moscow')
        self.cache.put('France','Paris')
        self.cache.put('UK','London')
        self.cache.put('Uzbekistan','Tashkent')

        self.assertEqual(self.cache.get('Russia'),'Moscow')
        self.assertEqual(self.cache.get('France'),'Paris')
        self.assertEqual(self.cache.get('UK'),'London')
        self.assertEqual(self.cache.get('Uzbekistan'),'Tashkent')

        i = self.cache.hash_fun('Russia')
        self.assertEqual(self.cache.hits[i],1)

        i = self.cache.hash_fun('France')
        self.assertEqual(self.cache.hits[i],1)

        i = self.cache.hash_fun('UK')
        self.assertEqual(self.cache.hits[i],1)

        i = self.cache.hash_fun('UK')
        self.assertEqual(self.cache.hits[i],1)


    def test_collisions(self):
        test_str = '0123456789'
        for i in range(10):
            self.cache.put(test_str, i)
            test_str = test_str[1::] + test_str[0]

        new_test_str = '1111111'
        self.cache.put(new_test_str, 11)
        i = self.cache.hash_fun('1111111')
        self.assertEqual(self.cache.hits[i],0)

        new_test_str = '2222222'
        self.cache.put(new_test_str, 22)
        i = self.cache.hash_fun('2222222')
        self.assertEqual(self.cache.hits[i],0)

