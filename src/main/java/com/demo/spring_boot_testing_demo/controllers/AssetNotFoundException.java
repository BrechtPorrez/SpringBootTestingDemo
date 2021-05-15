package com.demo.spring_boot_testing_demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class AssetNotFoundException extends RuntimeException {
}
