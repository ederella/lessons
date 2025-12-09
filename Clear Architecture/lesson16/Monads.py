from functools import wraps
from collections import namedtuple
import math

RobotState = namedtuple("RobotState", "x y angle state response")

WATER = 1
SOAP = 2
BRUSH = 3


class StateMonad:
    def __init__(self, state, log=None):
        self.state = state
        self.log = log or []

    def bind(self, func):
        new_state, new_log = func(self.state, self.log)
        return StateMonad(new_state, new_log)


def move(dist):
    def inner(old_state, log):
        angle_rads = old_state.angle * (math.pi / 180.0)
        x = old_state.x + dist * math.cos(angle_rads)
        y = old_state.y + dist * math.sin(angle_rads)
        (x_constrained, y_constrained, response) = check_position(x, y)
        new_state = RobotState(
            x_constrained,
            y_constrained,
            old_state.angle,
            old_state.state,
            response
        )
        return new_state, log + [f'POS({int(new_state.x)},{int(new_state.y)}, {new_state.response})']

    return inner


def turn(angle):
    def inner(old_state, log):
        new_state = RobotState(
            old_state.x,
            old_state.y,
            old_state.angle + angle,
            old_state.state, ''
        )
        return new_state, log + [f'ANGLE {new_state.angle}']
    return inner


def set_state(new_mode):
    def inner(old_state, log):
        new_state = RobotState(
            old_state.x,
            old_state.y,
            old_state.angle,
            new_mode,
            check_resources(new_mode)
        )
        return new_state, log + [f'STATE {new_state.state}, {new_state.response}']

    return inner


def start(old_state, log):
    return old_state, log + ['START']


def stop(old_state, log):
    return old_state, log + ['STOP']

class MoveResponse:
    OK = "MOVE_OK"
    BARRIER = "HIT_BARRIER"

def check_position(x: float, y: float) -> tuple[float, float, str]:
    constrained_x = max(0, min(100, x))
    constrained_y = max(0, min(100, y))

    if x == constrained_x and y == constrained_y:
        return (x, y, MoveResponse.OK)
    return (constrained_x, constrained_y, MoveResponse.BARRIER)

class SetStateResponse:
    OK = "STATE_OK"
    NO_WATER = "OUT_OF_WATER"
    NO_SOAP = "OUT_OF_SOAP"

def check_resources(new_mode: int) -> SetStateResponse:
    if new_mode == WATER:
        # ....
        return SetStateResponse.NO_WATER
    elif new_mode == SOAP:
        # ....
        return SetStateResponse.NO_SOAP
    return SetStateResponse.OK

initial_state = StateMonad(RobotState(0.0, 0.0, 0, WATER, ''))
result = (initial_state
          .bind(move(100))
          .bind(turn(-90))
          .bind(set_state(SOAP))
          .bind(start)
          .bind(move(50))
          .bind(stop))

print(f"Final state: {result.state}")
print(f"Log: {result.log}")
