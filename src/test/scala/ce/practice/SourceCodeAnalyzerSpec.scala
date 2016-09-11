package ce.practice

import org.scalatest.{FunSpec, ShouldMatchers}

/**
  * @author caie 
  * @since
  */
class SourceCodeAnalyzerSpec extends FunSpec with ShouldMatchers {

  describe("SourceCodeSpec Object") {
    it("it can create object instance by path") {
      // val path = System.getProperty("user.dir") + "/CodeAnalyzer/src/test/resources/4TestFile6.txt"

      val path = "/Users/zhpooer/ce-workspace/workspace/my-repo/scala/CodeAnalyzer/src/test/resources/4TestFile"
      val sourceCode = new SourceCodeAnalyzer {}.fromFile(path)
      sourceCode.name shouldBe "4TestFile"
      sourceCode.count shouldBe 113
    }
  }

}
