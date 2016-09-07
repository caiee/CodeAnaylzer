package ce.practice

import ce.practice.utils.FileUtils
import ce.practice.utils.FileUtils.Path

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

}
