# Implicit

* __Implicit Parameters__, when the caller providers the default value in its own nemaspace
* __Implicit Classes__, provides an automatic conversion from instances of Type A to type B

### implicit parameters
```scala
def calcTax(amount: Float)(implicit rate: Float): Float = amount * rate

implicit val currentTaxRate = 0.08F

val tax = calcTax(50000F)
```

### implicit class
```scala
object Helpers {
    implicit class RichInt(x: Int) {
        def transform(s: String) = s + x
    }
    
    println(6 transform "test")
}

import Helpers._

val ok = 5

val five = 5.transform("lucian")
val ten = 10 transform "esolutions"

println(ok transform "ghita")
```