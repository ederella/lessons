import pure_robot

class RobotApi:

    def setup(self, function_do, f_transfer):
        self.function_do = function_do
        self.f_transfer = f_transfer

    def make(self, command):
        if not hasattr(self, 'cleaner_state'):
            self.cleaner_state = pure_robot.RobotState(0.0, 0.0, 0, pure_robot.WATER)

        cmd = command.split(' ')
        do_smth = self.function_do(cmd[0])
        if cmd[0]=='move':
             self.cleaner_state = do_smth(self.f_transfer, int(cmd[1]), self.cleaner_state)
        elif cmd[0]=='turn':
            self.cleaner_state = do_smth(self.f_transfer,int(cmd[1]), self.cleaner_state)
        elif cmd[0]=='set':
            self.cleaner_state = do_smth(self.f_transfer,cmd[1], self.cleaner_state)
        elif cmd[0]=='start':
            self.cleaner_state = do_smth(self.f_transfer, self.cleaner_state)
        elif cmd[0]=='stop':
            self.cleaner_state = do_smth(self.f_transfer,  self.cleaner_state)
        return self.cleaner_state

    def __call__(self, command):
        return self.make(command)


def transfer_to_cleaner(message):
    print (message)

def double_move(transfer,dist,state):
    return pure_robot.move(transfer,dist*2,state)

def do(command):
    if command == 'move':
        return pure_robot.move
    if command == 'turn':
        return pure_robot.turn
    if command == 'set':
        return pure_robot.set_state
    if command == 'start':
        return pure_robot.start
    if command == 'stop':
        return pure_robot.stop
    return None

api = RobotApi()
api.setup(do, transfer_to_cleaner)
