import pure_robot
from pymonad.operators.maybe import Just, Maybe

def log(msg):
   print(msg)

def m_move(dist):
    def inner(state_ls: list):
        res = pure_robot.move(log, dist, state_ls[-1])
        state_ls.append(res)
        return Just(state_ls)
    return inner

def m_turn(angle):
    def inner(state_ls: list):
        res = pure_robot.turn(log, angle, state_ls[-1])
        state_ls.append(res)
        return Just(state_ls)
    return inner

def m_set_state(new_internal_state):
    def inner(state_ls: list):
        res = pure_robot.set_state(log, new_internal_state, state_ls[-1])
        state_ls.append(res)
        return Just(state_ls)
    return inner

def m_start():
    def inner(state_ls: list):
        res = pure_robot.start(log, state_ls[-1])
        state_ls.append(res)
        return Just(state_ls)
    return inner

def m_stop():
    def inner(state_ls: list):
        res = pure_robot.stop(log, state_ls[-1])
        state_ls.append(res)
        return Just(state_ls)
    return inner

robot_state = pure_robot.RobotState(0.0, 0.0, 0, pure_robot.WATER)
print(robot_state)
state_list = [robot_state]
result = Just(state_list) >> m_move(100) >> m_turn(-90) >> m_set_state('SOAP') >> m_start() >> m_move(50) >> m_stop()

print(result)
