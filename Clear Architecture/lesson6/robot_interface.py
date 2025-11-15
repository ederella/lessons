import abc
from abc import ABC, abstractmethod

class Robot(ABC):
    @abstractmethod
    def turn(self, transfer,turn_angle):
        raise NotImplementedError

    @abstractmethod
    def set_state(self,transfer,state):
        raise NotImplementedError

    @abstractmethod
    def move(self,transfer, dist, state):
        raise NotImplementedError

    @abstractmethod
    def start(self,transfer, state):
        raise NotImplementedError

    @abstractmethod
    def stop(self,transfer, state):
        raise NotImplementedError

    @abstractmethod
    def make(self,transfer,code,state):
        raise NotImplementedError

    @abstractmethod
    def transfer_to_cleaner(self, message):
        raise NotImplementedError
