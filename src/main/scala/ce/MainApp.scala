package ce

import java.io.File

import ce.practice.{CodebaseAnalyzer, DirectoryScanner, SourceCodeAnalyzer}

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
    if (file.isFile) {
      // file
      val sourceCode = new SourceCodeAnalyzer {}.fromFile(path)
      println(s"name: ${sourceCode.name}     lines: ${sourceCode.count}")
    } else {
      // directory, to Scan
      val directoryScanner = new CodebaseAnalyzer with DirectoryScanner with SourceCodeAnalyzer
      directoryScanner.scanFromPath(path).foreach {
        case (fileType, count) => println(s"fileType: $fileType, count: $count")
      }
    }
  }
}
