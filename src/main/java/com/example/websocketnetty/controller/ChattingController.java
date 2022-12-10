package com.example.websocketnetty.controller;

import com.example.websocketnetty.NettyChattingClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ChattingController {

    private final NettyChattingClient nettyChattingClient;

    @GetMapping("/")
    public String index(){

        return "error";
    }

    @GetMapping("/{id}")
    public String chattingRoom(@PathVariable String id, HttpSession session, Model model){
        if(id.equals("guest")){
            model.addAttribute("name", "guest");
        }else if(id.equals("master")){
            model.addAttribute("name", "master");
        }else if(id.equals("loose")){
            model.addAttribute("name", "loose");
        }else {
            return "error";
        }
        return "chattingRoom";
    }
    @GetMapping("/test")
    public void test(){
        nettyChattingClient.sendToServer("하이");
    }
}
