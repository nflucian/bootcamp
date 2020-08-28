# Functions and Closures

Scala has functions in addition to methods. A method operates on an object, but a function doesn't.
Define a function:
```scala
def abs(x: Double) = if (x >= 0) x else -x
```
You can provide default arguments for functions that are used when you don't specify explicit values.
```scala
def decorate(str: String, left: String = "[", right: String = "]") = left + str + right

println("Salut")
println("Salut", "<<<", ">>>")
println("Salut", ">>>[")
println(left = ">>>(", str = "Salut", right = ")")
```

## Value Evaluation

__val__ is evaluating ByValue, at the point of the definition itself

```
val x = 2
val y = square(x) // y = 4
```
__def__ is evaluating when called

```
def loop: Boolean = loop

def x = loop // OK, the code continue to execute  

val x = loop // it evaluates the 'loop', the code execution is stucks 
```

## Evaluation of Function Applications
Applications of parameterized functions are evaluated in a similar way as operators:

- Evaluate all functions arguments, from __left to right__ 
- Replace the function application by the function's right-hand side
- Replace the formal parameters of the function by the actual arguments

_Example:_ 
```
def square(x: Double) = x * x
def sumOfSquares(x: Double, y: Double) = square(x) + square(y)
```
Evaluating steps:
```
sumOfSquares(3, 2+2)
sumOfSquares(3, 4)
square(3) + square(4)
3 * 3 + square(4)
9 + square(4)
9 + 4 * 4
9 + 16
25
```

## Call-by-name and Call-by-value

- Call-by-value has the advantage that it evaluated every function argument only once
- Call-by-name has the advantage that a function argument is not evaluated if the corresponding parameter is unused in the evaluation of the function body.

```
def test(x: Int, y: Int) = x * x

// same CallByValue or CallByName 
test(2, 3)  
2 * 2
4

// CallByValue is faster
test(3+4, 8) 
// CallByValue                  CallByName
test(7, 8)                      (3+4) * (3+4)
7 * 7                           7 * (3+4)
49                              7 * 7
                                49
// CallByName is faster
test(7, 2+4)
// CallByValue                  CallByName
test(7, 6)                      7 * 7
7 * 7                           49
49
``` 

Scala normally uses __Call-By-Value__. But if the type of a function parameter start with __=>__ it uses __Call-By-Name__