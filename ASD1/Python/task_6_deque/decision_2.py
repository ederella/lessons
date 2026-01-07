import ctypes
import operator
from task_4_stack.decision import Stack
from task_6_deque.decision import Deque

"""
7.1. Почему и как будет различаться мера сложности для addHead/removeHead и addTail/removeTail?
Ответ:
    сложность addHead O(n)
    сложность removeHead O(n)
    сложность addTail O(1)
    сложность removeTail O(1)

    addHead/removeHead выполняют сдвиг всего списка поэлементно, начиная с элемента в позиции 1, так как двусторонняя очередь 
    реализована на динамическом массиве
    addTail/removeTail - работают только с последним элементом, при удалении - фактически можно даже не удалять его, а просто
    переместить указатель на новое окончание списка
"""

"""
7.3.* Напишите функцию, которая с помощью deque проверяет, является ли некоторая строка палиндромом
(читается одинаково слева направо и справа налево).
"""
def is_palindrome(in_str: str) -> bool:
    deque = Deque()
    for el in in_str:
        deque.addTail(el)

    while deque.size() > 1:
        if deque.removeFront() != deque.removeTail():
            return False
    return True

"""
7.4.* Напишите метод, который возвращает минимальный элемент деки за O(1).
"""
class DequeWithMin:
    def __init__(self):
        self.deque = []
        self.mins = []

    def min(self):
        if len(self.mins) > 0:
            return self.mins[-1]
        return None

    def addFront(self, item):
        if len(self.mins) == 0 or self.mins[-1] >= item:
            self.mins.append(item)
        self.deque.insert(0, item)

    def addTail(self, item):
        if len(self.mins) == 0 or self.mins[-1] >= item:
            self.mins.append(item)
        self.deque.append(item)

    def removeFront(self):
        if self.size() > 0:
            if self.mins[-1] == self.deque[0]:
                self.mins.pop()
                return self.deque.pop(0)
        return None

    def removeTail(self):
        if self.size() > 0:
            if self.mins[-1] == self.deque[-1]:
                self.mins.pop()
                return self.deque.pop()
        return None

    def size(self):
        return len(self.deque)


"""
7.5.* Реализуйте двустороннюю очередь с помощью динамического массива.
Методы добавления и удаления элементов с обоих концов деки должны работать за амортизированное время o(1).
"""

class DequeOnDynArray:
    def __init__(self):
        self.capacity = 16
        self.dyn_array = self.make_array(self.capacity)
        self.head: int = int(self.capacity//2) - 1
        self.tail: int  = self.head

    def make_array(self, new_capacity):
        arr= (new_capacity * ctypes.py_object)()
        for i in range(new_capacity -1):
            arr[i] = None
        return arr

    def addFront(self, item):
        if self.head == 0:
            self.resize(self.capacity * 2)
        self.dyn_array[self.head] = item
        self.head -= 1

    def addTail(self, item):
        if self.tail == self.capacity - 1:
            self.resize(self.capacity * 2)
        self.tail += 1
        self.dyn_array[self.tail] = item

    def removeFront(self):
        if self.tail == self.head:
            return  None
        self.head += 1
        res = self.dyn_array[self.head]
        self.dyn_array[self.head] = None
        if self.size() < self.capacity/2:
            new_capacity = max(int(self.capacity // 1.5), 16)
            self.resize(new_capacity)
        return res

    def removeTail(self):
        if self.tail == self.head:
            return  None
        res = self.dyn_array[self.tail]
        self.dyn_array[self.tail] = None
        self.tail -= 1
        if self.size() < self.capacity/2:
            new_capacity = max(int(self.capacity // 1.5), 16)
            self.resize(new_capacity)
        return res

    def size(self):
        return self.tail - self.head

    def resize(self, new_capacity):
        if new_capacity == self.capacity:
            return
        self.capacity = new_capacity
        curr_head = self.head
        curr_size = self.size()
        curr_array = self.dyn_array

        self.dyn_array = self.make_array(self.capacity)
        self.head: int = int(self.capacity // 2) - int(curr_size//2) - curr_size%2
        self.tail: int = int(self.capacity // 2) + int(curr_size//2)

        for i in range(1, curr_size + 1):
            self.dyn_array[self.head + i] = curr_array[curr_head + i]

    def to_list(self):
        ls = []
        for i in range(self.head + 1, self.tail + 1):
            ls.append(self.dyn_array[i])
        return ls


"""
7.6.* Напишите автономную функцию, которая проверяет правильность расстановки (баланс) скобок в символьном
(например, арифметическом) выражении. Оно подаётся на вход функции как строка, например "(())}{(" или "[]({})". 
Алгоритм должен обрабатывать и учитывать круглые (), квадратные [] и фигурные {} скобки. 
Строка всегда корректна: содержит только скобки указанных типов. Алгоритм должен работать за O(N), где N -- длина выражения.
Внутри этой функции используйте стек.
"""
def is_closed_bracket_ok(el, current_el):
    if el == ')':
        return current_el == '('
    if el == '}':
        return current_el == '{'
    if el == ']':
        return current_el == '['
    return False

def is_balanced(in_str:str)->bool:
    deque = Deque()
    for el in in_str:
        if el in '({[':
            deque.addTail(el)
            continue
        current_el = deque.removeTail()
        if current_el is None :
            return False
        if not is_closed_bracket_ok(el, current_el):
            return False
    return deque.size() == 0

"""
Рефлексия по решению задач задания 4.

5. Баланс открывающих и закрывающих скобок трёх типов.
Рекомендации:
    Для каждого символа в строке:
    - Если символ — открывающая скобка любого типа, добавьте её в стек.
    - Если символ — закрывающая скобка:
    ---- Проверьте, пуст ли стек. Если пуст — баланс нарушен (закрывающая скобка без пары).
    ---- Извлеките последнюю открывающую скобку из стека.
    ---- Сравните скобки. Если типы не совпадают - например, ( и ] - баланс нарушен.
    После обработки всех символов:
    - Если стек не пуст — есть незакрытые скобки (баланс нарушен).
    - Если стек пуст — все скобки сбалансированы.
    Если типов скобок много - например 100, для удобства проверки совпадения типов можно использовать словарь 
    (ключ - открывающая скобка , значение - закрывающая скобка).
Рефлексия:
    В моем решении я не выхожу из цикла проверки, если текущий символ закрывающая скобка и стек пуст, и если типы скобок не совпадают.
    Поэтому мое решение потребует больше времени для обработки. В остальном - все верно.
     
6. Текущий минимальный элемент в стеке за O(1).
Рекомендации:
    Используем два стека: основной и хранение минимумов.
    Когда добавляем элемент в основной стек:
    если стек минимумов пуст или новый элемент меньше или равен текущему минимуму (вершина стека минимумов), то добавляем этот элемент и в стек минимумов.
    Когда удаляем элемент из основного стека:
    если удалённый элемент равен вершине стека минимумов (меньше вершины он не может быть по логике алгоритма), удалите вершину и из стека минимумов.
Рефлексия:
    Мое решение выполнено в соответствии с рекомендациями
    
7. Среднее значение всех элементов в стеке за O(1).
    Добавляем в класс приватную переменную, которая будет хранить текущую сумму всех элементов в стеке. 
    При добавлении элемента в стек прибавляем его к этой сумме, а при удалении вычитаем.
    Среднее значение можно получить в любой момент, разделив эту сумму на текущее количество элементов в стеке.
Рефлексия:
    Мое решение выполнено в соответствии с рекомендациями
    
8. Постфиксная запись выражения.
Рекомендации:
    Нередкая ошибка: записывать оба аргумента в одной строке, наподобие
    a.pop() + b.pop()
    Если вы добавите вычитание и деление, код может получиться таким:
    a.pop() - b.pop()
    Тут уже возможна потенциальная ошибка: вы полагаетесь на конкретный порядок вычисления выражения, что в общем случае рискованно.
    Возможно, в выражении сперва будет вычислен не левый pop(), а правый pop() (или наоборот, или в случайном порядке каждый раз из-за оптимизации какой-нибудь). 
    Например, у вас в стеке (3, 5) , и может получиться 5-3 или 3-5 в зависимости от порядка, и соответственно разные результаты.
    Копипастить вычисление аргументов для каждой операции тоже плохо, ведь в общем случае операции может быть очень много:
    
    a = pop()
    b = pop()
    a + b
    ...
    a = pop()
    b = pop()
    a - b
    Лучшая схема: и сами операции вынести в декларативный вид, например, используя лямбда-функции. 
    С помощью словаря, где ключ - операция, а значение - соответствующая функция.
Рефлексия:
    Да, я как раз допустила вышеупомянутую ошибку. Исправляюсь:
"""
def to_stack(in_str:str) -> Stack:
    stack1 = Stack()
    for el in in_str[::-1]:
        if str.isnumeric(el):
            stack1.push(int(el))
        elif el in '*+=':
            stack1.push(el)
    return stack1

def apply(value, a, b):
    if value == '*':
        return operator.mul(a,b)
    if value == '+':
        return operator.add(a,b)
    raise Exception('Operand is not defined')


def postfix_calc(in_str: str) -> int:
    stack1 = to_stack(in_str)
    stack2 = Stack()

    while stack1.peek() != '=':
        value = stack1.pop()
        if str(value) in '*+':
            a = stack2.pop()
            b = stack2.pop()
            stack2.push(apply(value, a, b))
        else:
            stack2.push(value)
    return stack2.pop()

print(postfix_calc('8 2 + 5 * 9 + ='))
