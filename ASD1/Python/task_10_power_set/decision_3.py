import time
import unittest

from task_10_power_set.decision import PowerSet
from task_10_power_set.decision_2 import PowerSetExt, intersection


class TestPowerSet(unittest.TestCase):
    def setUp(self):
        self.power_set = PowerSet()

    def testPut(self):
        self.assertEqual(0, self.power_set.size())
        self.assertFalse(self.power_set.get('Odin'))
        self.power_set.put('Odin')
        self.assertEqual(1, self.power_set.size())
        self.assertTrue(self.power_set.get('Odin'))
        self.power_set.put('Odin')
        self.power_set.put('Odin')
        self.assertEqual(1, self.power_set.size())
        self.assertTrue(self.power_set.get('Odin'))

        self.assertFalse(self.power_set.get('Thor'))
        self.power_set.put('Thor')
        self.assertEqual(2, self.power_set.size())
        self.assertTrue(self.power_set.get('Thor'))
        self.power_set.put('Thor')
        self.power_set.put('Thor')
        self.assertEqual(2, self.power_set.size())
        self.assertTrue(self.power_set.get('Thor'))

    def testRemove(self):
        self.power_set.put('Odin')
        self.power_set.put('Thor')
        self.power_set.put('Loki')
        self.assertEqual(3, self.power_set.size())

        self.power_set.remove('Odin')
        self.assertEqual(2, self.power_set.size())
        self.assertFalse(self.power_set.get('Odin'))

        self.power_set.remove('Odin')
        self.assertEqual(2, self.power_set.size())
        self.assertFalse(self.power_set.get('Odin'))

        self.power_set.remove('Thor')
        self.assertEqual(1, self.power_set.size())
        self.assertFalse(self.power_set.get('Thor'))

        self.power_set.remove('Loki')
        self.assertEqual(0, self.power_set.size())
        self.assertFalse(self.power_set.get('Loki'))

    def testIntersection(self):
        set2 = PowerSet()
        set20 = PowerSet()
        for i in range(10):
            self.power_set.put(i)
            set2.put(i * 2)
            set20.put(i + 20)

        res_set = self.power_set.intersection(set2)
        self.assertEqual(5, res_set.size())
        self.assertTrue(res_set.get(0))
        self.assertTrue(res_set.get(2))
        self.assertTrue(res_set.get(4))
        self.assertTrue(res_set.get(6))
        self.assertTrue(res_set.get(8))

        res_set = self.power_set.intersection(self.power_set)
        for i in range(10):
            self.assertTrue(res_set.get(i))

        res_set = self.power_set.intersection(set20)
        self.assertEqual(0, res_set.size())

        empty_set = PowerSet()
        res_set = self.power_set.intersection(empty_set)
        self.assertEqual(0, res_set.size())

    def testUnion(self):
        #[]+[]
        res_set = self.power_set.union(PowerSet())
        self.assertEqual(0, res_set.size())

        # []+['Odin', 'Vili', 'Ve']
        set2 = PowerSet()
        set2.put('Odin')
        set2.put('Vili')
        set2.put('Ve')
        res_set = self.power_set.union(set2)
        self.assertEqual(3, res_set.size())
        self.assertTrue(res_set.get('Odin'))
        self.assertTrue(res_set.get('Vili'))
        self.assertTrue(res_set.get('Ve'))

        # ['Odin','Thor','Loki']+[]
        self.power_set.put('Odin')
        self.power_set.put('Thor')
        self.power_set.put('Loki')
        res_set = self.power_set.union(PowerSet())
        self.assertEqual(3, res_set.size())
        self.assertTrue(res_set.get('Odin'))
        self.assertTrue(res_set.get('Thor'))
        self.assertTrue(res_set.get('Loki'))

        # ['Odin','Thor','Loki']+['Odin', 'Vili', 'Ve']
        res_set = self.power_set.union(set2)
        self.assertEqual(5, res_set.size())
        self.assertTrue(res_set.get('Odin'))
        self.assertTrue(res_set.get('Thor'))
        self.assertTrue(res_set.get('Loki'))
        self.assertTrue(res_set.get('Vili'))
        self.assertTrue(res_set.get('Ve'))

    def testDifference(self):
        # [] - ['Odin', 'Vili', 'Ve']
        set2 = PowerSet()
        set2.put('Odin')
        set2.put('Vili')
        set2.put('Ve')
        res_set = self.power_set.difference(set2)
        self.assertEqual(0, res_set.size())

        # ['Odin','Thor','Loki'] - []
        self.power_set.put('Odin')
        self.power_set.put('Thor')
        self.power_set.put('Loki')
        res_set = self.power_set.difference(PowerSet())
        self.assertEqual(3, res_set.size())
        self.assertTrue(res_set.get('Odin'))
        self.assertTrue(res_set.get('Thor'))
        self.assertTrue(res_set.get('Loki'))

        # ['Odin','Thor','Loki'] - ['Odin', 'Vili', 'Ve']
        res_set = self.power_set.difference(set2)
        self.assertEqual(2, res_set.size())
        self.assertFalse(res_set.get('Odin'))
        self.assertTrue(res_set.get('Thor'))
        self.assertTrue(res_set.get('Loki'))
        self.assertFalse(res_set.get('Vili'))
        self.assertFalse(res_set.get('Ve'))

    def testIsSubset(self):
        set2 = PowerSet()
        self.assertTrue(self.power_set.issubset(set2))

        self.power_set.put('Odin')
        self.power_set.put('Thor')
        self.power_set.put('Loki')

        set2 = PowerSet()
        self.assertTrue(self.power_set.issubset(set2))

        set2.put('Odin')
        self.assertTrue(self.power_set.issubset(set2))

        set2.put('Vili')
        set2.put('Ve')
        self.assertFalse(self.power_set.issubset(set2))

        set2 = PowerSet()
        set2.put('Odin')
        set2.put('Thor')
        set2.put('Loki')
        self.assertTrue(self.power_set.issubset(set2))

        set2.put('Vili')
        set2.put('Ve')
        self.assertFalse(self.power_set.issubset(set2))

        self.power_set = PowerSet()
        self.assertFalse(self.power_set.issubset(set2))

    def testEquals(self):
        set2 = PowerSet()
        self.assertTrue(self.power_set.equals(set2))
        self.power_set.put('Odin')
        self.power_set.put('Thor')
        self.power_set.put('Loki')
        self.assertFalse(self.power_set.equals(set2))

        set2.put('Odin')
        self.assertFalse(self.power_set.equals(set2))
        set2.put('Thor')
        self.assertFalse(self.power_set.equals(set2))
        set2.put('Loki')
        self.assertTrue(self.power_set.equals(set2))


class TestPowerSetSpeed(unittest.TestCase):
    def setUp(self):
        self.empty_set = PowerSet()

        self.power_set1 = PowerSet()
        for i in range (20000):
            self.power_set1.put(i)

        self.power_set2 = PowerSet()
        for i in range (10000, 30000):
            self.power_set2.put(i)

    def testSpeedPut(self):
        started_at = time.time()
        for i in range (20000):
            self.empty_set.put(i)
        elapsed = time.time() - started_at
        self.assertLess(elapsed, 3)

    def testSpeedGet(self):
        started_at = time.time()
        for i in range (20000):
            self.power_set1.get(i)
        elapsed = time.time() - started_at
        self.assertLess(elapsed, 3)

    def testSpeedRemove(self):
        started_at = time.time()
        for i in range(20000):
            self.power_set1.remove(i)
        elapsed = time.time() - started_at
        self.assertLess(elapsed, 3)

    def testSpeedIsSubset(self):
        started_at = time.time()
        self.power_set1.issubset(self.power_set2)
        elapsed = time.time() - started_at
        self.assertLess(elapsed, 3)

class TestAdditional(unittest.TestCase):
    def test_cartesian_product(self):
        set1: PowerSetExt = PowerSetExt()
        set1.put('Odin')
        set1.put('Thor')
        set1.put('Loki')

        set2: PowerSetExt = PowerSetExt()
        set2.put('Vili')
        set2.put('Ve')

        res_set = set1.cartesian_product(set2)
        self.assertEqual(set1.size() * set2.size(), res_set.size())

    def test_intersection(self):
        set1: PowerSetExt = PowerSetExt()
        set1.put('a')
        set1.put('b')
        set1.put('c')
        set1.put('d')
        set1.put('e')

        set2: PowerSetExt = PowerSetExt()
        set2.put('b')
        set2.put('c')
        set2.put('d')
        set2.put('e')
        set2.put('f')

        set3: PowerSetExt = PowerSetExt()
        set3.put('c')
        set3.put('d')
        set3.put('e')
        set3.put('f')
        set3.put('g')

        res_set = intersection([set1, set2, set3])
        self.assertEqual(3, res_set.size())
        self.assertTrue(res_set.get('c'))
        self.assertTrue(res_set.get('d'))
        self.assertTrue(res_set.get('e'))

