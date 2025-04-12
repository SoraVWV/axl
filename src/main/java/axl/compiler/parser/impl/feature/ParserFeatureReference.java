package axl.compiler.parser.impl.feature;

import axl.compiler.data.Node;

import java.util.function.Consumer;

@FunctionalInterface
public interface ParserFeatureReference<Target extends Node<?>> extends Consumer<Target> {
}
