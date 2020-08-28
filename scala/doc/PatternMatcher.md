# Pattern Matcher
Pattern matching is a mechanism for checking a value against a pattern.
```scala
def matchTest(x: Int): String = x match {
  case 1 => "one"
  case 2 => "two"
  case _ => "many"
}
matchTest(3)  // many
matchTest(1)  // one
```

```scala
val day = "MON"
val kind = day match {
    case "MON" | "TUE" | "WED" | "THU" | "FRI" => "weekday"
    case "SAT" | "SUN" => "weekend"
    case other => s"Couldn't parse $other"
}
```

```scala
def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil        => Nil
    case x :: xs1   =>  {
        val (first, rest) = xs.span(y => y == x)
        first :: pack(rest)
    }
}

pack(List("a","a","a","b","c","c","a"))
```
