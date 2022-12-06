package MyFailTry;

public abstract class Converter {

    private Element root;

    public Element getRoot() {
        return root;
    }

    public void parsData(String input) {
        root = parser(input.trim());
    }

    protected abstract Element parser(String input);

    protected abstract boolean check(String src);

    protected abstract String print(Element element);
}

//package converter;
//
//import java.util.Map;
//import java.util.TreeMap;
//import java.util.Vector;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class Converter {
//
//    public static String getTag(String xmlContent) {
//        String tag = "";
//
//        Pattern patternTag = Pattern.compile("(?<=<)\\w+");
//        Matcher matcher = patternTag.matcher(xmlContent);
//        if (matcher.find()) {
//            tag = matcher.group().trim();
//        }
//        return tag;
//    }
//
//    public static String getAttributes(String xmlContent, String tag) {
//        String attributes = "";
//
//        Pattern patternAttributes = Pattern.compile("(?<=<" + tag + ")[^/>]*(?=/>|>)");
//        Matcher matcher = patternAttributes.matcher(xmlContent);
//        if (matcher.find()) {
//            attributes = matcher.group().trim();
//        }
//
//        return attributes;
//    }
//
//    public static String getContent(String xmlContent, String tag) {
//        String content = "";
//
//        Pattern emptyXmlContent = Pattern.compile("^<" + tag + "[^</>]*/>");
//        Matcher matcher = emptyXmlContent.matcher(xmlContent);
//        if (matcher.find()) {
//            return "null";
//        }
//
//        Pattern patternContent = Pattern.compile("^((?!</" + tag + ").)*");
//        matcher = patternContent.matcher(xmlContent.replaceFirst("<" + tag + "[^/>]*>", ""));
//        if (matcher.find()) {
//            content = matcher.group().trim();
//        } else {
//            System.out.println("Hello");
//        }
//
//        return content;
//    }
//
//    public static String nextXmlContent(String xmlContent, String tag) {
//        return xmlContent
//                .replaceFirst("^(<" + tag + "[^/>]*/>|<" + tag + "[^>]*>((?!</" + tag + ">).)*</" + tag + ">)", "").trim();
//    }
//
//    private static void convertXmlWithParents(String xmlContent, Vector<String> parents) {
//        String tag = getTag(xmlContent);
//        Vector<String> oldParents = new Vector<>(parents);
//        parents.add(tag);
//        String attributes = getAttributes(xmlContent, tag);
//        String content = getContent(xmlContent, tag);
//        System.out.println("Element:");
//        System.out.print("path = ");
//        System.out.println(String.join(", ", parents));
//
//        if (!content.matches("^<.*>$")) {
//            if (content.equals("null")) {
//                System.out.println("value = null");
//            } else {
//                System.out.println("value = \"" + content + "\"");
//            }
//        }
//            if (!attributes.matches("")) {
//                System.out.println("attributes:");
//                Pattern patternAttribute = Pattern.compile("\\w+\\s*=\\s*\"[^\"]*\"");
//                Matcher matcher = patternAttribute.matcher(attributes);
//                while (matcher.find()) {
//                    String attribute = matcher.group();
//                    String key = attribute.replaceAll("\\s*=.*", "");
//                    String value = attribute.replaceAll(".*=\\s*", "");
//                    System.out.println(key + " = " + value);
//                }
//            }
//
//        if (content.matches("^<.*>$")) {
//            System.out.println();
//            convertXmlWithParents(content, parents);
//        }
//
//        String nextXmlContent = nextXmlContent(xmlContent, tag);
//        if (!nextXmlContent.equals("")) {
//            System.out.println();
//            convertXmlWithParents(nextXmlContent, oldParents);
//        }
//    }
//
//    public static void convertXml(String xmlContent) {
//        Vector<String> parents = new Vector<>();
//        convertXmlWithParents(xmlContent, parents);
//    }
//
//    public static void convertJson(String jsonContent) {
//
//    }
//
//
//    public static void convertXmlToJson(String xmlContent) {
//        String tag = "";
//        String content = null;
//        Map<String, String> attributeStorage = new TreeMap<>();
//
//        Pattern patternTag = Pattern.compile("(?<=<)\\s*\\w+");
//        Matcher matcher = patternTag.matcher(xmlContent);
//        if (matcher.find())
//            tag = matcher.group().trim();
//
//        Pattern patternAttributes = Pattern.compile("(?<=<" + tag + ")[^>/]*(?=/>|>)");
//        matcher = patternAttributes.matcher(xmlContent);
//        if (matcher.find()) {
//            String attributes = matcher.group().trim();
//            Pattern patternAttribute = Pattern.compile("\\w+\\s*=\\s*\"[^\"]*\"");
//            matcher = patternAttribute.matcher(attributes);
//            while (matcher.find()) {
//                String attribute = matcher.group();
//                String key = attribute.replaceAll("\\s*=.*", "");
//                String value = attribute.replaceAll(".*=\\s*", "");
//                attributeStorage.put(key, value);
//            }
//        }
//
//        Pattern patternContent = Pattern.compile("(?<=>).*(?=<)");
//        matcher = patternContent.matcher(xmlContent);
//        if (matcher.find()) {
//            content = "\"" + matcher.group().trim() + "\"";
//        }
//
//        System.out.println("{");
//        System.out.println("\t\"" + tag + "\" : {");
//        attributeStorage.forEach((key, value) ->
//                System.out.println("\t\t\"@" + key + "\" : " + value + ",")
//        );
//        System.out.println("\t\t\"#" + tag + "\" : " + content);
//        System.out.println("\t}");
//        System.out.println("}");
//    }
//
//    public static void convertJsonToXml(String jsonContent) {
//        String tag = "";
//        String content = "null";
//        Map<String, String> attributeStorage = new TreeMap<>();
//
//        Pattern patternKey = Pattern.compile("(?<=\")\\w*(?=\")");
//        Matcher matcher = patternKey.matcher(jsonContent);
//        if (matcher.find()) {
//            tag = matcher.group();
//        }
//
//        Pattern patternContent = Pattern.compile("(?<=\"#" + tag + "\" : )[^}]*(?=})");
//        matcher = patternContent.matcher(jsonContent);
//        if (matcher.find()) {
//            content = matcher.group().trim().replaceAll("\"", "");
//        }
//
//        Pattern patternAttributes = Pattern.compile("\"@[^,]*(?=,)");
//        matcher = patternAttributes.matcher(jsonContent);
//        while (matcher.find()) {
//            String attribute = matcher.group();
//            String attributeKey = attribute.replaceAll("(\"@|\".*)", "");
//            String attributeValue = attribute.replaceAll("(.*:\\s*\"?|\"?$)", "");
//            attributeStorage.put(attributeKey, attributeValue);
//        }
//
//        System.out.print("<" + tag);
//        attributeStorage.forEach((key, value) -> System.out.print(" " + key + " = \"" + value + "\""));
//        if (content.equals("null")) {
//            System.out.println(" />");
//        } else {
//            System.out.println(">" + content + "</" + tag + ">");
//        }
//    }
//}
