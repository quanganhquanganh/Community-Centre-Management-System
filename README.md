Ứng dụng quản lí nhà văn hoá cho một khu dân cư với các chức năng:  
      + Cho phép người dân đăng kí sử dụng nhà văn hoá.  
      + Cho phép quản lí cơ sở vật chất cho nhà văn hoá một cách hiệu quả.  
      + Lên lịch sử dụng cho nhà văn hoá một cách dễ dàng.  
      + Lịch sử dụng nhà văn hoá được công khai cho tất cả mọi người.

#Hướng dẫn sử dụng chương trình </br>
-
Để sử dụng chương trình, chúng tôi giả định rằng bạn đã cài sẵn Mysql trên máy tính của mình.
Nếu như chưa hoặc không chắc chắn là máy tính của mình đã cài sẵn Mysql. Vui lòng tham khảo bài viết tại đường dẫn này: <br>https://o7planning.org/vi/10221/huong-dan-cai-dat-va-cau-hinh-mysql-community</br>

Sau khi chắc chắn là máy tính của bạn đã được cài đặt Mysql. Thực hiện các bước sau tuần tự để cài đặt và sử dụng chương trình.
- Sử dụng Git clone để tải chương trình về máy.
- Sử dụng file PoCProject.sql để tạo CSDL với Mysql. 
- Mở chương trình bằng ứng dụng Netbeans. 
- Nếu là người dân, chạy file UserLoginUI.java trong package views.user với Username là 'nguyenvana' (chuỗi nằm trong nháy đơn) và Password là 'root' để sử dụng các dịch vụ của mình.
- Nếu là người quản lí, chạy file LoginView.java trong package views.mng với Username là 'admin' và Password là 'admin' để sử dụng các dịch vụ của mình.

#Lưu ý
-
Chương trình hiện tại chưa hỗ trợ chức năng đăng kí thành viên cho cả người dân lẫn người quản lí.
