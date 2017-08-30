package de.my5t3ry.domain.projects.utils;

import org.w3c.dom.Node;

/**
 * created by: sascha.bast
 * since: 30.08.17
 */
public class ExternalDeviceNameExtractor implements IExtractDeviceNames {
    @Override
    public String extractName(final Node node) {
        return node.getAttributes().item(0).getNodeValue();
    }
}
