package ce.practice

import scala.io.Source

/**
  * @author caie 
  * @since
  */
class SourceCode(val path: String, val name: String, private val lines: List[String]) {
  def count: Int = lines.length
}

object SourceCode {
  type Path = String

  def fromFile(path: Path): SourceCode = {
    val source = Source.fromFile(path)
    new SourceCode(path, path.split("/").last, source.getLines().toList)
  }
}

