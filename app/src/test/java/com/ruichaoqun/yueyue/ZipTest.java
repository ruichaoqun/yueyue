package com.ruichaoqun.yueyue;


import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZipTest {
    private static final String TAG = "ZipCheckUtil";
    private static String mAvntFile = "avnt/";
    private static String mMcuFile = "mcu.zip";
    private static String mSocFile = "soc.zip";
    private static String mNoteFile = "release_note.xml";

    private static List<String> mAvntZipFiles = Arrays.asList("mcu.zip","soc.zip","release_note.xml");

    private static List<String> mMcuZipFiles = Arrays.asList(
            "dsp/",
            "dsp/Eight_Speaker_ADV_DSP_Demo_iram_offset80_withheader.bin",
            "dsp/Six_Speaker_ADV_DSP_Demo_iram_offset140_withheader.bin",
            "dsp/Six_Speaker_ADV_DSP_Demo_iram_offset80_withheader.bin",
            "dsp/Four_Speaker_ADV_DSP_Demo_dram1_offset31_withheader.bin",
            "dsp/Four_Speaker_ADV_DSP_Demo_iram_offset125_withheader.bin",
            "dsp/Four_Speaker_ADV_DSP_Demo_iram_offset140_withheader.bin",
            "dsp/Six_Speaker_ADV_DSP_Demo_dram0_offset0_withheader.bin",
            "dsp/Four_Speaker_ADV_DSP_Demo_iram_offset110_withheader.bin",
            "dsp/Eight_Speaker_ADV_DSP_Demo_iram_offset95_withheader.bin",
            "dsp/Six_Speaker_ADV_DSP_Demo_dram0_offset15_withheader.bin",
            "dsp/Six_Speaker_ADV_DSP_Demo_iram_offset125_withheader.bin",
            "dsp/Six_Speaker_ADV_DSP_Demo_dram1_offset16_withheader.bin",
            "dsp/Eight_Speaker_ADV_DSP_Demo_iram_offset140_withheader.bin",
            "dsp/Four_Speaker_ADV_DSP_Demo_iram_offset80_withheader.bin",
            "dsp/Six_Speaker_ADV_DSP_Demo_iram_offset110_withheader.bin",
            "dsp/Eight_Speaker_ADV_DSP_Demo_dram0_offset15_withheader.bin",
            "dsp/Four_Speaker_ADV_DSP_Demo_dram1_offset16_withheader.bin",
            "dsp/Four_Speaker_ADV_DSP_Demo_iram_offset95_withheader.bin",
            "dsp/Eight_Speaker_ADV_DSP_Demo_dram0_offset0_withheader.bin",
            "dsp/Eight_Speaker_ADV_DSP_Demo_dram1_offset16_withheader.bin",
            "dsp/Six_Speaker_ADV_DSP_Demo_iram_offset95_withheader.bin",
            "dsp/Six_Speaker_ADV_DSP_Demo_dram1_offset31_withheader.bin",
            "dsp/Eight_Speaker_ADV_DSP_Demo_iram_offset125_withheader.bin",
            "dsp/Four_Speaker_ADV_DSP_Demo_dram0_offset0_withheader.bin",
            "dsp/Eight_Speaker_ADV_DSP_Demo_iram_offset110_withheader.bin",
            "dsp/Eight_Speaker_ADV_DSP_Demo_dram1_offset31_withheader.bin",
            "dsp/Dione_Arm_Ffs_nxp_pro_encrypted.bin",
            "dsp/Four_Speaker_ADV_DSP_Demo_dram0_offset15_withheader.bin",
            "flash_drv.crc",
            "flash_drv.hex",
            "mcu_app.crc",
            "mcu_app.hex",
            "OtaCheckVersion",
            "OtaCheckVersion.crc");
    private static List<String> mSocZipFiles = Arrays.asList(
            "BTFM.bin.new.dat.br",
            "BTFM.bin.patch.dat",
            "META-INF/com/android/metadata",
            "NON-HLOS.firmware.bin.new.dat.br",
            "NON-HLOS.firmware.bin.patch.dat",
            "NON-HLOS.modem.bin.new.dat.br",
            "NON-HLOS.modem.bin.patch.dat",
            "adspso.bin.new.dat.br",
            "adspso.bin.patch.dat",
            "appfs.img.sparse.new.dat.br",
            "appfs.img.sparse.patch.dat",
            "devcfg_auto.mbn.new.dat.br",
            "devcfg_auto.mbn.patch.dat",
            "earlycfgbackup.bin.new.dat.br",
            "earlycfgbackup.bin.patch.dat",
            "emmc_appsboot.mbn.new.dat.br",
            "emmc_appsboot.mbn.patch.dat",
            "iflytek.img.new.dat.br",
            "iflytek.img.patch.dat",
            "ifs2.img.new.dat.br",
            "ifs2.img.patch.dat",
            "mifs.img.new.dat.br",
            "mifs.img.patch.dat",
            "pmic.elf.new.dat.br",
            "pmic.elf.patch.dat",
            "rpm.mbn.new.dat.br",
            "rpm.mbn.patch.dat",
            "system.img.sparse.new.dat.br",
            "system.img.sparse.patch.dat",
            "system.new.dat.br",
            "system.patch.dat",
            "trampoline.img.new.dat.br",
            "trampoline.img.patch.dat",
            "tz.mbn.new.dat.br",
            "tz.mbn.patch.dat",
            "vehicledefcfg.img.new.dat.br",
            "vehicledefcfg.img.patch.dat",
            "vendor.new.dat.br",
            "vendor.patch.dat",
            "BTFM.bin.transfer.list",
            "META-INF/",
            "META-INF/com/google/android/update-binary",
            "META-INF/com/google/android/updater-script",
            "NON-HLOS.firmware.bin.transfer.list",
            "NON-HLOS.modem.bin.transfer.list",
            "adspso.bin.transfer.list",
            "appfs.img.sparse.transfer.list",
            "devcfg_auto.mbn.transfer.list",
            "earlycfgbackup.bin.transfer.list",
            "emmc_appsboot.mbn.transfer.list",
            "iflytek.img.transfer.list",
            "ifs2.img.transfer.list",
            "mifs.img.transfer.list",
            "pmic.elf.transfer.list",
            "rpm.mbn.transfer.list",
            "system.img.sparse.transfer.list",
            "system.transfer.list",
            "trampoline.img.transfer.list",
            "tz.mbn.transfer.list",
            "vehicledefcfg.img.transfer.list",
            "vendor.transfer.list",
            "META-INF/com/android/otacert"
    );

    @Test
    public void printFilesInFolder() {
        File folder = new File("/media/ts/ruichaoqun/default-user-55.12R010-20240407_030858-VB-fastboot-AB-mario_signed-avnt_full/avnt_full");

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        System.out.println("File: " + file.getName());
                        if (file.getName().toLowerCase().endsWith(".zip")) {
                            System.out.println("Contents of ZIP file " + file.getName() + ":");
                            try {
                                List<String> zipContents = getZipFileContents(file);
                                for (String entry : zipContents) {
                                    System.out.println(entry);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("Folder not found or not a directory.");
        }
    }

    public List<String> getZipFileContents(File zipFile) throws IOException {
        List<String> zipContents = new ArrayList<>();
        try (ZipFile zip = new ZipFile(zipFile)) {
            Enumeration<? extends ZipEntry> entries = zip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                zipContents.add(entry.getName());
            }
        }
        return zipContents;
    }

    @Test
    public void test2() {
        String zipFilePath = "/media/ts/ruichaoqun/default-user-55.12R010-20240407_030858-VB-fastboot-AB-mario_signed-avnt_full/avnt_full/avnt.zip"; // 替换为你的 zip 文件路径
        try {
            List<String> allFiles = getAllFilesInZip(zipFilePath);
            for (String fileName : allFiles) {
                System.out.println(fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkAvnt() {
        String filePath = "/media/ts/KINGSTON/ota/avnt.zip"; // 替换为你的 zip 文件路径
        boolean result = true;
        try {
            //List<String> allFiles = new ArrayList<>();
            ZipFile zipFile = new ZipFile(filePath);
            for (Enumeration<?> entries = zipFile.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                if (entry.getName().equals(mAvntFile)) {
                    continue;
                }
//                if (!entry.getName().endsWith(mMcuFile) && !entry.getName().endsWith(mSocFile)
//                    && !entry.getName().endsWith(mNoteFile) && !entry.getName().endsWith(mAvntFile)) {
//                    System.out.println("Verification contains illegal files in avnt zip: "
//                            + entry.getName());
//                    result = false;
//                }
                if (!mAvntZipFiles.contains(entry.getName().replace(mAvntFile,""))) {
                    System.out.println("Verification contains illegal files in avnt zip: "
                            + entry.getName());
//                    Log.e(TAG, "Verification contains illegal files in avnt zip: "
//                            + entry.getName());
                    result = false;
//                    break;
                }
                if (entry.getName().endsWith(".zip")) {
                    List<String> matchList;
                    if (entry.getName().endsWith(mMcuFile)) {
                        matchList = mMcuZipFiles;
                    } else {
                        matchList = mSocZipFiles;
                    }
                    InputStream in = zipFile.getInputStream(entry);
                    ZipInputStream zis = new ZipInputStream(in);
                    ZipEntry childEntry;
                    while ((childEntry = zis.getNextEntry()) != null) {
                        if (!matchList.contains(childEntry.getName())) {
                            System.out.println("Verification contains illegal files in "
                                    + entry.getName() + "  " + childEntry.getName());
//                            Log.e(TAG, "Verification contains illegal files in "
//                                    + entry.getName() + "  " + childEntry.getName());
                            result = false;
//                            break;
                        }
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            result = false;
        }
        System.out.println("result = " + result);
    }

    public  List<String> getAllFilesInZip(String zipFilePath) throws IOException {
        return getAllFilesInZip(new ZipFile(zipFilePath));
    }

    public static List<String> getAllFilesInZip(ZipFile zipFile) throws IOException {
        List<String> allFiles = new ArrayList<>();
        for (Enumeration<?> entries = zipFile.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = ((ZipEntry) entries.nextElement());
            allFiles.add(entry.getName());
            if (entry.getName().endsWith(".zip")) {
                InputStream in = zipFile.getInputStream(entry);
                ZipInputStream zis = new ZipInputStream(in);
                ZipEntry childEntry;
                while ((childEntry = zis.getNextEntry()) != null) {
                    allFiles.add(childEntry.getName());
                }
            }
        }
        return allFiles;
    }


    private static InputStream getInputStreamForEntry(ZipInputStream zis) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = zis.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        return new ByteArrayInputStream(baos.toByteArray());
    }
}
