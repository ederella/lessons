import ctypes
from functools import reduce

from task_4_stack.decision import Stack
from task_5_queue.decision import Queue
import array

"""2. Оцените меру сложности для операций enqueue() и dequeue().
Ответ:
    - сложность enqueue() = O(1) - так как элемент добавляется в конец стандартного списка (в худшем случае O(N), когда требуется реаллокация списка)
    - сложность dequeue() = O(N) - так как элемент удаляется из головы стандартного списка, нужно переместить все последующие элементы на 1 позицию

3.* Напишите функцию, которая "вращает" очередь по кругу на N элементов.
"""
def rotate(queue:Queue):
    queue.enqueue(queue.dequeue())

"""
4.* Реализуйте очередь с помощью двух стеков.
"""
class QueueOnTwoStacks:
    def __init__(self):
        self.stack_main = Stack()
        self.stack_add = Stack()

    def enqueue(self, item):
        self.stack_main.push(item)

    def dequeue(self):
        result = None
        if self.size() > 0:
            while not self.stack_main.peek() is None:
                self.stack_add.push(self.stack_main.pop())
            result = self.stack_add.pop()
            while not self.stack_add.peek() is None:
                self.stack_main.push(self.stack_add.pop())
        return result

    def size(self):
        return self.stack_main.size()

"""
5.* Добавьте функцию, которая обращает все элементы в очереди в обратном порядке.
"""
def reverse(queue:Queue):
    stack = Stack()
    while queue.size() > 0:
        stack.push(queue.dequeue())
    while stack.size() > 0:
        queue.enqueue(stack.pop())
"""
6.* Реализуйте круговую (циклическую буферную) очередь статическим массивом фиксированного размера. 
Добавьте ей метод проверки, полна ли она (при этом добавление новых элементов невозможно).
Обеспечьте эффективное управление указателями начала и конца очереди в рамках массива, 
чтобы избежать неоправданных сдвигов данных.
"""
class BufferedQueue:
    def __init__(self):
        self.BUFFER_SIZE = 4
        self.storage = array.array('i', [0] * self.BUFFER_SIZE)
        self.count = 0
        self.head = -1
        self.tail = -1

    def is_full(self) -> bool:
        return self.count == self.BUFFER_SIZE

    def enqueue(self, item: int):
        if not self.is_full():
            self.tail = (self.tail + 1) % self.BUFFER_SIZE
            self.storage[self.tail] = item
            self.count +=1

    def dequeue(self):
        if self.count > 0:
            self.head = (self.head + 1) % self.BUFFER_SIZE
            self.count -=1
            return self.storage[self.head]
        return None

    def to_list(self):
        print_list = []
        for i in range (0, self.count):
            print_list.append(self.storage[(i+self.head+1)% self.BUFFER_SIZE])
        return print_list

"""
Рефлексия по решению задач задания 3.

Рекомендации:
5. Динамический массив на основе банковского метода.
При добавлении элемента например амортизационные 3 (1 реальные расходы + 2 кладем в банк).
При удалении амортизационные 2 (1 + 1).
При реаллокации (добавляем N элементов) - из банка списывается N.
Когда надо выполнять реаллокацию, вопрос неоднозначный, можно по некоторому порогу в банке, 
но лучше когда внутренний массив весь заполнен.
При уменьшении в реаллокации, в принципе, делать ничего не надо, или например 10% * N списать.

- В целом сделала похоже на рекомендацию, но при удалении я списываю 1 (а не 2, как рекомендовано) 
и при resize на более меньший размер - баланс "банка" увеличивается.

6. Многомерный динамический массив.
Идея, что у нас есть интерфейс добавления элемента в некоторую многомерную позицию (i,j,k,...), 
а внутри это обычный одномерный динамический массив.
Надо просто корректно реализовать операцию добавления элемента (отображение многомерной координаты в линию).
Например входной размер массива 3x4x5, создаём одномерный массив длиной 60, 
и многомерная координата внутри него элементарно определяется.

- Ну да, я перемудрила со списками список, поэтому не смогла довести до конца, все оказалось гораздо проще. Исправляюсь:
"""
class DynArrayMultiDimensional:

    def __init__(self, dim, *cap):
        self.capacity = cap
        self.total_capacity = reduce(lambda x, y: x * y, cap)
        self.dim = dim
        self.array = self.make_array(self.capacity)
        self.count = 0

    def __len__(self):
        return self.count

    def make_array(self, cap):
        self.total_capacity = reduce(lambda x, y: x * y, cap)
        return (self.total_capacity * ctypes.py_object)()

    def __getitem__(self, indices):
        j = 0
        for i in indices:
            if i < 0 or i >= self.capacity[j]:
                raise IndexError('Index is out of bounds')
            j += 1
        return self.array[self.calc_index(indices)]


    def calc_index(self, indices):
        res_index = indices[0]
        dim_cap = self.capacity[0]
        for n in range(1, len(indices)):
            res_index += dim_cap * indices[n]
            dim_cap *= self.capacity[n]
        return res_index

    def calc_pos(self, index, cap):
        pos = []
        current_dim = len(cap) - 1
        dim_cap = reduce(lambda x, y: x * y, cap)
        while current_dim >= 0:
            dim_cap /= cap[current_dim]
            pos.append(int(index//dim_cap))
            index -=dim_cap * pos[len(pos) - 1]
            current_dim -=1
        pos.reverse()
        return tuple(pos)

    def resize(self, new_capacities):
        old_arr = self.array
        old_cap = self.capacity
        self.array = self.make_array(new_capacities)
        self.capacity = new_capacities
        length = len(old_arr)
        for i in range(length):
            if old_arr[i] is not None:
                pos = self.calc_pos(i, old_cap)
                new_index = self.calc_index(pos)
                print(str(old_arr[i]) + ':' + str(pos) + ' -> ' + str(new_index))
                self.array[new_index] = old_arr[i]

    def append(self, itm):
        if self.count == self.total_capacity:
            self.resize(tuple(map(lambda x: x * 2, self.capacity)))
        self.array[self.count] = itm
        self.count += 1

    def insert(self, *indices, itm):
        for i in indices:
            if i < 0 or i > self.count:
                raise IndexError('Index is out of bounds')
        if self.count == self.capacity:
            self.resize(tuple(map(lambda x: x * 2, self.capacity)))
        self.count += 1
        i = self.calc_index(indices)
        for j in range(self.count - 1, i, -1):
            self.array[j] = self.array[j-1]
        self.array[i] = itm

    def delete(self, *indices):
        for i in indices:
            if i < 0 or i > self.count:
                raise IndexError('Index is out of bounds')
        i = self.calc_index(indices)
        for j in range(i, self.count - 1):
            self.array[j] = self.array[j+1]
        self.count -= 1
        if self.count < reduce(lambda x, y: x * y, self.capacity)/2:
            new_cap = tuple(map(lambda x: max(1, int(x // 1.5)), self.capacity))
            self.resize(new_cap)
