package com.ggaspar.apizap.controllers;

import com.ggaspar.apizap.scans.ActiveScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scan")
public class Scan {

    @PostMapping()
    public ResponseEntity<String> scan() {
        String result = ActiveScan.scan();
        return ResponseEntity.ok(result);
    }

}
