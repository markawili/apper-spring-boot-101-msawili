package com.msawili.estore.record;

import java.util.Map;

public record ServiceError(Map<String, String> validationErrors) {
}