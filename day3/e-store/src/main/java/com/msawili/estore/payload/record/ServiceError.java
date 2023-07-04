package com.msawili.estore.payload.record;

import java.util.Map;

public record ServiceError(Map<String, String> validationErrors) {
}