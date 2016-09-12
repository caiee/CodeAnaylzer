package ce.codeanalyzer

import ce.codeanalyzer.utils.FileUtils
import ce.codeanalyzer.utils.FileUtils.Path

/**
  * @author caie
  * @since 16/8/30
  */
trait CodebaseAnalyzer {

  this: DirectoryScanner with SourceCodeAnalyzer =>

  def scanFromPath(path: Path): Map[String, Int] = {
    scan(path).groupBy(FileUtils.extractFileName).mapValues(_.length)
  }

  def getSourceCode(path: Path): SourceCodeInfo = {
    fromFile(path)
  }

  /**
    * 分析平均代码行数
    */
  def getAverageCodeLines(path: Path): Int = {
    val sourceCodeInfos = scan(path).map(fromFile(_)).toList
    sourceCodeInfos.map(_.count).sum / sourceCodeInfos.length
  }

  /**
    * 分析最长文件有多少行
    */
  def getLongestFile(path: Path): Int = {
    scan(path).map(fromFile(_)).map(_.count).max
  }

  /**
    * 得到最长的五个文件名
    */
  def getTopFiveLongestFile(path: Path): Seq[SourceCodeInfo] = {
    scan(path).map(fromFile(_))
      .sortWith((codeInfo1, codeInfo2) => codeInfo1.count.compareTo(codeInfo2.count) > 0).toList.take(5)
  }

  def writeResult(path: Path, content: String): Unit = {
    FileUtils.writeFile(path, content)
  }

}
