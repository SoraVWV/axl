package axl.compiler.utils.symboltable.entries;

import axl.compiler.utils.symboltable.JvmAccessFlag;
import axl.compiler.utils.symboltable.type.JvmType;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class MethodEntry {
    private final String methodName;
    private final JvmType returnType;
    private final List<JvmType> parameters;
    private final Set<JvmAccessFlag> accessFlags;

    public String getDescriptor() {
        String params = parameters.stream()
                .map(JvmType::getDescriptor)
                .collect(Collectors.joining());
        return "(" + params + ")" + returnType.getDescriptor();
    }
}
