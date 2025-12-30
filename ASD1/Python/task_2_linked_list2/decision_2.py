from task_1_linked_list.test_decision import to_list
from task_2_linked_list2.decision import LinkedList2, Node

class LinkedList2ext(LinkedList2):
    def __init__(self):
        super().__init__()

    """2.10.* Добавьте метод, который "переворачивает" порядок элементов в связном списке,
    меняя его на противоположный.
    """
    def reverse(self):
        if self.head is not None and self.tail is not None:
            node = self.head
            while node is not None:
                node_next = node.next
                node.next = node.prev
                node.prev = node_next
                node = node_next
            head = self.head
            self.head = self.tail
            self.tail = head
    """
    Сложность временная O(N), пространственная O(1) - дополнительная память не требуется
    Более оптимальный вариант найти не получится, 
    так как в любом случае придется обойти каждый элемент массива, чтобы поменять указатели
    """

    """
    2.11.* Добавьте булев метод, который сообщает, имеются ли циклы (замкнутые на себя по кругу) внутри списка.
    """
    def is_cycled(self):
        node_list = []
        node = self.head
        while node is not None:
            if node in node_list:
                return True
            node_list.append(node)
            node = node.next
        return False

    """
        Сложность временная O(N^2), пространственная O(N)
        Сложность квадратичная, потому что внутри цикла еще дополнительно производится поиск во временном списке.
        Используется временный список для хранения уже пройденных элементов, также можно не использовать дополнительную память,
        а повторно обходить во внутреннем цикле список до текущего элемента. Второй способ мне нравится меньше, так как 
        он чуть хуже читается и легче ошибиться, внося изменения в код
        """

    """
    2.12.* Добавьте метод, сортирующий список.
    """
    def sort(self):
        if not self.head or not self.head.next:
            return

        swapped = True
        end = None

        while swapped:
            swapped = False
            current = self.head

            while current.next != end:
                if current.value > current.next.value:
                    current.value, current.next.value = current.next.value, current.value
                    swapped = True
                current = current.next
            end = current

    """
    Выполнена сортировка пузырьком, времення сложность O(N^2), пространственная - O(1).
    Не самый эффективный алгоритм сортировки, но зато простой в реализации
    """

"""
2.13.* Добавьте метод, объединяющий два списка в третий. 
Эти списки предварительно надо отсортировать, и выдать результирующий список, 
в котором все элементы также будут упорядочены (для результирующего списка использовать метод сортировки не разрешается).
"""
def merge(ls1: LinkedList2ext, ls2:LinkedList2ext):
    ls1.sort()
    ls2.sort()
    if ls1 is None or ls1.head is None:
        return ls2
    if ls2 is None or ls2.head is None:
        return ls1
    node1 = ls1.head
    node2 = ls2.head

    result_ls = LinkedList2()

    while node1 is not None or node2 is not None:
        if node1 is None:
            result_ls.add_in_tail(Node(node2.value))
            node2 = node2.next
            continue
        if node2 is None:
            result_ls.add_in_tail(Node(node1.value))
            node2 = node1.next
            continue

        if node1.value < node2.value:
            result_ls.add_in_tail(Node(node1.value))
            node1 = node1.next
        else:
            result_ls.add_in_tail(Node(node2.value))
            node2 = node2.next

    return result_ls

"""
    Временная сложность - O(N^2) - так как исходные списки сортируются, если они уже отсротированы - то O(N)
    Пространственная - O(N) - создается новый двусвязный список
"""
"""
2.14.* Существует интересный финт, обсуждаемый на курсе Стэнфордского университета CS106B -- фиктивный/пустой (dummy) узел. 
Пока нам при любых модификациях списка (добавление/удаление узла) приходится рассматривать три отдельные ситуации: 
работа с серединой списка, с его головой и с его хвостом. 
Фиктивный узел позволяет избавиться от этих краевых ситуаций, 
оставив только по одной универсальной операции на добавление и удаление. 
Идея в том, что мы добавляем в список два искусственных узла -- голову и хвост, 
которые пользователю класса не видны (они отличаются от видимых узлов, например, булевым флажком, 
а лучше всего это делать через наследование и перегрузку). 
Теперь, добавляя или удаляя узлы, мы всегда будем уверены, что у каждого из них имеется и предыдущий узел, 
и последующий, и от дополнительных проверок и модификаций полей head и tail можно избавиться."""


class DataNode:
    def __init__(self, data=None):
        self.data = data
        self.prev = None
        self.next = None

    def is_dummy(self):
        return False

class DummyNode(DataNode):
    def __init__(self):
        super().__init__(None)

    def is_dummy(self):
        return True

class LinkedList2d:
    def __init__(self):
        self.head = DummyNode()
        self.tail = DummyNode()
        self.head.next = self.tail
        self.tail.prev = self.head

    def add_in_tail(self, item):
        item.next = self.tail
        item.prev = self.tail.prev
        self.tail.prev = item


    def find(self, val):
        node = self.head.next
        while not node.is_dummy:
            if node.value == val:
                return node
            node = node.next
        return None

    def find_all(self, val):
        result = []
        node = self.head.next
        while not node.is_dummy:
            if node.value == val:
                result.append(node)
            node = node.next
        return result

    def delete(self, val, all=False):
        node = self.head.next
        while not node.is_dummy:
            if node.value == val:
                node.prev.next = node.next
                node.next.prev = node.prev
                if not all:
                    return None
            node = node.next
        return None

    def clean(self):
        self.head.next = self.tail
        self.tail.prev = self.head

    def len(self):
        node = self.head.next
        size = 0
        while not node.is_dummy:
            node = node.next
            size += 1
        return size

    def insert(self, afterNode, newNode):
        if newNode is None or afterNode == self.tail:
            return None
        if afterNode is None:
            if self.len() == 0:
                self.head.next = newNode
                newNode.prev = self.head
                self.tail.prev = newNode
                newNode.next = self.tail
            else:
                self.add_in_tail(newNode)
            return None
        after_node_next = afterNode.next
        afterNode.next = newNode
        newNode.next = after_node_next
        newNode.next.prev = newNode
        newNode.prev = afterNode
        return None


    def add_in_head(self, newNode):
        self.head.next.prev = newNode
        newNode.next = self.head.next
        self.head.next = newNode
        newNode.prev = self.head


