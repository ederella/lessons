class BloomFilter:

    def __init__(self, f_len):
        self.filter_len = f_len
        self.bitarr = 0b0

    def hash1(self, str1):
        res = 0
        for c in str1:
            res = res * 17 + ord(c)
        return res% self.filter_len

    def hash2(self, str1):
        res = 0
        for c in str1:
            res = res * 223 + ord(c)
        return res% self.filter_len

    def add(self, str1):
        self.bitarr = self.bitarr|self.hash1(str1)|self.hash2(str1)


    def is_value(self, str1):
        if self.bitarr & self.hash1(str1) == 0:
            return False
        if self.bitarr & self.hash2(str1) == 0:
            return False
        return True

    
