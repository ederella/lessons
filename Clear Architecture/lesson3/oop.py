import math

WATER = 1  # полив водой
SOAP = 2  # полив мыльной пеной
BRUSH = 3  # чистка щётками

class Robot:
    def __init__(self) :
        self.__pos = (0,0)
        self.__angle = 0
        self.__state = WATER
        self.__is_working = False

    def move(self, path: int) :
        (x, y) = self.__pos
        x += path * math.sin(self.__angle)
        y += path * math.cos(self.__angle)
        self.__pos = (x, y)
        print('POS ' + str(self.__pos))

    def turn(self, angle) :
        self.__angle = angle * (math.pi/180.0)
        print('ANGLE ' + str(angle))

    def set(self, state):
        if state == 'water':
            self.__state = WATER
        elif state == 'soap':
            self.__state = SOAP
        elif state == 'brush':
            self.__state = BRUSH
        print('STATE ' + str(self.__state))

    def start(self) :
        self.__is_working = True
        print('START WITH ' + str(self.__state))

    def stop(self) :
        self.__is_working = False
        print('STOP')

robot = Robot()
command = ('','')

code = (
    'move 100',
    'turn -90',
    'set soap',
    'start',
    'move 50',
    'stop'
)
for command in code:
    command = command.split(' ')
    if command[0] == 'move':
        robot.move(int(command[1]))
    if command[0] == 'turn':
        robot.turn(int(command[1]))
    if command[0] == 'set':
        robot.set(str(command[1]))
    if command[0] == 'start':
        robot.start()
    if command[0] == 'stop':
        robot.stop()
