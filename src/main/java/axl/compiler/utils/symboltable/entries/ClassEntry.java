package axl.compiler.utils.symboltable.entries;

import axl.compiler.utils.symboltable.JvmAccessFlag;
import axl.compiler.utils.symboltable.exception.SymbolResolutionException;
import lombok.Data;

import java.util.*;

@Data
public class ClassEntry {
    private final String className;
    private String superClass = "java/lang/Object";
    private final List<String> interfaces = new ArrayList<>();
    private final Map<String, FieldEntry> fields = new HashMap<>();
    private final Map<String, List<MethodEntry>> methods = new HashMap<>();
    private final Set<JvmAccessFlag> accessFlags = EnumSet.noneOf(JvmAccessFlag.class);

    public ClassEntry(String className) {
        this.className = className;
    }

    public void addField(FieldEntry field) {
        if (fields.containsKey(field.getFieldName())) {
            throw new SymbolResolutionException("Duplicate field: " + field.getFieldName());
        }
        fields.put(field.getFieldName(), field);
    }

    public void addMethod(MethodEntry method) {
        methods.computeIfAbsent(method.getMethodName(), k -> new ArrayList<>())
                .stream()
                .filter(m -> m.getDescriptor().equals(method.getDescriptor()))
                .findFirst()
                .ifPresent(m -> {
                    throw new SymbolResolutionException(
                            "Duplicate method: " + method.getMethodName() + method.getDescriptor()
                    );
                });
        methods.computeIfAbsent(method.getMethodName(), k -> new ArrayList<>()).add(method);
    }
}
