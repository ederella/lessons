from task_8_hash_table.decision import HashTable

"""
3.* Реализуйте динамическую хэш-таблицу, которая автоматически удваивает свой размер, 
если уровень заполненности превышает заданный порог (например, 75%).
"""
class HashTableDynamic:
    def __init__(self, sz : int, stp : int):
        self.size : int = sz
        self.count : int = 0
        self.hash_table : HashTable = HashTable(sz, stp)

    def hash_fun(self, value: str):
        return self.hash_table.hash_fun(value)

    def seek_slot(self, value: str):
        return self.hash_table.seek_slot(value)

    def put(self, value: str):
        if self.count / self.size >= 0.75:
            self.resize()
        self.count +=1
        return self.hash_table.put(value)

    def find(self, value: int):
        return self.hash_table.find(value)

    def resize(self):
        self.size = self.size * 2
        old_hash_table : HashTable = self.hash_table
        self.hash_table = HashTable(self.size, self.hash_table.step)
        for el in old_hash_table.slots:
            if el is not None:
                self.hash_table.put(el)


"""
4.* Реализуйте хэш-таблицу, которая использует несколько хэш-функций для каждой операции вставки, 
чтобы уменьшить вероятность коллизий. Проанализируйте, как это влияет на производительность и вероятность коллизий.

Ответ: производительность - в целом не изменилась, вероятность коллизий - стала меньше 
"""
class HashTableMultiFunc:
    def __init__(self, sz, stp):
        self.size = sz
        self.step = stp
        self.slots = [None] * self.size
        self.hash_funcs : list = []
        self.hash_funcs.append(lambda  value : len(value.encode()) % self.size)
        self.hash_funcs.append(lambda  value : (5 * len(value.encode()) + 7) % 19 % self.size)
        self.hash_funcs.append(lambda  value : (13 * len(value.encode() )+ 19) % 23 % self.size)

    def hash_func(self, value : str, is_free : bool) -> tuple:
        res = []
        search_val = None if is_free else value
        for f in self.hash_funcs:
            idx : int = f(value)
            if self.slots[idx] == search_val:
                return idx, res
            else:
                res.append(idx)
        return -1, res

    def seek_slot(self, value : str):
        idx, idx_res = self.hash_func(value, True)
        if idx > -1:
            return idx
        for i in range(self.size):
            for idx in idx_res:
                idx = (idx + self.step) % self.size
                if self.slots[idx] is None:
                    return idx
        return None

    def put(self, value):
        free_slot = self.seek_slot(value)
        if free_slot is not None:
            self.slots[free_slot] = value
        return free_slot

    def find(self, value):
        idx, idx_res = self.hash_func(value, False)
        if idx > -1:
            return idx
        for i in range(self.size):
            for idx in idx_res:
                idx = (idx + self.step) % self.size
                if self.slots[idx] == value:
                    return idx
        return None


"""
5.* Организуйте ddos-атаку на вашу исходную хэш-таблицу -- с помощью специально сгенерированных ключей,
вызывающих большое число коллизий. Затем модифицируйте хэш-таблицу для защиты от таких атак (например, посолите).
"""
import random

class HashTableSalted:
    def __init__(self, sz, stp):
        self.size = sz
        self.step = stp
        self.slots = [None] * self.size
        self.salted_values = {}

    def get_salt(self, value : str):
        salt = self.salted_values.get(value)
        if salt is None:
            salt = value + str(random.randint(0, 10000))
            self.salted_values.update({value: salt})
        return salt


    def hash_fun(self, value :str):
        return len(value.encode()) % self.size

    def seek_slot(self, value : str):
        start_idx : int = self.hash_fun(value)
        idx = start_idx
        for i in range(self.size):
            if self.slots[idx] is None:
                return idx
            idx = (idx + self.step) % self.size
            if idx == start_idx:
                break
        return None

    def put(self, value):
        free_slot = self.seek_slot(self.get_salt(value))
        if free_slot is not None:
            self.slots[free_slot] = value
        return free_slot

    def find(self, value):
        start_idx: int = self.hash_fun(self.get_salt(value))
        idx = start_idx
        for i in range(self.size):
            if self.slots[idx] == value:
                return idx
            idx = (idx + self.step) % self.size
            if idx == start_idx:
                break
        return None



"""Рефлексия по решению задач задания 6.

4. Проверка строки на палиндром.
 - Выполнено верно
 
5. Минимальный элемент деки за O(1).
- Выполнено верно

6. Двусторонняя очередь на базе динамического массива.
Нередкая ошибка: смешивать в одном классе логику двух разных структур данных (дека и дин.массива).
Лучше использовать композицию (дин.массив в очереди, или даже сама очередь в дин.массиве).
 - Я сделала как-то вообще по-своему: на основе обычного массива и реализации динамического массива и не использовала композицию.
 Честно говоря, не совсем понимаю, как на основе DynArray, где удаление из головы массива O(N), сделать удаление за O(1).
 В моей реализации это достигается за счет того, что элементы массива не двигаются к началу при удалении из головы (наподобие как в циклической очереди).
"""
