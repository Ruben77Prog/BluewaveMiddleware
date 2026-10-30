package com.ruben.bluewave.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

    public static void write(byte[] datos, String fileName, boolean append)
            throws IOException {

        File f = new File(fileName);
        File carpeta = f.getParentFile();

        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }

        try (FileOutputStream fos = new FileOutputStream(f, append)) {
            fos.write(datos);
            fos.flush();
        }
    }

    public static int read(String fileName, byte[] buffer)
            throws IOException {

        try (FileInputStream fis = new FileInputStream(fileName)) {
            return fis.read(buffer);
        }
    }

    public static byte[] readFully(String fileName)
            throws IOException {

        try (FileInputStream fis = new FileInputStream(fileName)) {

            byte[] datos = new byte[fis.available()];
            fis.read(datos);

            return datos;
        }
    }
}