package com.app.my;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static org.apache.tomcat.util.descriptor.Constants.PACKAGE_NAME;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ArchitectureTest {
    @Test
    @DisplayName("Выполнены требования слоеной архитектуры")
    void testLayeredArchitecture() {
        layeredArchitecture().consideringAllDependencies()
                .layer("domain").definedBy(PACKAGE_NAME + ".domain..")
                .layer("app").definedBy(PACKAGE_NAME + ".app..")
                .layer("extern")
                .definedBy(PACKAGE_NAME + ".api..", PACKAGE_NAME + ".infrastructure..")
                .whereLayer("app").mayOnlyBeAccessedByLayers("app", "extern")
                .whereLayer("extern").mayOnlyBeAccessedByLayers("extern");

    }
}
