package com.gxuwz.lab.controller;

import com.gxuwz.lab.api.CommonResult;
import com.gxuwz.lab.entry.User;
import com.gxuwz.lab.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;


@RestController
@RequestMapping("/userlogin")
public class LoginController {
//设置图片上传的路径
	private static final String UPLOAD_PATH = "D:/desktop/11/pic/";

	@Autowired
	private LoginService loginService ;
	
	@PostMapping("/login")
    public CommonResult login(@RequestBody User user) {
    	User user2 = new User();
    	user2 = loginService.checkLogin(user.getUserId(),user.getPwd());

    	 if (user2 != null)
	            return CommonResult.success(user2);
	        else
	            return CommonResult.validateFailed();
       
    }



	/**
	 * 上传图片接口
	 * */
	@RequestMapping("/upload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file,
								   HttpServletRequest request) {
		try {
			byte[] bytes = file.getBytes();
			String imageFileName = file.getOriginalFilename();
			String fileName = UpPhotoNameUtils.getPhotoName("img",imageFileName);
			Path path = Paths.get(UPLOAD_PATH + fileName);
			Files.write(path, bytes);
			System.out.println(fileName+"\n");
			return fileName;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}


	/**
	 * 处理图片名的工具类
	 * */
	public static class UpPhotoNameUtils {
		public static String getPhotoName(String name,String imageFileName){
			String fileName = name;
			Calendar cal=Calendar.getInstance();
			//如果年的目录不存在，创建年的目录
			int year=cal.get(Calendar.YEAR);
			fileName=fileName + "_" + year;
			//如果月份不存在，创建月份的目录
			int month=cal.get(Calendar.MONTH)+1;
			fileName=fileName+"_";
			if(month<10)
			{
				fileName=fileName+"0";
			}
			fileName=fileName+month;
			//生成文件名的日部分
			int day=cal.get(Calendar.DAY_OF_MONTH);
			fileName=fileName+"_";
			if(day<10)
			{
				fileName=fileName+"0";
			}
			fileName=fileName+day;
			//生成文件名的小时部分
			int hour=cal.get(Calendar.HOUR_OF_DAY);
			if(hour<10)
			{
				fileName=fileName+"0";
			}
			fileName=fileName+hour;
			//生成文件名的分钟部分
			int minute=cal.get(Calendar.MINUTE);
			if(minute<10)
			{
				fileName=fileName+"0";
			}
			fileName=fileName+minute;
			//生成文件名的秒部分
			int second=cal.get(Calendar.SECOND);
			if(second<10)
			{
				fileName=fileName+"0";
			}
			fileName=fileName+second;
			//生成文件名的毫秒部分
			int millisecond=cal.get(Calendar.MILLISECOND);
			if(millisecond<10)
			{
				fileName=fileName+"0";
			}
			if(millisecond<100)
			{
				fileName=fileName+"0";
			}
			fileName=fileName+millisecond;
			//生成文件的扩展名部分,只截取最后单位
			String endName = imageFileName.substring(imageFileName.lastIndexOf("."));//截取之后的值
			fileName=fileName+ endName;
			return fileName;
		}
	}

	//使用流将图片输出
	@GetMapping("/getImage/{name}")
	public void getImage(HttpServletResponse response, @PathVariable("name") String name) throws IOException {
		response.setContentType("image/jpeg;charset=utf-8");
		response.setHeader("Content-Disposition", "inline; filename=girls.png");
		System.out.println("图片方法");
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(Files.readAllBytes(Paths.get(UPLOAD_PATH).resolve(name)));
		outputStream.flush();
		outputStream.close();
	}


}
