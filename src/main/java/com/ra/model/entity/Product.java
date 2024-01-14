package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private Integer quantity;
    private Float price;
    private String image;
    @ManyToOne
    @JoinColumn(name = "categoryId",referencedColumnName = "id")
    private Category category;
    private Boolean productStatus = true;
//    @ManyToOnechú thích:
//    Đánh dấu một trường là thể hiện mối quan hệ nhiều-một với một thực thể khác.
//    Trong trường hợp này, nó gợi ý rằng nhiều sản phẩm có thể thuộc về một danh mục duy nhất.

//    @JoinColumn(name = "categoryId", referencedColumnName = "id")chú thích:
//    Định cấu hình cột khóa ngoại tạo liên kết giữa hai thực thể trong cơ sở dữ liệu.
//    Chi tiết:
//    name = "categoryId": Chỉ định tên của cột khóa ngoại trong bảng của thực thể hiện tại (có thể là bảng "sản phẩm").
//    referencedColumnName = "id": Cho biết cột này tham chiếu đến cột khóa chính (có tên là "id") trong bảng của thực thể đích (có thể là bảng "danh mục").

//    private Category category;
//    Thể hiện mối quan hệ thực tế với Categorythực thể.
//    Mỗi đối tượng sản phẩm sẽ giữ một tham chiếu đến đối tượng danh mục liên quan của nó.

//    private Boolean productStatus = true;
//    Tách biệt khỏi mối quan hệ, trường này cho biết trạng thái của sản phẩm, có thể là nó đang hoạt động hay không.
//    Giá trị mặc định được đặt thành true.
}
