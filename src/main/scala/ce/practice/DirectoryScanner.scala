package ce.practice

import ce.practice.utils.FileUtils.Path

/**
  * @author caie
  * @since 16/9/7
  */
trait DirectoryScanner {

  def scan(path: Path): Seq[Path] = {
    import java.io._
    val file = new File(path)
    val files = file.listFiles
    files.foldLeft(Vector[Path]()) {
      (acc, f) => {
        if (f.isFile) {
          acc :+ f.getAbsolutePath
        } else {
          acc ++ scan(f.getAbsolutePath)
        }
      }
    }
  }

}
