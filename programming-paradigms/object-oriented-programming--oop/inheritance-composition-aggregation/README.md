Между двумя классами/объектами существует разные типы отношений.

association (ассоциация):
relationship: unknown
означает, что два класса как-то связаны между собой, и пока неизвестно точно, в чем эта связь выражена и собираемся уточнить ее в будущем.
Обычно это отношение используется на ранних этапах дизайна, чтобы показать, что зависимость между классами существует, и двигаться дальше.

    CustomService --------> CustomRepository
    + execute()             + getAll()

inheritance (наследование):
relationship: is-a
означает, что все, что справедливо для базового класса справедливо и для его наследника.

    AbstractRepository
    + getAll()
    ^
    |
    |
    DatabaseRepository
    + getAll()

composition (композиция)
relationship: has-a

    Service +-------> Repository
    + execute()       + save()
    (управляет временем жизни части)

aggregation (агрегация)
relationship: has-a

    Service 0-------> Repository
    + execute()       + getAll()
    (получает часть извне)