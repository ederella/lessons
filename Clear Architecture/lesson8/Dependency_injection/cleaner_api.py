from Dependency_injection.client_cleaner_api import CleanerApi

cleaner_api = CleanerApi()

cleaner_api.activate_cleaner((
    'move 100',
    'turn -90',
    'set soap',
    'start',
    'move 50',
    'stop'
    ))

print (cleaner_api.get_x(),cleaner_api.get_y(),cleaner_api.get_angle(), cleaner_api.get_state())
