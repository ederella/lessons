import math

# входная программа управления роботом
code = (
    'move 100',
    'turn -90',
    'set soap',
    'start',
    'move 50',
    'stop'
)

# режимы работы устройства очистки
WATER = 1 # полив водой
SOAP  = 2 # полив мыльной пеной
BRUSH = 3 # чистка метлой

# текущий режим работы устройства очистки
state = WATER
# текущие позиция и угол (ориентация) робота
x = 0.0
y = 0.0
angle = 0

def move(distance: int):
    angle_rads = angle * (math.pi / 180.0)
    x_new = x + distance * math.cos(angle_rads)
    y_new = y + distance * math.sin(angle_rads)
    print('POS(', x_new, ',', y_new, ')')
    return x_new, y_new


def turn(angle_dif: int):
    angle_new = angle + angle_dif
    print('ANGLE', angle_new)
    return angle_new

def set_regime(regime: str):
    state_new = ''
    if cmd[1] == 'water':
        state_new = WATER
    elif cmd[1] == 'soap':
         state_new = SOAP
    elif cmd[1] == 'brush':
        state_new = BRUSH
    print('STATE', state_new)
    return state_new

# главная программа
for command in code:
    cmd = command.split(' ')

    if cmd[0]=='move':
        x, y = move(int(cmd[1]))

    elif cmd[0]=='turn':
        angle = turn(int(cmd[1]))

    elif cmd[0]=='set':
        state = set_regime(cmd[1])

    elif cmd[0]=='start':
        print ('START WITH',state)

    elif cmd[0]=='stop':
        print ('STOP')


