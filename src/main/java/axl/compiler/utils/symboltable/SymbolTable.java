package axl.compiler.utils.symboltable;

import axl.compiler.utils.symboltable.entries.ClassEntry;
import axl.compiler.utils.symboltable.entries.FieldEntry;
import axl.compiler.utils.symboltable.entries.MethodEntry;
import axl.compiler.utils.symboltable.exception.SymbolResolutionException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolTable {
    private final Map<String, ClassEntry> classes = new HashMap<>();
    private final Map<String, String> imports = new HashMap<>();
    private String currentPackage;

    public void addClass(ClassEntry classEntry) {
        if (classes.containsKey(classEntry.getClassName())) {
            throw new SymbolResolutionException(
                    "Class already exists: " + classEntry.getClassName()
            );
        }
        classes.put(classEntry.getClassName(), classEntry);
    }

    public ClassEntry getClass(String className) {
        return classes.get(className);
    }

    public boolean containsClass(String className) {
        return classes.containsKey(className);
    }

    public MethodEntry resolveMethod(String className, String methodName, String descriptor) {
        ClassEntry classEntry = classes.get(className);

        while (classEntry != null) {
            List<MethodEntry> candidates = classEntry.getMethods().get(methodName);
            if (candidates != null) {
                for (MethodEntry m : candidates) {
                    if (m.getDescriptor().equals(descriptor)) return m;
                }
            }
            classEntry = classes.get(classEntry.getSuperClass());
        }
        return null;
    }

    public String resolveType(String typeName) {
        if (typeName.contains(".")) {
            return typeName.replace('.', '/');
        }

        String fqn = imports.get(typeName);
        if (fqn != null) return fqn.replace('.', '/');

        if (currentPackage != null) {
            return (currentPackage + "." + typeName).replace('.', '/');
        }

        throw new SymbolResolutionException("Unresolved type: " + typeName);
    }

    public FieldEntry resolveField(String className, String fieldName) {
        ClassEntry classEntry = classes.get(className);
        return classEntry != null ? classEntry.getFields().get(fieldName) : null;
    }
}
