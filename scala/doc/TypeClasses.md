# Type Classes

```scala

trait HasGet[A] {
    def get(a: A): Int
}

implicit val wrap: HasGet[Wrapper] = new HasGet[Wrapper] {
    def get(a: Wrapper) = a.i
}

def add1[A](a: A)(implicit hga: HasGet[A]): Int = hga.get(a) + 1


add1(Wrapper(1))

// res1: Int = 2
```