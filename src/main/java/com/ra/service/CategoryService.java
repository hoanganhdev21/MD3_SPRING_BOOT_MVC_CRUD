package com.ra.service;

import com.ra.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Boolean save(Category category);
    Category findById(Long id);
    void delete(Long id);
}


// List<Category> getAll();
//
//Truy xuất danh sách tất cả các danh mục có sẵn.
//Trả về một tập hợp Categorycác đối tượng.
//Boolean save(Category category);
//
//Lưu một Categoryđối tượng nhất định vào bộ lưu trữ liên tục.
//Trả về truenếu thao tác lưu thành công, falsengược lại.
//Category findById(Long id);
//
//Truy xuất một Categoryđối tượng cụ thể dựa trên mã định danh (ID) duy nhất của nó.
//Trả về Categoryđối tượng nếu tìm thấy hoặc nullkhông tìm thấy.
//void delete(Long id);
//
//Xóa Categoryđối tượng có ID được chỉ định khỏi bộ lưu trữ liên tục.
//Không trả về bất kỳ giá trị nào.