package com.ra.model.repository;

import com.ra.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

// 1. Khai báo giao diện:
// public interface ProductRepository: Dòng này khai báo một giao diện công cộng có tên ProductRepository. Các giao diện đóng vai trò là bản thiết kế cho các lớp, xác định một tập hợp các phương thức mà các lớp triển khai phải cung cấp.
//2. Kế thừa từ JpaRepository:
// extends JpaRepository<Product, Long>: Phần này chỉ ra rằng ProductRepositorykế thừa từ JpaRepository, một giao diện cốt lõi trong Spring Data JPA cung cấp một bộ phương thức phong phú để làm việc với các thực thể JPA.
// <Product, Long>: Các loại chung này chỉ định rằng:
// Giao diện xử lý các loại thực thể Product(có thể là một lớp đại diện cho dữ liệu sản phẩm).
// Khóa chính của các thực thể này thuộc loại Long(số nguyên dài).
// 3. Chức năng:
// Bằng cách mở rộng JpaRepository, ProductRepositorytự động có quyền truy cập vào nhiều phương pháp khác nhau để thực hiện các thao tác CRUD (Tạo, Đọc, Cập nhật, Xóa) trên Productcác thực thể, cũng như khả năng phân trang và sắp xếp. Dưới đây là một số ví dụ phổ biến:
// save(Product product): Lưu một sản phẩm mới vào cơ sở dữ liệu.
// findById(Long id): Truy xuất một sản phẩm bằng khóa chính của nó.
// findAll(): Trả về tất cả các sản phẩm từ cơ sở dữ liệu.
// deleteById(Long id): Xóa một sản phẩm có ID được chỉ định.
// findAll(Sort sort): Tìm nạp sản phẩm có áp dụng sắp xếp.
// Page<Product> findAll(Pageable pageable): Truy xuất một trang sản phẩm có phân trang.
//4. Phương thức truy vấn tùy chỉnh:
// Bạn có thể mở rộng hơn nữa ProductRepositoryđể tạo các phương thức truy vấn tùy chỉnh phù hợp với nhu cầu truy cập dữ liệu cụ thể. Spring Data JPA thường có thể rút ra việc triển khai truy vấn dựa trên tên phương thức và từ khóa. Ví dụ:
// List<Product> findByName(String name): Tìm kiếm sản phẩm theo tên của chúng.
// List<Product> findByPriceLessThan(BigDecimal price): Tìm kiếm các sản phẩm có giá thấp hơn một giá trị nhất định.

// => Tương tự nhue bên category