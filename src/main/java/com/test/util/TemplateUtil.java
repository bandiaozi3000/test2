package com.test.util;

/**
 * @ClassName: TemplateUtil.java
 * @author: f.hu
 * @date: 2019-10-08 13:31
 * @Description:模板工具类
 */
public class TemplateUtil {

    /**
     * 模板占位填充数据
     *
     * @param tpl  模板，占位用{} {}... 标识
     * @param args 参数集合 -> 仅支持基础类型和包装类型
     * @return 填充完毕的模板
     */
    public static String tpl(String tpl, Object... args) {
        if (args == null || args.length <= 0)
            return tpl;
        if (tpl == null || tpl.isEmpty())
            return "";

        char[] charArray = tpl.toCharArray();
        int offset = 0, start = tpl.indexOf("{", offset), argsIndex = 0;
        if (start == -1)
            return tpl;
        StringBuilder builder = new StringBuilder();
        StringBuilder desc = null;
        while (start > -1) {
            if (start > 0 && charArray[start - 1] == '\\') {
                builder.append(charArray, offset, start - offset - 1).append("{");
                offset = start + 1;
            } else {
                if (desc == null) {
                    desc = new StringBuilder();
                } else {
                    desc.setLength(0);
                }
                builder.append(charArray, offset, start - offset);
                offset = start + 1;
                int end = tpl.indexOf("}", offset);
                while (end > -1) {
                    if (end > offset && charArray[end - 1] == '\\') {
                        desc.append(charArray, offset, end - offset - 1).append("}");
                        offset = end + 1;
                        end = tpl.indexOf("}", offset);
                    } else {
                        desc.append(charArray, offset, end - offset);
                        offset = end + 1;
                        break;
                    }
                }
                if (end == -1) {
                    builder.append(charArray, start, charArray.length - start);
                    offset = charArray.length;
                } else {
                    String value = (argsIndex <= args.length - 1)
                            ? (args[argsIndex] == null ? "" : String.valueOf(args[argsIndex])) : String.valueOf(desc);
                    builder.append(value);
                    offset = end + 1;
                    argsIndex++;
                }
            }
            start = tpl.indexOf("{", offset);
        }
        if (offset < charArray.length) {
            builder.append(charArray, offset, charArray.length - offset);
        }
        return builder.toString();
    }
}
