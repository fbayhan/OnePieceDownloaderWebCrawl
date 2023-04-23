package com.fbayhan.onepiececrawler.business;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface FileOperations {
    void createFolder(int folderNumber , int dowloandChapterCount);
    void downloadHtml(int episode, String episodeUrl) throws IOException ;
    void downloadImage(List<String> imageList, int episode) throws  IOException;


}
