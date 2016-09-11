package ce.practice.utils

import org.scalatest.{FunSpec, ShouldMatchers}

import scala.io.Source

/**
  * @author caie
  * @since 16/8/31
  */
class FileUtilsSpec extends FunSpec with ShouldMatchers {

  describe("file util") {
    it("can extract file name with correct file name suffix") {
      val name = "build.sbt"
      FileUtils.extractFileName(name) shouldBe "sbt"
    }

    it("can extract file name with no suffix") {
      val name = "4TestFile"
      FileUtils.extractFileName(name) shouldBe "Empty-File-Type"
    }

    it("can write results to a file ") {
      val currentPath = System.getProperty("user.dir")
      val content = "sth to write to a file"
      val path = currentPath + "/result.txt"
      FileUtils.writeFile(path, content)

      val source = Source.fromFile(path)
      source.getLines().toList.head shouldBe content
    }

  }
}
