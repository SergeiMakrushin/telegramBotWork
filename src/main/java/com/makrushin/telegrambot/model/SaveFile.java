package com.makrushin.telegrambot.model;

import jakarta.persistence.*;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class SaveFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

@Lob
    private byte[] bytes;

    public SaveFile() {
    }

    public SaveFile(Integer id, byte[] bytes) {
        this.id = id;
        this.bytes = bytes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaveFile saveFile = (SaveFile) o;
        return Objects.equals(id, saveFile.id) && Arrays.equals(bytes, saveFile.bytes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id);
        result = 31 * result + Arrays.hashCode(bytes);
        return result;
    }

    @Override
    public String toString() {
        return "SaveFile{" +
                "id=" + id +
                ", bytes=" + Arrays.toString(bytes) +
                '}';
    }
}
