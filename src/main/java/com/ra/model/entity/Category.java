package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    private Boolean status = true;
    @OneToMany(mappedBy = "category") // Nó được sử dụng để xác định mối quan hệ một-nhiều giữa hai thực thể trong ứng dụng JPA.
//    mappedBy = "category": map dữ liệu sang table product
//    Chỉ định rằng mối quan hệ là hai chiều (có nghĩa là nó được duy trì từ cả hai phía).
//    Khai báo rằng cột khóa ngoại nằm ở phía "nhiều" của mối quan hệ. "danh mục" đề cập đến tên của thuộc tính trên thực thể bên "nhiều" chứa tham chiếu đến thực thể bên "một"
    private List<Product> products;
//    private Set<Product> products = new HashSet<>(); => có thể dùng thay => private List<Product> products;
}
