import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 实现读入文本文件，并统计英文字符个数
 * 更新正则单词的正则表达式，匹配不同格式的单词。
 * 同时使用FileReader中readLine()方法时，
 * 在每个字符串末尾拼接换行符\n实现对换行符的统计
 *
 * @author 黄兴鑫
 * @version 0.1
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

    /**
     * 根据用户选择，返回字符个数或者单词个数
     *
     * @param choice   -c 或者 -w
     * @param filePath 文件路径（文件名称）
     * @return 返回统计个数
     */
    public static int count(String choice, String filePath) {
        String regex = null;
        if (choice.equals("-c")) {
            regex = "([^a-zA-Z]\n*)";
        } else {
            regex = "\\b([a-zA-Z-']+)\\b";
        }
        File file = new File(filePath);
        BufferedReader reader = null;
        int count = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                count += countElement(line + "\n", regex);
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
        if (choice.equals("-c")) count--;
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
//            System.out.println(matcher.group(1));
            count++;
        }
        return count;
    }
}
