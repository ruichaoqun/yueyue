package com.ruichaoqun.yueyue

import org.junit.Test

import org.junit.Assert.*
import java.io.File
import java.util.zip.ZipFile

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun printFilesInFolder() {
        val folderPath = "/media/ts/ruichaoqun/default-user-55.12R010-20240407_030858-VB-fastboot-AB-mario_signed-avnt_full/avnt_full/avnt" // 更改为你要打印的文件夹路径
        val folder = File(folderPath)

        if (folder.exists() && folder.isDirectory) {
            folder.listFiles()?.forEach { file ->
                if (file.isFile) {
                    println("File: ${file.name}")
                    if (file.extension.equals("zip", ignoreCase = true)) {
                        println("Contents of ZIP file ${file.name}:")
                        printFilesInZip(file)
                    }
                }
            }
        } else {
            println("Folder not found or not a directory.")
        }
    }

    fun printFilesInZip(zipFile: File) {
        ZipFile(zipFile).use { zip ->
            val entries = zip.entries()
            while (entries.hasMoreElements()) {
                val entry = entries.nextElement()
                println(""""${entry.name}"""")
            }
        }
    }

}