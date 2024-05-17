//package com.makrushin.telegrambot.model;
//
//import jakarta.persistence.*;
//
//import java.util.Arrays;
//import java.util.Objects;
//
//@Entity
//public class Avatar {
//

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    //    В нем будем хранить путь до файла на вашем диске
//    private String filePath;
//    //    содержит информацию о размере файла в байтах
//    private long fileSize;
//    //    Тип файла
//    private String mediaType;
//    //    хранится сама информация о файле, представленная в массиве байтов.
//    @Lob
//    private byte[] data;
//
//    public Avatar(Long id, String filePath, long fileSize, String mediaType, byte[] data) {
//        this.id = id;
//        this.filePath = filePath;
//        this.fileSize = fileSize;
//        this.mediaType = mediaType;
//        this.data = data;
//    }
//
//    public Avatar() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getFilePath() {
//        return filePath;
//    }
//
//    public void setFilePath(String filePath) {
//        this.filePath = filePath;
//    }
//
//    public long getFileSize() {
//        return fileSize;
//    }
//
//    public void setFileSize(long fileSize) {
//        this.fileSize = fileSize;
//    }
//
//    public String getMediaType() {
//        return mediaType;
//    }
//
//    public void setMediaType(String mediaType) {
//        this.mediaType = mediaType;
//    }
//
//    public byte[] getData() {
//        return data;
//    }
//
//    public void setData(byte[] data) {
//        this.data = data;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Avatar avatar = (Avatar) o;
//        return fileSize == avatar.fileSize && Objects.equals(id, avatar.id) && Objects.equals(filePath, avatar.filePath) && Objects.equals(mediaType, avatar.mediaType) && Arrays.equals(data, avatar.data);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = Objects.hash(id, filePath, fileSize, mediaType);
//        result = 31 * result + Arrays.hashCode(data);
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        return "Avatar{" +
//                "id=" + id +
//                ", filePath='" + filePath + '\'' +
//                ", fileSize=" + fileSize +
//                ", mediaType='" + mediaType + '\'' +
//                ", data=" + Arrays.toString(data) +
//                '}';
//    }
//}
