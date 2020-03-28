package com.ddbuy.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddbuy.entity.TbContent;
import com.ddbuy.manager.util.FastDfsUtil;
import com.ddbuy.service.TbContentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class TbContentController {

    //注入远程服务
    @Reference(interfaceClass = TbContentService.class)
    private TbContentService tbContentService;

    //使用@Value注解读取springboot配置文件中的键值
    @Value(value = "${FastDfs_nginx_server_address}")
    private String fastDfs_nginx_server_address;

    //添加广告
    @RequestMapping("/addTbContent")
    @ResponseBody
    //返回值:{"result":1成功，0失败} 2表示文件过大，3表示文件类型错误
    public String addTbContent(TbContent tbContent,
            @RequestParam(value = "pfile",required = false)MultipartFile uploadFile){
        try {
            //1.实现文件上传     文件保存到FastDFS中
                //获取文件的扩展名
            String fileName=uploadFile.getOriginalFilename();
            String extName=fileName.substring(fileName.lastIndexOf(".")+1);
            //将文件保存到FastDFS
            String[] fileinfos = FastDfsUtil.upload_file(uploadFile.getBytes(), extName);

            //2.实现数据信息的保存
                //设置保存上传文件的位置
            //String saveFilePath="http://nginx代理文件服务器地址:80/组名/文件位置";
            String saveFilePath="http://"+fastDfs_nginx_server_address+"/"+fileinfos[0]+"/"+fileinfos[1];
            tbContent.setPic(saveFilePath);
                //调用服务保存数据
            tbContentService.addTbContent(tbContent);
            return "{\"result\":1}";
        }catch (Exception e){  //错误记录到日志中
            e.printStackTrace();
        }
        return "{\"result\":0}";
    }
}
