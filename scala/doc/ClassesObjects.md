# Classes and Objects

## Classes
Classes in Scala are very similar to classes in Java. They are static templates containing fields and methods that can be instantiated into many objects at runtime.

```scala
class Point(x: Int, y: Int) {
  override def toString(): String = "(" + x + ", " + y + ")"
}
```
Classes in Scala are parameterized with constructor arguments. The code above defines two constructor arguments, x and y; they are both visible in the whole body of the class. In our example they are used to implement toString.
_Classes in Scala cannot have static members. You can use objects (see below) to achieve similar functionality as with static members in Java._

```scala
class Point(x: Int, y: Int) {
    def this(x: Int) = this(x, 0)
    def this() = this(0)
    
    override def toString(): String = "(" + x + ", " + y + ")"
}

val p1 = new Point(2,1)
val p2 = new Point(2)
val p3 = new Point()
```

```scala
class ClassWithValParameter(name: String) {
    val nume: String = name
}

val aClass = new ClassWithValParameter("Gandalf")
val isValid = aClass.nume.equals("Gandalf")
```

## Case Classes
Includes several automatically generated methods (link Lombok in java)

```scala
final case class Character(name: String, isThief: Boolean)

val h = Character("Hadrian", true)
val r = h.copy(name = "Royce")
println(r.name)
```
Bad useage
```scala
case class Foo(i: Int)

class Bar(i: Int, s: String) extends Foo(i)

new Bar(1, "foo") == new Bar(1, "bar")

//res0: Boolean = true

```


## Classes Operators

```scala
class Rational(x: Int, y: Int) {

    val numer = x
    val denom = y

    def < (that: Rational) = numer * that.denom < that.numer * denom

    def max(that: Rational) = if (this < that) that else this

    def + (that: Rational) = new Rational(
        numer * that.denom + that.numer * denom,
        denom * that.denom
    )
    
    override def toString = numer + "/" + denom
}

val x = new Rational(1,2)
val y = new Rational(1,3)

val ok = x.+(y)
val add = x + y
val max = x max y
val less = x < y
```

## Traits
Traits are like interfaces in Java, they are used to define object types by specifying the signature of the supported methods.

```scala
trait Similarity {
  def isSimilar(x: Any): Boolean
  def isNotSimilar(x: Any): Boolean = !isSimilar(x)
}
```

## Objects
An object is a singleton (one single instance), and not a static method in a class. It is not possible to create instances of objects using new, instead you can just access the members (methods or fields) of an object using its name.

```scala
object Greeting {
  def english = "Hi"
  def espanol = "Hola"
}

val eng = Greeting.english equals "Hi"
val span = Greeting.espanol.equals("Hola")
```

## Options
Scala tries to solve the problem by getting rid of _null_ values altogether and providing its own type for representing optional values.
_Option[A]_ is a container for an optional value of type A. If the value of type A is present, the Option[A] is an instance of _Some[A]_, containing the present value of type A. If the value is absent, the Option[A] is the object _None_. 

```scala
val someValue: Option[String] = Some("I am wrapped in something")
someValue should be(Some("I am wrapped in something"))

val emptyValue: Option[String] = None
emptyValue should be(None)
```

## Tuples
Scala tuple combines a fixed number of items together so that they can be passed around as a whole. They are one-indexed. Unlike an array or list, a tuple can hold objects with different types but they are also immutable. Here is an example of a tuple holding an integer, a string, and the console:
```scala
val t = (1, "hello", Console)
```

Which is syntactic sugar (short cut) for the following:
```scala
val t = new Tuple3(1, "hello", Console)
```
As you can see, tuples can be created easily: 
```scala
val tuple = ("apple", "dog")
val fruit = tuple._1
val animal = tuple._2

fruit should be(???)
animal should be(???)
```
---
You can assign multiple variables at once using tuples: 
```scala
val student = ("Sean Rogers", 21, 3.5)
val (name, age, gpa) = student

name should be(???)
age should be(???)
gpa should be(???)
```