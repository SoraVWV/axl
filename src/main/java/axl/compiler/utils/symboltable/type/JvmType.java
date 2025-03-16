package axl.compiler.utils.symboltable.type;

import lombok.Getter;

public final class JvmType {
    @Getter
    private final String descriptor;

    private JvmType(String descriptor) {
        this.descriptor = descriptor;
    }

    public static JvmType fromSourceType(String sourceType) {
        sourceType = sourceType.replaceAll("<.*>", "");
        boolean isArray = sourceType.endsWith("[]");
        int arrayDepth = 0;

        if (isArray) {
            arrayDepth = (sourceType.split("\\[]", -1).length) - 1;
            sourceType = sourceType.replaceAll("\\[]", "");
        }

        String baseDescriptor = switch (sourceType) {
            case "void" -> "V";
            case "boolean" -> "Z";
            case "byte" -> "B";
            case "char" -> "C";
            case "short" -> "S";
            case "int" -> "I";
            case "long" -> "J";
            case "float" -> "F";
            case "double" -> "D";
            default -> "L" + sourceType.replace('.', '/') + ";";
        };

        return new JvmType("[".repeat(arrayDepth) + baseDescriptor);
    }

}