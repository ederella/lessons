"""
6. Переделайте функцию поиска элемента по значению с учётом признака упорядоченности и возможности раннего прерывания поиска,
если найден заведомо больший или меньший элемент, нежели искомый. Оцените сложность операции поиска, изменилась ли она?

Ответ: сложность операции поиска с полным перебором элементов: O(N), поиск с ранним прерыванием имеет в лучшем случае сложность O(1), в худшем - O(N).
Таким образом, да сложность уменьшилась. А если использовать бинарный поиск, а не поиск перебором, то сложность должна быть O(logN)

8.* Добавьте метод удаления всех дубликатов из упорядоченного списка.
"""
from task_7_ordered_list.decision import OrderedList

class OrderedListExt1(OrderedList):
    def __init__(self, asc):
        super(OrderedListExt1, self).__init__(asc)

    def remove_duplicates(self):
        node = self.head
        if node is None:
            return
        while node.next is not None:
            node = node.next
            if node.prev.value == node.value:
                node.prev.next = node.next
                if node.next is not None:
                    node.next.prev = node.prev
                else:
                    self.tail = node.prev
"""
9.* Напишите алгоритм слияния двух упорядоченных списков в один, сохраняя порядок элементов. 
Подумайте, как это сделать наиболее эффективно.
Если оба списка отсортированы в одном направлении:
1) Если список 1 пуст - возвращаем список 2 (копию), если список 2 пуст - возвращаем список 1 (копию)
2) Проверяем, если tail одного из списков меньше или равен head второго - в результирующий список просто копируем последовательно меньший список и затем больший 
(или если исходные списки можно менять - tail меньшего связываем с head большего)
3) Итерируемся через next по обоим спискам: если элемент списка 1 < элемента списка 2 - в результирующий список добавляем элемент из списка 1, 
в списке 1 - переходим к следующему элементу, иначе -  в результирующий список добавляем элемент из списка 2, в списке 2 - переходим к следующему элементу
4) Если один из списков не закончился - добавляем его целиком в хвост результирующего списка
"""

"""
10.* Напишите метод проверки наличия заданного упорядоченного под-списка (параметр метода) в текущем списке.
"""
class OrderedListExt2(OrderedList):
    def __init__(self, asc):
        super(OrderedListExt2, self).__init__(asc)
    def exists_sublist(self, sublist:OrderedList) -> bool:
        if sublist.head is None:
            return True
        if self.head is None:
            return False
        sublist_node = sublist.head
        if sublist_node.value < self.head.value:
            return False
        node = self.find(sublist_node.value)
        while node is not None and sublist_node is not None:
            if node.value != sublist_node.value:
                return False
            node = node.next
            sublist_node = sublist_node.next
        return sublist_node is None
"""
11.* Добавьте метод, который находит наиболее часто встречающееся значение в списке.
"""
class OrderedListExt3(OrderedList):
    def __init__(self, asc):
        super(OrderedListExt3, self).__init__(asc)
    def most_frequent(self):
        node = self.head
        current_count = 1
        max_count = 1
        max_count_value = self.head.value if self.head is not None else None
        while node is not None:
            if node.next is not None and node.value == node.next.value:
                current_count +=1
            else:
                if max_count < current_count:
                    max_count = current_count
                    max_count_value = node.value
                current_count = 1
            node = node.next
        return max_count_value

"""
12.* Добавьте в упорядоченный список возможность найти индекс элемента (параметр) в списке, которая должна работать за o(log N).
"""
class OrderedListExt4(OrderedList):
    def __init__(self, asc):
        super(OrderedListExt4, self).__init__(asc)

    def get_node(self, start_node, pos:int):
        if pos > self.len() -1 or pos < 0:
            raise IndexError('Index out of bound exception')
        node = start_node
        for i in range(pos):
            node = node.next
        return node

    def get_index(self, val):
        start = 0
        end = self.len() - 1
        if self.head.value == val:
            return start
        if self.tail.value == val:
            return end
        start_node = self.head
        while start <= end:
            ind = int((start + end)//2)
            node = self.get_node(start_node, ind - start)
            if node.value == val:
                return ind
            elif node.value < val:
                start = ind
                start_node = node
            else:
                end = ind
        return -1

"""
Рефлексия по решению задач задания 5.

3. Вращение очереди по кругу на N элементов.
- Выполнено верно

4. Очередь с помощью двух стеков.
Идея, что кладём всегда в один стек, больше ничего не делая, за O(1),
а забираем всегда из другого, причём перегоняем в него всё из первого стека, 
только если данный стек пуст (асимптотика получается близкой к o(1)) .
 - Метод enqueue - верно, 
 dequeue - не очень удачная реализация, сложность O(N) - исправляюсь:
 """
def dequeue_o_n_1(self):
    if self.size() == 0:
        return None

    if len(self.stack_add.stack) == 0:
        while not self.stack_main.peek() is None:
            self.stack_add.push(self.stack_main.pop())
    return self.stack_add.pop()

"""5. Обращение всех элементов в очереди в обратном порядке.
- Выполнено верно


6. Циклическая буферную очередь на базе статического массива фиксированного размера..
- В моей реализации - tail - указывает на последний элемент, изначально оба указателя на -1.
Заполненность очереди определяется по счетчику - когда он равен размеру очереди - она заполнена.
Добавление и удаление элемента -  целом совпадает с рекомендацией.
"""

