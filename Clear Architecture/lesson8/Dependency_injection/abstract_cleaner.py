class AbstractCleaner:

    WATER = 1 # полив водой
    SOAP  = 2 # полив мыльной пеной
    BRUSH = 3 # чистка щётками

    def __init__(self, transfer):
        pass

    def move(self, dist):
        pass

    def turn(self, turn_angle):
        pass

    def set_state(self, new_state):
        pass

    def start(self):
        pass

    def stop(self):
        pass

    def make(self, code):
        pass
