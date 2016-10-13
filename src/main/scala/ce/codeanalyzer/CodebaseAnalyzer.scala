package ce.codeanalyzer

import ce.codeanalyzer.utils.FileUtils
import ce.codeanalyzer.utils.FileUtils.Path

/**
  * @author caie
  * @since 16/8/30
  */

case class CodebaseInfo(x: Map[String, Int], longestFile: SourceCodeInfo,
                        avgLineCount: Int,
                        topFiveLongestFile: Seq[SourceCodeInfo])

trait CodebaseAnalyzer {

  this: DirectoryScanner with SourceCodeAnalyzer =>

  def scanFromPath(path: Path): Map[String, Int] = {
    scan(path).groupBy(FileUtils.extractFileName).mapValues(_.length)
  }

  def getSourceCode(path: Path): SourceCodeInfo = {
    processFile(path)
  }

  /**
    * 分析平均代码行数
    */
  private[codeanalyzer] def getAverageCodeLines(sourceCodeInfoes: List[SourceCodeInfo], path: Path): Int = {
    sourceCodeInfoes.map(_.count).sum / sourceCodeInfoes.length
  }

  /**
    * 分析最长文件有多少行
    */
  private[codeanalyzer] def getLongestFile(path: Path, files: Map[String, Int]): Int = {
    scan(path).map(processFile(_)).map(_.count).max
  }

  /**
    * 得到最长的五个文件名
    */
  def top5Files(infos: Seq[SourceCodeInfo]): Seq[SourceCodeInfo] = {
    infos.sortWith((codeInfo1, codeInfo2) => codeInfo1.count.compareTo(codeInfo2.count) > 0).toList.take(10)
  }

  def writeResult(path: Path, content: String): Unit = {
    FileUtils.writeFile(path, content)
  }

}
