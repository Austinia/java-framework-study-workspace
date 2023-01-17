package com.austinia.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
@RequestMapping // RequestMethod"HandlerMapping"에 의해서 찾아진다, 생략 가능
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    @RequestMapping(path = "/user") // RequestMapping"HandlerAdapter"에 의해서 찾아진다
    public User getUser(@RequestParam("id") Integer id) {
        return userDao.get(id);
    }

    // GET 메서드로 화면을 출력
//    @RequestMapping(path = "/upload", method = RequestMethod.GET)
    @GetMapping("/upload")
    public void upload() {

    }

    //    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    @PostMapping("/upload")
    public ModelAndView upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        // 파일을 정의한다 ( 저장되는 장소를 지정 ), 서버의 물리적인 PATH를 받아서 파일의 오리지널 이름으로 저장한다
        File path = new File(request.getSession().getServletContext().getRealPath("/") + "/WEB-INF/static/" + file.getOriginalFilename());
        // 업로드된 파일의 바이트스트림을 저장한다
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        // 버퍼를 이용해서 I/O 비용을 줄인다 ( 성능 )
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        // 버퍼를 이용해 write 한다
        bufferedOutputStream.write(file.getBytes());
        // close
        bufferedOutputStream.close();

        // 사진을 저장했고 upload.jsp에 url을 준다
        ModelAndView modelAndView = new ModelAndView("/upload");
        // url이란 이름으로 사진 URL를 준다
        modelAndView.addObject("url", "/images/" + file.getOriginalFilename());
        return modelAndView;
    }

    @ExceptionHandler(Exception.class) // 모든 오류는 이리로
    public ModelAndView error(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("e", e);
        return modelAndView;
    }
}
