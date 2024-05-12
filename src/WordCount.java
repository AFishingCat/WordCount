import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 实现读入文本文件，并统计英文字符个数
 *
 * @author 黄兴鑫
 * @version 0.0
 */
public class WordCount {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        String choice = args[0];
        String file = args[1];
        if (choice.equals("-c")) {
            System.out.println("非单词特殊字符个数：");
        } else {
            System.out.println("单词个数：");
        }
        System.out.println(count(choice, file));
    }

    public static int count(String choice, String filePath) {
        String regex = null;
        if (choice.equals("-c")) {
            regex = "([^a-zA-Z])";
        } else {
            regex = "\\b([a-zA-Z]+)\\b";
        }
        File file = new File(filePath);
        BufferedReader reader = null;
        int count = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                count += countElement(line, regex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return count;
    }

    /**
     * 返回字符串中，给定模式的个数
     *
     * @param exp   传入的字符串
     * @param regex 正则表达式
     * @return 返回匹配模式的个数
     */
    private static int countElement(String exp, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(exp);
        int count = 0;
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            count++;
        }
        return count;
    }
}
