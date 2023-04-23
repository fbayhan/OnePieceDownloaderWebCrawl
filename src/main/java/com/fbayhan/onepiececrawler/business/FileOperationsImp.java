package com.fbayhan.onepiececrawler.business;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileOperationsImp implements FileOperations {
    String FolderRoot = "C:\\OnePiece";
    //String htmlPage = "https://cizgiromanarsivi.com/seri/one-piece/bolum-656/";
    String htmlPage = "https://cizgiromanarsivi.com/seri/one-piece/bolum-";

    String line;

    @Override
    public void createFolder(int folderNumber, int dowloandChapterCount) {
        for (int i = folderNumber; i < (folderNumber + dowloandChapterCount); i++) {
            try {
                Files.createDirectories(Paths.get(FolderRoot + "/" + i));
                String currentHtmlPage = htmlPage + i + "/";
                downloadHtml(i, currentHtmlPage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    @Override
    public void downloadHtml(int episode, String episodeUrl) throws IOException {
        URL url = new URL(episodeUrl);
        List<String> imageList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            //BufferedWriter writer = new BufferedWriter(new FileWriter("page.html"));
            Boolean nextLine = false;
            String imageRow = "";
            while ((line = reader.readLine()) != null) {

                if (line.contains("<img id")) {
                    // System.out.println(line);
                    imageRow = line;
                    nextLine = true;
                } else if (nextLine) {
                    // System.out.println(line);
                    imageRow = imageRow + line;

                    nextLine = false;

                    Pattern findUrl = Pattern.compile("\\bhttps.*?\\.JPG\\b");
                    Matcher matcher = findUrl.matcher(imageRow);
                    if (matcher.find()) {
                        imageList.add(matcher.group());
                    }
                }
            }
            downloadImage(imageList, episode);


        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public void downloadImage(List<String> imageList, int episode) throws IOException {
        for (String imageUrl : imageList) {
            System.out.println(imageUrl);

            String parsedUrl = imageUrl.substring(imageUrl.lastIndexOf('/') + 1);
            System.out.println(parsedUrl);

            URL url = new URL(imageUrl);
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1 != (n = in.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();


            FileOutputStream fos = new FileOutputStream(FolderRoot + "/" + episode + "/" + parsedUrl);
            fos.write(response);
            fos.close();
        }
    }


}
