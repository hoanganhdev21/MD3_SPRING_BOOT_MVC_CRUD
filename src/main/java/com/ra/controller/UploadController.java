package com.ra.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

// Upload file
@Controller // cho biết đó là bộ điều khiển Spring MVC, xử lý các yêu cầu web
public class UploadController {
    @Value("${path-upload}")
    private String pathUpload; // Lưu trữ đường dẫn đã định cấu hình để tải tệp lên (được chèn bằng cách sử dụng @Value

    // Trả về 1 giao diện để người ta thực hiện upload
    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }

    @PostMapping("/upload")
    public String postUpload(@RequestParam("img") MultipartFile file){
        // xu ly upload
        String fileName = file.getOriginalFilename();

        try {
            FileCopyUtils.copy(file.getBytes(),new File(pathUpload+fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }

    // Ánh xạ: @PostMapping("/upload") (xử lý các yêu cầu POST tới URL "/upload", có thể dùng để gửi tệp)
    // Chức năng:
    // Truy xuất tệp đã tải lên từ yêu cầu bằng cách sử dụng @RequestParam("img") MultipartFile file.
    // Trích xuất tên tệp gốc bằng cách sử dụng file.getOriginalFilename().
    // Cố gắng lưu tệp vào đường dẫn đã chỉ định bằng cách sử dụng FileCopyUtils.copy().
    // Ném ra RuntimeException nếu IOException xảy ra trong quá trình lưu.
    // Chuyển hướng người dùng quay lại trang chủ ("/") bằng cách sử dụng return "redirect:/";.

}