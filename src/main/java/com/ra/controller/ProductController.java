package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.service.CategoryService;
import com.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {
    @Value("${path-upload}") // Đưa giá trị của path-uploadthuộc tính từ tệp cấu hình, biểu thị đường dẫn tải tệp lên.
    private String pathUpload;
    // Đưa vào các phiên bản ProductServicevà CategoryServiceđể tương tác với dữ liệu sản phẩm và danh mục.
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/product")
    public String product(Model model){
        List<Product> list = productService.getAll();
        model.addAttribute("list",list);
        return "product/index";
    }
    @GetMapping("/add-product")
    public String save(Model model){ // Hàm này khai báo một phương thức có tên savetrả về một Stringgiá trị và lấy một Modelđối tượng làm tham số. Được Modelsử dụng để truyền dữ liệu từ bộ điều khiển đến chế độ xem.
        Product product = new Product(); // Dòng này tạo một phiên bản trống của Productlớp, có khả năng đại diện cho sản phẩm đang được thêm vào.
        List<Category> categories  = categoryService.getAll(); // Dòng này gọi một phương thức có tên getAll()trên thành phần dịch vụ có tên categoryServiceđể truy xuất danh sách các danh mục có sẵn. Điều này cho thấy ứng dụng cho phép gán sản phẩm vào các danh mục.
        model.addAttribute("categories", categories); // Dòng này thêm danh sách các danh mục vào bên Modeldưới tên thuộc tính "danh mục". Điều này làm cho các danh mục có thể truy cập được trong dạng xem để hiển thị hoặc lựa chọn.
        model.addAttribute("product",product); // Điều này cũng thêm phiên bản trống Productvào , có khả năng được sử dụng để liên kết dữ liệu đầu vào của người dùng trong biểu mẫu.Model
        return "product/add";
    }
    @PostMapping("/add-product")
    public String create(@ModelAttribute("product") Product product, @RequestParam("img") MultipartFile file){
        // @ModelAttribution("product") Sản phẩm sản phẩm : Đối số này liên kết dữ liệu từ biểu mẫu HTML (với thuộc tính mô hình "sản phẩm") với một Productđối tượng, đại diện cho sản phẩm sẽ được tạo.
        // @RequestParam("img") MultipartFile file : Đối số này truy xuất tệp hình ảnh đã tải lên từ yêu cầu, sử dụng tên tham số "img". Nó được gói gọn trong một MultipartFile đối tượng.

        // Upload File
        String fileName = file.getOriginalFilename(); // Lấy tên file gốc của file đã tải lên.
        try {
            FileCopyUtils.copy(file.getBytes(),new File(pathUpload+fileName));
            // FileCopyUtils.copy(...) : Cố gắng sao chép nội dung của tệp vào một đường dẫn được chỉ định trên máy chủ.
            // pathUpload : Biểu thị đường dẫn được xác định trước nơi các tệp đã tải lên sẽ được lưu trữ.
            // fileName : Tên tệp gốc được sử dụng để tạo đường dẫn tệp đích.

            // Save File in Data
            product.setImage(fileName); // Đặt imagethuộc tính của Productđối tượng thành tên tệp đã tải lên, lưu trữ tham chiếu hình ảnh để truy xuất sau này.

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        productService.save(product); // Gọi một dịch vụ (có lẽ chịu trách nhiệm về tương tác cơ sở dữ liệu) để duy trì Productđối tượng với tên tệp hình ảnh liên quan của nó.
        return "redirect:/product";
    }

    @GetMapping("/product/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        productService.delete(id);
        redirectAttrs.addFlashAttribute("success", "Xóa sản phẩm thành công");
        return "redirect:/product";
    }


    @GetMapping("product/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Product product = productService.findById(id); // Dòng này gọi một phương thức dịch vụ, có thể là từ một lớp dịch vụ, để truy xuất sản phẩm có ID được chỉ định từ nguồn dữ liệu
        List<Category> categories = categoryService.getAll(); // dòng này tìm nạp danh sách tất cả các danh mục có sẵn, có lẽ để sử dụng trong menu thả xuống hoặc menu lựa chọn trên trang chỉnh sửa.
        model.addAttribute("product", product); // Dòng này thêm đối tượng sản phẩm được truy xuất vào mô hình Spring MVC, làm cho nó có thể truy cập được vào mẫu xem để hiển thị.
        model.addAttribute("categories", categories); // Dòng này thêm danh sách các danh mục vào mô hình, cũng để sử dụng trong mẫu xem.
        return "/product/edit";
    }

    @PostMapping("/update-product")
    public String update(@ModelAttribute("product") Product product,
                         RedirectAttributes redirectAttrs,
                         @RequestParam("img")MultipartFile file) {
        // @ModelAttribute("product") Product product: Liên kết một đối tượng Sản phẩm từ dữ liệu biểu mẫu, đại diện cho sản phẩm sẽ được cập nhật.
        // RedirectAttributes redirectAttrs: Được sử dụng để chuyển thông báo thành công sau khi chuyển hướng.
        // @RequestParam("img") MultipartFile file: Thể hiện tệp hình ảnh được tải lên, có khả năng được liên kết với sản phẩm.
        System.out.println(file);
        String fileName = file.getOriginalFilename();
        // Kiểm tra xem tệp có được tải lên bằng fileName.isEmpty().
        // Nếu có một tập tin:
        // Sao chép tệp vào đường dẫn đã chỉ định ( pathUpload+fileName) bằng cách sử dụng FileCopyUtils.copy().
        // Đặt tên tệp hình ảnh trong productđối tượng để lưu.
        // Bắt bất kỳ IOExceptionvà ném một RuntimeExceptionđể xử lý các lỗi liên quan đến tập tin.
        if(!fileName.isEmpty()){
            try {
                FileCopyUtils.copy(file.getBytes(),new File(pathUpload+fileName));
                //Luu file vao data
                product.setImage(fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Boolean product1 = productService.save(product);
        // Các cuộc gọi productService.save(product)để cập nhật sản phẩm với các chi tiết mới (bao gồm tên tệp hình ảnh, nếu có).
        // Nếu cập nhật thành công ( product1is true):
        // Thêm thông báo thành công vào redirectAttrs.
        // Chuyển hướng đến /productURL.
        // Nếu cập nhật không thành công:
        // Chuyển hướng đến /productURL mà không có thông báo thành công (có thể báo lỗi).
        if(product1) {
            redirectAttrs.addFlashAttribute("success", "Cập nhật sản phẩm thành công");
            return "redirect:/product";
        }
        return "redirect:/product";
    }
}
