import math

class Robot:
    def __init__(self) :
        self.__pos = (0,0)
        self.__angle = 0
        self.__state = 'water'
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
        self.__state = state
        print('STATE ' + str(state))

    def start(self) :
        self.__is_working = True
        print('START WITH ' + self.__state)

    def stop(self) :
        self.__is_working = False
        print('STOP')

robot = Robot()
command = ('','')
while command[0] != 'quit':
    command = input('Введите команду: ').split(' ')
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
