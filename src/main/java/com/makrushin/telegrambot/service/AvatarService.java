//package com.makrushin.telegrambot.service;

//import com.makrushin.telegrambot.model.Avatar;
//import com.makrushin.telegrambot.repository.AvatarRepository;
//import jakarta.transaction.Transactional;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Path;
//
//import static io.swagger.v3.core.util.AnnotationsUtils.getExtensions;
//import static java.nio.file.StandardOpenOption.CREATE_NEW;

//@Service
//@Transactional
//public class AvatarService {
//
//
//
//        @Value("${path.to.avatars.folder}")
//        private String avatarsDir;
//
//        private final AvatarRepository avatarRepository;
////        private final StudentService studentService;
//        Logger logger = LoggerFactory.getLogger(AvatarService.class);
//
//        public AvatarService(AvatarRepository avatarRepository) {
//            this.avatarRepository = avatarRepository;
////            this.studentService = studentService;
//        }
//
//        public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
//            logger.info("Was invoked method for uploadAvatar");
////            Student student = studentService.findStudent(studentId);
//            int count=0;
////        хранит путь до директории с загружаемыми файлами.
//            Path filePath = Path.of(avatarsDir, (count++) + "." + getExtensions(avatarFile.getOriginalFilename()));
////        Создаем нужную нам директорию для хранения данных и удаляем из нее файл, если он уже присутствует там.
//            Files.createDirectories(filePath.getParent());
//            Files.deleteIfExists(filePath);
////        конструкция нам нужна, чтобы следить за закрытием открытых ресурсов
//            try (
////                Чтение файла. Открываем входной поток командой avatarFile.getInputStream() и начинаем считывать данные
//                    InputStream is = avatarFile.getInputStream();
////                запись файла
//                    OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
////                буферизация для чтения не по байтно, а частями заданного размера
//                    BufferedInputStream bis = new BufferedInputStream(is, 1024);
////                для записи
//                    BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
//            ) {
////            запустить сам процесс передачи данных методом transferTo.
//                bis.transferTo(bos);
//            }
//            Avatar avatar = new Avatar();
////            avatar.setStudent(student);
//            avatar.setFilePath(filePath.toString());
//            avatar.setFileSize(avatarFile.getSize());
//            avatar.setMediaType(avatarFile.getContentType());
//            avatar.setData(avatarFile.getBytes());
//            avatarRepository.save(avatar);
//        }
//
//    private String getExtensions(String fileName) {
//        logger.info("Was invoked method for getExtensions");
//        return fileName.substring(fileName.lastIndexOf(".") + 1);
//    }
//}
