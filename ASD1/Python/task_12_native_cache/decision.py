class NativeCache:
    def __init__(self, sz):
        self.size = sz
        self.slots = [None] * self.size
        self.values = [None] * self.size
        self.hits = [0] * self.size
        self.count = 0

    def hash_fun(self, key):
        return len(key.encode()) % self.size

    def is_key(self, key):
        return key in self.slots

    def put(self, key, value):
        slot_idx = self.hash_fun(key)
        if self.count == self.size:
            self.replace_element_with_min_hits(slot_idx, key, value)
            return
        while self.slots[slot_idx] is not None:
            slot_idx = (slot_idx + 1) % self.size
        self.slots[slot_idx] = key
        self.values[slot_idx] = value
        self.count +=1

    def get(self, key):
        slot_idx = self.hash_fun(key)
        if self.slots[slot_idx] == key:
            self.hits[slot_idx] += 1
            return self.values[slot_idx]
        i = (slot_idx + 1) % self.size
        while i != slot_idx:
            if self.slots[i] == key:
                self.hits[i] += 1
                return self.values[i]
            i = (i + 1) % self.size
        return None

    def replace_element_with_min_hits(self, i, key, value):
        if self.hits[i] != 0:
            i = min(self.hits)
        self.slots[i] = key;
        self.values[i] = value;
        self.hits[i] = 0;
