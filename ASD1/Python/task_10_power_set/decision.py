from __future__ import annotations
from typing import Any


class PowerSet:

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

    def intersection(self, set2: PowerSet) -> PowerSet:
        result_set: PowerSet = PowerSet()
        for value in set2.__storage:
            if self.get(value):
                result_set.put(value)
        return result_set

    def union(self, set2: PowerSet) -> PowerSet:
        result_set: PowerSet = PowerSet()
        for value in self.__storage:
            result_set.put(value)
        for value in set2.__storage:
            result_set.put(value)
        return result_set

    def difference(self, set2: PowerSet) -> PowerSet:
        result_set: PowerSet = PowerSet()
        for value in self.__storage:
            if not set2.get(value):
                result_set.put(value)
        return result_set

    def issubset(self, set2: PowerSet) -> bool:
        if len(set2.__storage) > len(self.__storage):
            return False
        for value in set2.__storage:
            if value not in self.__storage:
                return False
        return True

    def equals(self, set2: PowerSet) -> bool:
        return  self.__storage == set2.__storage

