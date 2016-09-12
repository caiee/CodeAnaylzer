package ce

import java.io.File

import ce.codeanalyzer.utils.FileUtils
import ce.codeanalyzer.{CodebaseAnalyzer, DirectoryScanner, SourceCodeAnalyzer}

/**
  * @author caie
  * @since 16/8/29
  */
object MainApp extends App {
  if (args.length < 1) {
    println("please input path")
  } else {
    val path = args(0)
    val file = new File(path)
    val analyzer = new CodebaseAnalyzer with DirectoryScanner with SourceCodeAnalyzer
    if (file.isFile) {
      // file
      val sourceCode = analyzer.getSourceCode(path)
      println(s"name: ${sourceCode.name}     lines: ${sourceCode.count}")
    } else {
      // directory, to Scan
      analyzer.scanFromPath(path).foreach {
        case (fileType, count) => println(s"fileType: $fileType, count: $count")
      }

      println()

      println(" 下面是行数最多的五个文件: ")


      val content = analyzer.getTopFiveLongestFile(path)
        .map(codeInfo => "file path: " + codeInfo.path + ", total lines: " + codeInfo.count)
        .mkString("\n")

      println(content)

      val destPath: String = System.getProperty("user.dir") + "/result.txt"
      FileUtils.writeFile(destPath, content)
      print(s"结果已经输出到 $destPath 路径中, 可以在该文件中查看.")

    }
  }
}
