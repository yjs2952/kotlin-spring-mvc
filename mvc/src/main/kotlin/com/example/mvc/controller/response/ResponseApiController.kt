package com.example.mvc.controller.response

import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/response")
class ResponseApiController {

    // 1. get 4xx
    @GetMapping
    fun getMapping(@RequestParam age: Int?): ResponseEntity<String> {

        return age?.let {
            // age is not null
            if (it < 20) {
                return ResponseEntity.status(400).body("age 가 20보다 커야 함")
            }

            ResponseEntity.ok("OK")
        } ?: kotlin.run {
            // age is null
            return ResponseEntity.badRequest().body("age 값 누락")
        }

        /*
        // 0. age == null -> 400 error
        if (age == null) {
            return ResponseEntity.badRequest().body("age 값 누락")
        }

        // 1. age < 20 -> 400 error
        if (age < 20) {
            return ResponseEntity.status(400).body("age 가 20보다 커야 함")
        }*/
//        return ResponseEntity.ok("OK")
    }

    // 2. post 200
    @PostMapping
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any> {
        return ResponseEntity.status(200).body(userRequest)
    }

    // 3. put 201
    @PutMapping
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
    }

    // 4. delete 500
    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable id: Int): ResponseEntity<Nothing> {
        return ResponseEntity.status(500).body(null)
    }

}