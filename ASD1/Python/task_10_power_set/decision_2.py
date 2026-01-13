"""4.* Добавьте метод, реализующий декартово произведение множеств.
"""
from typing import Any

from task_10_power_set.decision import PowerSet


class PowerSetExt:

    def __init__(self) -> None:
        self.__storage = []

    def size(self) -> int:
        return len(self.__storage)

    def put(self, value: Any) -> None:
        if not self.get(value):
            self.__storage.append(value)

    def get(self, value: Any) -> bool:
        return value in self.__storage

    def remove(self, value: Any) -> bool:
        if self.get(value):
            self.__storage.remove(value)
            return True
        return False

    def intersection(self, set2: PowerSetExt) -> PowerSetExt:
        result_set: PowerSetExt = PowerSetExt()
        for value in set2.__storage:
            if self.get(value):
                result_set.put(value)
        return result_set

    def union(self, set2: PowerSetExt) -> PowerSetExt:
        result_set: PowerSetExt = PowerSetExt()
        for value in self.__storage:
            result_set.put(value)
        for value in set2.__storage:
            result_set.put(value)
        return result_set

    def difference(self, set2: PowerSetExt) -> PowerSetExt:
        result_set: PowerSetExt = PowerSetExt()
        for value in self.__storage:
            if not set2.get(value):
                result_set.put(value)
        return result_set

    def issubset(self, set2: PowerSetExt) -> bool:
        if len(set2.__storage) > len(self.__storage):
            return False
        for value in set2.__storage:
            if value not in self.__storage:
                return False
        return True

    def equals(self, set2: PowerSetExt) -> bool:
        return  self.__storage == set2.__storage

    def cartesian_product(self, set2: PowerSetExt) -> PowerSetExt:
        result_set: PowerSetExt = PowerSetExt()
        for value1 in self.__storage:
            for value2 in set2.__storage:
                result_set.put((value1, value2))
        return result_set

"""
5.* Напишите функцию, которая находит пересечение любых трёх и более множеств (принимает количество множеств >= 3 в качестве списка).
"""
def intersection(set_list:[]) -> PowerSet:
    result_set: PowerSet = PowerSet()

    if len(set_list) < 3:
        return result_set

    result_set = set_list[0]
    for i in range(1, len(set_list)):
        result_set = result_set.intersection(set_list[i])
    return result_set

"""
6.* Реализуйте мульти-множество (Bag), в котором каждый элемент может присутствовать несколько раз. 
Добавьте методы добавления элементов, удаления одного экземпляра элемента и получения списка всех элементов с их частотами (сколько раз встречаются).
 """
class Bag:

    def __init__(self) -> None:
        self.__storage = []

    def size(self) -> int:
        return len(self.__storage)

    def put(self, value: Any) -> None:
        self.__storage.append(value)

    def get(self, value: Any) -> bool:
        return value in self.__storage

    def remove(self, value: Any) -> bool:
        if self.get(value):
            self.__storage.remove(value)
            return True
        return False

    def get_all(self) -> list:
        result = {}
        for value in self.__storage:
            cnt = result.get(value)
            cnt = 1 if cnt is None else cnt + 1
            result.update(value, cnt)
        return list(result.items())

"""Рефлексия по решению задач задания 8.

3. Динамическая хэш-таблица.
 - Как и рекомендуется, использую композицию - элементы хранятся в HashTable на динамическом массиве
 - Размер буфера хранится уже в самой динамической таблице и явно расширяется через заведение новой хэш-таблицы, 
 куда в соответствии с новым размером вставляются элементы старой таблицы

5. ddos хэш-таблицы и соль.
 - Использована динамическая соль, все соответствует рекомендации
 """
