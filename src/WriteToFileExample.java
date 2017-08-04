import java.io.*;

public class WriteToFileExample {
    public BufferedReader readFile(String inputfile) {
        try {
            File file = new File(inputfile);
            // 读取文件，并且以utf-8的形式写出去
            String read;
            BufferedReader bufread = new BufferedReader(new FileReader(file));
            return bufread;

//            while ((read = bufread.readLine()) != null) {
//                return read;
//            }
//            bufread.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void writeFile(String content, String resultFile){

        try {

//            String content = "This is the content to write into file";

            File file = new File(resultFile);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content+" ");
            bw.close();

//            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}