package com.ra.model.repository;

import com.ra.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
// 1. Khai báo giao diện:
// public interface CategoryRepository: Dòng này khai báo một giao diện có tên CategoryRepository. Các giao diện trong Java xác định một hợp đồng để các lớp triển khai, chỉ định các phương thức mà chúng phải cung cấp.
// 2. Kế thừa từ JpaRepository:
// extends JpaRepository<Category, Long>: Phần này chỉ ra rằng CategoryRepositorykế thừa từ JpaRepository, một giao diện cốt lõi được cung cấp bởi Spring Data JPA. Kế thừa này cấp quyền truy cập vào các phương thức truy cập dữ liệu khác nhau để làm việc với kho dữ liệu dựa trên JPA.
// 3. Thông số loại chung:
// <Category, Long>: Các tham số loại này chỉ định loại thực thể và loại khóa chính của nó:
// Category: Đây là thực thể mà kho lưu trữ quản lý. Nó có thể đại diện cho khái niệm "Danh mục" trong miền ứng dụng của bạn.
// Long: Đây là loại khóa chính dùng để nhận dạng duy nhất các thực thể Danh mục trong cơ sở dữ liệu.
// 4. Thân trống rỗng:
// Phần thân của giao diện trống trong trường hợp này, nghĩa là nó không khai báo bất kỳ phương thức bổ sung nào ngoài các phương thức được kế thừa từ JpaRepository
