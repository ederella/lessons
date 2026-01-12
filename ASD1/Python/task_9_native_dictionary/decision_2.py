from task_7_ordered_list.decision import Node, OrderedList

"""5.* Реализуйте словарь с использованием упорядоченного списка по ключу для оптимизации производительности поиска.
Оцените временную сложность операций вставки, удаления и поиска в таком словаре.
- Все O(N), так как везде вызывается find, в котором я использую линейный поиск, но если использовать бинарный поиск - будет O(logN)
"""
class OrderedKeyList(OrderedList):
    def __init__(self):
        super(OrderedKeyList, self).__init__(True)

    def compare(self, v1:tuple, v2:tuple) -> int:
        a :str = v1[0].strip()
        b :str = v2[0].strip()
        if a > b:
            return 1
        elif b > a :
            return -1
        else:
            return 0

    def find(self, val):
        node = self.head
        while node is not None:
            if node.value[0] > val:
                return None
            if node.value[0] == val:
                return node
            node = node.next
        return None


class NativeDictionaryOrdList:
    def __init__(self):
        self.slots = OrderedKeyList()
        self.values = []

    def is_key(self, key : str) -> bool:
         return self.slots.find(key) is not None

    def put(self, key : str, value):
        node : Node = self.slots.find(key)
        if node is None:
            self.values.append(value)
            ind = len(self.values) - 1
            self.slots.add((key, ind))
        else:
            self.values[node.value[1]] = value

    def get(self, key : str):
        node : Node = self.slots.find(key)
        if node is not None:
            return self.values[node.value[1]]
        return None

    def remove(self, key : str):
        self.slots.delete(key)

"""
6.* Создайте словарь, в котором ключи представлены битовыми строками фиксированной длины.
Реализуйте методы добавления, удаления и поиска элементов, используя битовые операции для ускорения работы.
- К сожалению, не получается придумать ничего удачного :( Постараюсь сделать через занятие, когда будут комментарии по решению.
"""
class NativeDictionaryBitKey:
    def __init__(self, sz):
        self.key_len = 3
        self.size = sz
        self.slots = [None] * self.size
        self.values = [None] * self.size

    def hash_fun(self, key):
        return int(key, 2) % self.size

    def put(self, key: bytes, value):
        if len(key) != self.key_len:
            raise Exception('Incorrect key length')
        slot_idx = self.hash_fun(key)
        while self.slots[slot_idx] is not None and self.slots[slot_idx] != key:
            slot_idx = (slot_idx + 1) % self.size
        self.slots[slot_idx] = key
        self.values[slot_idx] = value

    def get(self, key: bytes):
        index = self.get_index(key)
        if index > 0:
           return self.values[index]
        return None

    def get_index(self, key: bytes):
        slot_idx = self.hash_fun(key)
        if self.slots[slot_idx] == key:
            return slot_idx
        i = (slot_idx + 1)%self.size
        while i != slot_idx:
            if self.slots[i] == key:
                return i
            i = (i + 1) % self.size
        return -1

    def remove(self, key: bytes):
        index = self.get_index(key)
        if index > 0:
            self.slots[index] = None
            self.values[index] = None

"""Рефлексия по решению задач задания 7.

9. Слияние двух упорядоченных списков в один.
 - выполнено верно, единственное, я не рассматривала случай с N списками, но это и не требовалось

10. Проверка наличия заданного упорядоченного под-списка в текущем списке.
 1) Использовала последовательную проверку равенства элементов подсписка в заданном списке.
 2) Из оптимизаций - если голова подсписка меньше головы заданного списка - дальше не проверяем
 3) Не совсем верно поняла задачу - подумала, что подсписок должен именно непрерывно входить в заданный список. 
 Из рекомендаций выяснила, что в исследуемом участке заданного списка могут быть дополнительные элементы.
 4) Не проверяю количество элементов оставшейся части заданного списка - для этого потребовалось бы вводить 
 дополнительно счетчик количества элементов в классе OrderedList.
    
11. Ищем наиболее часто встречающееся значение в списке.
 - Выполнено в целом верно - считается за один подход, но я сбрасываю счетчик не на 0, а на 1 - так как начинаю сразу 
 считать размер интервала с текущим элементом

12. Индекс заданного элемента в списке за O(log N).
 - Использовала бинарный поиск, как и рекомендуется, 
 - Для поиска нужного элемента добавила метод, который отступает с заданной позиции на нужное количество шагов и возвращает
 находящийся там узел
 
 """
