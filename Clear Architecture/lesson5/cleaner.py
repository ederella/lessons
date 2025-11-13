import math

class Cleaner:

    # режимы работы устройства очистки
    WATER = 1 # полив водой
    SOAP  = 2 # полив мыльной пеной
    BRUSH = 3 # чистка щётками

    # конструктор
    def __init__(self, transfer):
        self.x = 0.0
        self.y = 0.0
        self.angle = 0
        self.state = self.WATER
        self.transfer = transfer

    # перемещение
    def move(self,dist):
        angle_rads = self.angle * (math.pi/180.0)
        self.x += dist * math.cos(angle_rads)
        self.y += dist * math.sin(angle_rads)
        self.transfer(('POS(',self.x,',',self.y,')'))

    # поворот
    def turn(self,turn_angle):
        self.angle += turn_angle
        self.transfer(('ANGLE',self.angle))

    # установка режима работы
    def set_state(self,new_state):
        if new_state=='water':
            self.state = self.WATER
        elif new_state=='soap':
            self.state = self.SOAP
        elif new_state=='brush':
            self.state = self.BRUSH
        self.transfer(('STATE',self.state))

    # начало чистки
    def start(self):
        self.transfer(('START WITH',self.state))

    # конец чистки
    def stop(self):
        self.transfer(('STOP',))

    # интерпретация набора команд
    def make(self,code):
        for command in code:
            cmd = command.split(' ')
            if cmd[0]=='move':
                self.move(int(cmd[1]))
            elif cmd[0]=='turn':
                self.turn(int(cmd[1]))
            elif cmd[0]=='set':
                self.set_state(cmd[1])
            elif cmd[0]=='start':
                self.start()
            elif cmd[0]=='stop':
                self.stop()
