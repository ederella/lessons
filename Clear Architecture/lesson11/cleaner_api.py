import pure_robot

class RobotApi:
    def __init__(self):
        self.cleaner_state = pure_robot.RobotState(0.0, 0.0, 0, pure_robot.WATER)
        self.stack = []

    def read(self, command_str):
        for el in command_str.split(' '):
            if el == 'start':
                self.cleaner_state = pure_robot.start(transfer_to_cleaner, self.cleaner_state)
            elif el == 'stop':
                self.cleaner_state = pure_robot.stop(transfer_to_cleaner, self.cleaner_state)
            elif el == 'move':
                self.cleaner_state = pure_robot.move(transfer_to_cleaner, int(self.stack.pop()), self.cleaner_state)
            elif el == 'turn':
                self.cleaner_state = pure_robot.turn(transfer_to_cleaner, int(self.stack.pop()), self.cleaner_state)
            elif el == 'set':
                self.cleaner_state = pure_robot.set_state(transfer_to_cleaner, self.stack.pop(), self.cleaner_state)
            else:
                self.stack.append(el)

def transfer_to_cleaner(message):
    print(message)
