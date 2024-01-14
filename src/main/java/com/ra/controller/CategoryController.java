package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller // Đánh dấu lớp này là bộ điều khiển Spring MVC, cho biết nó xử lý các yêu cầu web.
public class CategoryController {
    @Autowired // Đưa vào một CategoryService đối tượng, cung cấp các phương thức để tương tác với dữ liệu danh mục.
    private CategoryService categoryService;
     // @GetMapping, @PostMapping: Ánh xạ các yêu cầu HTTP GET và POST cụ thể tới các phương thức điều khiển.
    @GetMapping("/category") // Chú thích này cho biết category()phương thức xử lý các yêu cầu HTTP GET được gửi tới URL /category. Đó là một lối tắt cho @RequestMapping(method = RequestMethod.GET, value = "/category").
    public String category(Model model) { // Khai báo một phương thức có tên category()chấp nhận một Modelđối tượng làm đối số của nó. Đối Modeltượng được sử dụng để truyền dữ liệu từ bộ điều khiển đến khung nhìn. Phương thức này trả về một Stringgiá trị đại diện cho tên của khung nhìn được hiển thị.
        List<Category> list = categoryService.getAll(); // Truy xuất danh sách tất cả các danh mục bằng cách gọi getAll()phương thức trên lớp dịch vụ có tên categoryService. Dịch vụ này có thể tương tác với cơ sở dữ liệu hoặc nguồn dữ liệu khác để tìm nạp thông tin danh mục.
        model.addAttribute("list", list); // Thêm danh sách các danh mục được truy xuất vào Modelđối tượng dưới tên thuộc tính "list".
        return "category/index";
    }

    // Model: Được sử dụng để truyền dữ liệu giữa bộ điều khiển và khung nhìn.
    @GetMapping("/add-category")
    public String save(Model model){
        Category category = new Category(); // Dòng này tạo một phiên bản mới của một Categoryđối tượng, có lẽ đại diện cho một danh mục sẽ được thêm vào ứng dụng.
        model.addAttribute("category",category); // Thao tác này sẽ thêm đối tượng mới được tạo categoryvào mô hình dưới tên thuộc tính "category". Điều này làm cho đối tượng danh mục có thể truy cập được vào chế độ xem để hiển thị.
        return "category/add";
    }



    // @ModelAttribution: Liên kết dữ liệu từ biểu mẫu với đối tượng mô hình.
    @PostMapping("/add-category")
    public String create(@ModelAttribute("category") Category category){ // @ModelAttribute("category") Category category: Đối số này chấp nhận một Categoryđối tượng, đại diện cho dữ liệu danh mục được gửi trong biểu mẫu
        categoryService.save(category); // Dòng này gọi một phương thức có tên savetrên dịch vụ có tên categoryService. Dịch vụ này có thể chịu trách nhiệm tương tác với cơ sở dữ liệu hoặc cơ chế lưu trữ khác để duy trì dữ liệu danh mục.
        return "redirect:/category";
    }

    // PathVariable: Trích xuất các biến từ đường dẫn URL.
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id ){ // @PathVariable("id") Long id: Chú thích này trích xuất giá trị từ một {id}phần của URL và lưu trữ nó trong idbiến thuộc loại Long. Điều này cho phép phương thức truy cập ID danh mục cụ thể sẽ bị xóa.
        categoryService.delete(id); // Dòng này gọi deletephương thức của categoryServiceđối tượng, chuyển phần được trích xuất idlàm đối số. Điều này có thể liên quan đến việc tương tác với cơ sở dữ liệu hoặc kho lưu trữ dữ liệu để xóa danh mục có ID được chỉ định.
        return "redirect:/category";
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable Long id, Model model) { // @PathVariable Long id: Điều này trích xuất giá trị từ phần giữ chỗ "{id}" của đường dẫn URL và gán nó cho biến id. Model model: Đây là đối tượng được sử dụng để truyền dữ liệu giữa bộ điều khiển và khung nhìn.
        Category category = categoryService.findById(id); // Gọi categoryService(có thể là thành phần lớp dịch vụ) để truy xuất một Categoryđối tượng có id.
        model.addAttribute("category", category); // Thêm categoryđối tượng được truy xuất vào modeldưới tên "danh mục", làm cho nó có thể truy cập được trong mẫu dạng xem.
        return "/category/edit";
    }

    // RedirectAttribut: Cho phép truyền tin nhắn flash (tin nhắn tạm thời cho lần xem tiếp theo).
    // @ModelAttribution: Liên kết dữ liệu từ biểu mẫu với đối tượng mô hình.
    @PostMapping("/category/edit-category")
    public String update(@ModelAttribute("category") Category category, RedirectAttributes redirectAttrs) { // @ModelAttribute("category") Category category: Liên kết dữ liệu biểu mẫu đến đại diện cho một danh mục với một Categoryđối tượng. @ModelAttributeđảm bảo Spring điền vào đối tượng này các giá trị từ yêu cầu.
        // RedirectAttributes redirectAttrs: -> Cho phép chuyển các thuộc tính để sử dụng một lần trong trường hợp chuyển hướng.
        if (categoryService.save(category)) { // Các cuộc gọi categoryService.save(category)để cố gắng lưu thông tin danh mục đã cập nhật
            redirectAttrs.addFlashAttribute("success", "Cập nhật thành công"); // Thông báo này sẽ được hiển thị một lần trên trang được chuyển hướng
            return "redirect:/category";
        }
        return "redirect:/category";
    }
}
