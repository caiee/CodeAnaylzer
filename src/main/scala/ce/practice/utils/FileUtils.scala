package ce.practice.utils

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

}
