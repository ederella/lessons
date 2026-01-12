class NativeDictionary:
    def __init__(self, sz):
        self.size = sz
        self.slots = [None] * self.size
        self.values = [None] * self.size

    def hash_fun(self, key):
        return len(key.encode()) % self.size

    def is_key(self, key):
       return key in self.slots

    def put(self, key, value):
        slot_idx = self.hash_fun(key)
        while self.slots[slot_idx] is not None and self.slots[slot_idx] != key:
            slot_idx = (slot_idx + 1) % self.size
        self.slots[slot_idx] = key
        self.values[slot_idx] = value

    def get(self, key):
        slot_idx = self.hash_fun(key)
        if self.slots[slot_idx] == key:
            return self.values[slot_idx]
        i = (slot_idx + 1)%self.size
        while i != slot_idx:
            if self.slots[i] == key:
                return self.values[i]
            i = (i + 1) % self.size
        return None
