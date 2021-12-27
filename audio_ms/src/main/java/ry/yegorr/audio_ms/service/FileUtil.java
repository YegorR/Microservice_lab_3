package ry.yegorr.audio_ms.service;

import ry.yegorr.audio_ms.exception.*;

import java.io.*;

/**
 * User: egorr<br>
 * Date: 27.12.2021<br>
 * Time: 19:31<br>
 * todo name and feature
 */
public class FileUtil {

    public static void checkDirectory(String rootPath) throws ApplicationException {
        File file = new File(rootPath);
        if (!file.exists()) {
            file.mkdir();
        }
        if (!file.isDirectory()) {
            throw new ApplicationException("Document path is not available for saving documents");
        }
    }

    public static class Document {

        private byte[] file;

        private String filename;

        public byte[] getFile() {
            return file;
        }

        public void setFile(byte[] file) {
            this.file = file;
        }

        public String getFilename() {
            return filename;
        }

        public Document(byte[] file, String filename) {
            this.file = file;
            this.filename = filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }
    }
}
