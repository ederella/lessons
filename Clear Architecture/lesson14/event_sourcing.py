from enum import Enum
from dataclasses import dataclass
import math

# Режимы работы
class CleaningMode(Enum):
    WATER = 1
    SOAP = 2
    BRUSH = 3

# Состояние робота
@dataclass
class RobotState:
    x: float
    y: float
    angle: float
    state: int

initial_state = RobotState(0.0, 0.0, 0, CleaningMode.WATER.value)

def move(dist, state):
    angle_rads = state.angle * (math.pi / 180.0)
    new_state = RobotState(
        state.x + dist * math.cos(angle_rads),
        state.y + dist * math.sin(angle_rads),
        state.angle,
        state.state)
    return new_state

def turn(turn_angle,state):
    new_state = RobotState(
        state.x,
        state.y,
        state.angle + turn_angle,
        state.state)
    return new_state

def set_state(new_internal_state,state):
    if new_internal_state=='WATER':
        self_state = CleaningMode.WATER.value
    elif new_internal_state=='SOAP':
        self_state = CleaningMode.SOAP.value
    elif new_internal_state=='BRUSH':
        self_state = CleaningMode.BRUSH.value
    else:
        return state
    new_state = RobotState(
        state.x,
        state.y,
        state.angle,
        self_state)
    return new_state


def start(state):
    return state

def stop(state):
    return state

@dataclass
class Event:
    description: str
    state: RobotState

    def execute(self):
        pass


class MoveEvent(Event):
    def __init__(self, dist, state):
        self.description = 'Move'
        self.dist = dist
        self.state = state

    def exec(self):
        self.state = move(self.dist, self.state)
        return self

class TurnEvent(Event):
    def __init__(self, angle:int, state):
        self.description = 'Turn'
        self.angle = angle
        self.state = state

    def exec(self):
        self.state = turn(self.angle, self.state)
        return self

class SetEvent(Event):
    def __init__(self, new_state, state):
        self.description = 'Set'
        self.new_state = new_state
        self.state = state

    def exec(self):
        self.state = set_state(self.new_state, self.state)
        return self

class StartEvent(Event):
    def __init__(self, state):
        self.description = 'Start'
        self.state = state

    def exec(self):
        self.state = start(self.state)
        return self

class StopEvent(Event):
    def __init__(self, state):
        self.description = 'Stop'
        self.state = state

    def exec(self):
        self.state = stop(self.state)
        return self

class CommandHandler:
    def __init__(self, event_store: EventStore):
        self.event_store = event_store

    def handle(self, code):
        for command in code:
            cmd = command.split(' ')
            state = self.event_store.get_last_state()
            if cmd[0] == 'move':
                self.event_store.add_event(MoveEvent(int(cmd[1]), state).exec())
            elif cmd[0] == 'turn':
                self.event_store.add_event(TurnEvent(int(cmd[1]), state).exec())
            elif cmd[0] == 'set':
                self.event_store.add_event(SetEvent(str(cmd[1]), state).exec())
            elif cmd[0] == 'start':
                self.event_store.add_event(StartEvent(state).exec())
            elif cmd[0] == 'stop':
                self.event_store.add_event(StopEvent(state).exec())

class EventStore:
    def __init__(self):
        self.events = []

    def add_event(self, event: Event):
        self.events.append(event)

    def get_last_state(self):
        if len(self.events) == 0:
            return initial_state
        else:
            return self.events[-1].state


def main():
    event_store = EventStore()
    command_handler = CommandHandler(event_store)
    command_handler.handle(('move 100',
    'turn -90',
    'set SOAP',
    'start',
    'move 50',
    'stop'))

    print(event_store.events)

main()
