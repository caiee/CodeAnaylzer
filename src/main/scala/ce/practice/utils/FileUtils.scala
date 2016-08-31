package ce.practice.utils

/**
  * @author caie
  * @since 16/8/31
  */
object FileUtils {

  type FileType = String

  val emptyType = "Empty-File-Type"

  def extractFileName(fileName: String): String = {
    if (fileName.contains(".")) {
      fileName.split("\\.").last
    } else {
      emptyType
    }
  }

}
