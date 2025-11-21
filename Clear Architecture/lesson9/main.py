import Functional.cleaner_api

robot_api = Functional.cleaner_api.CleanerApi()

robot_api.create_cleaner()

state = robot_api.activate_cleaner((
    'move 100',
    'turn -90',
    'set soap',
    'start',
    'move 50',
    'stop'
    ))

print (state)
