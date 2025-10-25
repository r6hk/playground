package org.example.sealed;

/**
 * @author rennen.dev
 * @date 2025/10/10 10:35
 */
public sealed interface SealedInterface permits SealedInterfaceA, SealedImplB {
}
