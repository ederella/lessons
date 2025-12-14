import math
from abc import ABC
from dataclasses import dataclass

class CleanerApiState(ABC):
    def transfer_to_cleaner(self, message):
        pass

class CleanerApi(ABC):
    # режимы работы устройства очистки
    WATER = 1 # полив водой
    SOAP  = 2 # полив мыльной пеной
    BRUSH = 3 # чистка щётками

    def move(self, state:CleanerApiState, dist) -> CleanerApiState:
        pass

    def turn(self, state:CleanerApiState, turn_angle)-> CleanerApiState:
        pass

    def set_state(self, state:CleanerApiState, new_state)-> CleanerApiState:
        pass

    def stop(self, state:CleanerApiState)-> CleanerApiState:
        pass

    def start(self, state:CleanerApiState)-> CleanerApiState:
        pass

    def make(self, state:CleanerApiState, code):
        pass

# класс Чистильщик
class Cleaner(CleanerApi):

    @dataclass
    class RobotState(CleanerApiState):
        x: float
        y: float
        angle: float
        state: int

        def transfer_to_cleaner(self, message):
            print(message)

    def get_start_state(self):
        return self.RobotState(0.0,0.0,0, self.WATER)

    # перемещение
    def move(self, state:RobotState, dist):
        angle_rads = state.angle * (math.pi / 180.0)
        new_state = self.RobotState(
            state.x + dist * math.cos(angle_rads),
            state.y + dist * math.sin(angle_rads),
            state.angle,
            state.state)
        new_state.transfer_to_cleaner(('POS(', new_state.x, ',', new_state.y, ')'))
        return new_state


    # поворот
    def turn(self, state:RobotState,turn_angle):
        new_state = self.RobotState(
            state.x,
            state.y,
            state.angle + turn_angle,
            state.state)
        new_state.transfer_to_cleaner(('ANGLE', state.angle))
        return new_state

    # установка режима работы
    def set_state(self, state:RobotState,new_internal_state):
        if new_internal_state == 'water':
            self_state = self.WATER
        elif new_internal_state == 'soap':
            self_state = self.SOAP
        elif new_internal_state == 'brush':
            self_state = self.BRUSH
        else:
            return state
        new_state = self.RobotState(
            state.x,
            state.y,
            state.angle,
            self_state)
        new_state.transfer_to_cleaner(('STATE', self_state))
        return new_state

    # начало чистки
    def start(self, state:RobotState):
        state.transfer_to_cleaner(('START WITH', state.state))
        return state

    # конец чистки
    def stop(self, state:RobotState):
        state.transfer_to_cleaner(('STOP',))
        return state

    # интерпретация набора команд
    def make(self, state:RobotState, code):
        for command in code:
            cmd = command.split(' ')
            if cmd[0]=='move':
                self.move(state, int(cmd[1]))
            elif cmd[0]=='turn':
                self.turn(state,int(cmd[1]))
            elif cmd[0]=='set':
                self.set_state(state,cmd[1])
            elif cmd[0]=='start':
                self.start(state)
            elif cmd[0]=='stop':
                self.stop(state)


if __name__ == '__main__':
    cleaner = Cleaner()
    cleaner.make(cleaner.get_start_state(), (
        'move 100',
        'turn -90',
        'set soap',
        'start',
        'move 50',
        'stop'
    ))
