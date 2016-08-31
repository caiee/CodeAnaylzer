package ce.practice.utils

import org.scalatest.{FunSpec, ShouldMatchers}

/**
  * @author caie
  * @since 16/8/31
  */
class FileUtilsSpec extends FunSpec with ShouldMatchers {

  describe("file util") {
    it("it can extract file name with correct file name suffix") {
      val name = "build.sbt"
      FileUtils.extractFileName(name) shouldBe "sbt"
    }

    it("it can extract file name with no suffix") {
      val name = "4TestFile"
      FileUtils.extractFileName(name) shouldBe "Empty-File-Type"
    }

  }
}
