package at.fhj.swengb.apps.calculator

import org.scalatest.WordSpecLike
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.Path
import java.util
import scala.collection.JavaConverters._




class TimesheetSpec extends WordSpecLike {
  val zuTesten = 1
  "Test1" in {
    assert(zuTesten==1)
  }




  val expected =


    """== Time expenditure: Calculator assignment
      |
      |[cols="1,1,4", options="header"]
      |.Time expenditure
      ||===
      || Date
      || Hours
      || Description
      |
      || 29.11.17
      || 1
      || Review of this and that
      |
      || 30.11.17
      || 5
      || implemented css
      |
      || 11.07.17
      || 2
      || fixed bugs
      |
      ||===""".stripMargin

  val p: Path= Paths.get("C:\\workspace\\fhj.swengb2017.assignments\\calculator\\timesheet-calculator.adoc")
  "Test2" should {
    "work" in {

      val result : String=
        Files.readAllLines(p).asScala.mkString("\n")
      assert(result==expected)
    }
  }


}

