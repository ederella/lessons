import cleaner
import interaction

# главная программа
robot = cleaner.Cleaner(interaction.print_arrows)
robot.make((
    'move 100',
    'turn -90',
    'set soap',
    'start',
    'move 50',
    'stop'
))
