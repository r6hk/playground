package org.example.sealed;

/**
 * @author rennen.dev
 * @date 2025/10/10 10:36
 */
public sealed interface SealedInterfaceA extends SealedInterface permits SealedImplAA, SealedImplAB {

}
