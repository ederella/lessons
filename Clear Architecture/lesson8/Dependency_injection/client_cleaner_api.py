import cleaner
class CleanerApi:

    # взаимодействие с роботом вынесено в отдельную функцию
    def transfer_to_cleaner(self, message):
        print(message)


    def __init__(self):
        self.cleaner = cleaner.Cleaner(self.transfer_to_cleaner)

    def activate_cleaner(self, code):
        self.cleaner.make(code)

    def get_x(self):
        return self.cleaner.x

    def get_y(self):
        return self.cleaner.y

    def get_angle(self):
        return self.cleaner.angle

    def get_state(self):
        return self.cleaner.state
