package ce.codeanalyzer.utils

import java.io.{File, PrintWriter}

/**
  * @author caie
  * @since 16/8/31
  */
object FileUtils {

  type Path = String

  val emptyType = "Empty-File-Type"

  def extractFileName(fileName: Path): String = {
    if (fileName.contains(".")) {
      fileName.split("\\.").last
    } else {
      emptyType
    }
  }

  def writeFile(path: Path, content: String): Unit = {
    val writer = new PrintWriter(new File(path))
    writer.write(content)
    writer.close()
  }

}
