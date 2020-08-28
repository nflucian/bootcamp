# Higher-Order Functions
Functions that take other functions as parameters or that return functions as result.

### Liniar recursion
```scala
def sum(f: Int => Int, a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sum(f, a + 1, b)
}

def sumInts(a: Int, b: Int) = sum(x => x, a, b)
def sumCubes(a: Int, b: Int) = sum(x => x * x * x, a, b)

println(sumInts(3,5))

println(sumCubes(3,5))
```

### Tail recursion (avoid stack overflow)
```scala
def sum(f: Int => Int, a: Int, b: Int): Int = {
    
    def loop(x: Int, acc: Int): Int = {
        if(x > b) acc
        else loop(x + 1, f(x) + acc)
    }
    
    loop(a, 0)
}

def sumInts(a: Int, b: Int) = sum(x => x, a, b)
def sumCubes(a: Int, b: Int) = sum(x => x * x * x, a, b)

println(sumInts(3,5))
println(sumCubes(3,5))
```

