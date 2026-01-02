import ctypes
from functools import reduce

"""
5.* Реализуйте динамический массив на основе банковского метода.
"""
class DynArrayBank:
    def __init__(self):
        self.count = 0
        self.capacity = 16
        self.array = self.make_array(self.capacity)
        self.balance = 0

    def __len__(self):
        return self.count

    def make_array(self, new_capacity):
        return (new_capacity * ctypes.py_object)()

    def __getitem__(self, i):
        if i < 0 or i >= self.count:
            raise IndexError('Index is out of bounds')
        return self.array[i]

    def resize(self, new_capacity):
        new_array = self.make_array(new_capacity)
        for i in range(self.count):
            new_array[i] = self.array[i]
        self.balance-=(new_capacity - self.capacity)
        self.array = new_array
        self.capacity = new_capacity

    def append(self, itm):
        if self.count == self.capacity or self.balance >= 64:
            self.resize(2 * self.capacity)
        else:
            self.balance +=3
        self.array[self.count] = itm
        self.count += 1

    def insert(self, i, itm):
        if i < 0 or i > self.count:
            raise IndexError('Index is out of bounds')
        if self.count == self.capacity or self.balance >= 64:
            self.resize(2 * self.capacity)
        else:
            self.balance +=1
        self.count += 1
        for j in range(self.count - 1, i, -1):
            self.array[j] = self.array[j-1]
        self.array[i] = itm

    def delete(self, i):
        if i < 0 or i >= self.count:
            raise IndexError('Index is out of bounds')
        for j in range(i, self.count - 1):
            self.array[j] = self.array[j+1]
        self.count -= 1
        if self.count < self.capacity/2:
            new_capacity = int(self.capacity//1.5)
            self.resize(max(16,new_capacity))
        else:
            self.balance +=1

"""6.* Реализуйте многомерный динамический массив:
произвольное количество измерений, при этом каждое измерение может внутри масштабироваться по потребности.
В конструкторе задаётся число измерений и размер по каждому из них.
Обращаться к такому массиву надо как к обычному многомерному, например: myArr[1,2,3].
______________
Честно говоря, не могу до конца понять как такой многомерный массив должен заполняться (через append).
Ну то есть, если есть двумерный массив, определили ему размер 2 строки по 3 элемента - добавили 3 элемента, 
понятно их пишем в 1 строку, куда добавлять 4й элемент? Писать во вторую строку или же делать resize первой строки?
Допустим, заполняем все по-максимуму, потом делаем resize - увеличивать вдвое полностью каждый размер внутренних массивов? 
Или добавлять только в одной размерности?
В общем, очень хочу посмотреть эталонное решение.
"""
class DynArrayMultiDimension:
    def __init__(self, dim, *cap):
        self.count = 0
        self.total_count = 0
        self.capacity = cap[0]
        self.dim = dim
        if dim == 1:
            self.array = self.make_array(self.capacity)
            self.total_capacity = self.capacity
        else:
            inner_capacity = cap[1:]
            self.total_capacity = reduce(lambda x, y: x * y, cap)
            self.array = self.__init__(dim - 1, *inner_capacity)

    def make_array(self, new_capacity):
        return (new_capacity * ctypes.py_object)()

    def resize(self, new_capacity):
       pass

    def append(self, itm):
        pass
      
"""Рекомендации по решению задач задания 1.

8. Функция суммирования двух связанных списков.
Первоначально проверяем, если длины этих списков не равны, то выбрасываем исключение ( хотя в целом это всегда плохой подход).
Далее в цикле пробегаемся синхронно по элементам каждого из списков, и их сумму добавляем в хвост списка-результата. 
В заголовке цикла достаточно проверять только один список (не завершился ли он), так как длины равны.

Рефлексия:
1 - я не выбрасываю исключение (так как считается, что это плохо) - а возвращаю пустой список
2 - в заголовке цикла проверяю оба списка (мало ли :))
"""
