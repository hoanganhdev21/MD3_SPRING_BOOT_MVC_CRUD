package com.ra.service;

import com.ra.model.entity.Category;
import com.ra.model.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired // @Autowiredthích sẽ tự động đưa nội dung này CategoryRepositoryvào dịch vụ, thúc đẩy việc ghép nối lỏng lẻo và nội dung phụ thuộc.
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Boolean save(Category category) {
        categoryRepository.save(category);
        return true;
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}

// Các yếu tố chính:
//
//@Service: Chú thích này đánh dấu lớp này là một dịch vụ Spring, nghĩa là nó được quản lý bởi Spring framework và có thể được đưa vào các thành phần khác.
//CategoryServiceImpl: Tên lớp, cho biết đây là một triển khai của CategoryServicegiao diện (không được hiển thị ở đây).
//CategoryRepository: Một kho lưu trữ tự động được sử dụng để tương tác với cơ sở dữ liệu nhằm truy xuất và lưu trữ các đối tượng Danh mục.

//Phương pháp:
//
//getAll(): Trả về danh sách tất cả các danh mục từ kho lưu trữ.
//save(Danh mục danh mục): Lưu danh mục mới vào kho lưu trữ và trả về giá trị đúng khi thành công.
//findById(Long id): Truy xuất một danh mục theo ID của nó, trả về null nếu không tìm thấy.
//delete(Long id): Xóa một danh mục có ID được chỉ định khỏi kho lưu trữ.

// Những điểm chính:
//
//Chú @Autowiredthích sẽ tự động đưa nội dung này CategoryRepositoryvào dịch vụ, thúc đẩy việc ghép nối lỏng lẻo và nội dung phụ thuộc.
//Các phương thức findAll(), save(), findById()và deleteById()có thể được cung cấp bởi CategoryRepository, thể hiện cách lớp dịch vụ có thể tận dụng kho lưu trữ để quản lý quyền truy cập dữ liệu.