package ce.codeanalyzer

import ce.codeanalyzer.utils.FileUtils.Path

/**
  * @author caie
  * @since 16/9/7
  */
trait DirectoryScanner {

  val ignoreFolders = List[String](
    ".git",
    "target",
    ".idea"
  )

  def scan(path: Path): Seq[Path] = {
    import java.io._
    val file = new File(path)
    val files = file.listFiles
    files.foldLeft(Vector[Path]()) {
      (acc, f) => {
        if (ignoreFolders.contains(f.getName)) {
          // ignore useless directory
          acc
        } else {
          if (f.isFile) {
            acc :+ f.getAbsolutePath
          } else {
            acc ++ scan(f.getAbsolutePath)
          }
        }
      }
    }
  }

}
