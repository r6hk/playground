package dev.rennen.springdatamongo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <br/>
 * 2025/8/9
 *
 * @author rennen.dev
 */
@Document
public record People(int index, String name) {
}