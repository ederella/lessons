from task_4_stack.decision import Stack

"""
2. Переделайте реализацию стека так, чтобы она работала не с хвостом списка как с верхушкой стека, а с его головой.
"""
class StackHead:
    def __init__(self):
        self.stack = []

    def size(self):
        return len(self.stack)

    def pop(self):
        if self.size() > 0:
            return self.stack.pop(0)
        return None

    def push(self, value):
        self.stack.insert(0, value)

    def peek(self):
        if self.size() > 0:
            return self.stack[0]
        return None
"""
3. Не запуская программу, скажите, как отработает такой цикл?
while stack.size() > 0:
    print(stack.pop())
    print(stack.pop())
    
Ответ: 
Если стек пуст - напечатается дважды None,
если количество записей нечетное - напечатается содержимое стека и None в конце,
если количество записей четное - напечатается содержимое стека.
В результате работы цикла стек опустошится.
"""

"""
4. Оцените меру сложности для операций pop и push.

Ответ: pop - O(1) для реализации с хвостом списка, O(n) - для реализации с головой списка, так как происходит сдвиг всех элементов хвоста
push - O(1) для реализации с хвостом списка, O(n) - для реализации с головой списка, так как происходит сдвиг всех элементов хвоста

5.* Напишите функцию, которая получает на вход строку, состоящую из открывающих и закрывающих скобок 
(например, "(()((())()))" или "(()()(()") и, используя только стек и оператор цикла, определите, 
сбалансированы ли скобки в этой строке. 
Сбалансированной считается последовательность, в которой каждой открывающей обязательно соответствует закрывающая, 
а каждой закрывающей -- открывающая скобки, то есть последовательности "())(" , "))((" или "((())" будут несбалансированы.
"""
def is_balanced(in_str:str)->bool:
    stack = Stack()
    for el in in_str:
        if el == '(':
            stack.push(el)
        else:
            stack.pop()
    return stack.size() == 0


"""
6.* Расширьте функцию из предыдущего примера, если скобки могут быть трех типов: (), {}, [].
"""
def is_balanced_ext(in_str:str)->bool:
    stack = Stack()
    for el in in_str:
        if el in '({[':
            stack.push(el)
        elif (el == ')' and stack.peek() == '(') or (el== '}' and stack.peek() == '{') or (el == ']' and stack.peek() == '['):
            stack.pop()
    return stack.size() == 0

"""
7.* Добавьте в стек функцию, возвращающую текущий минимальный элемент в нём за O(1) (подсказка: используйте второй стек).
"""
class StackWithMin:
    def __init__(self):
        self.stack = []
        self.min_stack = []

    def size(self):
        return len(self.stack)

    def min(self):
        if len(self.min_stack) > 0:
            return self.min_stack[-1]
        return None

    def pop(self):
        if self.size() > 0:
            if self.min_stack[-1] == self.stack[-1]:
                self.min_stack.pop()
            return self.stack.pop()
        return None

    def push(self, value):
        if len(self.min_stack) == 0 or self.min_stack[-1] >= value:
            self.min_stack.append(value)
        self.stack.append(value)

    def peek(self):
        if self.size() > 0:
            return self.stack[-1]
        return None
"""
8.* Добавьте в стек функцию, которая возвращает среднее значение всех элементов в стеке. Она должна выполняться за O(1).
"""
class StackWithAverage:
    def __init__(self):
        self.stack = []
        self.total = 0

    def size(self):
        return len(self.stack)

    def average(self):
        if self.size() > 0:
            return self.total/self.size()
        return None

    def pop(self):
        if self.size() > 0:
            self.total -= self.stack[-1]
            return self.stack.pop()
        return None

    def push(self, value):
        self.total += value
        self.stack.append(value)

    def peek(self):
        if self.size() > 0:
            return self.stack[-1]
        return None
"""
9*. Напишите функцию, которая с помощью двух стеков реализует вычисление подобных постфиксных выражений.
Такой стек обрабатывается следующим образом: берём с верхушки объект, если это число, сохраняем во втором стеке, 
а если операция, выполняем её над двумя верхними элементами второго стека и возвращаем её обратно во второй стек.

Рассчитайте с её помощью например такое выражение:
8 2 + 5 * 9 + =
"""


def to_stack(in_str:str) -> Stack:
    stack1 = Stack()
    for el in in_str[::-1]:
        if str.isnumeric(el):
            stack1.push(int(el))
        elif el in '*+=':
            stack1.push(el)
    return stack1

def postfix_calc(in_str: str) -> int:
    stack1 = to_stack(in_str)
    stack2 = Stack()

    while stack1.peek() != '=':
        value = stack1.pop()
        if value == '*':
            stack2.push(stack2.pop() * stack2.pop())
        elif value == '+':
            stack2.push(stack2.pop() + stack2.pop())
        else:
            stack2.push(value)
    return stack2.pop()

print(postfix_calc('8 2 + 5 * 9 + ='))

"""Рефлексия по решению задач задания 2.

9. Метод, который "переворачивает" связный список.
Сделала точно так же как в рекомендации.

10. Проверка, имеются ли циклы внутри списка.
В рекомендации указано - 
цикл for по элементам до длины списка, и если конечным узлом не будет хвост, значит в списке есть цикл.
Я подумала, что раз есть цикл - это какая-то неканоничная структура списка, поэтому более надежным показалось
обходить список, записывая все узлы в список и проверять - нет ли среди уже пройденных узлов текущего узла.
Ну и длину в этой структуре данных я вычисляю динамически, а не храню в поле.

11. Сортировка списка.
Сделала точно так же как в рекомендации.

12. Слияние списков.
В целом, сделано как в рекомендации, за исключением, что я не использовала список списков, чтобы функция могла быть применена для любого количества списков,
а сделала только для двух списков.

13. Dummy.
Идея, что создаём отдельный класс Dummy (наследник основного узла), и в циклах не узлы сравниваем, а проверяем тип узла -
- он Dummy или нет.
В целом, мое решение похоже на рекомендованное -DummyNode - наследник DataNode, в циклах проверяется тип узла:
while not node.is_dummy:
    ...
"""
