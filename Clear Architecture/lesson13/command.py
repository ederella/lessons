import pure_robot
from pymonad.operators.maybe import Just, Maybe
from abc import ABC, abstractmethod

class RobotCommand(ABC):
    @abstractmethod
    def execute(self, state_ls: list) -> Maybe:
        pass

class MoveCommand(RobotCommand):
    def __init__(self, distance):
        self.distance = distance

    def execute(self, state_ls: list) -> Maybe:
        res = pure_robot.move(log, self.distance, state_ls[-1])
        state_ls.append(res)
        return Just(state_ls)


class TurnCommand(RobotCommand):
    def __init__(self, angle):
        self.angle = angle

    def execute(self, state_ls: list) -> Maybe:
        res = pure_robot.turn(log, self.angle, state_ls[-1])
        state_ls.append(res)
        return Just(state_ls)


class SetStateCommand(RobotCommand):
    def __init__(self, new_state):
        self.new_state = new_state

    def execute(self, state_ls: list) -> Maybe:
        res = pure_robot.set_state(log, self.new_state, state_ls[-1])
        state_ls.append(res)
        return Just(state_ls)


class StartCommand(RobotCommand):
    def execute(self, state_ls: list) -> Maybe:
        res = pure_robot.start(log, state_ls[-1])
        state_ls.append(res)
        return Just(state_ls)


class StopCommand(RobotCommand):
    def execute(self, state_ls: list) -> Maybe:
        res = pure_robot.stop(log, state_ls[-1])
        state_ls.append(res)
        return Just(state_ls)


class RobotController:
    def __init__(self, initial_state):
        self.state_ls = [initial_state]
        self.commands = []
        self.history = []  # для возможной реализации undo

    def add_command(self, command: RobotCommand):
        self.commands.append(command)
        return self  # для fluent interface

    def execute_all(self):
        result = Just(self.state_ls)
        for command in self.commands:
            result = result >> command.execute
        return result

    def execute_single(self, command: RobotCommand):
        self.commands.append(command)
        return command.execute(self.state_ls)

    def clear_commands(self):
        self.commands.clear()
        return self


class CommandFactory:
    @staticmethod
    def move(distance):
        return MoveCommand(distance)

    @staticmethod
    def turn(angle):
        return TurnCommand(angle)

    @staticmethod
    def set_state(new_state):
        return SetStateCommand(new_state)

    @staticmethod
    def start():
        return StartCommand()

    @staticmethod
    def stop():
        return StopCommand()

def log(msg):
    print(msg)

if __name__ == "__main__":
    robot_state = pure_robot.RobotState(0.0, 0.0, 0, pure_robot.WATER)
    print("Initial state:", robot_state)

    controller = RobotController(robot_state)

    state_list = [robot_state]
    result = Just(state_list) >> CommandFactory.move(100).execute \
              >> CommandFactory.turn(-90).execute \
              >> CommandFactory.set_state('SOAP').execute \
              >> CommandFactory.start().execute \
              >> CommandFactory.move(50).execute \
              >> CommandFactory.stop().execute
    print("Result:", result)
