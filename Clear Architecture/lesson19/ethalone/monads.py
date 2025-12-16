from dataclasses import dataclass
from typing import Optional, Callable, Tuple, NamedTuple
from enum import Enum
import math


class MoveResponse(Enum):
    OK = "MOVE_OK"
    HIT_BARRIER = "HIT_BARRIER"


class SetStateResponse(Enum):
    OK = "STATE_OK"
    NO_WATER = "OUT_OF_WATER"
    NO_SOAP = "OUT_OF_SOAP"


class RobotState(NamedTuple):
    x: float
    y: float
    angle: float
    mode: int


MoveFn = Callable[[float], Tuple[MoveResponse, 'RobotFunctions']]
TurnFn = Callable[[float], 'RobotFunctions']
SetStateFn = Callable[[], Tuple[SetStateResponse, 'RobotFunctions']]


@dataclass
class RobotFunctions:
    move: Optional[MoveFn]
    turn: TurnFn
    set_water: Optional[SetStateFn]
    set_soap: Optional[SetStateFn]
    set_brush: Optional[SetStateFn]


def check_water_available() -> bool:
    return True


def check_soap_available() -> bool:
    return False


def create_robot() -> RobotFunctions:
    state = RobotState(0.0, 0.0, 0.0, 0)

    def check_position(x: float, y: float) -> tuple[float, float, MoveResponse]:
        constrained_x = max(0, min(100, x))
        constrained_y = max(0, min(100, y))
        if x == constrained_x and y == constrained_y:
            return (x, y, MoveResponse.OK)
        return (constrained_x, constrained_y, MoveResponse.HIT_BARRIER)

    def move_fn(distance: float) -> Tuple[MoveResponse, RobotFunctions]:
        nonlocal state
        angle_rads = state.angle * (math.pi / 180.0)
        new_x = state.x + distance * math.cos(angle_rads)
        new_y = state.y + distance * math.sin(angle_rads)

        constrained_x, constrained_y, move_result = check_position(new_x, new_y)
        state = RobotState(constrained_x, constrained_y, state.angle, state.mode)

        if move_result == MoveResponse.HIT_BARRIER:
            return move_result, RobotFunctions(
                move=None,
                turn=turn_fn,
                set_water=set_water_fn,
                set_soap=set_soap_fn,
                set_brush=set_brush_fn
            )

        return move_result, create_functions_from_state(state)

    def turn_fn(angle: float) -> RobotFunctions:
        nonlocal state
        new_angle = state.angle + angle
        state = RobotState(state.x, state.y, new_angle, state.mode)
        return create_functions_from_state(state)

    def set_water_fn() -> Tuple[SetStateResponse, RobotFunctions]:
        nonlocal state
        if check_water_available():
            state = RobotState(state.x, state.y, state.angle, 1)
            return SetStateResponse.OK, create_functions_from_state(state)
        return SetStateResponse.NO_WATER, RobotFunctions(
            move=move_fn,
            turn=turn_fn,
            set_water=None,
            set_soap=set_soap_fn,
            set_brush=set_brush_fn
        )

    def set_soap_fn() -> Tuple[SetStateResponse, RobotFunctions]:
        nonlocal state
        if check_soap_available():
            state = RobotState(state.x, state.y, state.angle, 2)
            return SetStateResponse.OK, create_functions_from_state(state)
        return SetStateResponse.NO_SOAP, RobotFunctions(
            move=move_fn,
            turn=turn_fn,
            set_water=set_water_fn,
            set_soap=None,
            set_brush=set_brush_fn
        )

    def set_brush_fn() -> Tuple[SetStateResponse, RobotFunctions]:
        nonlocal state
        state = RobotState(state.x, state.y, state.angle, 3)
        return SetStateResponse.OK, create_functions_from_state(state)

    def create_functions_from_state(state: RobotState) -> RobotFunctions:
        return RobotFunctions(
            move=move_fn,
            turn=turn_fn,
            set_water=set_water_fn,
            set_soap=set_soap_fn,
            set_brush=set_brush_fn
        )

    return create_functions_from_state(state)


def main():
    robot = create_robot()

    move_result, robot = robot.move(50)
    if move_result == MoveResponse.OK:
        print("Move successful")
    else:
        print("Hit barrier")

    robot = robot.turn(90)

    water_result, robot = robot.set_water()
    if water_result == SetStateResponse.OK:
        print("Water mode activated")
    else:
        print("No water available")

    if robot.set_soap:
        soap_result, robot = robot.set_soap()
        if soap_result == SetStateResponse.OK:
            print("Soap mode activated")
        else:
            print("No soap available")

if __name__ == '__main__':
    main()
