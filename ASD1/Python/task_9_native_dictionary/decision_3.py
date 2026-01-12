"""4. Сделайте тесты, проверяющие, как работают put(), is_key() и get():
- добавление значения по новому ключу и добавление значения по уже существующему ключу с проверками, что записалось,
проверка присутствующего и отсутствующего ключей, извлечение значения по существующему и отсутствующему ключу.

В данном обучающем примере исходим из фиксированного размера ассоциативного массива.
В автоматических тестах этот размер гарантированно не будет превышен.
"""

import unittest
from unittest.mock import Mock

from task_9_native_dictionary.decision import NativeDictionary
from task_9_native_dictionary.decision_2 import NativeDictionaryOrdList


class TestNativeDictionary(unittest.TestCase):
    def setUp(self):
        self.dictionary = NativeDictionary(10)

    def test_put(self):
        self.dictionary.put('Pushkin', 'Fairytales')
        self.dictionary.put('Lermontov', 'Poems')

        self.assertEqual(self.dictionary.get('Pushkin'), 'Fairytales')
        self.assertEqual(self.dictionary.get('Lermontov'), 'Poems')

        self.dictionary.put('Pushkin', 'Poems')
        self.assertEqual(self.dictionary.get('Pushkin'), 'Poems')

        self.dictionary.hash_fun = Mock(return_value=self.dictionary.hash_fun('Pushkin'))
        self.dictionary.put('Dickens', 'Stories')
        self.dictionary.put('Tolstoy', 'Anna Karenina')
        self.assertEqual(self.dictionary.get('Dickens'), 'Stories')
        self.assertEqual(self.dictionary.get('Tolstoy'), 'Anna Karenina')

    def test_get(self):
        self.assertIsNone(self.dictionary.get('Pushkin'))
        self.dictionary.put('Pushkin', 'Fairytales')
        self.assertEqual(self.dictionary.get('Pushkin'), 'Fairytales')

        self.assertIsNone(self.dictionary.get('Lermontov'))
        self.dictionary.put('Lermontov', 'Poems')
        self.assertEqual(self.dictionary.get('Lermontov'), 'Poems')

        self.assertIsNone(self.dictionary.get('Dickens'))
        self.dictionary.put('Dickens', 'Stories')
        self.assertEqual(self.dictionary.get('Dickens'), 'Stories')

        self.assertIsNone(self.dictionary.get('Tolstoy'))
        self.dictionary.put('Tolstoy', 'Anna Karenina')
        self.assertEqual(self.dictionary.get('Tolstoy'), 'Anna Karenina')

        self.assertIsNone(self.dictionary.get('Pupkin'))

    def test_is_key(self):
        self.dictionary.put('Pushkin', 'Fairytales')
        self.dictionary.put('Lermontov', 'Poems')
        self.dictionary.put('Dickens', 'Stories')
        self.dictionary.put('Tolstoy', 'Anna Karenina')

        self.assertTrue(self.dictionary.is_key('Pushkin'))
        self.assertTrue(self.dictionary.is_key('Lermontov'))
        self.assertTrue(self.dictionary.is_key('Dickens'))
        self.assertTrue(self.dictionary.is_key('Tolstoy'))
        self.assertFalse(self.dictionary.is_key('Pupkin'))


class TestNativeDictionaryOrdList(unittest.TestCase):
    def setUp(self):
        self.dictionary = NativeDictionaryOrdList()

    def test_put(self):
        self.dictionary.put('Pushkin', 'Fairytales')
        self.dictionary.put('Lermontov', 'Poems')

        self.assertEqual(self.dictionary.get('Pushkin'), 'Fairytales')
        self.assertEqual(self.dictionary.get('Lermontov'), 'Poems')

        self.dictionary.put('Pushkin', 'Poems')
        self.assertEqual(self.dictionary.get('Pushkin'), 'Poems')


    def test_get(self):
        self.assertIsNone(self.dictionary.get('Pushkin'))
        self.dictionary.put('Pushkin', 'Fairytales')
        self.assertEqual(self.dictionary.get('Pushkin'), 'Fairytales')

        self.assertIsNone(self.dictionary.get('Lermontov'))
        self.dictionary.put('Lermontov', 'Poems')
        self.assertEqual(self.dictionary.get('Lermontov'), 'Poems')

        self.assertIsNone(self.dictionary.get('Dickens'))
        self.dictionary.put('Dickens', 'Stories')
        self.assertEqual(self.dictionary.get('Dickens'), 'Stories')

        self.assertIsNone(self.dictionary.get('Tolstoy'))
        self.dictionary.put('Tolstoy', 'Anna Karenina')
        self.assertEqual(self.dictionary.get('Tolstoy'), 'Anna Karenina')

        self.assertIsNone(self.dictionary.get('Pupkin'))

    def test_is_key(self):
        self.dictionary.put('Pushkin', 'Fairytales')
        self.dictionary.put('Lermontov', 'Poems')
        self.dictionary.put('Dickens', 'Stories')
        self.dictionary.put('Tolstoy', 'Anna Karenina')

        self.assertTrue(self.dictionary.is_key('Pushkin'))
        self.assertTrue(self.dictionary.is_key('Lermontov'))
        self.assertTrue(self.dictionary.is_key('Dickens'))
        self.assertTrue(self.dictionary.is_key('Tolstoy'))
        self.assertFalse(self.dictionary.is_key('Pupkin'))

    def test_remove(self):
        self.dictionary.put('Pushkin', 'Fairytales')
        self.assertTrue(self.dictionary.is_key('Pushkin'))
        self.dictionary.remove('Pushkin')
        self.assertFalse(self.dictionary.is_key('Pushkin'))
