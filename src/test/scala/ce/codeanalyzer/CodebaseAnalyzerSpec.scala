package ce.codeanalyzer

import java.io.File

import ce.codeanalyzer.utils.FileUtils.Path
import org.scalatest.{FunSpec, ShouldMatchers}

/**
  * @author caie
  * @since 16/8/30
  */
class CodebaseAnalyzerSpec extends FunSpec with ShouldMatchers {

  val codebaseAnalyzer = new CodebaseAnalyzer with DirectoryScanner with SourceCodeAnalyzer {
    override def scan(path: Path): Seq[Path] = List("hello.scala", "world.scala", "CodeAnalyzer.scala", "build.sbt")

    override def processFile(path: Path): SourceCodeInfo = path match {
      case "hello.scala" => SourceCodeInfo(path, path, 10)
      case "world.scala" => SourceCodeInfo(path, path, 20)
      case "CodeAnalyzer.scala" => SourceCodeInfo(path, path, 30)
      case "build.sbt" => SourceCodeInfo(path, path, 20)
    }
  }

  describe("CodebaseAnalyzer") {
    // 递归读取指定目录, 获取文件个数信息
    it("can read specified directory recursion and get file count") {
      val path = "/Users/zhpooer/ce-workspace/workspace/my-repo/scala/CodeAnalyzer/src/test/resources"
      val direct = new CodebaseAnalyzer with DirectoryScanner with SourceCodeAnalyzer
      val scanResult = direct.scanFromPath(path)

      scanResult.size shouldBe 3
      scanResult should contain theSameElementsAs Map("properties" -> 2, "Empty-File-Type" -> 1, "txt" -> 5)

    }

    /**
      * 监测异常用法:
      * an[IllegalArgumentException] should be thrownBy DirectoryScanner.scan(path)
      */
    it("read file ") {

      val path = "/Users/zhpooer/ce-workspace/workspace/my-repo/scala/CodeAnalyzer/" +
        "src/test/scala/ce/practice/SourceCodeSpec"

      val file = new File(path)
      println(s" file path: ${file.getPath}")

      println(s" file absolute path: ${file.getAbsolutePath}")

    }

    it("can calculate the average of code lines ") {

      // codebaseAnalyzer.getAverageCodeLines("whatever path") shouldBe 20
    }

    it("can calculate longest file and return longest file lines") {
      val path = "/Users/zhpooer/ce-workspace/workspace/my-repo/scala/CodeAnalyzer/src/test/resources"
      val codebaseAnalyzer = new CodebaseAnalyzer with DirectoryScanner with SourceCodeAnalyzer
      // codebaseAnalyzer.getLongestFile(path) shouldBe 113
    }

    it("can show the top ten longest file") {

      val list = (1 to 11).map(i => SourceCodeInfo(s"$i.scala", s"$i.scala", i)).toList
      val files = codebaseAnalyzer.top5Files(list)
      files should have size 10
      files should not contain SourceCodeInfo("1.scala", "1.scala", 1)
    }

  }

}
