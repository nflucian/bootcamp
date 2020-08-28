# Currying

Partially Applied Functions

```scala
def factorOf(a: Int, b: Int): Int = a * b

val f = factorOf _
val x = f(7, 20)

val multipleOf3 = factorOf(3, _: Int)
val y = multipleOf3(78)

```

Currying

```scala
def factorOf(x: Int)(y: Int) = y % x == 0

val isEven = factorOf(2) _

val z = isEven(32)
```
