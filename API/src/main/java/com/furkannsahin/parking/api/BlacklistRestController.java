package com.furkannsahin.parking.api;

import com.furkannsahin.parking.dto.BlacklistDto;
import com.furkannsahin.parking.mapper.BlacklistMapper;
import com.furkannsahin.parking.model.Blacklist;
import com.furkannsahin.parking.service.BlacklistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Blacklist Controller")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "https://reactbootcamp66-bitirme-odevi-furk2sahin.netlify.app"})
public class BlacklistRestController {

    @Autowired
    private final BlacklistService blacklistService;

    private final BlacklistMapper blacklistMapper;

    @ApiOperation(value = "Get all blacklist items", notes = "returns all blacklist item")
    @GetMapping("/blacklist")
    public ResponseEntity<List<BlacklistDto>> getBlacklist(){
        List<Blacklist> blacklist = blacklistService.getBlacklist();
        return ResponseEntity.ok(blacklistMapper.toBlacklistDtos(blacklist));
    }

    @ApiOperation(value = "Create a blacklist item", notes = "Creates a blacklist item and returns it")
    @PostMapping("/blacklist")
    public ResponseEntity<BlacklistDto> createBlacklistItem(@RequestBody BlacklistDto blacklistDto){
        try{
            Blacklist createdBlacklist = blacklistService.createBlackListItem(blacklistMapper.toBlacklist(blacklistDto));
            return ResponseEntity.ok(blacklistMapper.toBlacklistDto(createdBlacklist));
        } catch (ConstraintViolationException ex){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Delete a blacklist item", notes = "Deletes a blacklist item by given id")
    @DeleteMapping("/blacklist/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id){
        try{
            blacklistService.deleteBlacklistItem(id);
        } catch (Exception ex) {
            return new ResponseEntity<>("Blacklist item not found by id: " + id, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Blacklist item successfully deleted by id: " + id, HttpStatus.OK);
    }
}
