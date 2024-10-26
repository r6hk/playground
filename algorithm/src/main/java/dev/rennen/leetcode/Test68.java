package dev.rennen.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Test68 {
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        List<String> line = new ArrayList<>();
        int lineLength = 0;
        for (int k = 0; k < words.length; k++) {
            String word = words[k];
            if (maxWidth - lineLength >= word.length()) {
                line.add(word);
                lineLength += word.length();
                if (maxWidth - lineLength >= 1) lineLength++;
            } else { // 当前行结算
                StringBuilder sb = new StringBuilder();
                int spaceSize = maxWidth;
                for (String t : line) {
                    spaceSize -= t.length();
                }
                for (int i = 0; i < line.size(); i++) {
                    sb.append(line.get(i));
                    // 当前行最后一个单词后面不加空格
                    if (i != line.size() - 1) {
                        for (int j = 0; j < spaceSize / (line.size() - 1); j++) {
                            sb.append(' ');
                        }
                        if (i < spaceSize % (line.size() - 1)) sb.append(' ');
                    }
                    if (line.size() == 1) {
                        for (int j = 0; j < spaceSize; j++) {
                            sb.append(' ');
                        }
                    }
                }
                res.add(sb.toString());
                line.clear();
                lineLength = 0;
                k--;
            }
        }
        // 处理最后一行
        if (!line.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for (int i = 0; i < line.size(); i++) {
                if (i != 0) {
                    sb.append(' ');
                    count++;
                }
                sb.append(line.get(i));
                count += line.get(i).length();
            }
            for (int i = 0; i < maxWidth - count; i++) {
                sb.append(' ');
            }
            res.add(sb.toString());
        }
        return res;
    }

    public static void main(String[] args) {
        fullJustify(new String[]{"What","must","be","acknowledgment","shall","be"}, 16);
    }
}