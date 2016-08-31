package ce.practice

import java.io.File

import ce.practice.SourceCode.Path
import ce.practice.utils.FileUtils
import ce.practice.utils.FileUtils.FileType

/**
  * @author caie
  * @since 16/8/30
  */
class DirectoryScanner {

  def scan(path: Path): Map[FileType, Int] = {
    import java.io._
    val file = new File(path)
    val files = file.listFiles
    files.foldLeft(Map[FileType, Int]()) {
      (acc, f) => {
        accumulator(acc, f)
      }
    }
  }

  def accumulator(acc: Map[FileType, Int], f: File): Map[FileType, Int] = {
    if (f.isFile) {
      val fileName: String = FileUtils.extractFileName(f.getName)
      if (acc.contains(fileName))
        acc.updated(fileName, acc(fileName) + 1)
      else
        acc + (fileName -> 1)
    } else {
      merge(acc, scan(f.getPath))
    }
  }

  def merge(acc: Map[FileType, Int], scanMap: Map[FileType, Int]): Map[FileType, Int] = {
    scanMap.foldLeft(acc) {
      case (acc1, (scanK, scanV)) =>
        if (acc1.contains(scanK))
          acc1.updated(scanK, acc1(scanK) + scanV)
        else
          acc1 + (scanK -> scanV)
    }
  }
}
