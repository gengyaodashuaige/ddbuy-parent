package com.ddbuy.manager.util;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import java.io.IOException;

//FastDFS的文件上传工具类
public class FastDfsUtil {

    /**
     * 实现文件上传
     * @param file  文件的十进制数据
     * @param extName  文件的扩展名，不需要 .
     * @return  返回文件的相关信息
     */
    public static String [] upload_file(byte[]file,String extName){
        //使用Java操作FastDFS的文件上传的步骤
        //1.导入依赖
        //2.编写配置
        //3.实现上传的步骤
        TrackerClient trackerClient=null;
        TrackerServer trackerServer=null;
        StorageClient storageClient=null;
        try {
            //3.1加载配置
            ClientGlobal.init("fastdfs.properties");
            //3.2创建TrackerClient对象，并获得TrackerServer对象
            trackerClient=new TrackerClient();
            trackerServer=trackerClient.getConnection();
            //3.3获得StoregeClient存储对象
            storageClient=new StorageClient(trackerServer,null);
            //3.4进行上传
            //storageClient.upload_file("上传文件的路径","文件扩展名",null);
            String [] fileInfos=storageClient.upload_file(file,extName,null);

            return fileInfos;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            storageClient=null;
            try {
                //3.5关闭资
                trackerServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            trackerServer=null;
            trackerClient=null;
        }
        return null;

    }

    /**
     * 文件删除
     * @param group  组名
     * @param filepos  远程文件的位置
     * @return  true成功   false失败
     */
    public static boolean delete_file(String group,String filepos){
//使用Java操作FastDFS的文件上传的步骤
        //1.导入依赖
        //2.编写配置
        //3.实现上传的步骤
        TrackerClient trackerClient=null;
        TrackerServer trackerServer=null;
        StorageClient storageClient=null;
        try {
            //3.1加载配置
            ClientGlobal.init("fastdfs.properties");
            //3.2创建TrackerClient对象，并获得TrackerServer对象
            trackerClient=new TrackerClient();
            trackerServer=trackerClient.getConnection();
            //3.3获得StoregeClient存储对象
            storageClient=new StorageClient(trackerServer,null);
            //3.4进行删除
            storageClient.delete_file(group,filepos);
            //3.5关闭资
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            storageClient = null;
            try {
                trackerServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            trackerServer = null;
            trackerClient = null;
        }
        return false;
    }

    /**
     * 文件下载
     * @param groupname  组名
     * @param filepos  文件位置
     * @return  下载文件的二进制的数据
     */
    public static byte[] download_file(String groupname,String filepos){
//使用Java操作FastDFS的文件上传的步骤
        //1.导入依赖
        //2.编写配置
        //3.实现上传的步骤
        TrackerClient trackerClient=null;
        TrackerServer trackerServer=null;
        StorageClient storageClient=null;
        try {
            //3.1加载配置
            ClientGlobal.init("fastdfs.properties");
            //3.2创建TrackerClient对象，并获得TrackerServer对象
            trackerClient=new TrackerClient();
            trackerServer=trackerClient.getConnection();
            //3.3获得StoregeClient存储对象
            storageClient=new StorageClient(trackerServer,null);
            //3.4进行下载
            byte [] bs=storageClient.download_file(groupname,filepos);
            //3.5关闭资
            return bs;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            storageClient=null;
            try {
                trackerServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            trackerServer=null;
            trackerClient=null;
        }
        return null;
    }
}
