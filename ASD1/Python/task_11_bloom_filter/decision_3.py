import unittest

from task_11_bloom_filter.decision import BloomFilter


class TestBloomFilter(unittest.TestCase):

    def test_filter(self):
        test_str = '0123456789'
        for i in range(10):
            self.filter = BloomFilter(32)
            self.assertFalse(self.filter.is_value(test_str))
            self.filter.add(test_str)
            self.assertTrue(self.filter.is_value(test_str))
            test_str = test_str[1::] + test_str[0]
