package ce.practice

import java.io.File

import org.scalatest.{FunSpec, ShouldMatchers}

/**
  * @author caie
  * @since 16/8/30
  */
class CodebaseAnalyzerSpec extends FunSpec with ShouldMatchers {

  describe("CodebaseAnalyzer") {
    // 递归读取指定目录, 获取文件个数信息
    it("it can read specified directory recursion and get file count") {
      val path = "/Users/zhpooer/ce-workspace/workspace/my-repo/scala/CodeAnalyzer/src/test/resources"
      val direct = new CodebaseAnalyzer with DirectoryScanner with SourceCodeAnalyzer
      val scanResult = direct.scanFromPath(path)

      scanResult.size shouldBe 3
      scanResult should contain theSameElementsAs Map("properties" -> 2, "Empty-File-Type" -> 1, "txt" -> 2)

    }

    /**
      * 监测异常用法:
      * an[IllegalArgumentException] should be thrownBy DirectoryScanner.scan(path)
      */
    it("it read file ") {

      val path = "/Users/zhpooer/ce-workspace/workspace/my-repo/scala/CodeAnalyzer/" +
        "src/test/scala/ce/practice/SourceCodeSpec"

      val file = new File(path)
      println(s" file path: ${file.getPath}")

      println(s" file absolute path: ${file.getAbsolutePath}")

    }
  }

}
