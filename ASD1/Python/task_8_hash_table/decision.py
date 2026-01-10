class HashTable:
    def __init__(self, sz, stp):
        self.size = sz
        self.step = stp
        self.slots = [None] * self.size

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
        free_slot = self.seek_slot(value)
        if free_slot is not None:
            self.slots[free_slot] = value
        return free_slot

    def find(self, value):
        start_idx: int = self.hash_fun(value)
        idx = start_idx
        for i in range(self.size):
            if self.slots[idx] == value:
                return idx
            idx = (idx + self.step) % self.size
            if idx == start_idx:
                break
        return None

