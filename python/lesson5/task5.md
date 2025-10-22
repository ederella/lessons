## Код на Python
```python
from random import randint


class Country:
    def __init__(self, name: str, leader_name: str):
        self.__name = name
        self.__leader_name = leader_name
        self.__points = 0
        self.__gold = 0
        self.__resources = []
        self.__cities = []
        self.__military_units = []
        self.__production_units = []
        self.__relations = []

    def get_name(self):
        return self.__name

    def get_cities(self):
        return self.__cities

    def get_gold(self):
        return self.__gold

    def get_military_units(self):
        return self.__military_units

    def build_new_city(self, name: str, location) -> City:
        city = City(name, location, self)
        self.__cities.append(city)
        return city

    def obtain_gold(self, gold: int):
        self.__gold += gold
        print(self.__name + ' сейчас имеет ' + str(self.__gold) + ' золота')

    def pay_gold(self, gold: int):
        self.obtain_gold(-gold)

    def discover_resource(self, resource: str):
        self.__resources.append(resource)

    def sell_resource(self, resource: str, price: int, buyer):
        if buyer.buy_resource(resource, price):
            print(self.__name + ' продано ' + resource)
            self.__resources.remove(resource)
            self.obtain_gold(price)
        else:
            print('Сделка отменена')

    def buy_resource(self, resource: str, price: int):
        if self.__gold >= price:
            print(self.__name + ' купил ' + resource)
            self.__resources.append(resource)
            self.obtain_gold(-price)
            return True
        return False

    def hire_military_unit(self, unit: MilitaryUnit):
        self.__military_units.append(unit)

    def hire_production_unit(self, unit: ProductionUnit):
        self.__production_units.append(unit)

    def hire_military_units(self, name:str, location, count):
        for i in range(0, count):
            if name.lower() == 'стрелец':
                self.hire_military_unit(Archer(location))
                print('стрелец добавлен')
            if name.lower() == 'рыцарь':
                self.hire_military_unit(Knight(location))
                print('рыцарь добавлен')

    def hire_production_units(self, name:str, location, count):
        for i in range(0, count):
            if name.lower() == 'рабочий':
                self.hire_production_unit(Worker(location))
                print('рабочий добавлен')
            if name.lower() == 'купец':
                self.hire_production_unit(Merchant(location))
                print('купец добален')

    def move_all_units(self, location) :
        grouped_units = self.__production_units + self.__military_units
        for unit in grouped_units:
            unit.move(location)

    def move_all_units_to_city(self, city: City) :
        grouped_units = self.__production_units + self.__military_units
        for unit in grouped_units:
            unit.move_to_city(city)

class City:
    def __init__(self, name, location, country):
        self.__name = name
        self.__location = location
        self.__country = country
        self.__population = 1
        self.__buildings = []
        self.__gold_per_step = 0
        print('город ' + self.__name + ' основан в стране ' + country.get_name())

    def get_location(self):
        return self.__location

    def get_name(self):
        return self.__name

class Unit:
    def __init__(self, speed, location, name):
        self.__location = location
        self.__speed = speed
        self.__name = name

    def move_to_city(self, city: City):
        self.move(city.get_location())
        print(self.__name + ' перемещен в ' + city.get_name())

    def move(self, location):
        (x1, y1) = self.__location
        (x2, y2) = location
        distance = ((x2 - x1)**2 + (y2 - y1)**2)** 0.5
        time = distance/self.__speed
        if time < 1:
           time = 1
        distance /= time
        new_location = (x1 + time * (x2 - x1), y1 + time * (y2 - y1))
        self.__location = new_location


class MilitaryUnit(Unit):
    def __init__(self, speed, strength, location, name):
        super().__init__(speed, location, name)
        self.__strength = strength
        self.__health = 100
        self.__is_activated = True
        self.__bonuses = 0

    def increase_bonuses(self):
        self.__bonuses += 1

    def fight(self, enemy):
        if enemy.__health < self.__strength:
            enemy.__health = 0
        else:
            enemy.__health -= self.__strength
        if enemy.__health == 0:
            self.increase_bonuses()

class Archer(MilitaryUnit):
    def __init__(self, location):
        super().__init__(5, 5, location, 'стрелец')

    def shoot(self, enemy, distance):
        if distance == 0:
            self.fight(enemy)
        else:
            damage = self.__strength * 2
            if enemy.__health < damage:
                enemy.__health = 0
            else:
                enemy.__health -= damage
            if enemy.__health == 0:
                self.increase_bonuses()

    def upgrade(self, country: Country):
        price = 200
        if country.get_gold() > price:
            self.__strength *= 2
            self.__speed *= 2
            country.pay_gold(price)

class Knight(MilitaryUnit):
    def __init__(self, location):
        super().__init__(20, 8, location,'рыцарь')

    def heal(self, health_points):
        self.__health += health_points
        print('излечился до ' + str(self.__health) + '%')

    def rob(self, country: Country):
        loot = randint(1, 100)
        print('украдено золота: ' + str(loot))
        country.obtain_gold(loot)
        self.increase_bonuses()

class ProductionUnit(Unit):
    def __init__(self, gold_produced, location, name):
        super().__init__(1, location, name)
        self.__task = []
        self.__gold_produced = gold_produced

    def accept_task(self, task):
        self.__task.append(task)

    def execute(self, task_no):
        if task_no < len(self.__task):
            return 0
        print('задача исполнена: ' + self.__task[task_no])
        self.__task.remove(task_no)
        return self.__gold_produced

class Worker(ProductionUnit):
    def __init__(self, location):
        super().__init__(9, location, 'рабочий')
        self.__amortization = 0
        self.__task_list = {'строить дорогу', 'строить здание', 'рубить лес', 'собирать урожай'}
        self.__is_deactivated = False

    def deactivate(self):
        print('Рабочий отправлен на пенсию')
        self.__is_deactivated = True

    def execute(self, task_no):
        if self.__is_deactivated:
            print('Рабочий на пенсии')
            return 0
        else:
            self.__amortization += 1
            return super().execute(task_no) - self.__amortization


class Merchant(ProductionUnit):
    def __init__(self, location):
        super().__init__(5, location, 'купец')
        self.__relation_points = 5
        self.__gold = 0
        self.percent = 0.25
        self.__task_list = {'торговать', 'заключить сделку', 'отменить сделку'}

    def execute(self, task_no):
        gold = super().execute(task_no)
        self.__gold = gold * self.percent
        return [gold - self.__gold, self.__relation_points]

    def fund(self, amount):
        if self.__gold > amount:
            self.percent +=0.05
            self.__gold -= amount
            return amount
        return 0

country_name = input("Введите название страны: ")
country_leader = input("Введите имя лидера: ")

user_country = Country(country_name, country_leader)
capital_name = input("Введите название города: ")
capital = user_country.build_new_city(capital_name, (10, 100))
location_of_capital = capital.get_location()
user_country.hire_production_units('рабочий', location_of_capital, 2)
user_country.hire_production_units('купец', location_of_capital, 1)
user_country.hire_military_units('стрелец', location_of_capital, 2)
user_country.hire_military_units('рыцарь', location_of_capital, 3)

city_name = input("Введите название города: ")
some_city = user_country.build_new_city(city_name, (20, 100))
user_country.move_all_units_to_city(some_city)
```

## Пример работы кода

<img width="620" height="514" alt="image" src="https://github.com/user-attachments/assets/9826bb05-377e-4fd9-8de2-ad8c378806db" />

