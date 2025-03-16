package axl.compiler.utils.symboltable.entries;

import axl.compiler.utils.symboltable.JvmAccessFlag;
import axl.compiler.utils.symboltable.type.JvmType;
import lombok.Data;

import java.util.Set;

@Data
public class FieldEntry {
    private final String fieldName;
    private final JvmType type;
    private final Set<JvmAccessFlag> accessFlags;
}
