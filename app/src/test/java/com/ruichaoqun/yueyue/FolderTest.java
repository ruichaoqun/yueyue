package com.ruichaoqun.yueyue;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FolderTest {

    public static final String ANDROID_LOG_COLLECTOR = "Export_log_analysis.log";
    //记录android日志导出过程中的情况
    File logRecordLog;


    @Test
    public void saveOriginalAndroidLog() {
        String originalPath = "/media/ts/ruichaoqun/bugs/A55/GACAFF-19476/log-unknown-2023-12-12-08_20_49/log_backup";
        String usbPath = "/media/ts/ruichaoqun/bugs/A55/GACAFF-19476/log-unknown-2023-12-12-08_20_49";
        File file = new File(usbPath + File.separator + ANDROID_LOG_COLLECTOR);
        File originalFile = new File(originalPath);
        //原始android log日志文件不存在，报丢失
        if (!originalFile.exists()) {
            //return false;
        }
        File[] files = originalFile.listFiles();
        if (files == null || files.length == 0) {
            //原始android log日志文件不存在，报丢失
            //return false;
        }
        FileEntity fileEntity = new FileEntity(true, originalPath, originalFile.getName(), originalFile.getParent());

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            calculateOriginalFileLogs(fileEntity, writer, "");
            writer.flush();
            writer.close();
            System.out.println("File list generated successfully.");
        } catch (IOException e) {
            System.out.println("Failed to generate file list: " + e.getMessage());
        }
    }

    private void calculateOriginalFileLogs(FileEntity entity, BufferedWriter writer, String seq) throws IOException {
        File file = new File(entity.path);
        writer.write(seq + entity.fileName);
        writer.newLine();

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                return;
            }
            entity.child = new ArrayList<>();
            for (File child : files) {
                if (!child.exists()) {
                    return;
                }
                FileEntity childFileEntity;
                if (child.isFile()) {
                    childFileEntity = new FileEntity(false, child.getPath(), child.getName(), child.getParent());
                    entity.child.add(childFileEntity);
                    calculateOriginalFileLogs(childFileEntity, writer, seq + "    ");
                } else if (child.isDirectory()) {
                    childFileEntity = new FileEntity(true, child.getPath(), child.getName(), child.getParent());
                    entity.child.add(childFileEntity);
                    calculateOriginalFileLogs(childFileEntity, writer, seq + "    ");
                }
            }
        }
    }


    public boolean isAContainsB(FileEntity a,FileEntity b) {



        File filea = new File(a.path);
        File fileb = new File(b.path);
        if (a.fileName != b.fileName) {
            return false;
        }
        if (filea.isFile() && fileb.isFile()) {
            return a.fileName == b.fileName;
        }
        boolean isMatch = true;
        if (filea.isDirectory() && fileb.isDirectory()) {
            if (a.fileName == b.fileName) {
                return false;
            }
        }

    }

    public class FileEntity {
        public final boolean isFolder;
        public final String path;
        public final String fileName;
        public final String parentPath;
        public List<FileEntity> child;

        public FileEntity(boolean isFolder, String path, String fileName, String parentPath) {
            this.isFolder = isFolder;
            this.fileName = fileName;
            this.path = path;
            this.parentPath = parentPath;
        }
    }
}
