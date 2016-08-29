package ce

import ce.practice.SourceCode

/**
  * @author caie
  * @since 16/8/29
  */
object MainApp extends App {
  if (args.length < 1) {
    println("please input path")
  } else {
    val sourceCode = SourceCode.fromFile(args(0))
    println(s"name: ${sourceCode.name}     lines: ${sourceCode.count}")
  }
}
