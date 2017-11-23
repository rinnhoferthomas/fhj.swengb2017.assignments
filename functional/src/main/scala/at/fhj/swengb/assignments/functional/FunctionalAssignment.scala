package at.fhj.swengb.assignments.functional

/**
  * In this assignment you have the chance to demonstrate basic understanding of
  * functions like map/filter/foldleft a.s.o.
  **/
object FunctionalAssignment {

  /**
    * A function which returns its parameters in a changed order. Look at the type signature.
    */
  def flip[A, B](t: (A, B)): (B, A) = (t._2,t._1)

  /**
    * given a Seq[A] and a function f : A => B, return a Seq[B]
    */
  def unknown[A, B](as: Seq[A], fn: A => B): Seq[B] = for (x <- as) yield fn(x)

  /**
    * Returns the absolute value of the parameter i.
    *
    * @param i a value, either with a positive or a negative sign.
    * @return
    */
  def abs(i: Int): Int = if (i < 0) (-i) else i


  // Describe with your own words what this function does.
  // in the comment below, add a description what this function does - in your own words - and give
  // the parameters more descriptive names.
  //
  // Afterwards, compare your new naming scheme with the original one.
  // What did you gain with your new names? What did you loose?
  //
  /**
    *
    * @param as -> list oder seq (da vom Typ Seq, Seq ist der Obertyp von List)
    * @param b -> acc, da dieser Wert als Accumulator dient
    * @param fn -> func, da eine Funktion die Inhalte der Liste bzw der Sequenz sowie den Accumulator als Parameter verwendet
    * @tparam A -> würde es bei A belassen, parameter vom Typ A
    * @tparam B -> würde es bei B belassen, parameter vom Typ B
    * @return ergebnis welches zurückgegeben werden soll
    *         Funktion op nimmt den Accumulator (b) als Startwert, und wendet eine gewünschte Funktion (fn)
    *         mit 2 Parametern, nämlich den Accumulator sowie eine Sequenz (wie eine Liste), an.
    *
    *         Beim darauffolgenden Beispiel wird etwa eine Seq mit Int Zahlen der Reihe nach zum derzeitigen Accumulator
    *         hinzugerechnet. So wird etwa der Startwert auf 0 gesetzt, und pro Schritt immer um die nächste Zahl der Seq erhöht
    *
    *         Neue Namenskonvention viel besser, da klar wird, was dahinter steckt. So ist etwa klar das es sich bei
    *         fn um eine Funktion handelt, sowie bei b um einen Accumulator. Dar Name as für die Seq war auch nicht wirklich aussagekräftig.
    *         Einzig A und B waren in Ordnung, da hier klar wird das es sich hierbei um zwei unterschiedliche Parameter mit jeweils dazugehörigen Typ handelt.
    */
  def op[A, B](as: Seq[A], b: B)(fn: (B, A) => B): B = as.foldLeft(b)(fn)

  /**
    * implement the summation of the given numbers parameter.
    * Use the function 'op' defined above.
    *
    * @param numbers
    * @return
    */
  def sum(numbers: Seq[Int]): Int = op(numbers,0) (_+_)


  /**
    * calculate the factorial number of the given parameter.
    *
    * for example, if we pass '5' as parameter, the function should do the following:
    *
    * 5! = 5 * 4 * 3 * 2 * 1
    *
    * @param i parameter for which the factorial must be calculated
    * @return i!
    */
  def fact(i: Int): Int = if (i == 0) 1 else i*fact(i-1)

  /**
    * compute the n'th fibonacci number
    *
    * hint: use a internal loop function which should be written in a way that it is tail
    * recursive.
    *
    * https://en.wikipedia.org/wiki/Fibonacci_number
    */
  def fib(n: Int): Int ={
    def fib_tail (n: Int, a: Int, b:Int): Int = n match {
      case 0 => a
      case _ => fib_tail (n-1,b,a+b)
    }
    return fib_tail(n,0,1)
  }

  /**
    * Implement a isSorted which checks whether an Array[A] is sorted according to a
    * given comparison function.
    *
    * Implementation hint: you always have to compare two consecutive elements of the array.
    * Elements which are equal are considered to be ordered.
    */
  def isSorted[A](as: Array[A], gt: (A, A) => Boolean): Boolean =   {
    def loop(n: Int): Boolean = {
      if (n + 1 > as.length-1) true
      else if (!gt(as(n), as(n + 1))) false
      else loop(n + 1)
    }
    loop(0)
  }
  /**
    * Takes both lists and combines them, element per element.
    *
    * If one sequence is shorter than the other one, the function stops at the last element
    * of the shorter sequence.
    */
  def genPairs[A, B](as: Seq[A], bs: Seq[B]): Seq[(A, B)] = as zip bs

  // a simple definition of a linked list, we define our own list data structure
  sealed trait MyList[+A]

  case object MyNil extends MyList[Nothing]

  case class Cons[+A](head: A, tail: MyList[A]) extends MyList[A]

  // the companion object contains handy methods for our data structure.
  // it also provides a convenience constructor in order to instantiate a MyList without hassle
  object MyList {

    def sum(list: MyList[Int]): Int = list match {
      case MyNil => 0
      case Cons(x,xs) => x + sum(xs)
    }
    def product(list: MyList[Int]): Int = list match {
      case MyNil => 1
      case Cons(x,xs) => x * product(xs)
    }

    def apply[A](as: A*): MyList[A] = {
      if (as.isEmpty) MyNil
      else Cons(as.head, apply(as.tail: _*))
    }

  }

}

