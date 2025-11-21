import pure_robot

# класс Чистильщик API
class CleanerApi:

    # конструктор
    def __init__(self):
        self.turn = None
        self.move = None
        self.stop = None
        self.set = None
        self.start = None
        

    def setup_move(self, func):
        self.move = func

    def setup_turn(self, func):
        self.turn = func

    def setup_set(self, func):
        self.set = func

    def setup_start(self, func):
        self.start = func

    def setup_stop(self, func):
        self.stop = func


    # взаимодействие с роботом вынесено в отдельную функцию
    def transfer_to_cleaner(self,message):
        print (message)

    def activate_cleaner(self, code):
        cleaner_state = pure_robot.RobotState(0.0, 0.0, 0, pure_robot.WATER)
        for command in code:
            cmd = command.split(' ')
            if cmd[0]=='move':
                cleaner_state = self.move(self.transfer_to_cleaner, int(cmd[1]), cleaner_state)
            elif cmd[0]=='turn':
                cleaner_state = self.turn(self.transfer_to_cleaner, int(cmd[1]), cleaner_state)
            elif cmd[0]=='set':
                cleaner_state = self.set(self.transfer_to_cleaner, cmd[1], cleaner_state)
            elif cmd[0]=='start':
                cleaner_state = self.start(self.transfer_to_cleaner, cleaner_state)
            elif cmd[0]=='stop':
                cleaner_state = self.stop(self.transfer_to_cleaner,  cleaner_state)
        return cleaner_state

    def create_cleaner(self):
        self.setup_move(pure_robot.move)
        self.setup_turn(pure_robot.turn)
        self.setup_set(pure_robot.set_state)
        self.setup_start(pure_robot.start)
        self.setup_stop(pure_robot.stop)
