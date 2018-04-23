Красно-чёрное дерево (Red-black tree) - это одно из самобалансирующихся двоичных деревьев поиска, гарантирующих логарифмический рост высоты дерева от числа узлов и быстро выполняющее основные операции дерева поиска: добавление, удаление и поиск узла.
Сбалансированность достигается за счёт введения дополнительного атрибута узла дерева - "цвета".
Этот атрибут может принимать одно из двух возможных значений - "чёрный" или "красный".

Rules:
- root node is BLACK
- end nodes are BLACK
- if parent node is RED then child node is BLACK (if parent node is BLACK then child node can be BLACK or RED)
- depth in BLACK nodes is the same always