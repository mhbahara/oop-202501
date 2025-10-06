class Hello:
    def __init__(self, name, nim):
        self.name = name
        self.nim = nim

    def say_hello(self):
        print(f"Hello World, I am {self.name}-{self.nim}")

program = Hello("Wisnu", "240320565")
program.say_hello()
